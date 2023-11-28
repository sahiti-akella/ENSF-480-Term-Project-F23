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

INSERT INTO SEATS (SeatID, FlightID, LayoutID, IsAvailable) VALUES

-- FlightID 1
(1, 1, 1, 1), (2, 1, 1, 1), (3, 1, 1, 1), (4, 1, 1, 1),
(5, 1, 2, 1), (6, 1, 2, 1), (7, 1, 2, 1), (8, 1, 2, 0),
(9, 1, 3, 1), (10, 1, 3, 0), (11, 1, 3, 1), (12, 1, 3, 1),

-- FlightID 2
(13, 2, 1, 1), (14, 2, 1, 1), (15, 2, 1, 1), (16, 2, 1, 1),
(17, 2, 2, 1), (18, 2, 2, 1), (19, 2, 2, 0), (20, 2, 2, 1),
(21, 2, 3, 1), (22, 2, 3, 0), (23, 2, 3, 1), (24, 2, 3, 1),

-- FlightID 3
(25, 3, 1, 1), (26, 3, 1, 1), (27, 3, 1, 1), (28, 3, 1, 1),
(29, 3, 2, 1), (30, 3, 2, 1), (31, 3, 2, 1), (32, 3, 2, 1),
(33, 3, 3, 1), (34, 3, 3, 1), (35, 3, 3, 1), (36, 3, 3, 1),

-- FlightID 4
(37, 4, 1, 1), (38, 4, 1, 0), (39, 4, 1, 1), (40, 4, 1, 1),
(41, 4, 2, 1), (42, 4, 2, 1), (43, 4, 2, 1), (44, 4, 2, 1),
(45, 4, 3, 1), (46, 4, 3, 1), (47, 4, 3, 0), (48, 4, 3, 1),

-- FlightID 5
(49, 5, 1, 1), (50, 5, 1, 1), (51, 5, 1, 0), (52, 5, 1, 1),
(53, 5, 2, 1), (54, 5, 2, 0), (55, 5, 2, 1), (56, 5, 2, 1),
(57, 5, 3, 1), (58, 5, 3, 1), (59, 5, 3, 1), (60, 5, 3, 1),

-- FlightID 6
(61, 6, 1, 1), (62, 6, 1, 1), (63, 6, 1, 1), (64, 6, 1, 1),
(65, 6, 2, 1), (66, 6, 2, 0), (67, 6, 2, 1), (68, 6, 2, 1),
(69, 6, 3, 1), (70, 6, 3, 1), (71, 6, 3, 0), (72, 6, 3, 1),

-- FlightID 7
(73, 7, 1, 1), (74, 7, 1, 1), (75, 7, 1, 1), (76, 7, 1, 0),
(77, 7, 2, 1), (78, 7, 2, 0), (79, 7, 2, 1), (80, 7, 2, 1),
(81, 7, 3, 1), (82, 7, 3, 1), (83, 7, 3, 0), (84, 7, 3, 1),

-- FlightID 8
(85, 8, 1, 1), (86, 8, 1, 1), (87, 8, 1, 0), (88, 8, 1, 1),
(89, 8, 2, 1), (90, 8, 2, 0), (91, 8, 2, 0), (92, 8, 2, 0),
(93, 8, 3, 1), (94, 8, 3, 0), (95, 8, 3, 1), (96, 8, 3, 1),

-- FlightID 9
(97, 9, 1, 1), (98, 9, 1, 0), (99, 9, 1, 1), (100, 9, 1, 1),
(101, 9, 2, 1), (102, 9, 2, 1), (103, 9, 2, 0), (104, 9, 2, 1),
(105, 9, 3, 1), (106, 9, 3, 1), (107, 9, 3, 0), (108, 9, 3, 1),

-- FlightID 10
(109, 10, 1, 1), (110, 10, 1, 0), (111, 10, 1, 1), (112, 10, 1, 1),
(113, 10, 2, 1), (114, 10, 2, 1), (115, 10, 2, 0), (116, 10, 2, 1),
(117, 10, 3, 1), (118, 10, 3, 0), (119, 10, 3, 0), (120, 10, 3, 1);


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
