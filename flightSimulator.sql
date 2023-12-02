/* F23 Flight Simulator Database
 Each time this file is executed, it will reset the database to the original state defined below.
 */

DROP DATABASE IF EXISTS FS;
CREATE DATABASE FS; 
USE FS;

DROP TABLE IF EXISTS PERMISSIONS;
CREATE TABLE PERMISSIONS (
	accessLevel		int  not null  default 1,
    role			varchar(25),
	registration	boolean,
    promotions		boolean,
    viewInfo		boolean,
    modifyInfo		boolean,
	primary key (accessLevel)
);

INSERT INTO PERMISSIONS VALUES 
(1, 'Normal User', false, false, false, false),
(2, 'Registered User', true, true, false, false),
(3, 'Employee', true, false, true, false),
(4, 'Admin', true, false, true, true);


DROP TABLE IF EXISTS PROMOTIONS;
CREATE TABLE PROMOTIONS (
	promotionID		int not null auto_increment,
    name			varchar(25),
	discount		varchar(25),
    startDate		DATE,
	endDate			DATE,
	primary key (promotionID)
);

INSERT INTO PROMOTIONS VALUES
(1, 'Summer Sale', '15%', '2023-06-01', '2023-08-31'),
(2, 'Holiday Special', '20%', '2023-12-01', '2023-12-31'),
(3, 'Back-to-School', '10%', '2023-08-15', '2023-09-15'),
(4, "New Year's Sale", '18%', '2023-01-01', '2023-01-07'),
(5, 'Fall Discount', '12%', '2023-09-01', '2023-11-30');

DROP TABLE IF EXISTS AIRCRAFT;
CREATE TABLE AIRCRAFT (
	aircraftID		int not null AUTO_INCREMENT,
    name			varchar(25),
	primary key (aircraftID)
);

INSERT INTO AIRCRAFT VALUES
(1, 'Boeing 737'),
(2, 'Airbus A320'),
(3, 'Embraer E190'),
(4, 'Cessna 172');

DROP TABLE IF EXISTS ALLUSERS;
CREATE TABLE ALLUSERS (
	userID				int not null AUTO_INCREMENT,
    accessLevel			INT,
    promotionID			INT default NULL,
    firstName			varchar(25),
    lastName			varchar(25),
    address				varchar(25),
    email				varchar(50),
    password			varchar(25) default NULL,
    birthDate			DATE,
    phoneNumber			varchar(12),
    balance				FLOAT default NULL,
    
	primary key (userID),
    FOREIGN KEY (accessLevel) REFERENCES PERMISSIONS(accessLevel),
    FOREIGN KEY (promotionID) REFERENCES PROMOTIONS(promotionID)
);

