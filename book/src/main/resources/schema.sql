create table if not exists genres
(
    genre_id   uuid primary key,
    genre_name varchar(100)
);

create table if not exists authors
(
    author_id      uuid primary key,
    author_surname varchar(100),
    author_name    varchar(100)
);

create table if not exists books
(
    book_id   uuid primary key,
    book_name varchar(100),
    author_id uuid references authors (author_id),
    genre_id  uuid references genres (genre_id)
);

create table if not exists comments
(
    comment_id uuid primary key,
    book_id    uuid references books (book_id) on delete cascade,
    content    varchar(10000)
);

create table if not exists roles
(
    role        varchar(30) primary key,
    description varchar
);

create table if not exists users
(
    login    varchar(10) primary key,
    password varchar not null,
    role     varchar references roles (role)
);
