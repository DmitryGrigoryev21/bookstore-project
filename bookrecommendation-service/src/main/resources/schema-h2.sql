DROP TABLE tags IF EXISTS;

CREATE TABLE tags (
    id INT NOT NULL AUTO_INCREMENT,
    bookUUID VARCHAR(255) NOT NULL,
    tag VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

