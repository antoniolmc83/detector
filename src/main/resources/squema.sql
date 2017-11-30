DROP TABLE IF EXISTS detectortaskconfig;
CREATE TABLE IF NOT EXISTS detectortaskconfig
(detectorname VARCHAR(255) PRIMARY KEY,
 timerange INT,
 polldate VARCHAR(255),
 fixeddelay INT,
 enabled VARCHAR(1));

