create sequence role_id_sequence start with 1 increment by 1;

create table role
(
    id smallint not null default nextval('role_id_sequence'),
    name varchar(16) not null,
    primary key (id),
    constraint role_name_unique unique (name),
    constraint role_name_allowed check ( lower(trim(name)) in ('admin', 'manager', 'system', 'user') )
);