use jwtservice;

drop table if exists T_USER;
drop table if exists T_ROLE;
drop table if exists T_USER_ROLE;


create table T_USER (
                        user_id BIGINT auto_increment primary key not null,
                        user_name varchar(32) not null,
                        name varchar(32) not null,
                        password LONGTEXT not null
)ENGINE = InnoDB;

create table T_ROLE (
                        role_id BIGINT auto_increment primary key not null,
                        role_name varchar(32) not null
)ENGINE = InnoDB;

create table T_USER_ROLE (
                             user_id BIGINT not null references T_USER(user_id),
                             role_id BIGINT not null references T_ROLE(role_id)
)ENGINE = InnoDB;

COMMIT;