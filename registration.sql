USE test;

CREATE TABLE IF NOT EXISTS students (
  id int(8) NOT NULL,
  name varchar(60) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO students (id, name) VALUES
(1, 'Bucky Roberts'),
(2, 'Noah Parker'),
(3, 'Kelsey Burger'),
(4, 'Corey Smith'),
(5, 'Harry Potter'),
(6, 'Henry Jackson'),
(7, 'Cynthia Alvarez'),
(8, 'Teresa Smith'),
(9, 'Gary Foster'),
(10, 'Sherry Gibbons');

CREATE TABLE IF NOT EXISTS courses (
	id varchar(8) NOT NULL, 
	name varchar(40) NOT NULL, 
	day varchar(1), 
	start varchar(5), 
	end varchar(5),
	seats int(3),
	instructor varchar(20),
	building varchar(16),
	room varchar(4),
	PRIMARY KEY (id)
);

INSERT INTO courses VALUES
('CSE101', 'Introduction to computer science', 'M', '9:30', '11:20', 10, 'Bill Gates', 'Lockwood Library', '201'),
('BIO118', 'Anatomy', 'W', '13:00', '15:30', 5, 'Tim Cook', 'Rogers Hall', '132'),
('MTH421', 'Discrete numbers', 'M', '9:15', '10:00', 5, 'Mark Zuckerberg', 'Law Library', '201'),
('GEO206', 'Geographic data analysis', 'W', '12:00', '13:00', 5, 'Jun Lei', 'Knox Hall', '115'),
('STA307', 'Linear regression', 'F', '11:00', '12:30', 3, 'Yun Ma', 'Knox Hall', '115');

CREATE TABLE IF NOT EXISTS registration (class varchar(8) NOT NULL, student int(8) NOT NULL);