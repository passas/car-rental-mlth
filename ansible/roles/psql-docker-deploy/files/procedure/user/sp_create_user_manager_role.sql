create extension if not exists pgcrypto;

create procedure sp_create_user_manager_role
(
    p_username varchar,
    p_password varchar,
    p_is_active boolean default true
)
language plpgsql
as $$
declare
    v_user_id bigint;
	v_role_id smallint;
begin
    perform pg_advisory_xact_lock(hashtext(lower(p_username)));

    select id
    into v_role_id
    from role
    where lower(name) = 'manager';

    if v_role_id is null then
            raise exception 'Role "manager" does not exist';
    end if;

    insert into _user (username, password, is_active)
    values (p_username, crypt(p_password, gen_salt('bf', 10)), p_is_active)
    returning id into v_user_id;

    insert into user_role (user_id, role_id)
    values (v_user_id, v_role_id);

exception
    when unique_violation then
        raise exception 'Username "%" already exists', username;
end;
$$;

/*
call sp_create_user_manager_role('manager', 'manager');

select password = crypt('manager', password)
from _user
where username = 'manager';

select * from _user
    inner join user_role
        on _user.id = user_role.user_id
    inner join role
        on user_role.role_id = role.id
;
 */