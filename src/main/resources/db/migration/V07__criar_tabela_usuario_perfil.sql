CREATE TABLE usuarios_perfis (
	usuario_id BIGINT(20) NOT NULL,
	perfis_id BIGINT(20) NOT NULL,
	FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
	FOREIGN KEY (perfis_id) REFERENCES perfis(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuarios_perfis (usuario_id, perfis_id) VALUES(1,1);
INSERT INTO usuarios_perfis (usuario_id, perfis_id) VALUES(2,2);