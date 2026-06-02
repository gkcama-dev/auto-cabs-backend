CREATE TABLE IF NOT EXISTS booking (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT NOT NULL,
        car_id BIGINT NOT NULL,
        start_date DATE NOT NULL,
        end_date DATE NOT NULL,
        total_days INT NOT NULL,
        total_price DOUBLE NOT NULL,
        payment_status VARCHAR(50) DEFAULT 'PENDING',
    booking_status VARCHAR(50) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_booking_car FOREIGN KEY (car_id) REFERENCES car(id)
    );