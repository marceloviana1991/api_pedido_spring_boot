create table verificadores(

    id bigint not null auto_increment,
    uuid binary(16) not null,
    data_expiracao timestamp not null,
    id_usuario bigint not null,
    foreign key (id_usuario) references usuarios(id),

    primary key(id)
);