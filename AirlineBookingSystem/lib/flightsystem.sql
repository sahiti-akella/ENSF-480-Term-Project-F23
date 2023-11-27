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
    isRegistered		BOOLEAN,
    CreditCardNumber 	INT,
    
   FOREIGN KEY (UserID) REFERENCES USERS(UserID)
);

INSERT INTO CUSTOMERS (UserID, UserName, UserPassword, FirstName, LastName, Address, Email, isRegistered) VALUES 

# CUSTOMERS VALUES
(1, 'johndoe','password','John', 'Doe', '123 Main St', 'john@gmail.com', False),
(2, 'nedberry','password','Ned', 'Berry', '123 Main St', 'ned@gmail.com', False),
(3, 'alicejohnson','password','Alice', 'Johnson', '456 Oak St', 'alice@gmail.com', False),
(4, 'bobsmith','password','Bob', 'Smith', '789 Pine St', 'bob@gmail.com', True),
(5, 'evadavis','password','Eva', 'Davis', '101 Elm St', 'eva@gmail.com', True),
(6, 'frankwhite','password','Frank', 'White', '222 Maple St', 'frank@gmail.com',True);

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

INSERT INTO TOURISM_AGENTS (UserID, UserName, UserPassword, FirstName, LastName, Email) VALUES 

# TOURISM AGENTS VALUES
(7, 'kellyjohnson','password','Kelly', 'Johnson', 'kelly@gmail.com'),
(8, 'tombrown','password','Tom', 'Brown', 'tom@gmail.com'),
(9, 'saragreen','password','Sara', 'Green', 'sara@gmail.com');

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

INSERT INTO FLIGHT_ATTENDANTS (UserID, UserName, UserPassword, FirstName, LastName) VALUES 
# FLIGHT ATTENDANTS VALUES
(10, 'philthomas', 'password','Phil','Thomas'),
(11, 'michaellee','password','Michael', 'Lee' ),
(12, 'lucywang','password','Lucy','Wang');

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

INSERT INTO AIRLINE_AGENTS (UserID, UserName, UserPassword, FirstName, LastName) VALUES 
# AIRLINE AGENTS VALUES
(13, 'janesmith', 'password','Jane', 'Smith'),
(14, 'jackwilson','password','Jack', 'Wilson'),
(15, 'emmamiller','password','Emma', 'Miller');

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

INSERT INTO SYSTEM_ADMINS (UserID, UserName, UserPassword, FirstName, LastName) VALUES 
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
(1, 'Ordinary', 800.00),
(2, 'Comfort', 1150.00),
(3, 'Business-Class', 1600.00);


# SEATS TABLE
DROP TABLE IF EXISTS SEATS;
CREATE TABLE SEATS (
    FlightID 			INT,
    SeatID 				INT NOT NULL AUTO_INCREMENT,
    LayoutID            INT,
    IsAvailable 		INT NOT NULL DEFAULT 1,
    
    PRIMARY KEY (SeatID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID),
    FOREIGN KEY (LayoutID) REFERENCES SEAT_LAYOUTS(LayoutID)
);

INSERT INTO SEATS (FlightID, LayoutID, IsAvailable) VALUES

#  FlightID 1
(1, 1, 1), (1, 1, 1), (1, 1, 1), (1, 1, 1), 
(1, 2, 1), (1, 2, 1), (1, 2, 1), (1, 2, 0), 
(1, 3, 1), (1, 3, 0), (1, 3, 1), (1, 3, 1),

# FlightID 2
(2, 1, 1), (2, 1, 1), (2, 1, 1), (2, 1, 1), 
(2, 2, 1), (2, 2, 1), (2, 2, 0), (2, 2, 1), 
(2, 3, 1), (2, 3, 0), (2, 3, 1), (2, 3, 1),

# FlightID 3
(3, 1, 1), (3, 1, 1), (3, 1, 1), (3, 1, 1), 
(3, 2, 1), (3, 2, 1), (3, 2, 1), (3, 2, 1), 
(3, 3, 1), (3, 3, 1), (3, 3, 1), (3, 3, 1),

# FlightID 4
(4, 1, 1), (4, 1, 0), (4, 1, 1), (4, 1, 1), 
(4, 2, 1), (4, 2, 1), (4, 2, 1), (4, 2, 1), 
(4, 3, 1), (4, 3, 1), (4, 3, 0), (4, 3, 1),

# FlightID 5
(5, 1, 1), (5, 1, 1), (5, 1, 0), (5, 1, 1), 
(5, 2, 1), (5, 2, 0), (5, 2, 1), (5, 2, 1), 
(5, 3, 1), (5, 3, 1), (5, 3, 1), (5, 3, 1),

# FlightID 6
(6, 1, 1), (6, 1, 1), (6, 1, 1), (6, 1, 1), 
(6, 2, 1), (6, 2, 0), (6, 2, 1), (6, 2, 1), 
(6, 3, 1), (6, 3, 1), (6, 3, 0), (6, 3, 1),

# FlightID 7
(7, 1, 1), (7, 1, 1), (7, 1, 1), (7, 1, 0), 
(7, 2, 1), (7, 2, 0), (7, 2, 1), (7, 2, 1), 
(7, 3, 1), (7, 3, 1), (7, 3, 0), (7, 3, 1),

# FlightID 8
(8, 1, 1), (8, 1, 1), (8, 1, 0), (8, 1, 1), 
(8, 2, 1), (8, 2, 0), (8, 2, 0), (8, 2, 0), 
(8, 3, 1), (8, 3, 0), (8, 3, 1), (8, 3, 1),

# FlightID 9
(9, 1, 1), (9, 1, 0), (9, 1, 1), (9, 1, 1), 
(9, 2, 1), (9, 2, 1), (9, 2, 0), (9, 2, 1), 
(9, 3, 1), (9, 3, 1), (9, 3, 0), (9, 3, 1),

# FlightID 10
(10, 1, 1), (10, 1, 0), (10, 1, 1), (10, 1, 1), 
(10, 2, 1), (10, 2, 1), (10, 2, 0), (10, 2, 1), 
(10, 3, 1), (10, 3, 0), (10, 3, 1), (10, 3, 1);

# PAYMENTS TABLE
DROP TABLE IF EXISTS PAYMENTS;
CREATE TABLE PAYMENTS (
    PaymentID 			INT NOT NULL AUTO_INCREMENT,
    UserID 				INT,
    FlightID 			INT,
    LayoutID			INT,
    InsuranceSelected 	BOOLEAN,
    Amount 				DECIMAL(10, 2) NOT NULL,
    
    PRIMARY KEY (PaymentID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID),
    FOREIGN KEY (LayoutID) REFERENCES Seat_Layouts(LayoutID)
);

# TICKETS TABLE
DROP TABLE IF EXISTS TICKETS;
CREATE TABLE TICKETS (
    TicketID 			INT NOT NULL AUTO_INCREMENT,
    UserID 				INT,
    FlightID 			INT,
    SeatID 				INT,
    TicketDate 			DATETIME NOT NULL,
    IsCancelled 		BOOLEAN NOT NULL DEFAULT FALSE,
    
    PRIMARY KEY (TicketID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID),
    FOREIGN KEY (SeatID) REFERENCES Seats(SeatID)
);