INSERT INTO ALLUSERS VALUES
(1, 1, NULL, 'John', 'Doe', '123 Main St', 'john.doe@example.com', NULL, '1990-01-15', '123-456-7890', NULL),
(2, 1, NULL, 'Jane', 'Smith', '456 Oak St', 'jane.smith@example.com', NULL, '1985-05-22', '234-567-8901', NULL),
(3, 1, NULL, 'Bob', 'Johnson', '789 Maple St', 'bob.johnson@example.com', NULL, '1992-09-10', '345-678-9012', NULL),
(4, 1, NULL, 'Alice', 'Williams', '234 Birch St', 'alice.williams@example.com', NULL, '1988-03-30', '456-789-0123', NULL),
(5, 1, NULL, 'Charlie', 'Brown', '567 Pine St', 'charlie.brown@example.com', NULL, '1995-07-12', '567-890-1234', NULL),
(6, 1, NULL, 'Eva', 'Davis', '890 Cedar St', 'eva.davis@example.com', NULL, '1983-11-05', '678-901-2345', NULL),
(7, 1, NULL, 'David', 'Lee', '345 Elm St', 'david.lee@example.com', NULL, '1998-02-18', '789-012-3456', NULL),
(8, 1, NULL, 'Grace', 'Miller', '678 Walnut St', 'grace.miller@example.com', NULL, '1980-06-25', '890-123-4567', NULL),
(9, 1, NULL, 'Sam', 'Taylor', '901 Redwood St', 'sam.taylor@example.com', NULL, '1993-04-08', '901-234-5678', NULL),
(10, 1, NULL, 'Olivia', 'Anderson', '123 Cedar St', 'olivia.anderson@example.com', NULL, '1987-08-15', '012-345-6789', NULL),
(11, 1, NULL, 'Sophie', 'Evans', '123 Oak St', 'sophie.evans@example.com', NULL, '1990-04-18', '234-567-8901', NULL),
(12, 1, NULL, 'Daniel', 'Morris', '456 Pine St', 'daniel.morris@example.com', NULL, '1987-08-02', '345-678-9012', NULL),
(13, 1, NULL, 'Oliver', 'Carter', '789 Maple St', 'oliver.carter@example.com', NULL, '1993-11-12', '456-789-0123', NULL),
(14, 1, NULL, 'Emma', 'Barnes', '234 Elm St', 'emma.barnes@example.com', NULL, '1985-02-25', '567-890-1234', NULL),
(15, 1, NULL, 'Jackson', 'Fisher', '567 Cedar St', 'jackson.fisher@example.com', NULL, '1991-05-08', '678-901-2345', NULL),
(16, 2, NULL, 'Sophia', 'Anderson', '456 Oak St', 'sophia.anderson@example.com', 'pass456', '1985-05-22', '234-567-8901', 75.50),
(17, 2, 1, 'William', 'Davis', '789 Maple St', 'william.davis@example.com', 'pass789', '1992-09-10', '345-678-9012', 120.75),
(18, 2, 3, 'Olivia', 'Harris', '234 Birch St', 'olivia.harris@example.com', 'passabc', '1988-03-30', '456-789-0123', 50.25),
(19, 2, 4, 'James', 'Smith', '567 Pine St', 'james.smith@example.com', 'passdef', '1995-07-12', '567-890-1234', 80.00),
(20, 2, 5, 'Emily', 'Taylor', '890 Cedar St', 'emily.taylor@example.com', 'passxyz', '1983-11-05', '678-901-2345', 95.30),
(21, 2, NULL, 'Alexander', 'Brown', '345 Elm St', 'alexander.brown@example.com', 'pass123', '1998-02-18', '789-012-3456', 60.75),
(22, 2, 1,'Samantha', 'Wilson', '678 Walnut St', 'samantha.wilson@example.com', 'pass456', '1980-06-25', '890-123-4567', 110.50),
(23, 2, 2, 'Henry', 'Miller', '901 Redwood St', 'henry.miller@example.com', 'pass789', '1993-04-08', '901-234-5678', 45.80),
(24, 2, 3, 'Ava', 'Garcia', '123 Cedar St', 'ava.garcia@example.com', 'passabc', '1987-08-15', '012-345-6789', 70.25),
(25, 2, 4, 'Jackson', 'Cooper', '234 Pine St', 'jackson.cooper@example.com', 'passdef', '1995-03-20', '234-567-8901', 85.50),
(26, 2, 5, 'Madison', 'Hill', '567 Maple St', 'madison.hill@example.com', 'passxyz', '1989-11-12', '345-678-9012', 95.30),
(27, 2, NULL, 'Logan', 'White', '890 Birch St', 'logan.white@example.com', 'pass123', '1992-07-05', '456-789-0123', 60.75),
(28, 2, 1, 'Abigail', 'Lee', '123 Willow St', 'abigail.lee@example.com', 'pass456', '1986-09-28', '567-890-1234', 110.50),
(29, 2, 2, 'Lucas', 'Baker', '456 Elm St', 'lucas.baker@example.com', 'pass789', '1994-08-14', '678-901-2345', 75.30),
(30, 2, 3, 'Ella', 'Turner', '789 Oak St', 'ella.turner@example.com', 'passabc', '1982-04-03', '789-012-3456', 80.75),
(31, 2, 4, 'Mason', 'Fisher', '901 Cedar St', 'mason.fisher@example.com', 'passdef', '1997-12-19', '890-123-4567', 95.50),
(32, 2, 5, 'Harper', 'Perez', '234 Maple St', 'harper.perez@example.com', 'passxyz', '1984-02-08', '901-234-5678', 110.30),
(33, 2, NULL, 'Isaac', 'Reed', '567 Pine St', 'isaac.reed@example.com', 'pass123', '1991-06-27', '012-345-6789', 65.75),
(34, 2, 1, 'Scarlett', 'Collins', '890 Elm St', 'scarlett.collins@example.com', 'pass456', '1988-03-15', '123-456-7890', 120.50),
(35, 2, NULL, 'Lily', 'Parker', '123 Birch St', 'lily.parker@example.com', 'pass123', '1994-03-21', '789-012-3456', 65.75),
(36, 2, 1, 'Caleb', 'Reed', '456 Walnut St', 'caleb.reed@example.com', 'pass456', '1988-06-15', '890-123-4567', 95.50),
(37, 2, 2, 'Ella', 'Turner', '789 Oak St', 'ella.turner@example.com', 'pass789', '1992-11-27', '901-234-5678', 80.30),
(38, 2, 3, 'Henry', 'Collins', '234 Pine St', 'henry.collins@example.com', 'passabc', '1984-01-03', '012-345-6789', 110.75),
(39, 2, 4, 'Mia', 'Baker', '567 Cedar St', 'mia.baker@example.com', 'passdef', '1997-09-19', '123-456-7890', 75.50),
(40, 3, NULL, 'Liam', 'Wilson', '123 Oak St', 'liam.wilson@example.com', 'pass123', '1990-04-18', '234-567-8901', NULL),
(41, 3, NULL, 'Chloe', 'Thompson', '456 Pine St', 'chloe.thompson@example.com', 'pass456', '1987-08-02', '345-678-9012', NULL),
(42, 3, NULL, 'Ethan', 'Clark', '789 Maple St', 'ethan.clark@example.com', 'pass789', '1993-11-12', '456-789-0123', NULL),
(43, 3, NULL, 'Aria', 'Anderson', '234 Elm St', 'aria.anderson@example.com', 'passabc', '1985-02-25', '567-890-1234', NULL),
(44, 3, NULL, 'Mason', 'Ward', '567 Cedar St', 'mason.ward@example.com', 'passdef', '1991-05-08', '678-901-2345', NULL),
(45 ,4, NULL, 'Sophia', 'Miller', '123 Oak St', 'sophia.miller@example.com', 'pass123', '1990-04-18', '234-567-8901', NULL),
(46, 4, NULL, 'Jackson', 'Barnes', '456 Pine St', 'jackson.barnes@example.com', 'pass456', '1987-08-02', '345-678-9012', NULL),
(47, 4, NULL, 'Ava', 'Carter', '789 Maple St', 'ava.carter@example.com', 'pass789', '1993-11-12', '456-789-0123', NULL),
(48, 4, NULL, 'Noah', 'Evans', '234 Elm St', 'noah.evans@example.com', 'passabc', '1985-02-25', '567-890-1234', NULL),
(49, 4, NULL, 'Emma', 'Fisher', '567 Cedar St', 'emma.fisher@example.com', 'passdef', '1991-05-08', '678-901-2345', NULL);


