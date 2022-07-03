drop schema drugstore;
create schema drugstore;

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

