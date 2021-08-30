CREATE TABLE starters(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	quatro_letras VARCHAR(4) NOT NULL,
	telefone VARCHAR(20) NOT NULL,
	linguagem VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(10),
	complemento VARCHAR(50),
	bairro VARCHAR(50),
	cep VARCHAR(8),
	cidade VARCHAR(50),
	estado VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;