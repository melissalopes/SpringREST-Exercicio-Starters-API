CREATE TABLE submissao_desafios(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	endereco_repositorio VARCHAR(100) NOT NULL,
	codigo_starter BIGINT(20) NOT NULL,
	codigo_desafio BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_starter) REFERENCES starters(codigo),
	FOREIGN KEY (codigo_desafio) REFERENCES desafios(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;