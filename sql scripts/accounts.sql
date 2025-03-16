CREATE TABLE bank_accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(12) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    account_type ENUM('SAVINGS', 'CHECKING') NOT NULL,
    status ENUM('ACTIVE', 'SUSPENDED', 'CLOSED') NOT NULL,
    balance DOUBLE NOT NULL DEFAULT 0.0,
    currency VARCHAR(3) NOT NULL DEFAULT 'USD'
);

CREATE TABLE account_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'REJECTED') NOT NULL DEFAULT 'PENDING'
);
