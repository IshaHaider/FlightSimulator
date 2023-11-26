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
    FOREIGN KEY (aircraftID) REFERENCES AIRCRAFT(aircraftID)
);

INSERT INTO FLIGHT VALUES
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
(16, 1, '6A', 'Business Class', 500.00, true, true),
(17, 1, '6B', 'Business Class', 500.00, true, true),
(18, 1, '6C', 'Business Class', 500.00, true, true),
(19, 1, '7A', 'Business Class', 500.00, true, true),
(20, 1, '7B', 'Business Class', 500.00, true, true),
(21, 1, '7C', 'Business Class', 500.00, true, true),
(22, 1, '8A', 'Business Class', 500.00, true, true),
(23, 1, '8B', 'Business Class', 500.00, true, true),
(24, 1, '8C', 'Business Class', 500.00, true, true),
(25, 1, '9A', 'Business Class', 500.00, true, true),
(26, 1, '9B', 'Business Class', 500.00, true, true),
(27, 1, '9C', 'Business Class', 500.00, true, true),
-- Aircraft 1: Economy Class
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
(39, 1, '13C', 'Economy Class', 250.00, true, true),

-- Aircraft 2: Business Class
(40, 2, '1A', 'Business Class', 500.00, true, true),
(41, 2, '1B', 'Business Class', 500.00, true, true),
(42, 2, '1C', 'Business Class', 500.00, true, true),
(43, 2, '2A', 'Business Class', 500.00, true, true),
(44, 2, '2B', 'Business Class', 500.00, true, true),
(45, 2, '2C', 'Business Class', 500.00, true, true),
(46, 2, '3A', 'Business Class', 500.00, true, true),
(47, 2, '3B', 'Business Class', 500.00, true, true),
(48, 2, '3C', 'Business Class', 500.00, true, true),
(49, 2, '4A', 'Business Class', 500.00, true, true),
(50, 2, '4B', 'Business Class', 500.00, true, true),
(51, 2, '4C', 'Business Class', 500.00, true, true),
(52, 2, '5A', 'Business Class', 500.00, true, true),
(53, 2, '5B', 'Business Class', 500.00, true, true),
(54, 2, '5C', 'Business Class', 500.00, true, true),
(55, 2, '6A', 'Business Class', 500.00, true, true),
(56, 2, '6B', 'Business Class', 500.00, true, true),
(57, 2, '6C', 'Business Class', 500.00, true, true),
(58, 2, '7A', 'Business Class', 500.00, true, true),
(59, 2, '7B', 'Business Class', 500.00, true, true),
(60, 2, '7C', 'Business Class', 500.00, true, true),
(61, 2, '8A', 'Business Class', 500.00, true, true),
(62, 2, '8B', 'Business Class', 500.00, true, true),
(63, 2, '8C', 'Business Class', 500.00, true, true),
(64, 2, '9A', 'Business Class', 500.00, true, true),
(65, 2, '9B', 'Business Class', 500.00, true, true),
(66, 2, '9C', 'Business Class', 500.00, true, true),
-- Aircraft 2: Economy Class
(67, 2, '10A', 'Economy Class', 250.00, true, true),
(68, 2, '10B', 'Economy Class', 250.00, true, true),
(69, 2, '10C', 'Economy Class', 250.00, true, true),
(70, 2, '11A', 'Economy Class', 250.00, true, true),
(71, 2, '11B', 'Economy Class', 250.00, true, true),
(72, 2, '11C', 'Economy Class', 250.00, true, true),
(73, 2, '12A', 'Economy Class', 250.00, true, true),
(74, 2, '12B', 'Economy Class', 250.00, true, true),
(75, 2, '12C', 'Economy Class', 250.00, true, true),
(76, 2, '13A', 'Economy Class', 250.00, true, true),
(77, 2, '13B', 'Economy Class', 250.00, true, true),
(78, 2, '13C', 'Economy Class', 250.00, true, true),
(79, 2, '14A', 'Economy Class', 250.00, true, true),
(80, 2, '14B', 'Economy Class', 250.00, true, true),
(81, 2, '14C', 'Economy Class', 250.00, true, true),
(82, 2, '15A', 'Economy Class', 250.00, true, true),
(83, 2, '15B', 'Economy Class', 250.00, true, true),
(84, 2, '15C', 'Economy Class', 250.00, true, true),
-- Aircraft 3: Business Class
(85, 3, '1A', 'Business Class', 500.00, true, true),
(86, 3, '1B', 'Business Class', 500.00, true, true),
(87, 3, '2A', 'Business Class', 500.00, true, true),
(88, 3, '2B', 'Business Class', 500.00, true, true),
(89, 3, '3A', 'Business Class', 500.00, true, true),
(90, 3, '3B', 'Business Class', 500.00, true, true),
(91, 3, '4A', 'Business Class', 500.00, true, true),
(92, 3, '4B', 'Business Class', 500.00, true, true),
(93, 3, '5A', 'Business Class', 500.00, true, true),
(94, 3, '5B', 'Business Class', 500.00, true, true),
(95, 3, '6A', 'Business Class', 500.00, true, true),
(96, 3, '6B', 'Business Class', 500.00, true, true),
(97, 3, '7A', 'Business Class', 500.00, true, true),
(98, 3, '7B', 'Business Class', 500.00, true, true),
(99, 3, '8A', 'Business Class', 500.00, true, true),
(100, 3, '8B', 'Business Class', 500.00, true, true),
(101, 3, '9A', 'Business Class', 500.00, true, true),
(102, 3, '9B', 'Business Class', 500.00, true, true),
-- Aircraft 3: Economy Class
(103, 3, '10A', 'Economy Class', 250.00, true, true),
(104, 3, '10B', 'Economy Class', 250.00, true, true),
(105, 3, '10C', 'Economy Class', 250.00, true, true),
(106, 3, '10D', 'Economy Class', 250.00, true, true),
(107, 3, '11A', 'Economy Class', 250.00, true, true),
(108, 3, '11B', 'Economy Class', 250.00, true, true),
(109, 3, '11C', 'Economy Class', 250.00, true, true),
(110, 3, '11D', 'Economy Class', 250.00, true, true),
(111, 3, '12A', 'Economy Class', 250.00, true, true),
(112, 3, '12B', 'Economy Class', 250.00, true, true),
(113, 3, '12C', 'Economy Class', 250.00, true, true),
(114, 3, '12D', 'Economy Class', 250.00, true, true),
(115, 3, '13A', 'Economy Class', 250.00, true, true),
(116, 3, '13B', 'Economy Class', 250.00, true, true),
(117, 3, '13C', 'Economy Class', 250.00, true, true),
(118, 3, '13D', 'Economy Class', 250.00, true, true),
(119, 3, '14A', 'Economy Class', 250.00, true, true),
(120, 3, '14B', 'Economy Class', 250.00, true, true),
(121, 3, '14C', 'Economy Class', 250.00, true, true),
(122, 3, '14D', 'Economy Class', 250.00, true, true),
(123, 3, '15A', 'Economy Class', 250.00, true, true),
(124, 3, '15B', 'Economy Class', 250.00, true, true),
(125, 3, '15C', 'Economy Class', 250.00, true, true),
(126, 3, '15D', 'Economy Class', 250.00, true, true),

