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
  street VARCHAR(100),
  building INT,

  FOREIGN KEY (city_id) REFERENCES City(id)
);

CREATE TABLE Route (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(500) NOT NULL,
);

 CREATE TABLE Photo (
   id INT AUTO_INCREMENT PRIMARY KEY,
   photo BLOB
 );

CREATE TABLE Traveller (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  date_of_birth DATE,
  current_avatar INT,
  current_city INT,
  email VARCHAR(100),

  FOREIGN KEY (current_avatar) REFERENCES Photo (id),
  FOREIGN KEY (current_city) REFERENCES City (id),
  UNIQUE (email)
);

CREATE TABLE Friend (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_traveller_id INT,
  second_traveller_id INT,

  FOREIGN KEY (first_traveller_id) REFERENCES Traveller (id),
  FOREIGN KEY (second_traveller_id) REFERENCES Traveller (id),
);

CREATE TABLE PersonalRoute (
  id INT AUTO_INCREMENT PRIMARY KEY,
  city_id INT,
  traveller_id INT,
  route_id INT,
  start DATE,
  end DATE,

  FOREIGN KEY (city_id) REFERENCES City(id),
  FOREIGN KEY (traveller_id) REFERENCES Traveller(id),
  FOREIGN KEY (route_id) REFERENCES Route(id)
);

CREATE TABLE Conversation (
  id INT AUTO_INCREMENT PRIMARY KEY,
  friend_id INT,
  message VARCHAR(1000),

  FOREIGN KEY (friend_id) REFERENCES Friend(id)
);

CREATE TABLE Blog (
  id INT AUTO_INCREMENT PRIMARY KEY,
  traveller_id INT,
  sight_id INT,
  description VARCHAR(3000),

  FOREIGN KEY (traveller_id) REFERENCES Traveller (id),
  FOREIGN KEY (sight_id) REFERENCES Sight (id)
);