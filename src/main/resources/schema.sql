    drop table if exists translations;

    create table translations (
       id int not null,
       associated_id int,
       msg_key varchar(255) not null,
       language varchar(2) not null,
       content varchar(255) not null,
       created_at timestamp not null,
       updated_at timestamp not null,
       primary key (id)
    ) engine=InnoDB;