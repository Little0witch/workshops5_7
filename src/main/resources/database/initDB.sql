CREATE TABLE IF NOT EXISTS mytable
(
    id          INTEGER PRIMARY KEY     ,
    input_number VARCHAR(7) NOT NULL     ,
    random1     INTEGER NOT NULL        ,
    random2     INTEGER NOT NULL        ,
    random3     INTEGER NOT NULL        ,
    random4     INTEGER NOT NULL        ,
    random5     INTEGER NOT NULL
);

--DROP SEQUENCE IF EXISTS id_generator;
CREATE SEQUENCE IF NOT EXISTS id_generator START WITH 1 INCREMENT BY 1;



