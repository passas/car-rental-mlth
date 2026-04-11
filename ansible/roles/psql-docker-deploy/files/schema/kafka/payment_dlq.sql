create table payment_dlq
(
    payment_id bigint,
    primary key (payment_id),
    constraint fk_payment_dlq_payment_payment_id foreign key (payment_id) references payment(id)
);