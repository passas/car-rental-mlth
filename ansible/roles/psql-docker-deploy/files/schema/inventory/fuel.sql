create sequence fuel_id_sequence start with 1 increment by 1;

create table fuel
(
    id smallint not null default nextval('fuel_id_sequence'),
    name varchar(32) not null ,
    primary key (id),
    constraint fuel_engine_name_unique unique (name),
    constraint fuel_engine_name_allowed check (lower(trim(name)) in ('diesel', 'elétrico', 'gasolina', 'gnc', 'gpl', 'híbrido (diesel)', 'híbrido (gasolina)', 'híbrido plug-in', 'hidrogénio'))
);
