package br.com.investimento.financas.service.relatorio;

import br.com.investimento.financas.dto.relatorio.DadosRelatorioLancamentosMensaisDto;
import br.com.investimento.financas.exception.NotFoundException;
import br.com.investimento.financas.model.Lancamento;
import br.com.investimento.financas.repository.LancamentoRepository;
import br.com.investimento.financas.utils.constants.Constants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class RelatorioLancamento {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public byte[] gerarRelatorioLancamentosMensais(Long usuarioId) throws JRException {
        List<Lancamento> lancamentos = lancamentoRepository.dadosLancamentosMensal(usuarioId, LocalDate.now());
        List<DadosRelatorioLancamentosMensaisDto> dados = DadosRelatorioLancamentosMensaisDto.converter(lancamentos);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ID_USUARIO", usuarioId);
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

        if(dados.isEmpty())
            throw new NotFoundException(Constants.MSG_NENHUM_REGISTRO_ENCONTRADO);

        InputStream inputStream = this.getClass()
                .getResourceAsStream("/relatorios/lancamentos-valor-total-mensal-por-categoria.jasper");

        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
                new JRBeanCollectionDataSource(dados));

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
