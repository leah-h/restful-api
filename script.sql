DROP TABLE IF EXISTS accounts;


CREATE TABLE clients(
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        accountId INTEGER NOT NULL,
                        firstName VARCHAR(255) NOT NULL,
                        lastName VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        phoneNumber VARCHAR(255),
                        address VARCHAR(255),
                        city VARCHAR(255),
                        state VARCHAR(255),
                        zip VARCHAR(255)
);

INSERT INTO clients
VALUES
(1, 12345, "Joe", "Smith", "joe.smith@test.com", "123-456-7890", "123 Fake St.", "Atlanta", "GA", "30307"),
(2, 54321, "Jane", "Doe", "jane.doe@test.com", "404-456-7890", "564 Peachtree Rd.", "Atlanta", "GA", "30312");

CREATE TABLE accounts(
                         id INTEGER PRIMARY KEY AUTO_INCREMENT,
                         account_type VARCHAR(255) NOT NULL,
                         balance DECIMAL NOT NULL,
                         client_id INTEGER NOT NULL,
                         CONSTRAINT `fk_accounts_clients` FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);


INSERT INTO accounts
VALUES
(5432, "Checking", 5000.00, 1),
(6789, "Savings", 556.00, 2);

SELECT * FROM clients;
SELECT * FROM accounts;
