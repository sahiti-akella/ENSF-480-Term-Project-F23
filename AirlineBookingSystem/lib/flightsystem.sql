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
    CreditCardNumber 	INT,
    
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
(1, 'Boston', 'Los Angeles', 'Dec 1, 2023', 1),
(2, 'Houston', 'Philadelphia', 'Dec 12, 2023', 1),
(3, 'Portland', 'Chicago', 'Dec 5, 2023', 2),
(4, 'San Jose', 'Halifax', 'Dec 15, 2023', 2),
(5, 'Denver', 'Miami', 'Dec 8, 2023', 3),
(6, 'Edmonton', 'Austin', 'Dec 17, 2023', 3),
(7, 'Toronto', 'San Francisco', 'Dec 11, 2023', 4),
(8, 'Phoenix', 'Montreal', 'Dec 29, 2023', 4),
(9, 'Las Vegas', 'Calgary', 'Dec 26, 2023', 5),
(10, 'Vancouver', 'Dallas', 'Dec 10, 2023', 5);

# SEATS TABLE
DROP TABLE IF EXISTS SEATS;
CREATE TABLE SEATS (
    FlightID 			INT,
    SeatID 				INT NOT NULL AUTO_INCREMENT,
    SeatType 			ENUM('Ordinary', 'Comfort', 'Business-Class') NOT NULL,
    Price 				DECIMAL(10, 2) NOT NULL,
    IsAvailable 		BOOLEAN NOT NULL DEFAULT TRUE,
    
    PRIMARY KEY (SeatID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID)
);

INSERT INTO SEATS (FlightID, SeatID, SeatType, Price, IsAvailable) VALUES

#  FlightID 1
(1, '1', 'Business-Class', 500.00, true),
(1, '2', 'Business-Class', 500.00, true),
(1, '3', 'Comfort', 300.00, true),
(1, '4', 'Comfort', 300.00, true),
(1, '5', 'Ordinary', 150.00, true),
(1, '6', 'Ordinary', 150.00, true),

# FlightID 2
(2, '1', 'Business-Class', 550.00, true),
(2, '2', 'Business-Class', 550.00, true),
(2, '3', 'Comfort', 330.00, true),
(2, '4', 'Comfort', 330.00, true),
(2, '5', 'Ordinary', 165.00, true),
(2, '6', 'Ordinary', 165.00, true),

# FlightID 3
(3, '1', 'Business-Class', 600.00, true),
(3, '2', 'Business-Class', 600.00, true),
(3, '3', 'Comfort', 400.00, true),
(3, '4', 'Comfort', 400.00, true),
(3, '5', 'Ordinary', 100.00, true),
(3, '6', 'Ordinary', 100.00, true),

# FlightID 4
(4, '1', 'Business-Class', 600.00, true),
(4, '2', 'Business-Class', 600.00, true),
(4, '3', 'Comfort', 400.00, true),
(4, '4', 'Comfort', 400.00, true),
(4, '5', 'Ordinary', 100.00, true),
(4, '6', 'Ordinary', 100.00, true),

# FlightID 5
(5, '1', 'Business-Class', 600.00, true),
(5, '2', 'Business-Class', 600.00, true),
(5, '3', 'Comfort', 400.00, true),
(5, '4', 'Comfort', 400.00, true),
(5, '5', 'Ordinary', 100.00, true),
(5, '6', 'Ordinary', 100.00, true),

# FlightID 6
(6, '1', 'Business-Class', 600.00, true),
(6, '2', 'Business-Class', 600.00, true),
(6, '3', 'Comfort', 400.00, true),
(6, '4', 'Comfort', 400.00, true),
(6, '5', 'Ordinary', 100.00, true),
(6, '6', 'Ordinary', 100.00, true),

# FlightID 7
(7, '1', 'Business-Class', 600.00, true),
(7, '2', 'Business-Class', 600.00, true),
(7, '3', 'Comfort', 400.00, true),
(7, '4', 'Comfort', 400.00, true),
(7, '5', 'Ordinary', 100.00, true),
(7, '6', 'Ordinary', 100.00, true),

# FlightID 8
(8, '1', 'Business-Class', 600.00, true),
(8, '2', 'Business-Class', 600.00, true),
(8, '3', 'Comfort', 400.00, true),
(8, '4', 'Comfort', 400.00, true),
(8, '5', 'Ordinary', 100.00, true),
(8, '6', 'Ordinary', 100.00, true),

# FlightID 9
(9, '1', 'Business-Class', 600.00, true),
(9, '2', 'Business-Class', 600.00, true),
(9, '3', 'Comfort', 400.00, true),
(9, '4', 'Comfort', 400.00, true),
(9, '5', 'Ordinary', 100.00, true),
(9, '6', 'Ordinary', 100.00, true),

# FlightID 10
(10, '1', 'Business-Class', 600.00, true),
(10, '2', 'Business-Class', 600.00, true),
(10, '3', 'Comfort', 400.00, true),
(10, '4', 'Comfort', 400.00, true),
(10, '5', 'Ordinary', 100.00, true),
(10, '6', 'Ordinary', 100.00, true);

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
