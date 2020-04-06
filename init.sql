CREATE DATABASE IF NOT EXISTS parking_lot_system DEFAULT CHARSET utf8mb4;
USE parking_lot_system;

DROP TABLE IF EXISTS parking_lot_information;
CREATE TABLE parking_lot_information(
    id CHAR(1) PRIMARY KEY,
    capacity INT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET utf8mb4;

INSERT INTO parking_lot_information VALUES ('A', 8);
INSERT INTO parking_lot_information VALUES ('B', 10);

SELECT * FROM parking_lot_information;
