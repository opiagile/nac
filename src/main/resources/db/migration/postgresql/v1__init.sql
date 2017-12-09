    create table empresa (
        id int8 not null,
        cnpj_cpf varchar(255) not null,
        data_atualizacao timestamp not null,
        data_criacao timestamp not null,
        razao_social varchar(255) not null,
        primary key (id)
    );

    create table funcionalidade (
        id int8 not null,
        data_atualizacao timestamp not null,
        data_criacao timestamp not null,
        descricao varchar(255) not null,
        observacao varchar(255) not null,
        primary key (id)
    );

    create table plano (
        id int8 not null,
        data_atualizacao timestamp not null,
        data_criacao timestamp not null,
        descricao varchar(255) not null,
        funcionalidade bytea not null,
        periodo_contrato varchar(255) not null,
        preco numeric(19, 2) not null,
        empresa_id int8,
        primary key (id)
    );

    create table usuario (
        id int8 not null,
        data_atualizacao timestamp not null,
        data_criacao timestamp not null,
        email varchar(255) not null,
        nome varchar(255) not null,
        perfil varchar(255) not null,
        senha varchar(255) not null,
        empresa_id int8,
        primary key (id)
    );

    alter table plano
        add constraint FKd9fg91qq5435m4gkdqb05vubm
        foreign key (empresa_id)
        references empresa;

    alter table usuario
        add constraint FK87ckfs30l64gnivnfk7ywp8l6
        foreign key (empresa_id)
        references empresa;