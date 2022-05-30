 CREATE TABLE pessoa (
        id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
        person_name VARCHAR(50) NOT NULL,
        street_address VARCHAR(30),
        number_address VARCHAR(30),
        complement VARCHAR(30),
        province VARCHAR(30),
        zip_code VARCHAR(30),
        city VARCHAR(30),
        state VARCHAR(30),
        active_status BOOLEAN NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

