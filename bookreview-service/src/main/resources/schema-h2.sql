DROP TABLE reviews IF EXISTS;

CREATE TABLE reviews (
    id INT NOT NULL AUTO_INCREMENT,
    bookUUID VARCHAR(255) NOT NULL,
    review VARCHAR(255) NOT NULL,
    rating INTEGER(5) NOT NULL,
    PRIMARY KEY (id)
);

