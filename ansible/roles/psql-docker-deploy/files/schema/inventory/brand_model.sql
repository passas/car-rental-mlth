create sequence brand_model_id_sequence start with 1 increment by 1;

create table brand_model
(
    id integer not null default nextval('brand_model_id_sequence'),
    name varchar(32) not null,
    variant varchar(24) not null,
    brand_id smallint not null, -- fk
    fuel_id smallint not null, -- fk
    primary key (id),
    constraint brand_model_brand foreign key (brand_id) references brand(id) on delete restrict on update cascade,
    constraint brand_model_fuel foreign key (fuel_id) references fuel(id) on delete restrict on update cascade,
    constraint brand_model_name_variant_unique unique (name, variant),
    constraint brand_model_name_not_blank check (trim(name) <> ''),
    constraint brand_model_variant_not_blank check (trim(variant) <> '')
);