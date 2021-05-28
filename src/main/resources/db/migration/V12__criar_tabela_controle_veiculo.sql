CREATE TABLE movimentacao (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_chegada DATETIME NOT NULL,
    status VARCHAR(30) NOT NULL,
    situacao VARCHAR(30) NOT NULL,
    observacao_chegada VARCHAR(250),
    observacao_saida VARCHAR(250),
    data_saida DATETIME,
    codigo_motorista BIGINT(20) NOT NULL,
    codigo_veiculo BIGINT(20) NOT NULL,
    codigo_usuario BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_motorista) REFERENCES motorista(codigo),
    FOREIGN KEY (codigo_veiculo) REFERENCES veiculo(codigo),
    FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
