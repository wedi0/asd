
CREATE TABLE user (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user ( name, email, password) values ( 'Raul Dias', 'raul@ifsp.edu.br', 'raul123');
INSERT INTO user ( name, email, password) values ( 'Wesley WO', 'wesleywo@ifsp.edu.br', 'wesley123');