-- Aircraft 4: Business Class
(127, 4, '1A', 'Business Class', 500.00, true, true),
(128, 4, '1B', 'Business Class', 500.00, true, true),
(129, 4, '2A', 'Business Class', 500.00, true, true),
(130, 4, '2B', 'Business Class', 500.00, true, true),
(131, 4, '3A', 'Business Class', 500.00, true, true),
(132, 4, '3B', 'Business Class', 500.00, true, true),
(133, 4, '4A', 'Business Class', 500.00, true, true),
(134, 4, '4B', 'Business Class', 500.00, true, true),
(135, 4, '5A', 'Business Class', 500.00, true, true),
(136, 4, '5B', 'Business Class', 500.00, true, true),
(137, 4, '6A', 'Business Class', 500.00, true, true),
(138, 4, '6B', 'Business Class', 500.00, true, true),
(139, 4, '7A', 'Business Class', 500.00, true, true),
(140, 4, '7B', 'Business Class', 500.00, true, true),
(141, 4, '8A', 'Business Class', 500.00, true, true),
(142, 4, '8B', 'Business Class', 500.00, true, true),
(143, 4, '9A', 'Business Class', 500.00, true, true),
(144, 4, '9B', 'Business Class', 500.00, true, true),
-- Aircraft 4: Economy Class
(145, 4, '10A', 'Economy Class', 250.00, true, true),
(146, 4, '10B', 'Economy Class', 250.00, true, true),
(147, 4, '10C', 'Economy Class', 250.00, true, true),
(148, 4, '10D', 'Economy Class', 250.00, true, true),
(149, 4, '11A', 'Economy Class', 250.00, true, true),
(150, 4, '11B', 'Economy Class', 250.00, true, true),
(151, 4, '11C', 'Economy Class', 250.00, true, true),
(152, 4, '11D', 'Economy Class', 250.00, true, true),
(153, 4, '12A', 'Economy Class', 250.00, true, true),
(154, 4, '12B', 'Economy Class', 250.00, true, true),
(155, 4, '12C', 'Economy Class', 250.00, true, true),
(156, 4, '12D', 'Economy Class', 250.00, true, true),
(157, 4, '13A', 'Economy Class', 250.00, true, true),
(158, 4, '13B', 'Economy Class', 250.00, true, true),
(159, 4, '13C', 'Economy Class', 250.00, true, true),
(160, 4, '13D', 'Economy Class', 250.00, true, true),
(161, 4, '14A', 'Economy Class', 250.00, true, true),
(162, 4, '14B', 'Economy Class', 250.00, true, true),
(163, 4, '14C', 'Economy Class', 250.00, true, true),
(164, 4, '14D', 'Economy Class', 250.00, true, true),
(165, 4, '15A', 'Economy Class', 250.00, true, true),
(166, 4, '15B', 'Economy Class', 250.00, true, true),
(167, 4, '15C', 'Economy Class', 250.00, true, true),
(168, 4, '15D', 'Economy Class', 250.00, true, true);

DROP TABLE IF EXISTS TICKET;
CREATE TABLE TICKET (
    ticketNumber		int not null AUTO_INCREMENT,
    flightID			int,
    seatID				int,
    userID				int,
  
	primary key (ticketNumber),
    FOREIGN KEY (flightID) REFERENCES FLIGHT(flightID),
    FOREIGN KEY (seatID) REFERENCES SEAT(seatID),
    FOREIGN KEY (userID) REFERENCES ALLUSERS(userID)
);

-- Inserting fake values into TICKET table
INSERT INTO TICKET (flightID, seatID, userID)
SELECT 
    FLOOR(RAND() * (SELECT MAX(flightID) FROM FLIGHT) + 1) as flightID,
    FLOOR(RAND() * (SELECT MAX(seatID) FROM SEAT) + 1) as seatID,
    FLOOR(RAND() * (SELECT MAX(userID) FROM ALLUSERS) + 1) as userID

FROM INFORMATION_SCHEMA.TABLES;

