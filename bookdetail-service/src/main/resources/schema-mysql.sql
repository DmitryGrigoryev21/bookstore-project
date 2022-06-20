USE `bookstore-db`;

-- dropping for testing purposes
DROP TABLE IF EXISTS books;
create table if not exists books (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bookUUID VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    summary VARCHAR(2000) NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    price DOUBLE(20,2) NOT NULL
) engine=InnoDB;

-- create table if not exists tags (
--     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--     bookUUID VARCHAR(255) NOT NULL,
--     tag VARCHAR(255) NOT NULL,
--     FOREIGN KEY (bookUUID) REFERENCES books(bookUUID)
-- )engine=InnoDB;

-- create table if not exists reviews (
--     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--     bookUUID VARCHAR(255) NOT NULL,
--     review VARCHAR(255) NOT NULL,
--     rating INTEGER(5) NOT NULL,
--     FOREIGN KEY (bookUUID) REFERENCES books(bookUUID)
-- ) engine=InnoDB;
