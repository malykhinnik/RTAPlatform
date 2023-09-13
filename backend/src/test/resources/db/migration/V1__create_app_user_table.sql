create table app_user
(
    id          bigserial not null unique primary key,
    created     timestamp with time zone,
    updated     timestamp with time zone,
    first_name  varchar(256),
    last_name   varchar(256)
);
