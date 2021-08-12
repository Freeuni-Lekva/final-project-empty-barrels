CREATE TABLE UserInfo(
  ID                INT NOT NULL AUTO_INCREMENT,
  first_name        VARCHAR(50) NOT NULL,
  last_name         VARCHAR(70) NOT NULL,
  email             VARCHAR(100) NOT NULL,
  address           VARCHAR(150), -- we'll make mandatory fields NOT NULL for safety
  phone_number      VARCHAR(15) NOT NULL,
  note              VARCHAR(150),
  PRIMARY KEY (ID)
);

CREATE TABLE User(
  ID                INT NOT NULL AUTO_INCREMENT,
  user_info_ID      INT NOT NULL,
  user_name         VARCHAR(100) NOT NULL,
  password          VARCHAR(500) NOT NULL, -- 500 because we will probably save HASHes
  is_dealer         BOOLEAN NOT NULL,
  is_admin          BOOLEAN NOT NULL,
  auctions_won      INT NOT NULL DEFAULT 0,
  rating            INT NOT NULL DEFAULT 0,
  num_reviews       INT NOT NULL DEFAULT 0,
  PRIMARY KEY (ID),
  FOREIGN KEY (user_info_ID) REFERENCES UserInfo(ID)
);
