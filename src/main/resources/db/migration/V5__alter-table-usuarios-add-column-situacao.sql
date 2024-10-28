alter table usuarios add situacao_usuario varchar(100);
update usuarios set situacao_usuario = "ATIVO";
ALTER TABLE usuarios MODIFY situacao_usuario varchar(100) NOT NULL;