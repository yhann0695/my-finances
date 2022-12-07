package br.com.investimento.financas.utils.constants;

import java.time.LocalDate;

import br.com.investimento.financas.utils.DateUtils;

public class Constants {

    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    public static final String DESCRICAO_INVALIDA = "Descrição inválida";
    public static final String EMAIL_CADASTRADO = "O Email informado não está disponível";
    public static final String USERNAME_CADASTRADO = "O Login informado não está disponível";
    public static final String TIPO_INVALIDO = "Tipo inválido";
    public static final String USUARIO_INVALIDO = "Usuário inválido";
    public static final String VALOR_INVALIDO = "Valor inválido";
    public static final String OBSERVACAO_INVALIDA = "A observação não pode ter mais de 50 caracteres";
    public static final String DADOS_INVALIDO = "Credenciais incorreta";
    public static final String LANCAMENTO_NAO_ENCONTRADO = "Lançamento não encontrado na base de dados";
    public static final String MSG_NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado!";
    public static final String CATEGORIA_EXISTENTE = "Já existe uma categoria com essa descrição!";
    public static final String CATEGORIA_INEXISTENTE = "Categoria não encontrada.";
    public static final String EXISTE_RELACAO = "Existe relação dessa categoria com lançamentos.";
    public static final String EMAIL_REMETENTE = "financas.investimento06@gmail.com";
    public static final String EMAIL_ASSUNTO_E_DATA = "Relação das suas atividades de " + DateUtils.formatarData(LocalDate.now().minusMonths(1));
}
