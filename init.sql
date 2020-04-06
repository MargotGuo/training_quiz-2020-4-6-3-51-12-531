CREATE DATABASE IF NOT EXISTS parking_lot_system DEFAULT CHARSET utf8mb4;
USE parking_lot_system;

DROP TABLE IF EXISTS parking_lot_A;
CREATE TABLE parking_lot_A(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('EMPTY','IN_USE'),
    car_id CHAR(6)
) ENGINE = InnoDB DEFAULT CHARSET utf8mb4;

DROP TABLE IF EXISTS parking_lot_B;
CREATE TABLE parking_lot_B(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('EMPTY','IN_USE'),
    car_id CHAR(6)
) ENGINE = InnoDB DEFAULT CHARSET utf8mb4;
