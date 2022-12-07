-- USUÁRIO --

INSERT INTO TB_USUARIO (ID_USUARIO, EMAIL_USUARIO, SENHA_USUARIO, LOGIN_USUARIO, NOME_USUARIO, SOBRENOME_USUARIO
) VALUES (1, 'yhann06950@gmail.com', '123', 'teste', 'Teste', 'Teste do Teste');

INSERT INTO TB_USUARIO (ID_USUARIO, EMAIL_USUARIO, SENHA_USUARIO, LOGIN_USUARIO, NOME_USUARIO, SOBRENOME_USUARIO
) VALUES (2, 'yhann0695@hotmail.com', '123', 'yhann', 'Yhann', 'Gomes');

-- INSERT INTO TB_USUARIO (ID_USUARIO, EMAIL_USUARIO, SENHA_USUARIO, LOGIN_USUARIO, NOME_USUARIO, SOBRENOME_USUARIO
-- ) VALUES (3, 'annyeKarolyne@hotmail.com', '123', 'karol', 'Karol', 'Morais');

-- CATEGORIAS --

INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (1, 'Supermercado', 1);
INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (2, 'TV / Internet / Telefone.', 1);
INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (3, 'Transporte', 1);
INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (4, 'Lazer', 1);
INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (5, 'Saúde', 1);
INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (6, 'Bares e Restaurantes', 1);
INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (7, 'Eletrônicos', 1);
INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (8, 'Jogos', 1);
INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (9, 'Esporte', 1);
INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (10, 'Outros', 1);

INSERT INTO TB_CATEGORIA (ID_CATEGORIA, DS_CATEGORIA, ID_USUARIO) VALUES (11, 'Eletrônicos',2);

-- LANÇAMENTOS --

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (1, 'Parcela do xbox', 1, 565.0, '2021-11-12', 'DESPESA', 'DEBITO', 'Despesa gerada pela compra do xbox', 7, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (2, 'Parcela monitor', 1, 164.0, '2021-11-11', 'DESPESA', 'CREDITO', 'Despesa gerada pela compra do monitor', 7, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (3, 'Dentista', 1, 180.0, '2021-11-10', 'DESPESA', 'DEBITO', 'Consulta dentista', 5, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (4, 'Mensalidade Karatê', 1, 120.0, '2021-11-12', 'DESPESA', 'PIX', 'Aulas de karatê', 9, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (5, 'Mensalidade jiu-jitsu', 1, 110.0, '2021-11-09', 'DESPESA', 'PIX', 'Aulas de jiu-jitsu', 9, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (6, 'Uber', 1, 32.5, '2021-11-12', 'DESPESA', 'DEBITO', 'Uber', 3, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (7, 'Jantar', 1, 223.8, '2020-10-22', 'DESPESA', 'CREDITO', 'Jantar final de semana', 6, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (8, 'Rodízio sushi', 1, 250.7, '2021-11-12', 'DESPESA', 'PIX', 'Rodízio de sushi', 6, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (9, 'Compras mercado', 1, 988.3, '2021-10-11', 'DESPESA', 'CHEQUE', 'Compras da casa', 1, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (10, 'Mouse', 1, 344.3, '2021-10-11', 'DESPESA', 'CREDITO', 'Compras de mouse novo', 7, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (11, 'Salário', 1, 20000.0, '2021-11-12', 'RECEITA', 'PAGAMENTO', 'Salário do mês', 10, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (12, 'Viagem', 1, 4300.0, '2021-11-12', 'DESPESA', 'CREDITO', 'Viagem para bahia', 4, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (13, 'Freelancer', 1, 3500.0, '2021-11-12', 'RECEITA', 'PIX', 'Serviço para uma padaria', 10, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (14, 'Premium Account no tibia', 1, 60.0, '2021-11-12', 'DESPESA', 'PIX', 'Compra do P.A no tibia', 8, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (15, 'Jogo Forza 5', 1, 514.0, '2021-10-12', 'DESPESA', 'DEBITO', 'Compra do jogo forza', 8, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (16, 'Controle xbox novo', 1, 380.0, '2021-10-12', 'DESPESA', 'CREDITO', 'Controle xbox', 7, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (17, 'Roupas natal', 1, 380.0, '2021-12-01', 'DESPESA', 'CREDITO', 'Roupas', 10, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (18, 'Roupas ano novo', 1, 380.0, '2021-12-01', 'DESPESA', 'CREDITO', 'Roupas', 10, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (19, 'Batterfiled 2042', 1, 380.0, '2021-12-04', 'DESPESA', 'CREDITO', 'Jogos xbox', 8, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (20, 'Jantar', 1, 456.0, '2021-12-05', 'DESPESA', 'DEBITO', 'Jantar final de semana', 6, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (21, 'Gasolina', 1, 280.0, '2021-12-05', 'DESPESA', 'DEBITO', 'Abastecer o tanque de gasolina', 10, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (22, 'Mc Donalds', 1, 54.0, '2021-12-06', 'DESPESA', 'DEBITO', 'Lanche final de semana', 6, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (23, 'Livro código limpo', 1, 49.0, '2021-12-04', 'DESPESA', 'CREDITO', 'Jogos xbox', 4, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (24, 'Uber', 1, 60.0, '2021-12-07', 'DESPESA', 'CREDITO', 'Passeio', 3, null);

INSERT INTO TB_LANCAMENTO (
ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (25, 'Salário', 1, 20000.0, '2021-12-01', 'RECEITA', 'PAGAMENTO', 'Salário do mês', 10, null);

INSERT INTO TB_LANCAMENTO (
 ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (26, 'Xbox S', 2, 2000.0, '2021-12-01', 'DESPESA', 'DEBITO', 'xbox na promoção', 11, null);

INSERT INTO TB_LANCAMENTO (
 ID_LANCAMENTO, DS_LANCAMENTO, ID_USUARIO, VL_LANCAMENTO,
  DT_CADASTRO_LANCAMENTO, TIPO_LANCAMENTO,
  DS_PAGAMENTO_LANCAMENTO, OBSERVACAO_LANCAMENTO, ID_CATEGORIA, NO_ARQUIVO_LANCAMENTO
) VALUES (27, 'Xbox X', 2, 5800.0, '2021-11-01', 'DESPESA', 'CREDITO', 'Xbox da minha vida', 11, null);