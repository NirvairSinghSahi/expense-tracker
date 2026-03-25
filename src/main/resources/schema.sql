CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(100) UNIQUE,
    password VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS expense (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       title VARCHAR(100),
    amount DOUBLE,
    category VARCHAR(50),
    date DATE,
    user_id BIGINT
    );