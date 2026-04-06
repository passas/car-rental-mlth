create sequence seq_rental_id start with 1 increment by 1;
create table rental
(
    id bigint not null default nextval('seq_rental_id'),
    reservation_id bigint not null,
    start_date timestamptz not null,
    end_date timestamp with time zone,
    amount numeric(8,3),
    primary key (id),
    constraint fk_rental_reservation_reservation_id foreign key (reservation_id) references reservation(id) on delete restrict on update cascade,
    constraint uq_rental_reservation_id unique (reservation_id),
    constraint chk_end_date check (end_date is null or start_date < end_date)
);
