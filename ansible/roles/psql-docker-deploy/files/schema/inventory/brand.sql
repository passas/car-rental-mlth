create sequence brand_id_sequence start with 1 increment by 1;

create table brand
(
    id smallint not null default nextval('brand_id_sequence'),
    name varchar(48) not null,
    primary key (id),
    constraint brand_name_unique unique (name),
    constraint brand_name_not_blank check (trim(name) <> '')
);