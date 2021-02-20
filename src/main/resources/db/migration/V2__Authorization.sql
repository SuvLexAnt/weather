create table if not exists users
(
    id serial8 not null
        constraint users_pk
            primary key,
    username varchar(50) unique not null,
    password varchar(50) not null,
    enabled bool not null
);

insert into users (username, password, enabled)
values
('admin', '1234567Abc', true),
('user', 'password', true);

create table if not exists user_roles
(
    username varchar(50) not null,
    role varchar(50) not null
);

alter table user_roles
    add constraint user_roles_users_username_fk
        foreign key (username) references users (username)
            on update cascade on delete cascade;

insert into user_roles (username, role)
values
('admin','ROLE_ADMIN'),
('user', 'ROLE_USER');

