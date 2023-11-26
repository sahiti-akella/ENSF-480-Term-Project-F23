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
    
    PRIMARY KEY (UserID)
);

INSERT INTO USERS (UserID, UserName, UserPassword, FirstName, LastName) VALUES
(1, 'johndoe', 'password', 'John', 'Doe'),
(2, 'nedberry', 'password', 'Ned', 'Berry'),
(3, 'alicejohnson', 'password', 'Alice', 'Johnson'),
(4, 'bobsmith', 'password', 'Bob', 'Smith'),
(5, 'evadavis', 'password', 'Eva', 'Davis'),
(6, 'frankwhite', 'password', 'Frank', 'White'),
(7, 'kellyjohnson', 'password', 'Kelly', 'Johnson'),
(8, 'tombrown', 'password', 'Tom', 'Brown'),
(9, 'saragreen', 'password', 'Sara', 'Green'),
(10, 'philthomas', 'password', 'Phil', 'Thomas'),
(11, 'michaellee', 'password', 'Michael', 'Lee'),
(12, 'lucywang', 'password', 'Lucy', 'Wang'),
(13, 'janesmith', 'password', 'Jane', 'Smith'),
(14, 'jackwilson', 'password', 'Jack', 'Wilson'),
(15, 'emmamiller', 'password', 'Emma', 'Miller'),
(16, 'hellenpotter', 'password', 'Helen', 'Potter'),
(17, 'adamjohnson', 'password', 'Adam', 'Johnson'),
(18, 'gracedavis', 'password', 'Grace', 'Davis'),
(19, 'robertwhite', 'password', 'Robert', 'White');

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
    MembershipStatus 	ENUM('Regular', 'Registered') DEFAULT 'Regular',
    CreditCardNumber 	INT,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO CUSTOMERS (UserID, UserName, UserPassword, FirstName, LastName, Address, Email, MembershipStatus) VALUES 

# CUSTOMERS VALUES
(1, 'johndoe','password','John', 'Doe', '123 Main St', 'john@gmail.com', 'Regular'),
(2, 'nedberry','password','Ned', 'Berry', '123 Main St', 'ned@gmail.com', 'Regular'),
(3, 'alicejohnson','password','Alice', 'Johnson', '456 Oak St', 'alice@gmail.com', 'Regular'),
(4, 'bobsmith','password','Bob', 'Smith', '789 Pine St', 'bob@gmail.com', 'Registered'),
(5, 'evadavis','password','Eva', 'Davis', '101 Elm St', 'eva@gmail.com', 'Registered'),
(6, 'frankwhite','password','Frank', 'White', '222 Maple St', 'frank@gmail.com','Registered');

