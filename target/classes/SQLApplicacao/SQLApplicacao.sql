drop schema ssapplication;
create schema ssapplication;

select * from ssapplication.usuario;
select * from ssapplication.pessoa;

insert into ssapplication.usuario values(1, true, "ironman@stark.com.br", "123890", "Administrador", "Tony");
insert into ssapplication.usuario values(2, true, "batman@waynenterprise.com.br", "147258", "Administrador", "Bruce");
insert into ssapplication.usuario values(3, false, "spiderman@osborg.com.br", "258147", "Atendente", "Peter");
insert into ssapplication.usuario values(4, true, "scarllet@mk.com.br", "777444", "Atendente", "Scarllet");
insert into ssapplication.usuario values(5, true, "stranger@marvel.com.br", "999666", "Gerente", "Stranger");

select cpf, senha from usuario as u join pessoa as p on u.pessoa_codigo = p.codigo where p.cpf = "254.397.854-73" and u.senha = "1q2w3e4r";
    
insert into usuario values(1, true, "1q2w3e4r", "ADMINISTRADOR", 1);
insert into usuario values(2, true, "12345678", "GERENTE", 3);
insert into usuario values(3, true, "09876543", "BALCONISTA", 2);
    
create table Usuario (
  codigo bigint not null,
  ativo bit not null,
  senha varchar(32) not null,
  tipoUsuario varchar(255) not null,
  pessoa_codigo bigint not null,
  primary key (codigo)
) engine=InnoDB;

