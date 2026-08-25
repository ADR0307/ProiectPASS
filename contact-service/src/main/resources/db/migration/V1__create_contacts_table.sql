CREATE TABLE contacts (

                          id BIGSERIAL PRIMARY KEY,

                          owner_email VARCHAR(255) NOT NULL,

                          first_name VARCHAR(100) NOT NULL,

                          last_name VARCHAR(100) NOT NULL,

                          phone VARCHAR(30),

                          email VARCHAR(255),

                          company VARCHAR(150),

                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_contacts_owner
    ON contacts(owner_email);