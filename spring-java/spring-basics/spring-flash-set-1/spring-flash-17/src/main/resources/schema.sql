drop table if exists authorities;
drop table if exists users;
drop table if exists flash_users;
create table flash_users
(
    id       int auto_increment primary key,
    username varchar(255),
    password varchar(255),
    role     varchar(255)
);
create table users
(
    username varchar(50)  not null primary key,
    password varchar(255) not null,
    enabled  boolean      not null
);
create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username),
    unique index authorities_idx_1 (username, authority)
);