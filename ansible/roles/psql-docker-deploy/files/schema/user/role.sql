create sequence seq_role_id start with 1 increment by 1;
create table role
(
    id smallint not null default nextval('seq_role_id'),
    name varchar(16) not null,
    primary key (id),
    constraint uq_role_name unique (name),
    constraint chk_name check (lower(trim(name)) in ('admin', 'manager', 'system', 'user'))
);