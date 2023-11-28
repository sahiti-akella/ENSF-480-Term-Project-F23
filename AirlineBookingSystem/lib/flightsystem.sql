/* F23 ENSF 480 Term Project - Group 6 */

DROP DATABASE IF EXISTS FRWA;
CREATE DATABASE FRWA; 
USE FRWA;

# USERS TABLE
DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
    UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    Address             VARCHAR(100),
    Email               VARCHAR(100) NOT NULL,
    AccountType         VARCHAR(50) NOT NULL,
    isRegistered        BOOLEAN,
    CreditCardNumber 	VARCHAR(16),
    
    PRIMARY KEY (UserID)
);

INSERT INTO USERS (UserID, UserName, UserPassword, FirstName, LastName, Address, Email, AccountType, isRegistered, CreditCardNumber) VALUES
(1, 'johndoe', 'password', 'John', 'Doe', '123 Main St', 'john@gmail.com', 'admin', FALSE, NULL),
(2, 'nedberry', 'password', 'Ned', 'Berry', '123 Main St', 'ned@gmail.com', 'customer', TRUE, '1234567897654321'),
(3, 'alicejohnson', 'password', 'Alice', 'Johnson', '456 Oak St', 'alice@gmail.com', 'airline-agent', NULL, NULL),
(4, 'bobsmith', 'password', 'Bob', 'Smith', '789 Pine St', 'bob@gmail.com', 'flight-attendant', NULL, NULL),
(5, 'evadavis', 'password', 'Eva', 'Davis', '101 Elm St', 'eva@gmail.com', 'tourism-agent', NULL, NULL),
(6, 'frankwhite', 'password', 'Frank', 'White', '222 Maple St', 'frank@gmail.com', 'customer', TRUE, NULL),
(7, 'kellyjohnson', 'password', 'Kelly', 'Johnson', '543 Cedar St', 'kelly@gmail.com', 'customer', FALSE, NULL),
(8, 'tombrown', 'password', 'Tom', 'Brown', '654 Birch St', 'tom@gmail.com', 'customer', FALSE, NULL),
(9, 'saragreen', 'password', 'Sara', 'Green', '765 Pine St', 'sara@gmail.com', 'customer', TRUE, NULL),
(10, 'philthomas', 'password', 'Phil', 'Thomas', '876 Oak St', 'thomas@gmail.com', 'customer', TRUE, NULL),
(11, 'michaellee', 'password', 'Michael', 'Lee', '987 Elm St', 'michael@gmail.com', 'customer', FALSE, NULL),
(12, 'lucywang', 'password', 'Lucy', 'Wang', '876 Cedar St', 'lucy@gmail.com', 'tourism-agent', NULL, NULL),
(13, 'janesmith', 'password', 'Jane', 'Smith', '765 Birch St', 'jane@gmail.com', 'tourism-agent', NULL, NULL),
(14, 'jackwilson', 'password', 'Jack', 'Wilson', '654 Pine St', 'jack@gmail.com', 'flight-attendant', NULL, NULL),
(15, 'emmamiller', 'password', 'Emma', 'Miller', '543 Oak St', 'emma@gmail.com', 'flight-attendant', NULL, NULL),
(16, 'hellenpotter', 'password', 'Helen', 'Potter', '432 Main St', 'helen@gmail.com', 'airline-agent', NULL, NULL),
(17, 'adamjohnson', 'password', 'Adam', 'Johnson', '321 Elm St', 'adam@gmail.com', 'airline-agent', NULL, NULL),
(18, 'gracedavis', 'password', 'Grace', 'Davis', '210 Oak St', 'grace@gmail.com', 'admin', NULL, NULL),
(19, 'robertwhite', 'password', 'Robert', 'White', '109 Pine St', 'robert@gmail.com', 'admin', NULL, NULL);



