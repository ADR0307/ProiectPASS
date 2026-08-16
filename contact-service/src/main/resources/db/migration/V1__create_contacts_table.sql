CREATE TABLE contacts (
                          id BIGSERIAL PRIMARY KEY,

                          name VARCHAR(150) NOT NULL,

                          address VARCHAR(500) NOT NULL,

                          picture VARCHAR(1000),

                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_contacts_name
    ON contacts(name);