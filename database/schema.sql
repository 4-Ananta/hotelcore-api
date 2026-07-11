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