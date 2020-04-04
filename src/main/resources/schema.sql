drop table if exists users;

create table users(
    username varchar(50) not null primary key,
    password varchar (500) not null,
    enabled boolean not null
);
drop table if exists authorities;

create table authorities(
    username varchar(50) not null ,
    authority varchar (500) not null,
    constraint fk_auth foreign key (username) REFERENCES users(username)
);

create unique index id_user on authorities(username,authority);