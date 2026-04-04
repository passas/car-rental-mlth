create sequence _user_id_sequence start with 1 increment by 1;

create table _user
(
    id bigint not null default nextval('_user_id_sequence'),
    username varchar(32) not null,
    password varchar(255) not null,
    is_active bool not null default false,
    primary key (id),
    constraint _user_username_unique unique (username),
    constraint _user_username_not_blank check (trim(username) <> ''),
    constraint _user_password_not_blank check (trim(password) <> '')
);