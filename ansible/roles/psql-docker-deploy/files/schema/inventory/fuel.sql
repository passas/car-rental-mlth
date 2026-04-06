create sequence seq_fuel_id start with 1 increment by 1;
create table fuel
(
    id smallint not null default nextval('seq_fuel_id'),
    name varchar(32) not null ,
    primary key (id),
    constraint uq_fuel_name unique (name),
    constraint chk_name check (lower(trim(name)) in ('diesel', 'elétrico', 'gasolina', 'gnc', 'gpl', 'híbrido (diesel)', 'híbrido (gasolina)', 'híbrido plug-in', 'hidrogénio'))
);