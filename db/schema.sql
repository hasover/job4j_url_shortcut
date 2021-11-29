create table roles (
    id serial primary key,
    authority varchar(200)
);

insert into roles values ('ROLE_ADMIN'), ('ROLE_USER');