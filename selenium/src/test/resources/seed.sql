-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT true
    );

-- Create user_roles table
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL REFERENCES users(id),
    role VARCHAR(255) NOT NULL
    );

-- Insert test user
INSERT INTO users (username, password, email, enabled)
VALUES ('testuser', '$2a$10$rKz9niiE6Q74fNa8MlX9peEzpwF1WXyFviYaMCt6MMyMmQ/rh4tHe', 'testuser@test.com', true);

-- Assign role
INSERT INTO user_roles (user_id, role)
VALUES (1, 'USER');