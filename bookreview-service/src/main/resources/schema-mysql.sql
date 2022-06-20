USE `bookreview-db`;

-- dropping for testing purposes
DROP TABLE IF EXISTS reviews;
create table if not exists reviews (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bookUUID VARCHAR(255) NOT NULL,
    review VARCHAR(255) NOT NULL,
    rating INTEGER(5) NOT NULL
) engine=InnoDB;
