DROP TABLE IF EXISTS banking_user_role;
DROP TABLE IF EXISTS banking_user;
DROP SEQUENCE IF EXISTS banking_user_id_seq;
DROP SEQUENCE IF EXISTS banking_user_role_id_seq;
DROP TABLE IF EXISTS account_transaction;
DROP SEQUENCE IF EXISTS account_transaction_id_seq;
DROP TABLE IF EXISTS account;
DROP SEQUENCE IF EXISTS account_id_seq;
DROP TABLE IF EXISTS client;
DROP SEQUENCE IF EXISTS  client_id_seq;

CREATE SEQUENCE banking_user_id_seq
START WITH 1
INCREMENT BY 50
NO MINVALUE
NO MAXVALUE
CACHE 1;


CREATE TABLE banking_user (
    banking_user_id BIGINT DEFAULT nextval('banking_user_id_seq' :: REGCLASS) NOT NULL ,
    username text NOT NULL,
    password text NOT NULL,
    enabled boolean NOT NULL DEFAULT TRUE,
    PRIMARY KEY (banking_user_id)
);


COMMENT ON TABLE banking_user IS 'Stores banking systems user details';
COMMENT ON COLUMN banking_user.banking_user_id  IS 'Primary sequential id for banking user';
COMMENT ON COLUMN banking_user.username  IS 'Username for the banking user';
COMMENT ON COLUMN banking_user.password  IS 'Encrypted password for the banking user';
COMMENT ON COLUMN banking_user.enabled  IS 'Flag determining if user is enabled';

CREATE SEQUENCE banking_user_role_id_seq
START WITH 1
INCREMENT BY 50
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE banking_user_role (
    banking_user_role_id BIGINT DEFAULT nextval('banking_user_role_id_seq' :: REGCLASS) NOT NULL ,
    banking_user_id integer NOT NULL,
    role_name text NOT NULL,
    PRIMARY KEY (banking_user_role_id),
    UNIQUE (role_name,banking_user_id),
    CONSTRAINT fk_username FOREIGN KEY (banking_user_id) REFERENCES banking_user (banking_user_id)
);

COMMENT ON TABLE banking_user_role IS 'Stores roles for the user';
COMMENT ON COLUMN banking_user_role.banking_user_role_id  IS 'Primary sequential id for banking user role';
COMMENT ON COLUMN banking_user_role.banking_user_id  IS 'FK to user table';
COMMENT ON COLUMN banking_user_role.role_name  IS 'Role name';

CREATE SEQUENCE client_id_seq
START WITH 10000
INCREMENT BY 50
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE client (
    id  BIGINT DEFAULT nextval('client_id_seq' :: REGCLASS) NOT NULL,
    client_name text NOT NULL,
    client_surname text NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE client IS 'Stores clients for the banking system';
COMMENT ON COLUMN client.id  IS 'Primary sequential id for client';
COMMENT ON COLUMN client.client_name  IS 'Stores clients name';
COMMENT ON COLUMN client.client_surname  IS 'Stores clients surname';


CREATE SEQUENCE account_id_seq
START WITH 10000
INCREMENT BY 50
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE account (
    id  BIGINT DEFAULT nextval('account_id_seq' :: REGCLASS) NOT NULL,
    description varchar(40) NOT NULL,
    balance  numeric(19,8) DEFAULT 0,
    client_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client (id)
);

COMMENT ON TABLE account IS 'Stores clients accounts for the banking system';
COMMENT ON COLUMN account.id  IS 'Primary sequential id for client';
COMMENT ON COLUMN account.description  IS 'Human friendly description for account';
COMMENT ON COLUMN account.balance  IS 'Stores account balance';
COMMENT ON COLUMN account.client_id  IS 'Stores accounts client';

CREATE SEQUENCE account_transaction_id_seq
START WITH 1
INCREMENT BY 50
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE account_transaction (
    id  BIGINT DEFAULT nextval('account_transaction_id_seq' :: REGCLASS) NOT NULL,
    reference varchar(40) NOT NULL,
    source_account_id  integer NOT NULL,
    debited_amount  numeric(19,8) DEFAULT 0,
    destination_account_id integer NOT NULL,
    credited_amount  numeric(19,8) DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT fk_source FOREIGN KEY (source_account_id) REFERENCES account (id),
    CONSTRAINT fk_destination FOREIGN KEY (destination_account_id) REFERENCES account (id)
);

COMMENT ON TABLE account_transaction IS 'Stores transactions done in the banking system';
COMMENT ON COLUMN account_transaction.id  IS 'Primary sequential id for client';
COMMENT ON COLUMN account_transaction.reference  IS 'Human friendly description for transaction';
COMMENT ON COLUMN account_transaction.source_account_id  IS 'Account where funds were taken from';
COMMENT ON COLUMN account_transaction.debited_amount  IS 'Debited amount from source account';
COMMENT ON COLUMN account_transaction.destination_account_id  IS 'Account where funds were transferred';
COMMENT ON COLUMN account_transaction.credited_amount  IS 'Credited amount to destination account';








