create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(250) not null,
    tipo_usuario varchar(100) not null,

    primary key(id)
);

create table produtos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    valor double not null,

    primary key(id)
);

create table pedidos(

    id bigint not null auto_increment,
    data datetime not null,
    id_usuario bigint not null,
    foreign key (id_usuario) references usuarios(id),

    primary key(id)
);

create table itens(

    id bigint not null auto_increment,
    quantidade int not null,
    id_pedido bigint not null,
    foreign key (id_pedido) references pedidos(id),
    id_produto bigint not null,
    foreign key (id_produto) references produtos(id),

    primary key(id)
);