DROP TABLE IF EXISTS FLIGHT;
CREATE TABLE FLIGHT (
	flightID			int not null AUTO_INCREMENT,
    aircraftID			int,
    departDate			DATE,
    departTime			TIME, 
    departLocation		varchar(25),
    arriveDate			DATE,
    arriveTime			TIME, 
    arriveLocation		varchar(25),
    flightStatus		varchar(25),
    cost				float,
    meal				boolean,
    crewMember1			int,
    crewMember2			int,
    crewMember3			int,
  
	primary key (flightID),
    FOREIGN KEY (aircraftID) REFERENCES AIRCRAFT(aircraftID),
    FOREIGN KEY (crewMember1) REFERENCES ALLUSERS(userID), 
    FOREIGN KEY (crewMember2) REFERENCES ALLUSERS(userID),
    FOREIGN KEY (crewMember2) REFERENCES ALLUSERS(userID)
);

INSERT INTO FLIGHT (flightID, aircraftID, departDate, departTime, departLocation, arriveDate, arriveTime, 
arriveLocation, flightStatus, cost, meal, crewMember1, crewMember2, crewMember3) 
VALUES
(1, 1, '2023-11-27', '08:00:00', 'New York', '2023-11-27', '10:00:00', 'Los Angeles', 'On Time', 300.50, TRUE, 40, 41, 42),
(2, 2, '2023-11-28', '10:30:00', 'Los Angeles', '2023-11-28', '12:30:00', 'Chicago', 'Delayed', 250.75, FALSE, 41, 42, 43),
(3, 3, '2023-11-29', '12:00:00', 'Chicago', '2023-11-29', '14:00:00', 'Miami', 'On Time', 400.00, TRUE, 42, 43, 44),
(4, 4, '2023-11-30', '14:30:00', 'Miami', '2023-11-30', '16:30:00', 'San Francisco', 'Delayed', 350.25, FALSE, 43, 44, 40),
(5, 1, '2023-12-01', '16:00:00', 'San Francisco', '2023-12-01', '18:00:00', 'Seattle', 'On Time', 280.75, TRUE, 44, 40, 41),
(6, 2, '2023-12-02', '18:30:00', 'Seattle', '2023-12-02', '20:30:00', 'Denver', 'Delayed', 320.00, FALSE, 40, 41, 42),
(7, 3, '2023-12-03', '20:00:00', 'Denver', '2023-12-03', '22:00:00', 'Atlanta', 'On Time', 380.50, TRUE, 41, 42, 43),
(8, 4, '2023-12-04', '22:30:00', 'Atlanta', '2023-12-04', '00:30:00', 'New York', 'Delayed', 310.25, FALSE, 42, 43, 44),
(9, 1, '2023-12-05', '08:00:00', 'New York', '2023-12-05', '10:00:00', 'Los Angeles', 'On Time', 290.50, TRUE, 43, 44, 40),
(10, 2, '2023-12-06', '10:30:00', 'Los Angeles', '2023-12-06', '12:30:00', 'Chicago', 'Delayed', 260.75, FALSE, 44, 40, 41),
(11, 3, '2023-12-07', '12:00:00', 'Chicago', '2023-12-07', '14:00:00', 'Miami', 'On Time', 410.00, TRUE, 40, 41, 42),
(12, 4, '2023-12-08', '14:30:00', 'Miami', '2023-12-08', '16:30:00', 'San Francisco', 'Delayed', 360.25, FALSE, 41, 42, 43),
(13, 1, '2023-12-09', '16:00:00', 'San Francisco', '2023-12-09', '18:00:00', 'Seattle', 'On Time', 300.75, TRUE, 42, 43, 44),
(14, 2, '2023-12-10', '18:30:00', 'Seattle', '2023-12-10', '20:30:00', 'Denver', 'Delayed', 330.00, FALSE, 43, 44, 40),
(15, 3, '2023-12-11', '20:00:00', 'Denver', '2023-12-11', '22:00:00', 'Atlanta', 'On Time', 390.50, TRUE, 44, 40, 41);

