CREATE TABLE events (
    id BIGINT AUTO_INCREMENT NOT NULL,
    reference VARCHAR(64) NOT NULL UNIQUE,
    amount DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    status VARCHAR(20) NOT NULL,
    theme VARCHAR(250) NOT NULL,
    venue VARCHAR(250) NOT NULL,
    total_capacity INT,
    available_capacity INT,
    start_time DATETIME,
    end_time DATETIME,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_EVENT_ID PRIMARY KEY (id)
);

CREATE INDEX idx_event_reference ON events(reference);

INSERT INTO events (id, reference, amount, status, theme, venue, total_capacity, available_capacity, start_time, end_time)
VALUES
(1, '3fa85f64-5717-4562-b3fc-2c963f66afa6', 30.00, 'OPEN', 'Tech Conference', 'Convention Center A', 100, 40, '2025-06-10 09:00:00', '2025-06-10 17:00:00'),
(2, '1c6e8fbf-9c0a-4d87-9a2c-bf68cc1e1f4b', 0.00, 'EXPIRED', 'Nature Walk', 'Hilltop Trail', 30, 5, '2025-04-15 07:00:00', '2025-04-15 09:00:00'),
(3, 'c0a8015a-000f-11ee-be56-0242ac120002', 75.00, 'FULLY_BOOKED', 'AI Summit', 'Tech Pavilion', 200, 0, '2025-07-01 10:00:00', '2025-07-01 18:00:00'),
(4, '123e4567-e89b-12d3-a456-426614174000', 20.00, 'OPEN', 'Art Showcase', 'Gallery District', 80, 35, '2025-07-15 11:00:00', '2025-07-15 17:00:00'),
(5, '550e8400-e29b-41d4-a716-446655440000', 55.00, 'FULLY_BOOKED', 'Music Festival', 'Open Air Stage', 500, 0, '2025-08-01 15:00:00', '2025-08-01 23:00:00'),
(6, 'f47ac10b-58cc-4372-a567-0e02b2c3d479', 15.00, 'EXPIRED', 'Book Club', 'Library Room 3', 25, 3, '2025-05-10 17:00:00', '2025-05-10 18:30:00');




