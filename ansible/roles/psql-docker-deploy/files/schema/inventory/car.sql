create sequence seq_car_id start with 1 increment by 1;
create table car
(
    id bigint not null default nextval('seq_car_id'),
    brand_model_id integer not null,
    license_plate varchar(32) not null,
    tank numeric(6,2) not null default 0.0, -- lts
    distance numeric(10,3) not null default 0.0, -- kms
    primary key (id),
    constraint fk_car_brand_model_brand_model_id foreign key (brand_model_id) references brand_model(id) on delete restrict on update cascade,
    constraint uq_car_license_plate unique (license_plate),
    constraint chk_license_plate check (trim(license_plate) <> '')
);