# CUSTOMERS TABLE
DROP TABLE IF EXISTS CUSTOMERS;
CREATE TABLE CUSTOMERS (
	UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    Address 			VARCHAR(100) NOT NULL,
    Email 				VARCHAR(100) NOT NULL,
    isRegistered		BOOLEAN,
    CreditCardNumber 	INT,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO CUSTOMERS (UserID, UserName, UserPassword, FirstName, LastName, Address, Email, isRegistered)
SELECT UserID, UserName, UserPassword, FirstName, LastName, Address, Email, isRegistered
FROM USERS
WHERE AccountType = 'customer';

# TOURISM AGENT TABLE
DROP TABLE IF EXISTS TOURISM_AGENTS;
CREATE TABLE TOURISM_AGENTS (
    UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    Email 				VARCHAR(100) NOT NULL,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO TOURISM_AGENTS (UserID, UserName, UserPassword, FirstName, LastName, Email)
SELECT UserID, UserName, UserPassword, FirstName, LastName, Email
FROM USERS
WHERE AccountType = 'tourism-agent';


# FLIGHT ATTENDANT TABLE
DROP TABLE IF EXISTS FLIGHT_ATTENDANTS;
CREATE TABLE FLIGHT_ATTENDANTS (
	UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO FLIGHT_ATTENDANTS (UserID, UserName, UserPassword, FirstName, LastName) 
SELECT UserID, UserName, UserPassword, FirstName, LastName
FROM USERS
WHERE AccountType = 'flight-attendant';

# AIRLINE AGENT TABLE
DROP TABLE IF EXISTS AIRLINE_AGENTS;
CREATE TABLE AIRLINE_AGENTS (
    UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO AIRLINE_AGENTS (UserID, UserName, UserPassword, FirstName, LastName)
SELECT UserID, UserName, UserPassword, FirstName, LastName
FROM USERS
WHERE AccountType = 'airline-agent';

# SYSTEM ADMIN TABLE
DROP TABLE IF EXISTS SYSTEM_ADMINS;
CREATE TABLE SYSTEM_ADMINS (
    UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO SYSTEM_ADMINS (UserID, UserName, UserPassword, FirstName, LastName) 
SELECT UserID, UserName, UserPassword, FirstName, LastName
FROM USERS
WHERE AccountType = 'admin';

# AIRCRAFTS TABLE
DROP TABLE IF EXISTS AIRCRAFTS;
CREATE TABLE AIRCRAFTS (
    AircraftID 			INT NOT NULL AUTO_INCREMENT,
    AircraftType 		VARCHAR(50) NOT NULL,
    
    PRIMARY KEY (AircraftID)
);

INSERT INTO AIRCRAFTS (AircraftID, AircraftType) VALUES
(1, 'Boeing 737'),
(2, 'Airbus A320'),
(3, 'Boeing 747'),
(4, 'Embraer E190'),
(5, 'Airbus A380');

# FLIGHTS TABLE
DROP TABLE IF EXISTS FLIGHTS;
CREATE TABLE FLIGHTS (
    FlightID 			INT NOT NULL AUTO_INCREMENT,
    Origin 				VARCHAR(50) NOT NULL,
    Destination 		VARCHAR(50) NOT NULL,
    DepartureDate 		VARCHAR(50) NOT NULL,
    AircraftID 			INT,
    
    PRIMARY KEY (FlightID),
    FOREIGN KEY (AircraftID) REFERENCES Aircrafts(AircraftID)
);

INSERT INTO FLIGHTS (FlightID, Origin, Destination, DepartureDate, AircraftID) VALUES
(1, 'Boston', 'Los Angeles', '12/01/2023', 1),
(2, 'Houston', 'Philadelphia', '12/12/2023', 1),
(3, 'Portland', 'Chicago', '12/05/2023', 2),
(4, 'San Jose', 'Halifax', '12/15/2023', 2),
(5, 'Denver', 'Miami', '12/08/2023', 3),
(6, 'Edmonton', 'Austin', '12/17/2023', 3),
(7, 'Toronto', 'San Francisco', '12/11/2023', 4),
(8, 'Phoenix', 'Montreal', '12/29/2023', 4),
(9, 'Las Vegas', 'Calgary', '12/26/2023', 5),
(10, 'Vancouver', 'Dallas', '12/10/2023', 5);


# SEAT LAYOUT TABLE
DROP TABLE IF EXISTS SEAT_LAYOUTS;
CREATE TABLE SEAT_LAYOUTS (
    LayoutID            INT NOT NULL AUTO_INCREMENT,
    SeatType            ENUM('Ordinary', 'Comfort', 'Business-Class') NOT NULL,
    Price               DECIMAL(10, 2) NOT NULL,
    
    PRIMARY KEY (LayoutID)
);

INSERT INTO SEAT_LAYOUTS (LayoutID, SeatType, Price) VALUES
(1, 'Ordinary', 150.00),
(2, 'Comfort', 300.00),
(3, 'Business-Class', 500.00);


# SEATS TABLE
DROP TABLE IF EXISTS SEATS;
CREATE TABLE SEATS (
    FlightID 			INT,
    SeatID 				INT NOT NULL AUTO_INCREMENT,
    LayoutID            INT,
    IsAvailable 		BOOLEAN NOT NULL DEFAULT TRUE,
    
    PRIMARY KEY (SeatID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID),
    FOREIGN KEY (LayoutID) REFERENCES SEAT_LAYOUTS(LayoutID)
);

INSERT INTO SEATS (FlightID, LayoutID, IsAvailable) VALUES

#  FlightID 1
(1, 1, true), (1, 1, true), (1, 1, true), (1, 1, true), 
(1, 2, false), (1, 2, true), (1, 2, true), (1, 2, false), 
(1, 3, true), (1, 3, false), (1, 3, true), (1, 3, true),

# FlightID 2
(2, 1, false), (2, 1, true), (2, 1, true), (2, 1, true), 
(2, 2, true), (2, 2, true), (2, 2, false), (2, 2, true), 
(2, 3, true), (2, 3, false), (2, 3, true), (2, 3, true),

# FlightID 3
(3, 1, true), (3, 1, true), (3, 1, true), (3, 1, false), 
(3, 2, true), (3, 2, false), (3, 2, true), (3, 2, true), 
(3, 3, false), (3, 3, true), (3, 3, true), (3, 3, true),

# FlightID 4
(4, 1, true), (4, 1, false), (4, 1, true), (4, 1, true), 
(4, 2, false), (4, 2, true), (4, 2, true), (4, 2, true), 
(4, 3, true), (4, 3, true), (4, 3, false), (4, 3, true),

# FlightID 5
(5, 1, true), (5, 1, true), (5, 1, false), (5, 1, true), 
(5, 2, true), (5, 2, false), (5, 2, true), (5, 2, true), 
(5, 3, false), (5, 3, true), (5, 3, true), (5, 3, true),

# FlightID 6
(6, 1, true), (6, 1, true), (6, 1, true), (6, 1, true), 
(6, 2, true), (6, 2, false), (6, 2, true), (6, 2, true), 
(6, 3, true), (6, 3, true), (6, 3, false), (6, 3, true),

# FlightID 7
(7, 1, true), (7, 1, true), (7, 1, true), (7, 1, false), 
(7, 2, true), (7, 2, false), (7, 2, true), (7, 2, true), 
(7, 3, true), (7, 3, true), (7, 3, false), (7, 3, true),

# FlightID 8
(8, 1, false), (8, 1, true), (8, 1, true), (8, 1, true), 
(8, 2, true), (8, 2, true), (8, 2, false), (8, 2, true), 
(8, 3, true), (8, 3, false), (8, 3, true), (8, 3, true),

# FlightID 9
(9, 1, true), (9, 1, false), (9, 1, true), (9, 1, true), 
(9, 2, false), (9, 2, true), (9, 2, true), (9, 2, true), 
(9, 3, true), (9, 3, true), (9, 3, false), (9, 3, true),

# FlightID 10
(10, 1, true), (10, 1, false), (10, 1, true), (10, 1, true), 
(10, 2, true), (10, 2, true), (10, 2, false), (10, 2, true), 
(10, 3, false), (10, 3, true), (10, 3, true), (10, 3, true);

# PAYMENTS TABLE
DROP TABLE IF EXISTS PAYMENTS;
CREATE TABLE PAYMENTS (
    PaymentID 			INT NOT NULL AUTO_INCREMENT,
    UserID 				INT,
    FlightID 			INT,
    Amount 				DECIMAL(10, 2) NOT NULL,
    PaymentStatus 		ENUM('Pending', 'Completed', 'Failed') DEFAULT 'Pending',
    
    PRIMARY KEY (PaymentID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID)
);

# TICKETS TABLE
DROP TABLE IF EXISTS TICKETS;
CREATE TABLE TICKETS (
    TicketID 			INT NOT NULL AUTO_INCREMENT,
    UserID 				INT,
    FlightID 			INT,
    SeatID 				INT,
    InsuranceSelected 	BOOLEAN,
    TicketDate 			DATETIME NOT NULL,
    IsCancelled 		BOOLEAN NOT NULL DEFAULT FALSE,
    
    PRIMARY KEY (TicketID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID),
    FOREIGN KEY (SeatID) REFERENCES Seats(SeatID)
);
