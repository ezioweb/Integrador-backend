create table if not exists endereco(
       id int auto_increment primary key,
       rua varchar(100),
       numero varchar(100),
       bairro varchar(100)
);

create table if not exists paciente(
    id int auto_increment primary key,
    nome varchar(100),
    sobrenome varchar(100),
    endereco varchar(100),
    rg varchar(100),
    datacadastro current_timestamp()
);

create table if not exists dentista(
    int id auto_increment primary key,
    nome varchar(100),
    sobrenome varchar(100),
    matricula varchar(100)
);