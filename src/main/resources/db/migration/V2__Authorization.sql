create table if not exists users
(
    id serial8 not null
        constraint users_pk
            primary key,
    username varchar(60) unique not null,
    password varchar(60) not null,
    enabled bool not null
);

insert into users (username, password, enabled)
values
('admin', '$2a$10$ZLhnHxdpHETcxmtEStgpI./Ri1mksgJ9iDP36FmfMdYyVg9g0b2dq', true),
('user', '$2a$10$ZLhnHxdpHETcxmtEStgpI./Ri1mksgJ9iDP36FmfMdYyVg9g0b2dq', true);

create table if not exists user_roles
(
    username varchar(60) not null,
    role varchar(60) not null
);

alter table user_roles
    add constraint user_roles_users_username_fk
        foreign key (username) references users (username)
            on update cascade on delete cascade;

insert into user_roles (username, role)
values
('admin','ROLE_ADMIN'),
('user', 'ROLE_USER');

