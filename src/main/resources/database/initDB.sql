CREATE TABLE IF NOT EXISTS mytable
(
    id          INTEGER PRIMARY KEY     ,
    predefined_id INTEGER       ,
    input_number VARCHAR(7)     ,
    random1     INTEGER        ,
    random2     INTEGER        ,
    random3     INTEGER        ,
    random4     INTEGER        ,
    random5     INTEGER
);

CREATE SEQUENCE IF NOT EXISTS id_generator START WITH 1 INCREMENT BY 1;



