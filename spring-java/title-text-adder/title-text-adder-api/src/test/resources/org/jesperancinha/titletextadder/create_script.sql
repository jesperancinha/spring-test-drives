create user 'tta'@'localhost' identified by 'tta';

create database tta_db;

create table titles(
   title_id INT NOT NULL AUTO_INCREMENT,
   title VARCHAR(100) NOT NULL,
   title_text VARCHAR(500) NOT NULL,
   title_submission_date DATE,
   title_text_score TINYINT,
   PRIMARY KEY ( title_id )
);

GRANT ALL ON tta_db.* TO 'tta'@'localhost';
