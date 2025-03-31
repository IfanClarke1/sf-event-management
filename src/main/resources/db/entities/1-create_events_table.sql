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

    CONSTRAINT pk_EVENT_ID PRIMARY KEY (id)
);

CREATE INDEX idx_event_reference ON events(reference);

