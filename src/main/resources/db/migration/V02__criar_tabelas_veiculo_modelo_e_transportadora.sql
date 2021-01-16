CREATE TABLE modelo (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE transportadora (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    tipo_pessoa VARCHAR(15) NOT NULL,
    cpf_cnpj VARCHAR(30),
    cep VARCHAR(15),    
    endereco VARCHAR(200),
    bairro VARCHAR(150),
    complemento VARCHAR(20),
    numero VARCHAR(15),
   	telefone VARCHAR(20),
    email VARCHAR(50) NOT NULL,  
    codigo_cidade BIGINT(20),
    FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE veiculo (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,  
    placa VARCHAR(80) NOT NULL,    
    codigo_modelo BIGINT(20) NOT NULL,
    codigo_transportadora BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_modelo) REFERENCES modelo(codigo),
    FOREIGN KEY (codigo_transportadora) REFERENCES transportadora(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO modelo VALUES (0, 'Carreta');
INSERT INTO modelo VALUES (0, 'Truck');