DROP TABLE IF EXISTS SEAT;
CREATE TABLE SEAT (
	seatID				int not null AUTO_INCREMENT,
    aircraftID			int, 
    seatName			varchar(3), -- example: 10A, or 9B letters go from A->D
    class				varchar(25),
    cost				float,
    baggage				boolean,
    available			boolean,
  
	primary key (seatID),
    FOREIGN KEY (aircraftID) REFERENCES AIRCRAFT(aircraftID)
);

INSERT INTO SEAT (seatID, aircraftID, seatName, class, cost, baggage, available) VALUES
-- Aircraft 1: Business Class
(1, 1, '1A', 'Business Class', 500.00, true, true),
(2, 1, '1B', 'Business Class', 500.00, true, true),
(3, 1, '1C', 'Business Class', 500.00, true, true),
(4, 1, '2A', 'Business Class', 500.00, true, true),
(5, 1, '2B', 'Business Class', 500.00, true, true),
(6, 1, '2C', 'Business Class', 500.00, true, true),
(7, 1, '3A', 'Business Class', 500.00, true, true),
(8, 1, '3B', 'Business Class', 500.00, true, true),
(9, 1, '3C', 'Business Class', 500.00, true, true),
(10, 1, '4A', 'Business Class', 500.00, true, true),
(11, 1, '4B', 'Business Class', 500.00, true, true),
(12, 1, '4C', 'Business Class', 500.00, true, true),
(13, 1, '5A', 'Business Class', 500.00, true, true),
(14, 1, '5B', 'Business Class', 500.00, true, true),
(15, 1, '5C', 'Business Class', 500.00, true, true),
-- Aircraft 1: Economy Class
(16, 1, '6A', 'Economy Class', 500.00, true, true),
(17, 1, '6B', 'Economy Class', 500.00, true, true),
(18, 1, '6C', 'Economy Class', 500.00, true, true),
(19, 1, '7A', 'Economy Class', 500.00, true, true),
(20, 1, '7B', 'Economy Class', 500.00, true, true),
(21, 1, '7C', 'Economy Class', 500.00, true, true),
(22, 1, '8A', 'Economy Class', 500.00, true, true),
(23, 1, '8B', 'Economy Class', 500.00, true, true),
(24, 1, '8C', 'Economy Class', 500.00, true, true),
(25, 1, '9A', 'Economy Class', 500.00, true, true),
(26, 1, '9B', 'Economy Class', 500.00, true, true),
(27, 1, '9C', 'Economy Class', 500.00, true, true),
(28, 1, '10A', 'Economy Class', 250.00, true, true),
(29, 1, '10B', 'Economy Class', 250.00, true, true),
(30, 1, '10C', 'Economy Class', 250.00, true, true),
(31, 1, '11A', 'Economy Class', 250.00, true, true),
(32, 1, '11B', 'Economy Class', 250.00, true, true),
(33, 1, '11C', 'Economy Class', 250.00, true, true),
(34, 1, '12A', 'Economy Class', 250.00, true, true),
(35, 1, '12B', 'Economy Class', 250.00, true, true),
(36, 1, '12C', 'Economy Class', 250.00, true, true),
(37, 1, '13A', 'Economy Class', 250.00, true, true),
(38, 1, '13B', 'Economy Class', 250.00, true, true),
(39, 1, '13C', 'Economy Class', 250.00, true, true);

