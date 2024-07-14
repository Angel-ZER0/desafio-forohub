create table usuarios (
    id bigint auto_increment not null,
    correo VARCHAR(255) not null unique,
    usuario VARCHAR(255) not null unique,
    contrasena VARCHAR(255) not null unique,
    primary key (id)
);
