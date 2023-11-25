/* F23 ENSF 480 Term Project - Group 6 */

DROP DATABASE IF EXISTS FRWA;
CREATE DATABASE FRWA; 
USE FRWA;

# USERS TABLE
DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
    UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    Address 			VARCHAR(100) NOT NULL,
    Email 				VARCHAR(100) NOT NULL,
    UserType 			ENUM('Customer', 'TourismAgent', 'AirlineAgent', 'FlightAttendant', 'Administrator') NOT NULL,
    MembershipStatus 	ENUM('Regular', 'Registered') DEFAULT 'Regular',
    CreditCardNumber 	VARCHAR(16),
    
    PRIMARY KEY (UserID)
);

INSERT INTO USERS (UserName, Address, Email, UserType, MembershipStatus) VALUES 

# CUSTOMERS
('John Doe', '123 Main St', 'john@gmail.com', 'Customer', 'Regular'),
('Ned Berry', '123 Main St', 'ned@gmail.com', 'Customer', 'Regular'),
('Alice Johnson', '456 Oak St', 'alice@gmail.com', 'Customer', 'Regular'),
('Bob Smith', '789 Pine St', 'bob@gmail.com', 'Customer', 'Registered'),
('Eva Davis', '101 Elm St', 'eva@gmail.com', 'Customer', 'Registered'),
('Frank White', '222 Maple St', 'frank@gmail.com', 'Customer', 'Registered'),

# TOURISM AGENTS
('Kelly Johnson', '101 Elm St', 'kelly@gmail.com', 'TourismAgent', NULL),
('Tom Brown', '333 Oak St', 'tom@gmail.com', 'TourismAgent', NULL),
('Sara Green', '444 Pine St', 'sara@gmail.com', 'TourismAgent', NULL),

# FLIGHT ATTENDANTS
('Phil Thomas', '555 Maple St', 'phil@gmail.com', 'FlightAttendant', NULL),
('Michael Lee', '666 Oak St', 'michael@gmail.com', 'FlightAttendant', NULL),
('Lucy Wang', '777 Pine St', 'lucy@gmail.com','FlightAttendant', NULL),

# AIRLINE AGENTS
('Jane Smith', '456 Oak St', 'jane@gmail.com', 'AirlineAgent', NULL),
('Jack Wilson', '888 Pine St', 'jack@gmail.com', 'AirlineAgent', NULL),
('Emma Miller', '999 Elm St', 'emma@gmail.com','AirlineAgent', NULL),

# SYSTEM ADMINISTRATORS
('Helen Potter', '789 Pine St', 'helen@gmail.com', 'Administrator', NULL),
('Adam Johnson', '123 Main St', 'adam@gmail.com','Administrator', NULL),
('Grace Davis', '456 Oak St', 'grace@gmail.com','Administrator', NULL),
('Robert White', '789 Pine St', 'robert@gmail.com', 'Administrator', NULL);

# AIRCRAFTS TABLE
DROP TABLE IF EXISTS AIRCRAFTS;
CREATE TABLE AIRCRAFTS (
    AircraftID 			INT NOT NULL AUTO_INCREMENT,
    AircraftType 		VARCHAR(50) NOT NULL,
    
    PRIMARY KEY (AircraftID)
);

INSERT INTO AIRCRAFTS (AircraftType) VALUES
('Boeing 737'),
('Airbus A320'),
('Boeing 747'),
('Embraer E190'),
('Airbus A380');

# FLIGHTS TABLE
DROP TABLE IF EXISTS FLIGHTS;
CREATE TABLE FLIGHTS (
    FlightID 			INT NOT NULL AUTO_INCREMENT,
    Origin 				VARCHAR(50) NOT NULL,
    Destination 		VARCHAR(50) NOT NULL,
    DepartureDate 		DATETIME NOT NULL,
    AircraftID 			INT,
    
    PRIMARY KEY (FlightID),
    FOREIGN KEY (AircraftID) REFERENCES Aircrafts(AircraftID)
);

INSERT INTO FLIGHTS (Origin, Destination, DepartureDate, AircraftID) VALUES
('Boston', 'Los Angeles', '2023-12-01 08:00', 1),
('Houston', 'Philadelphia', '2023-12-10 09:30', 1),
('Portland', 'Chicago', '2023-12-05 10:00', 2),
('San Jose', 'Halifax', '2023-12-15 11:30', 2),
('Denver', 'Miami', '2023-12-08 12:00', 3),
('Edmonton', 'Austin', '2023-12-20 13:30', 3),
('Toronto', 'San Francisco', '2023-12-23 14:00', 4),
('Phoenix', 'Montreal', '2023-12-24 15:30', 4),
('Las Vegas', 'Calgary', '2023-12-05 16:00', 5),
('Vancouver', 'Dallas', '2023-12-29 17:30', 5);

