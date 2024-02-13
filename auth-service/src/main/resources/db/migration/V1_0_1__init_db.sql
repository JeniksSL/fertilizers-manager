begin;

create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

create index if not exists flyway_schema_history_s_idx on flyway_schema_history (success);

CREATE TABLE roles
(
    user_id BIGINT      NOT NULL,
    role    VARCHAR(30) NOT NULL
);

CREATE TABLE users
(
    user_id             BIGINT AUTO_INCREMENT NOT NULL,
    date_of_creation    TIMESTAMP,
    date_of_last_update TIMESTAMP,
    email               VARCHAR(255)          NOT NULL,
    password            VARCHAR(255),
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    ava_image           VARCHAR(255),
    is_email_confirmed  BOOLEAN,
    is_enabled          BOOLEAN,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE roles
    ADD CONSTRAINT fk_roles_on_user FOREIGN KEY (user_id) REFERENCES users (user_id);

CREATE TABLE user_codes
(
    code_id UUID    NOT NULL,
    user_id BIGINT  NOT NULL,
    is_sent BOOLEAN NOT NULL,
    CONSTRAINT pk_user_codes PRIMARY KEY (code_id)
);

ALTER TABLE user_codes
    ADD CONSTRAINT FK_USER_CODES_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);