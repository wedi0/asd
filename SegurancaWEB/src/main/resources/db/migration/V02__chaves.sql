CREATE TABLE simetric_private_key (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    value VARCHAR(50) NOT NULL,
    user_id BIGINT(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
