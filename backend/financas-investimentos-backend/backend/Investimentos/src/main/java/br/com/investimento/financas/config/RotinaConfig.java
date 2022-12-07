package br.com.investimento.financas.config;

import br.com.investimento.financas.model.Lancamento;
import br.com.investimento.financas.model.Usuario;
import br.com.investimento.financas.repository.UsuarioRepository;
import br.com.investimento.financas.service.EmailService;
import br.com.investimento.financas.utils.LoggerUtils;
import br.com.investimento.financas.utils.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RotinaConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 1 * * *", zone = "America/Sao_Paulo")
    //@Scheduled(fixedDelay = 1000 * 60 * 30)
    @Transactional
    public void executarEnvioEmails() {
        if(LoggerUtils.isDebugEnabled()) LoggerUtils.logDebug("Avisando lancamentos do mês anterior");

        List<Usuario> destinatarios = usuarioRepository.findAll();
        enviarEmailLancamentosMesAnterior(destinatarios);

        LoggerUtils.logInfo("Aviso enviado para " + destinatarios.size() + " usuários");
    }

    public void enviarEmailLancamentosMesAnterior(List<Usuario> destinatarios) {
        String template = "mail/lancamentos-do-mes";

        for (Usuario usuario : destinatarios) {
            Map<String, Object> lancamentosUsuario = buscarLancamentosUsuarioMesAnterior(usuario);
            emailService.enviarEmail(Constants.EMAIL_REMETENTE, usuario.getEmail(), Constants.EMAIL_ASSUNTO_E_DATA, template, lancamentosUsuario);
        }
    }

    private Map<String, Object> buscarLancamentosUsuarioMesAnterior(Usuario usuario) {
        Map<String, Object> lancamentosUsuario = new HashMap<String, Object>();
        lancamentosUsuario.put("lancamentos", usuario.getLancamentos().stream()
                .sorted(Comparator.comparing(Lancamento::getDataCadastro))
                .filter(this::isMesAnterior)
                .collect(Collectors.toList()));
        return lancamentosUsuario;
    }

    private boolean isMesAnterior(Lancamento lancamento) {
        return lancamento.getDataCadastro().getMonth().equals(mesAnterior());
    }

    private Month mesAnterior() {
        return LocalDate.now().minusMonths(1).getMonth();
    }
}
