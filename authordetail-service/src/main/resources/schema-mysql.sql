USE `author-db`;

-- dropping for testing purposes
DROP TABLE IF EXISTS authors;
create table if not exists authors (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    author VARCHAR(255) NOT NULL UNIQUE,
    bestseller VARCHAR(255) NOT NULL,
    bio VARCHAR(255) NOT NULL
) engine=InnoDB;

