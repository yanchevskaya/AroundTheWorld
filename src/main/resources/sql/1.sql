CREATE TABLE Country (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE City (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  country_id INT,

  FOREIGN KEY (country_id) REFERENCES Country(id)
);

CREATE TABLE Sight (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  city_id INT,
  street VARCHAR(100) NOT NULL,
  building INT,

  FOREIGN KEY (city_id) REFERENCES City(id)
);

 CREATE TABLE Photo (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(50),
   photo VARCHAR(50)
 );

CREATE TABLE Gender (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20)
);

CREATE TABLE Traveller (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL ,
  last_name VARCHAR(50) NOT NULL ,
  gender_id INT,
  date_of_birth DATE,
  current_avatar INT,
  current_city INT,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,

  FOREIGN KEY (gender_id) REFERENCES Gender (id),
  FOREIGN KEY (current_avatar) REFERENCES Photo (id),
  FOREIGN KEY (current_city) REFERENCES City (id),
  UNIQUE (email)
);

CREATE TABLE Route (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(500) NOT NULL,
  traveller_id INT,
  description VARCHAR(3000),

  FOREIGN KEY (traveller_id) REFERENCES Traveller(id)
);

CREATE TABLE Friend (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_traveller_id INT,
  second_traveller_id INT,

  FOREIGN KEY (first_traveller_id) REFERENCES Traveller (id),
  FOREIGN KEY (second_traveller_id) REFERENCES Traveller (id)
);

CREATE TABLE PersonalRoute (
  id INT AUTO_INCREMENT PRIMARY KEY,
  city_id INT,
  route_id INT,

  FOREIGN KEY (city_id) REFERENCES City(id),
  FOREIGN KEY (route_id) REFERENCES Route(id)
);

CREATE TABLE Conversation (
  id INT AUTO_INCREMENT PRIMARY KEY,
  friend_id INT,
  message VARCHAR(1000) NOT NULL,

  FOREIGN KEY (friend_id) REFERENCES Friend(id)
);

CREATE TABLE Blog (
  id INT AUTO_INCREMENT PRIMARY KEY,
  traveller_id INT,
  sight_id INT,
  description VARCHAR(3000) NOT NULL,

  FOREIGN KEY (traveller_id) REFERENCES Traveller (id),
  FOREIGN KEY (sight_id) REFERENCES Sight (id)
);