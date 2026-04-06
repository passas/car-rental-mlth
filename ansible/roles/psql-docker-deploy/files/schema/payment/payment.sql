create sequence seq_payment_id start with 1 increment by 1;
create table payment
(
    id bigint not null default nextval('seq_payment_id'),
    rental_id bigint not null,
    amount numeric(8,3),
    created_at timestamptz not null,
    payed_at timestamp with time zone,
    primary key (id),
    constraint fk_payment_rental_rental_id foreign key (rental_id) references rental(id) on delete restrict on update cascade,
    constraint uq_payment_rental_id unique (rental_id),
    constraint chk_payed_at check (payed_at is null or created_at <= payed_at)
);