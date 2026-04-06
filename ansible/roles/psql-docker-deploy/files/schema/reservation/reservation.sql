create extension if not exists btree_gist;
create sequence seq_reservation_id start with 1 increment by 1;
create table reservation
(
    id bigint not null default nextval('seq_reservation_id'),
    user_id bigint not null,
    car_id bigint not null,
    from_date timestamp with time zone not null,
    to_date timestamptz not null,
    period tstzrange generated always as (tstzrange(from_date, to_date, '[)')) stored,
    primary key (id),
    constraint fk_reservation_user_user_id foreign key (user_id) references _user(id) on delete restrict on update cascade,
    constraint fk_reservation_car_car_id foreign key (car_id) references car(id) on delete restrict on update cascade,
    constraint chk_from_date_to_date check (from_date < to_date),
    constraint ex_period_overlap exclude using gist (car_id with =, period with &&)
);