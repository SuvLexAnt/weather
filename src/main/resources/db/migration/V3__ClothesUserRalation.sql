alter table clothes
    add user_id int8;

alter table clothes
    add constraint clothes_users_id_fk
        foreign key (user_id) references users
            on update cascade on delete cascade;