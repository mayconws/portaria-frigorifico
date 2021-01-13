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