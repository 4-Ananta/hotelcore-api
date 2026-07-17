CREATE DATABASE hotelcore_db;
USE hotelcore_db;

CREATE TABLE room_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(50) NOT NULL UNIQUE,

    capacity INT NOT NULL,

    description VARCHAR(255),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE room (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    room_number VARCHAR(10) NOT NULL UNIQUE,

    room_type_id BIGINT NOT NULL,

    floor INT NOT NULL,

    status ENUM(
        'AVAILABLE',
        'OCCUPIED',
        'OO'
    ) NOT NULL DEFAULT 'AVAILABLE',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_room_room_type
        FOREIGN KEY (room_type_id)
        REFERENCES room_type(id)

);

CREATE TABLE rate (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    room_type_id BIGINT NOT NULL,

    rate_name VARCHAR(100) NOT NULL,

    price DECIMAL(12,2) NOT NULL,

    start_date DATE NOT NULL,

    end_date DATE NOT NULL,

    description VARCHAR(255),

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_rate_room_type
        FOREIGN KEY(room_type_id)
        REFERENCES room_type(id)

);

CREATE TABLE guest (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    identity_number VARCHAR(50) NOT NULL,

    identity_type ENUM(
        'KTP',
        'PASSPORT',
        'SIM'
    ) NOT NULL,

    full_name VARCHAR(100) NOT NULL,

    gender ENUM(
        'MALE',
        'FEMALE'
    ) NOT NULL,

    phone VARCHAR(20),

    email VARCHAR(100),

    address TEXT,

    nationality VARCHAR(100),

    is_blacklisted BOOLEAN DEFAULT FALSE

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP

);

CREATE TABLE user (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(50) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL,

    full_name VARCHAR(100) NOT NULL,

    role ENUM(
        'ADMIN',
        'RECEPTIONIST',
        'HOUSEKEEPING'
    ) NOT NULL,

    is_active BOOLEAN DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP

);

CREATE TABLE reservation (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    guest_id BIGINT NOT NULL,

    room_id BIGINT NOT NULL,

    rate_id BIGINT NOT NULL,

    created_by BIGINT NOT NULL,

    check_in_date DATE NOT NULL,

    check_out_date DATE NOT NULL,

    number_of_nights INT NOT NULL,

    price_per_night DECIMAL(12,2) NOT NULL,

    total_amount DECIMAL(12,2) NOT NULL,

    status ENUM(
        'BOOKED',
        'CHECKED_IN',
        'CHECKED_OUT',
        'CANCELLED',
        'NO_SHOW'
    ) NOT NULL DEFAULT 'BOOKED',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_reservation_guest
        FOREIGN KEY (guest_id)
        REFERENCES guest(id),

    CONSTRAINT fk_reservation_room
        FOREIGN KEY (room_id)
        REFERENCES room(id),

    CONSTRAINT fk_reservation_rate
        FOREIGN KEY (rate_id)
        REFERENCES rate(id),

    CONSTRAINT fk_reservation_user
        FOREIGN KEY (created_by)
        REFERENCES user(id)

);



CREATE TABLE payment (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    reservation_id BIGINT NOT NULL,

    amount DECIMAL(12,2) NOT NULL,

    payment_method ENUM(
        'CASH',
        'DEBIT_CARD',
        'CREDIT_CARD',
        'BANK_TRANSFER',
        'QRIS'
    ) NOT NULL,

    payment_status ENUM(
        'PENDING',
        'PAID',
        'FAILED',
        'REFUNDED'
    ) NOT NULL DEFAULT 'PENDING',

    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    reference_number VARCHAR(100),

    created_by BIGINT NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_reservation
        FOREIGN KEY (reservation_id)
        REFERENCES reservation(id),

    CONSTRAINT fk_payment_user
        FOREIGN KEY (created_by)
        REFERENCES user(id)

);