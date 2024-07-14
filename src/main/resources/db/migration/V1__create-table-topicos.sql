create table topicos (
    id bigint not null auto_increment,
    autor varchar(255) not null,
    curso varchar(255) not null,
    estado tinyint check (estado between 0 and 2) not null,
    fecha datetime(6) not null,
    mensaje varchar(255) not null unique,
    titulo varchar(255) not null unique,
    primary key (id)
);
