SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS users;

create table users (
    id int unsigned primary key auto_increment,
    username varchar(100) not null unique,
    password varchar(50) NOT NULL,
    points int(11) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO `users`
VALUES
(1,'admin','{noop}admin123',1),
(2,'bart','{noop}admin123',1),
(3,'user','{noop}admin123',1);


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

SET FOREIGN_KEY_CHECKS = 1;