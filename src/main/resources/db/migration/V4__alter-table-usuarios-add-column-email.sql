alter table usuarios add email varchar(100);
update usuarios set email = "marcelo@email.com";
ALTER TABLE usuarios MODIFY email varchar(100) NOT NULL;