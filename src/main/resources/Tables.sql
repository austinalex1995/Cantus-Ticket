--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS users;

CREATE TABLE events (
    id INT AUTO_INCREMENT,
    title VARCHAR(50),
    eventType VARCHAR(50),
    standardSeating INT,
    standardPrice FLOAT,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id INT AUTO_INCREMENT,
    email VARCHAR(30) UNIQUE,
    password VARCHAR(25),
    PRIMARY KEY (id)
);

CREATE TABLE tickets (
    ticket_id INT AUTO_INCREMENT,
    event_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    PRIMARY KEY (ticket_id)
);