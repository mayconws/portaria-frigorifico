CREATE TABLE motorista (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(250) NOT NULL,
    rg VARCHAR(20),
    cpf VARCHAR(20),
    telefone VARCHAR(15) NOT NULL,
    orgao_expedidor VARCHAR(10),
    codigo_transportadora BIGINT(20),
    FOREIGN KEY (codigo_transportadora) REFERENCES transportadora(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;