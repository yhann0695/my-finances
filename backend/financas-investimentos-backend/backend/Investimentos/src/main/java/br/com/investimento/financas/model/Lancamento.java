package br.com.investimento.financas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.investimento.financas.dto.LancamentoDto;
import br.com.investimento.financas.utils.enums.PagamentoEnum;
import br.com.investimento.financas.utils.enums.TipoLancamento;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@Table(name = "TB_LANCAMENTO")
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LANCAMENTO")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "DS_LANCAMENTO")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "VL_LANCAMENTO")
    private BigDecimal valor;

    @Column(name = "DS_PAGAMENTO_LANCAMENTO")
    @Enumerated(EnumType.STRING)
    private PagamentoEnum formaPagamento;

    @Column(name = "DT_CADASTRO_LANCAMENTO")
    private LocalDate dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORIA", nullable = false)
    private Categoria categoria;

    @Column(name = "TIPO_LANCAMENTO")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @Column(name = "OBSERVACAO_LANCAMENTO")
    private String observacao;

    @Column(name = "NO_ARQUIVO_LANCAMENTO")
	private String nomeArquivo;

    public Lancamento(LancamentoDto dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.usuario = new Usuario(dto.getUsuario());
        this.categoria = new Categoria(dto.getCategoria());
        this.valor = dto.getValor();
        this.formaPagamento = dto.getFormaPagamento();
        this.dataCadastro = dto.getDataCadastro();
        this.observacao = dto.getObservacao();
        this.tipo = dto.getTipo();
        this.nomeArquivo = dto.getNomeArquivo();
    }
}
