CREATE TABLE bookings (
    id BIGINT AUTO_INCREMENT NOT NULL,
    event_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    reference VARCHAR(64) NOT NULL UNIQUE,
    fee DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    type VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    admission_number INT NOT NULL DEFAULT 100,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT pk_BOOKING_ID PRIMARY KEY (id),
    CONSTRAINT FK_EVENT_ON_BOOKING FOREIGN KEY (event_id) REFERENCES events(id)
);

CREATE INDEX idx_booking_reference ON bookings(reference);
CREATE INDEX idx_booking_event_id ON bookings(event_id);
CREATE INDEX idx_user_booking_id ON bookings(user_id);