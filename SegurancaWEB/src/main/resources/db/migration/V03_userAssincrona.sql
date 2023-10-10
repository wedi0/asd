CREATE TABLE user (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL,
	publicKey VARCHAR(150) NOT NULL
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (id, name, email, password, publicKey) values (1, 'Raulzinho da massa', 'raulzinho@ifsp.edu.br', 'raul123', 'chaveraul123');
INSERT INTO user (id, name, email, password, publicKey) values (2, 'Wesley WO', 'wesleywo@ifsp.edu.br', 'wesley123', 'chaveWesley123');
INSERT INTO user (id, name, email, password, publicKey) values (3, 'Sarinha', 'sarinha@ifsp.edu.br', 'Sara123', 'chaveSara123');