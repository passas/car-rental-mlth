create sequence car_id_sequence start with 1 increment by 1;

create table car
(
    id bigint not null default nextval('car_id_sequence'),
	brand_model_id integer not null, -- fk
    license_plate VARCHAR(24) not null,
	tank numeric(6,2) not null default 0.0, -- lts
	distance numeric(10,3) not null default 0.0, -- kms
    primary key (id),
	constraint car_brand_model foreign key (brand_model_id) references brand_model(id) on delete restrict on update cascade,
    constraint car_license_plate_unique unique (license_plate),
    constraint car_license_plate_not_blank check (trim(license_plate) <> '')
);