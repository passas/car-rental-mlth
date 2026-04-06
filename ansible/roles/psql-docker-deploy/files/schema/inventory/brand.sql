create sequence seq_brand_id start with 1 increment by 1;
create table brand
(
    id smallint not null default nextval('seq_brand_id'),
    name varchar(64) not null,
    primary key (id),
    constraint uq_brand_name unique (name),
    constraint chk_name check (trim(name) <> '')
);