-- Create a table to store authorization decisions
CREATE TABLE IF NOT EXISTS authorization_decisions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    station_id VARCHAR(36) NOT NULL,
    driver_token VARCHAR(80) NOT NULL,
    status VARCHAR(20) NOT NULL,
    decision_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert test data for debugging
INSERT INTO authorization_decisions (station_id, driver_token, status) VALUES
('123e4567-e89b-12d3-a456-426614174000', 'validDriverToken123', 'allowed'),
('123e4567-e89b-12d3-a456-426614174001', 'invalidDriverToken456', 'not_allowed'),
('123e4567-e89b-12d3-a456-426614174002', 'unknownDriverToken789', 'unknown');