INSERT INTO SEAT (aircraftID, seatName, class, cost, baggage, available) VALUES
-- Aircraft 2: Business Class
(2, '1A', 'Business Class', 500.00, true, true),
(2, '1B', 'Business Class', 500.00, true, true),
(2, '1C', 'Business Class', 500.00, true, true),
(2, '2A', 'Business Class', 500.00, true, true),
(2, '2B', 'Business Class', 500.00, true, true),
(2, '2C', 'Business Class', 500.00, true, true),
(2, '3A', 'Business Class', 500.00, true, true),
(2, '3B', 'Business Class', 500.00, true, true),
(2, '3C', 'Business Class', 500.00, true, true),
(2, '4A', 'Business Class', 500.00, true, true),
(2, '4B', 'Business Class', 500.00, true, true),
(2, '4C', 'Business Class', 500.00, true, true),
(2, '5A', 'Business Class', 500.00, true, true),
(2, '5B', 'Business Class', 500.00, true, true),
(2, '5C', 'Business Class', 500.00, true, true),
-- Aircraft 2: Economy Class
(2, '6A', 'Economy Class', 500.00, true, true),
(2, '6B', 'Economy Class', 500.00, true, true),
(2, '6C', 'Economy Class', 500.00, true, true),
(2, '7A', 'Economy Class', 500.00, true, true),
(2, '7B', 'Economy Class', 500.00, true, true),
(2, '7C', 'Economy Class', 500.00, true, true),
(2, '8A', 'Economy Class', 500.00, true, true),
(2, '8B', 'Economy Class', 500.00, true, true),
(2, '8C', 'Economy Class', 500.00, true, true),
(2, '9A', 'Economy Class', 500.00, true, true),
(2, '9B', 'Economy Class', 500.00, true, true),
(2, '9C', 'Economy Class', 500.00, true, true),
(2, '10A', 'Economy Class', 250.00, true, true),
(2, '10B', 'Economy Class', 250.00, true, true),
(2, '10C', 'Economy Class', 250.00, true, true),
(2, '11A', 'Economy Class', 250.00, true, true),
(2, '11B', 'Economy Class', 250.00, true, true),
(2, '11C', 'Economy Class', 250.00, true, true),
(2, '12A', 'Economy Class', 250.00, true, true),
(2, '12B', 'Economy Class', 250.00, true, true),
(2, '12C', 'Economy Class', 250.00, true, true),
(2, '13A', 'Economy Class', 250.00, true, true),
(2, '13B', 'Economy Class', 250.00, true, true),
(2, '13C', 'Economy Class', 250.00, true, true),
(2, '14A', 'Economy Class', 250.00, true, true),
(2, '14B', 'Economy Class', 250.00, true, true),
(2, '14C', 'Economy Class', 250.00, true, true),
(2, '15A', 'Economy Class', 250.00, true, true),
(2, '15B', 'Economy Class', 250.00, true, true),
(2, '15C', 'Economy Class', 250.00, true, true),
(2, '16A', 'Economy Class', 250.00, true, true),
(2, '16B', 'Economy Class', 250.00, true, true),
(2, '16C', 'Economy Class', 250.00, true, true),

-- Aircraft 3: Business Class
(3, '1A', 'Business Class', 500.00, true, true),
(3, '1B', 'Business Class', 500.00, true, true),
(3, '1C', 'Business Class', 500.00, true, true),
(3, '2A', 'Business Class', 500.00, true, true),
(3, '2B', 'Business Class', 500.00, true, true),
(3, '2C', 'Business Class', 500.00, true, true),
(3, '3A', 'Business Class', 500.00, true, true),
(3, '3B', 'Business Class', 500.00, true, true),
(3, '3C', 'Business Class', 500.00, true, true),
(3, '4A', 'Business Class', 500.00, true, true),
(3, '4B', 'Business Class', 500.00, true, true),
(3, '4C', 'Business Class', 500.00, true, true),
(3, '5A', 'Business Class', 500.00, true, true),
(3, '5B', 'Business Class', 500.00, true, true),
(3, '5C', 'Business Class', 500.00, true, true),
-- Aircraft 3: Economy Class
(3, '6A', 'Economy Class', 500.00, true, true),
(3, '6B', 'Economy Class', 500.00, true, true),
(3, '6C', 'Economy Class', 500.00, true, true),
(3, '7A', 'Economy Class', 500.00, true, true),
(3, '7B', 'Economy Class', 500.00, true, true),
(3, '7C', 'Economy Class', 500.00, true, true),
(3, '8A', 'Economy Class', 500.00, true, true),
(3, '8B', 'Economy Class', 500.00, true, true),
(3, '8C', 'Economy Class', 500.00, true, true),
(3, '9A', 'Economy Class', 500.00, true, true),
(3, '9B', 'Economy Class', 500.00, true, true),
(3, '9C', 'Economy Class', 500.00, true, true),
(3, '10A', 'Economy Class', 250.00, true, true),
(3, '10B', 'Economy Class', 250.00, true, true),
(3, '10C', 'Economy Class', 250.00, true, true),
(3, '11A', 'Economy Class', 250.00, true, true),
(3, '11B', 'Economy Class', 250.00, true, true),
(3, '11C', 'Economy Class', 250.00, true, true),
(3, '12A', 'Economy Class', 250.00, true, true),
(3, '12B', 'Economy Class', 250.00, true, true),
(3, '12C', 'Economy Class', 250.00, true, true),
(3, '13A', 'Economy Class', 250.00, true, true),
(3, '13B', 'Economy Class', 250.00, true, true),
(3, '13C', 'Economy Class', 250.00, true, true),
(3, '14A', 'Economy Class', 250.00, true, true),
(3, '14B', 'Economy Class', 250.00, true, true),
(3, '14C', 'Economy Class', 250.00, true, true),
(3, '15A', 'Economy Class', 250.00, true, true),
(3, '15B', 'Economy Class', 250.00, true, true),
(3, '15C', 'Economy Class', 250.00, true, true),
(3, '16A', 'Economy Class', 250.00, true, true),
(3, '16B', 'Economy Class', 250.00, true, true),
(3, '16C', 'Economy Class', 250.00, true, true),

