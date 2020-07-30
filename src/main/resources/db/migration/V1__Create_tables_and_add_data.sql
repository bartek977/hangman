SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS users;

create table users (
    id int unsigned primary key auto_increment,
    username varchar(100) not null unique,
    password varchar(60) NOT NULL,
    points int(11) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO `users`
VALUES
(1,'admin','$2a$10$HBrgMr6TJbfNPU7LfFoUq.THIsqv21lHOIlgXU9FtPSTi5Cu5AZB.',10),
(2,'bart','$2a$10$HBrgMr6TJbfNPU7LfFoUq.THIsqv21lHOIlgXU9FtPSTi5Cu5AZB.',1),
(3,'testt','$2a$10$HBrgMr6TJbfNPU7LfFoUq.THIsqv21lHOIlgXU9FtPSTi5Cu5AZB.',10),
(4,'top-player','$2a$10$HBrgMr6TJbfNPU7LfFoUq.THIsqv21lHOIlgXU9FtPSTi5Cu5AZB.',2555),
(5,'hangmannn','$2a$10$HBrgMr6TJbfNPU7LfFoUq.THIsqv21lHOIlgXU9FtPSTi5Cu5AZB.',10),
(7,'user','$2a$10$HBrgMr6TJbfNPU7LfFoUq.THIsqv21lHOIlgXU9FtPSTi5Cu5AZB.',5);


DROP TABLE IF EXISTS passwords;

create table passwords (
    id int unsigned primary key auto_increment,
    password varchar(250) NOT NULL,
    accepted bit,
    PRIMARY KEY (id)
);

insert into passwords (password,accepted) values ('Hello World',1);
insert into passwords (password,accepted) values ('Nothing ventured nothing gained',1);
insert into passwords (password,accepted) values ('No pain no gain',1);
insert into passwords (password,accepted) values ('Do not push your lucky',1);

DROP TABLE IF EXISTS games;

create table games (
    user_id int unsigned NOT NULL,
    password_id int unsigned NOT NULL,

    PRIMARY KEY (user_id,password_id),

    CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_PASSWORD_ID FOREIGN KEY (password_id)
    REFERENCES passwords (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO games
VALUES
(1,1),
(1,4);



DROP TABLE IF EXISTS role;

CREATE TABLE roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'),('ROLE_ADMIN');


DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,

  PRIMARY KEY (user_id,role_id),

  CONSTRAINT FK_USER_05 FOREIGN KEY (user_id)
  REFERENCES users (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_ROLE FOREIGN KEY (role_id)
  REFERENCES roles (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
);

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users_roles (user_id,role_id)
VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2);