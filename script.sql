DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS clients;

CREATE TABLE clients(
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
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
(1, "Joe", "Smith", "joe.smith@test.com", "123-456-7890", "123 Fake St.", "Atlanta", "GA", "30307"),
(2, "Jane", "Doe", "jane.doe@test.com", "404-456-7890", "564 Peachtree Rd.", "Atlanta", "GA", "30312"),
(4, "Katherine", "Smith", "katherine.smith@test.com", "718-458-9234", "23 N Highland Ave NE", "Atlanta", "GA", "30314");

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


SELECT *
FROM clients
WHERE id = 1;

-- getAllAccountsByClientId
SELECT * FROM accounts
WHERE client_id = 10;


