create table user_role
(
    user_id bigint not null,
    role_id smallint not null,
    primary key (user_id, role_id),
    constraint user_role_user foreign key (user_id) references _user(id) on delete restrict on update cascade,
    constraint user_role_role foreign key (role_id) references role(id) on delete restrict on update cascade
);