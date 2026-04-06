create sequence seq_brand_model_id start with 1 increment by 1;
create table brand_model
(
    id integer not null default nextval('seq_brand_model_id'),
    brand_id smallint not null,
    fuel_id smallint not null,
    name varchar(32) not null,
    variant varchar(32) not null,
    primary key (id),
    constraint fk_brand_model_brand_brand_id foreign key (brand_id) references brand(id) on delete restrict on update cascade,
    constraint fk_brand_model_fuel_fuel_id foreign key (fuel_id) references fuel(id) on delete restrict on update cascade,
    constraint uq_brand_model_name_variant unique (name, variant),
    constraint chk_name check (trim(name) <> ''),
    constraint chk_variant check (trim(variant) <> '')
);