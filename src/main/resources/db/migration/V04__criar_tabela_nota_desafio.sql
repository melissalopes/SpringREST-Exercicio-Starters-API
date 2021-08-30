CREATE TABLE nota_desafio(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nota_qualidade_codigo VARCHAR(4) NOT NULL,
	nota_qtd_entregada VARCHAR(4) NOT NULL,
	codigo_submissao BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_submissao) REFERENCES submissao_desafios(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;