# SEATS TABLE
DROP TABLE IF EXISTS SEATS;
CREATE TABLE SEATS (
    SeatID 				INT NOT NULL AUTO_INCREMENT,
    FlightID 			INT,
    SeatNumber 			VARCHAR(10) NOT NULL,
    SeatType 			ENUM('Ordinary', 'Comfort', 'Business-Class') NOT NULL,
    Price 				DECIMAL(10, 2) NOT NULL,
    IsAvailable 		BOOLEAN NOT NULL DEFAULT TRUE,
    
    PRIMARY KEY (SeatID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID)
);

INSERT INTO SEATS (FlightID, SeatNumber, SeatType, Price, IsAvailable) VALUES

#  FlightID 1
(1, '1A', 'Business-Class', 500.00, true),
(1, '1B', 'Business-Class', 500.00, true),
(1, '2A', 'Comfort', 300.00, true),
(1, '2B', 'Comfort', 300.00, true),
(1, '3A', 'Ordinary', 150.00, true),
(1, '3B', 'Ordinary', 150.00, true),

# FlightID 2
(2, '1A', 'Business-Class', 550.00, true),
(2, '1B', 'Business-Class', 550.00, true),
(2, '2A', 'Comfort', 330.00, true),
(2, '2B', 'Comfort', 330.00, true),
(2, '3A', 'Ordinary', 165.00, true),
(2, '3B', 'Ordinary', 165.00, true),

# FlightID 3
(2, '1A', 'Business-Class', 600.00, true),
(2, '1B', 'Business-Class', 600.00, true),
(2, '2A', 'Comfort', 400.00, true),
(2, '2B', 'Comfort', 400.00, true),
(2, '3A', 'Ordinary', 100.00, true),
(2, '3B', 'Ordinary', 100.00, true),

# FlightID 4
(2, '1A', 'Business-Class', 550.00, true),
(2, '1B', 'Business-Class', 550.00, true),
(2, '2A', 'Comfort', 330.00, true),
(2, '2B', 'Comfort', 330.00, true),
(2, '3A', 'Ordinary', 165.00, true),
(2, '3B', 'Ordinary', 165.00, true),

# FlightID 5
(2, '1A', 'Business-Class', 550.00, true),
(2, '1B', 'Business-Class', 550.00, true),
(2, '2A', 'Comfort', 330.00, true),
(2, '2B', 'Comfort', 330.00, true),
(2, '3A', 'Ordinary', 165.00, true),
(2, '3B', 'Ordinary', 165.00, true),

# FlightID 6
(2, '1A', 'Business-Class', 550.00, true),
(2, '1B', 'Business-Class', 550.00, true),
(2, '2A', 'Comfort', 330.00, true),
(2, '2B', 'Comfort', 330.00, true),
(2, '3A', 'Ordinary', 165.00, true),
(2, '3B', 'Ordinary', 165.00, true),

# FlightID 7
(2, '1A', 'Business-Class', 550.00, true),
(2, '1B', 'Business-Class', 550.00, true),
(2, '2A', 'Comfort', 330.00, true),
(2, '2B', 'Comfort', 330.00, true),
(2, '3A', 'Ordinary', 165.00, true),
(2, '3B', 'Ordinary', 165.00, true),

# FlightID 8
(2, '1A', 'Business-Class', 550.00, true),
(2, '1B', 'Business-Class', 550.00, true),
(2, '2A', 'Comfort', 330.00, true),
(2, '2B', 'Comfort', 330.00, true),
(2, '3A', 'Ordinary', 165.00, true),
(2, '3B', 'Ordinary', 165.00, true),

# FlightID 9
(2, '1A', 'Business-Class', 550.00, true),
(2, '1B', 'Business-Class', 550.00, true),
(2, '2A', 'Comfort', 330.00, true),
(2, '2B', 'Comfort', 330.00, true),
(2, '3A', 'Ordinary', 165.00, true),
(2, '3B', 'Ordinary', 165.00, true),

# FlightID 10
(2, '1A', 'Business-Class', 550.00, true),
(2, '1B', 'Business-Class', 550.00, true),
(2, '2A', 'Comfort', 330.00, true),
(2, '2B', 'Comfort', 330.00, true),
(2, '3A', 'Ordinary', 165.00, true),
(2, '3B', 'Ordinary', 165.00, true);

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
