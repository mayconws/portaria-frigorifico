CREATE TABLE visitante (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(250) NOT NULL,
    rg VARCHAR(20),
    cpf VARCHAR(20),
    telefone VARCHAR(15),
    empresa VARCHAR(200) NOT NULL,
    foto VARCHAR(100),
   	content_type VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;