-- Aircraft 4: Business Class
(4, '1A', 'Business Class', 500.00, true, true),
(4, '1B', 'Business Class', 500.00, true, true),
(4, '1C', 'Business Class', 500.00, true, true),
(4, '2A', 'Business Class', 500.00, true, true),
(4, '2B', 'Business Class', 500.00, true, true),
(4, '2C', 'Business Class', 500.00, true, true),
(4, '3A', 'Business Class', 500.00, true, true),
(4, '3B', 'Business Class', 500.00, true, true),
(4, '3C', 'Business Class', 500.00, true, true),
(4, '4A', 'Business Class', 500.00, true, true),
(4, '4B', 'Business Class', 500.00, true, true),
(4, '4C', 'Business Class', 500.00, true, true),
(4, '5A', 'Business Class', 500.00, true, true),
(4, '5B', 'Business Class', 500.00, true, true),
(4, '5C', 'Business Class', 500.00, true, true),
-- Aircraft 4: Economy Class
(4, '6A', 'Economy Class', 500.00, true, true),
(4, '6B', 'Economy Class', 500.00, true, true),
(4, '6C', 'Economy Class', 500.00, true, true),
(4, '7A', 'Economy Class', 500.00, true, true),
(4, '7B', 'Economy Class', 500.00, true, true),
(4, '7C', 'Economy Class', 500.00, true, true),
(4, '8A', 'Economy Class', 500.00, true, true),
(4, '8B', 'Economy Class', 500.00, true, true),
(4, '8C', 'Economy Class', 500.00, true, true),
(4, '9A', 'Economy Class', 500.00, true, true),
(4, '9B', 'Economy Class', 500.00, true, true),
(4, '9C', 'Economy Class', 500.00, true, true),
(4, '10A', 'Economy Class', 250.00, true, true),
(4, '10B', 'Economy Class', 250.00, true, true),
(4, '10C', 'Economy Class', 250.00, true, true),
(4, '11A', 'Economy Class', 250.00, true, true),
(4, '11B', 'Economy Class', 250.00, true, true),
(4, '11C', 'Economy Class', 250.00, true, true),
(4, '12A', 'Economy Class', 250.00, true, true),
(4, '12B', 'Economy Class', 250.00, true, true),
(4, '12C', 'Economy Class', 250.00, true, true),
(4, '13A', 'Economy Class', 250.00, true, true),
(4, '13B', 'Economy Class', 250.00, true, true),
(4, '13C', 'Economy Class', 250.00, true, true),
(4, '14A', 'Economy Class', 250.00, true, true),
(4, '14B', 'Economy Class', 250.00, true, true),
(4, '14C', 'Economy Class', 250.00, true, true),
(4, '15A', 'Economy Class', 250.00, true, true),
(4, '15B', 'Economy Class', 250.00, true, true),
(4, '15C', 'Economy Class', 250.00, true, true),
(4, '16A', 'Economy Class', 250.00, true, true),
(4, '16B', 'Economy Class', 250.00, true, true),
(4, '16C', 'Economy Class', 250.00, true, true);

DROP TABLE IF EXISTS TICKET;
CREATE TABLE TICKET (
    ticketNumber		int not null AUTO_INCREMENT,
    aircraftID			int,
    flightID 			int,
    seatID				int,
    userID				int,
  
	primary key (ticketNumber),
    FOREIGN KEY (flightID) REFERENCES FLIGHT(flightID),
    FOREIGN KEY (aircraftID) REFERENCES AIRCRAFT(aircraftID),
    FOREIGN KEY (seatID) REFERENCES SEAT(seatID),
    FOREIGN KEY (userID) REFERENCES ALLUSERS(userID)
);

