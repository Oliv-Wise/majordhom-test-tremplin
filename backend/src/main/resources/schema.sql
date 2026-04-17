CREATE TABLE IF NOT EXISTS contact_requests (
                                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                civility VARCHAR(10) NOT NULL,
                                                last_name VARCHAR(100) NOT NULL,
                                                first_name VARCHAR(100) NOT NULL,
                                                email VARCHAR(255) NOT NULL,
                                                phone VARCHAR(30) NOT NULL,
                                                request_type VARCHAR(50) NOT NULL,
                                                message CLOB,
                                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS availabilities (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              contact_request_id BIGINT NOT NULL,
                                              day_label VARCHAR(30) NOT NULL,
                                              hour_value VARCHAR(2) NOT NULL,
                                              minute_value VARCHAR(2) NOT NULL,
                                              CONSTRAINT fk_availability_contact
                                                  FOREIGN KEY (contact_request_id) REFERENCES contact_requests(id)
                                                      ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_availabilities_contact_request_id
    ON availabilities(contact_request_id);