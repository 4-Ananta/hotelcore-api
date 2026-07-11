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