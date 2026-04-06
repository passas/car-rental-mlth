create sequence seq_user_id start with 1 increment by 1;
create table _user
(
    id bigint not null default nextval('seq_user_id'),
    username varchar(32) not null,
    password varchar(255) not null,
    is_active bool not null default false,
    primary key (id),
    constraint uq_user_username unique (username),
    constraint chk_username check (trim(username) <> ''),
    constraint chk_password check (trim(password) <> '')
);