USE `bookrecommendation-db`;

-- dropping for testing purposes
DROP TABLE IF EXISTS tags;
create table if not exists tags (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bookUUID VARCHAR(255) NOT NULL,
    tag VARCHAR(255) NOT NULL
)engine=InnoDB;

