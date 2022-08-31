create or replace table if not exists endereco(
       id int auto_increment primary key,
       rua varchar(100) not null,
       numero varchar(100) not null,
       bairro varchar(100) not null
);

create or replace table if not exists paciente(
    id int auto_increment primary key,
    nome varchar(100) not null,
    sobrenome varchar(100) not null,
    id_endereco int not null,
    rg varchar(100) not null,
    datacadastro DATE,
    foreign key (id_endereco) references endereco(id)
);

create or replace table if not exists dentista(
    id int auto_increment primary key,
    nome varchar(100) not null,
    sobrenome varchar(100) not null,
    matricula varchar(100) not null
);

create or replace table if not exists consulta(
    id int auto_increment primary key,
    id_dentista int,
    id_paciente int,
    foreign key (id_dentista) references dentista(id),
    foreign key (id_paciente) references paciente(id),
    datahoraconsulta DATE

);