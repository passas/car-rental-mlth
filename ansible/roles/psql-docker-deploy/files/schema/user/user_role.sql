create table user_role
(
    user_id bigint not null,
    role_id smallint not null,
    primary key (user_id, role_id),
    constraint fk_user_role_user_user_id foreign key (user_id) references _user(id) on delete restrict on update cascade,
    constraint fk_user_role_role_role_id foreign key (role_id) references role(id) on delete restrict on update cascade
);