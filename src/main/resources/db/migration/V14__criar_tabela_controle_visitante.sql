CREATE TABLE controle (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_entrada DATETIME NOT NULL,
    status VARCHAR(30) NOT NULL,
    observacao VARCHAR(250),
    data_saida DATETIME,
    codigo_visitante BIGINT(20) NOT NULL,
    codigo_setor BIGINT(20) NOT NULL,
    codigo_usuario BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_visitante) REFERENCES visitante(codigo),
    FOREIGN KEY (codigo_setor) REFERENCES setor(codigo),
    FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
