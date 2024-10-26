delete from produtos;
alter table produtos add (id_usuario bigint not null, foreign key (id_usuario) references usuarios(id));
alter table produtos add data_de_cadastro datetime not null;