-- Inserting fake values into TICKET table
-- INSERT INTO TICKET (aircraftID, flightID, seatID, userID)
-- SELECT 
-- 	aircraftID,
--     FLOOR(RAND() * (SELECT MAX(flightID) FROM FLIGHT) + 1) as flightID,
--     seatID,
--     FLOOR(RAND() * (SELECT MAX(userID) FROM ALLUSERS) + 1) as userID
-- FROM SEAT 
-- ORDER BY RAND()

INSERT INTO TICKET (aircraftID, flightID, seatID, userID) VALUES
(4,8,128,2),
(1,1,5,19),
(2,6,50,32),
(1,5,13,27),
(2,6,71,42),
(3,11,102,32),
(3,3,114,21),
(2,10,79,11),
(1,13,21,29),
(3,11,87,46),
(1,1,24,20),
(2,10,66,14),
(3,15,120,20),
(2,14,72,10),
(2,14,52,40),
(2,6,60,36),
(2,14,76,25),
(4,8,138,14),
(3,11,98,9),
(4,8,134,5),
(3,7,126,23),
(2,6,84,20),
(2,2,40,27),
(4,12,156,6),
(4,4,137,20),
(2,6,55,7),
(3,7,92,42),
(3,3,112,24),
(1,13,36,49),
(2,6,42,30),
(4,8,160,9),
(3,11,117,41),
(4,4,152,31),
(3,3,103,9),
(1,5,34,45),
(3,11,86,35),
(4,8,168,13),
(2,10,67,15),
(4,12,164,2),
(4,12,161,1),
(1,1,32,34),
(2,6,81,1),
(1,5,6,21),
(2,14,68,37),
(4,8,145,24),
(1,5,3,35),
(1,13,7,19),
(3,11,108,29),
(3,3,101,46),
(3,7,91,5),
(1,9,1,31),
(4,12,148,11),
(3,3,107,27),
(4,12,153,33),
(2,14,43,47),
(2,10,54,1),
(3,11,104,27),
(3,3,90,39),
(2,6,61,36),
(2,6,64,36),
(3,15,124,42),
(1,1,8,16),
(3,3,125,17),
(1,9,35,22),
(1,1,18,44),
(2,14,53,4),
(4,4,139,15),
(2,6,51,37),
(4,4,159,38),
(1,13,10,42),
(3,7,123,7),
(4,4,166,5),
(1,5,28,32),
(4,4,133,17),
(4,12,149,8),
(4,8,154,18),
(3,11,118,6),
(4,8,131,6),
(1,9,2,20),
(1,1,37,2),
(2,6,65,12),
(4,12,140,12),
(2,14,45,49),
(4,4,163,19),
(3,15,95,48),
(1,1,23,37),
(1,5,39,24),
(4,8,146,45),
(3,11,115,41),
(3,15,109,17),
(2,10,78,46),
(1,5,31,39),
(4,4,158,32),
(1,9,4,3),
(4,8,143,30),
(1,1,17,15),
(1,5,25,8),
(4,8,147,32),
(3,11,121,23),
(1,13,38,5),
(4,8,150,17),
(3,3,89,29),
(4,4,165,38),
(2,14,80,1),
(4,8,127,27),
(1,9,15,45),
(3,7,110,41),
(3,3,116,18),
(4,8,142,21),
(3,3,106,12),
(1,5,12,19),
(3,7,85,44),
(3,7,100,10),
(3,15,105,36),
(2,14,49,35),
(4,8,130,25),
(3,3,122,2),
(1,9,33,40),
(1,5,11,21),
(4,8,151,13),
(2,14,44,13),
(1,1,27,7),
(4,4,155,13),
(2,6,77,38),
(3,11,99,29),
(1,5,16,32),
(1,9,19,47),
(1,13,29,3),
(3,11,113,28),
(4,4,162,25),
(4,12,167,26),
(4,8,132,29),
(2,6,58,23),
(3,11,96,21),
(2,10,83,30),
(4,4,136,28),
(3,7,92,24),
(3,11,119,21),
(4,4,157,27),
(4,12,141,48),
(1,1,9,39),
(1,13,22,47),
(2,2,82,44),
(1,13,20,15),
(4,4,129,5),
(1,5,14,29),
(3,3,94,23),
(3,7,97,14),
(3,15,88,44),
(4,12,135,19),
(4,4,144,37),
(1,9,30,49),
(3,3,111,27),
(4,4,149,9),
(4,12,140,37),
(3,3,106,3),
(3,11,92,5),
(4,8,144,23),
(1,9,8,13),
(4,8,154,46),
(1,9,36,40),
(2,6,49,19),
(3,15,108,17),
(4,8,137,33),
(4,8,136,29),
(2,10,57,28),
(1,1,4,11),
(3,7,103,29),
(4,8,161,5),
(4,4,146,12),
(2,6,78,7),
(2,10,52,9),
(4,4,156,38),
(1,1,3,41),
(3,3,124,44),
(4,4,139,18),
(3,7,85,10),
(2,14,84,17),
(4,8,133,26),
(4,12,130,29),
(2,6,68,23),
(2,14,66,41),
(2,10,65,41),
(3,15,91,22),
(1,9,25,44),
(1,5,10,12),
(1,1,6,19),
(2,6,81,4),
(4,4,157,39),
(2,2,51,49),
(1,9,9,48),
(2,14,47,30),
(3,11,94,41),
(1,1,20,46),
(1,5,34,1),
(1,5,15,33),
(4,4,129,30),
(3,11,114,6),
(2,2,75,17),
(2,2,42,33),
(2,2,64,2),
(1,1,11,35),
(2,10,43,19),
(4,4,160,19),
(2,14,67,9),
(3,7,95,7),
(1,5,30,7),
(3,11,87,29),
(1,9,23,41),
(1,5,5,48),
(2,6,58,30),
(4,12,150,37),
(3,3,122,22),
(4,12,163,13),
(2,14,56,20),
(2,10,83,1),
(2,14,53,15),
(4,12,132,10),
(1,1,13,45),
(1,9,28,46),
(4,8,143,27),
(1,9,38,19),
(4,8,147,11),
(3,7,121,14),
(2,6,55,27),
(1,1,1,31),
(3, 3, 119, 37),
(2, 6, 76, 31),
(3, 11, 98, 18),
(4, 4, 166, 8),
(4, 12, 165, 9),
(3, 3, 86, 1),
(4, 12, 148, 20),
(3, 11, 110, 44),
(4, 12, 131, 14),
(1, 13, 18, 37),
(2, 2, 62, 35),
(1, 9, 24, 2),
(4, 12, 138, 26),
(2, 2, 59, 28),
(2, 2, 48, 33),
(3, 7, 112, 6),
(3, 7, 97, 18),
(4, 8, 151, 14),
(4, 8, 168, 49),
(4, 4, 153, 27),
(3, 15, 120, 31),
(3, 15, 90, 39),
(3, 7, 104, 13),
(2, 14, 60, 33),
(2, 2, 74, 26),
(4, 12, 162, 5),
(4, 12, 142, 43),
(3, 7, 115, 42),
(2, 2, 69, 41),
(2, 14, 41, 22),
(3, 7, 100, 41),
(1, 9, 17, 38),
(1, 5, 33, 2),
(4, 4, 127, 25),
(3, 7, 109, 13),
(2, 10, 61, 25),
(4, 4, 155, 38),
(2, 2, 71, 32),
(2, 2, 82, 39),
(3, 3, 113, 16),
(3, 3, 102, 27),
(3, 11, 101, 49),
(2, 2, 70, 11),
(4, 8, 141, 38),
(3, 11, 123, 21),
(2, 6, 77, 46),
(2, 6, 44, 35),
(1, 1, 37, 14),
(4, 12, 152, 37),
(1, 9, 39, 9),
(4, 12, 158, 8),
(2, 14, 73, 19),
(1, 5, 22, 43),
(3, 11, 89, 37),
(1, 13, 29, 18),
(2, 10, 54, 9),
(2, 2, 72, 3),
(1, 9, 32, 44),
(3, 3, 116, 3),
(4, 4, 128, 21),
(1, 1, 7, 9),
(1, 1, 35, 31),
(2, 10, 50, 23),
(3, 7, 126, 13),
(1, 13, 16, 24),
(3, 7, 105, 34),
(2, 6, 46, 14),
(3, 15, 96, 43),
(4, 8, 167, 32),
(3, 11, 93, 24);


SET SQL_SAFE_UPDATES = 0;
-- UPDATE TICKET
-- SET flightID = CASE
--     WHEN aircraftID = 1 AND flightID NOT IN (1, 5, 9, 13) THEN
--         (SELECT FLOOR(1 + RAND() * 4) * 4 - 3)
--     WHEN aircraftID = 2 AND flightID NOT IN (2, 6, 10, 14) THEN
--         (SELECT FLOOR(1 + RAND() * 4) * 4 - 2)
--     WHEN aircraftID = 3 AND flightID NOT IN (3, 7, 11, 15) THEN
--         (SELECT FLOOR(1 + RAND() * 4) * 4 - 1)
--     WHEN aircraftID = 4 AND flightID NOT IN (4, 8, 12) THEN
--         (SELECT FLOOR(1 + RAND() * 3) * 4)
--     ELSE flightID
-- END;

UPDATE SEAT
SET available = false
WHERE seatID IN (SELECT seatID FROM ticket);

UPDATE SEAT
SET available = true
WHERE seatID NOT IN (SELECT seatID FROM ticket);