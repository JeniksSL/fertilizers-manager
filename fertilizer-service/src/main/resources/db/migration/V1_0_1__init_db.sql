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

CREATE TABLE substances
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(50)           NOT NULL,
    color       INT                   NOT NULL,
    description VARCHAR(255),
    image_url   VARCHAR(255),
    CONSTRAINT pk_substances PRIMARY KEY (id)
);

ALTER TABLE substances
    ADD CONSTRAINT uc_substances_name UNIQUE (name);

CREATE TABLE compositions
(
    product_id   BIGINT  NOT NULL,
    content      DECIMAL(30, 15) NOT NULL,
    substance_id BIGINT  NOT NULL,
    CONSTRAINT pk_compositions PRIMARY KEY (product_id, substance_id)
);

CREATE TABLE products
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(25)           NOT NULL,
    full_name VARCHAR(50)           NOT NULL,
    image_url VARCHAR(255),
    user_id   BIGINT                NOT NULL,
    is_common BOOLEAN               NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE compositions
    ADD CONSTRAINT fk_compositions_on_product FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE compositions
    ADD CONSTRAINT fk_compositions_on_substance FOREIGN KEY (substance_id) REFERENCES substances (id);

CREATE TABLE case_nutrients
(
    case_id      BIGINT  NOT NULL,
    rate         DECIMAL(30, 15) NOT NULL,
    substance_id BIGINT  NOT NULL,
    CONSTRAINT pk_case_nutrients PRIMARY KEY (case_id, substance_id)
);

CREATE TABLE case_products
(
    case_id    BIGINT NOT NULL,
    price      DECIMAL(30, 15),
    rate       DECIMAL(30, 15),
    product_id BIGINT NOT NULL,
    CONSTRAINT pk_case_products PRIMARY KEY (case_id, product_id)
);

CREATE TABLE product_cases
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    crop_name  VARCHAR(25)                             NOT NULL,
    field_name VARCHAR(50)                             NOT NULL,
    area       DECIMAL(30, 15),
    user_id    BIGINT                                  NOT NULL,
    CONSTRAINT pk_product_cases PRIMARY KEY (id)
);

ALTER TABLE case_nutrients
    ADD CONSTRAINT fk_case_nutrients_on_product_case FOREIGN KEY (case_id) REFERENCES product_cases (id);

ALTER TABLE case_nutrients
    ADD CONSTRAINT fk_case_nutrients_on_substance FOREIGN KEY (substance_id) REFERENCES substances (id);

ALTER TABLE case_products
    ADD CONSTRAINT fk_case_products_on_product FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE case_products
    ADD CONSTRAINT fk_case_products_on_product_case FOREIGN KEY (case_id) REFERENCES product_cases (id);

CREATE TABLE product_prices
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    user_id    BIGINT                NOT NULL,
    product_id BIGINT                NOT NULL,
    "value"    DECIMAL(30, 15)               NOT NULL,
    type       VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_product_prices PRIMARY KEY (id)
);

ALTER TABLE product_prices
    ADD CONSTRAINT user_id_product_id_type_unique UNIQUE (user_id, product_id, type);

ALTER TABLE product_prices
    ADD CONSTRAINT FK_PRODUCT_PRICES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);



--insert into substances (name, color, description) VALUES ('K2O',8731495, 'Potassium oxide');
--insert into substances (name, color, description) VALUES ('N',16772864, 'Nitrogen');
--insert into substances (name, color, description) VALUES ('P2O5',8945787, 'Phosphorus oxide');
--insert into substances (name, color, description) VALUES ('SO3',9313821, 'Sulphur oxide');

--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES('MAP 12-52', 'Monoammonium Phosphate', 0, true);
--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES('DAP 18-47', 'Diammonium Phosphate', 0, true);
--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES('CSP 45', 'Concentrated Superphosphate', 0, true);
--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES('Urea 46', 'Urea', 0, true);
--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES('AS 21', 'Ammonium sulfate', 0, true);
--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES('AN 34','Ammonium Nitrate', 0, true);
--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES('MOP 60', 'Potassium chloride', 0, true);
--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES('SOP 50', 'Potassium sulfate', 0, true);
--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES( 'NPK 7-20-30', 'Complex 7-20-30', 0, true);
--INSERT INTO products (name, full_name, user_id, is_common)
--VALUES('NPK 16-16-16', 'Complex 16-16-16', 0, true);


commit;