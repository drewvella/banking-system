DROP TABLE IF EXISTS logging_data;
DROP SEQUENCE IF EXISTS logging_data_id_seq;

CREATE SEQUENCE logging_data_id_seq
START WITH 1
INCREMENT BY 50
NO MINVALUE
NO MAXVALUE
CACHE 1;


CREATE TABLE logging_data (
    log_id BIGINT DEFAULT nextval('logging_data_id_seq' :: REGCLASS) NOT NULL ,
    user_id integer NOT NULL,
    log_date_time timestamp without time zone NOT NULL,
    log_type text NOT NULL,
    PRIMARY KEY (log_id)
);


COMMENT ON TABLE logging_data IS 'Stores logged actions for systems user';
COMMENT ON COLUMN logging_data.log_id  IS 'Primary sequential id for log data';
COMMENT ON COLUMN logging_data.user_id  IS 'Id referring to the user';
COMMENT ON COLUMN logging_data.log_date_time  IS 'When data was actioned';
COMMENT ON COLUMN logging_data.log_type  IS 'Type of action done';








