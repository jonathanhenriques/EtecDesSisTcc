-- CREATE DATABASE db_sprintquiz_h2;
-- USE db_sprintquiz_h2;


-- criando usuario
INSERT INTO tb_usuario(foto, nome, senha, tipo, usuario) VALUES ('foto', 'jonathan','$2a$10$aXACsqNAtCRsercQFWJjqOW0T3WNbvJtI.PSnR.DTFGY87qmkQliq', 'admin', 'jonathan@email.com');

-- criando categoria de prova
INSERT INTO tb_categoria_prova(titulo, descricao) VALUES('Vestibulares Públicos', 'Provas de Vestibulares Públicos');
INSERT INTO tb_categoria_prova(titulo, descricao) VALUES('Vestibulares Particulares', 'Provas de Vestibulares Particulares');

-- criando prova
INSERT INTO tb_prova(nome, descricao, instituicao, duracao, categoria_id, usuario_id) VALUES('Fuvest','Vestibular da fuvest 2019','Usp', 6, 1, 1);
INSERT INTO tb_prova(nome, descricao, instituicao, duracao, categoria_id, usuario_id) VALUES('UFRJ Vestibular','Vestibular da UFRJ 2021','UFRJ', 6, 1, 1);

-- criando categoria de questao
INSERT INTO tb_categoria_questao(titulo, descricao) VALUES('Fácil', 'Questões de nível fácil');
INSERT INTO tb_categoria_questao(titulo, descricao) VALUES('Médio', 'Questões de nível médio');
INSERT INTO tb_categoria_questao(titulo, descricao) VALUES('Difícel', 'Questões de nível difícil');

-- criando questao
INSERT INTO tb_questao(texto, imagem, instituicao,categoria_id, criador_id) VALUES('Qual a cor da neve?','https://i.imgur.com/r98IEMu.png', 'Etec', 1, 1);
INSERT INTO tb_questao(texto, imagem, instituicao,categoria_id, criador_id) VALUES('Qual a cor da Laranja?','https://i.imgur.com/r98IEMu.png', 'Usp', 1, 1);
INSERT INTO tb_questao(texto, imagem, instituicao,categoria_id, criador_id) VALUES('Qual a cor do Limão?','https://i.imgur.com/r98IEMu.png', 'UFRJ', 1, 1);

-- criando associação entre prova e questao
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (1, 1);
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (1, 2);
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (1, 3);

INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (2, 1);
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (2, 2);
INSERT INTO tb_questao_prova(prova_id, questao_id) VALUES (2, 3);

-- criando alternativas
INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Verde', '', 1);
INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Branco', '', 1);
INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Laranja', '', 1);

INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Preto', '', 2);
INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Rosa', '', 2);
INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Laranja', '', 2);

INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Verde', '', 3);
INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Cinza', '', 3);
INSERT INTO tb_alternativa(texto, foto, questao_id) VALUES('Roxo', '', 3);



-- atualizando respostas das questoes
UPDATE tb_questao SET resposta_alternativa_id = 2 WHERE (id = 1);
UPDATE tb_questao SET resposta_alternativa_id = 3 WHERE (id = 2);
UPDATE tb_questao SET resposta_alternativa_id = 1 WHERE (id = 3);