# TOURISM AGENT TABLE
DROP TABLE IF EXISTS TOURISM_AGENT;
CREATE TABLE TOURISM_AGENT (
    UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    Email 				VARCHAR(100) NOT NULL,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO TOURISM_AGENT (UserID, UserName, UserPassword, FirstName, LastName, Email) VALUES 

# TOURISM AGENTS VALUES
(7, 'kellyjohnson','password','Kelly', 'Johnson', 'kelly@gmail.com'),
(8, 'tombrown','password','Tom', 'Brown', 'tom@gmail.com'),
(9, 'saragreen','password','Sara', 'Green', 'sara@gmail.com');

# FLIGHT ATTENDANT TABLE
DROP TABLE IF EXISTS FLIGHT_ATTENDANT;
CREATE TABLE FLIGHT_ATTENDANT (
	UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO FLIGHT_ATTENDANT (UserID, UserName, UserPassword, FirstName, LastName) VALUES 
# FLIGHT ATTENDANTS VALUES
(10, 'philthomas', 'password','Phil','Thomas'),
(11, 'michaellee','password','Michael', 'Lee' ),
(12, 'lucywang','password','Lucy','Wang');

# AIRLINE AGENT TABLE
DROP TABLE IF EXISTS AIRLINE_AGENT;
CREATE TABLE AIRLINE_AGENT (
    UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO AIRLINE_AGENT (UserID, UserName, UserPassword, FirstName, LastName) VALUES 
# AIRLINE AGENTS VALUES
(13, 'janesmith', 'password','Jane', 'Smith'),
(14, 'jackwilson','password','Jack', 'Wilson'),
(15, 'emmamiller','password','Emma', 'Miller');

# SYSTEM ADMIN TABLE
DROP TABLE IF EXISTS SYSTEM_ADMIN;
CREATE TABLE SYSTEM_ADMIN (
    UserID 				INT NOT NULL AUTO_INCREMENT,
    UserName 			VARCHAR(50) NOT NULL,
    UserPassword 		VARCHAR(50) NOT NULL,
    FirstName 			VARCHAR(50) NOT NULL,
    LastName 			VARCHAR(50) NOT NULL,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO SYSTEM_ADMIN(UserID, UserName, UserPassword, FirstName, LastName) VALUES 
# SYSTEM ADMINISTRATORS VALUES
(16, 'hellenpotter','password','Helen', 'Potter'),
(17, 'adamjohnson','password','Adam', 'Johnson'),
(18, 'gracedavis','password','Grace', 'Davis'),
(19, 'robertwhite','password','Robert', 'White');

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
(1, 1, 'Business-Class', 500.00, true),
(1, 2, 'Business-Class', 500.00, true),
(1, 3, 'Comfort', 300.00, true),
(1, 4, 'Comfort', 300.00, true),
(1, 5, 'Ordinary', 150.00, true),
(1, 6, 'Ordinary', 150.00, true),

# FlightID 2
(2, 7, 'Business-Class', 550.00, true),
(2, 8, 'Business-Class', 550.00, true),
(2, 9, 'Comfort', 330.00, true),
(2, 10, 'Comfort', 330.00, true),
(2, 11, 'Ordinary', 165.00, true),
(2, 12, 'Ordinary', 165.00, true),

# FlightID 3
(3, 13, 'Business-Class', 600.00, true),
(3, 14, 'Business-Class', 600.00, true),
(3, 15, 'Comfort', 400.00, true),
(3, 16, 'Comfort', 400.00, true),
(3, 17, 'Ordinary', 100.00, true),
(3, 18, 'Ordinary', 100.00, true),

# FlightID 4
(4, 19, 'Business-Class', 600.00, true),
(4, 20, 'Business-Class', 600.00, true),
(4, 21, 'Comfort', 400.00, true),
(4, 22, 'Comfort', 400.00, true),
(4, 23, 'Ordinary', 100.00, true),
(4, 24, 'Ordinary', 100.00, true),

# FlightID 5
(5, 25, 'Business-Class', 600.00, true),
(5, 26, 'Business-Class', 600.00, true),
(5, 27, 'Comfort', 400.00, true),
(5, 28, 'Comfort', 400.00, true),
(5, 29, 'Ordinary', 100.00, true),
(5, 30, 'Ordinary', 100.00, true),

# FlightID 6
(6, 31, 'Business-Class', 600.00, true),
(6, 32, 'Business-Class', 600.00, true),
(6, 33, 'Comfort', 400.00, true),
(6, 34, 'Comfort', 400.00, true),
(6, 35, 'Ordinary', 100.00, true),
(6, 36, 'Ordinary', 100.00, true),

# FlightID 7
(7, 37, 'Business-Class', 600.00, true),
(7, 38, 'Business-Class', 600.00, true),
(7, 39, 'Comfort', 400.00, true),
(7, 40, 'Comfort', 400.00, true),
(7, 41, 'Ordinary', 100.00, true),
(7, 42, 'Ordinary', 100.00, true),

# FlightID 8
(8, 43, 'Business-Class', 600.00, true),
(8, 44, 'Business-Class', 600.00, true),
(8, 45, 'Comfort', 400.00, true),
(8, 46, 'Comfort', 400.00, true),
(8, 47, 'Ordinary', 100.00, true),
(8, 48, 'Ordinary', 100.00, true),

# FlightID 9
(9, 49, 'Business-Class', 600.00, true),
(9, 50, 'Business-Class', 600.00, true),
(9, 51, 'Comfort', 400.00, true),
(9, 52, 'Comfort', 400.00, true),
(9, 53, 'Ordinary', 100.00, true),
(9, 54, 'Ordinary', 100.00, true),

# FlightID 10
(10, 55, 'Business-Class', 600.00, true),
(10, 56, 'Business-Class', 600.00, true),
(10, 57, 'Comfort', 400.00, true),
(10, 58, 'Comfort', 400.00, true),
(10, 59, 'Ordinary', 100.00, true),
(10, 60, 'Ordinary', 100.00, true);

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
