CREATE DATABASE IF NOT EXISTS parking_lot_system DEFAULT CHARSET utf8mb4;
USE parking_lot_system;

DROP TABLE IF EXISTS parking_lot;
CREATE TABLE parking_lot(
    id INT PRIMARY KEY AUTO_INCREMENT,
    parking_lot_id CHAR(1),
    position_id INT,
    status ENUM('EMPTY','IN_USE') DEFAULT 'EMPTY',
    car_number VARCHAR(6) DEFAULT '------'
) ENGINE = InnoDB DEFAULT CHARSET utf8mb4;