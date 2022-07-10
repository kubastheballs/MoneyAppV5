create table if not exists genres
(
    id int primary key auto_increment,
    name varchar not null,
    description varchar,
    hash int
);