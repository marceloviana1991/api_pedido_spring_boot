create table produtos_estoque(

    id bigint not null auto_increment,
    quantidade int not null,
    id_produto bigint not null,
    foreign key (id_produto) references produtos(id),

    primary key(id)
);