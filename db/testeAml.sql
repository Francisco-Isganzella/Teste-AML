CREATE SCHEMA testeAml;
use testeAml;

CREATE TABLE Transacao(
	idTransacao INT PRIMARY KEY AUTO_INCREMENT,
    orgao VARCHAR(200),
    portador VARCHAR(100),
    favorecido VARCHAR(100),
    tipoTransacao VARCHAR(50),
    valor DOUBLE  
);