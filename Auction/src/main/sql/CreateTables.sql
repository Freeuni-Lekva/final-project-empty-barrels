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
  user_name         VARCHAR(100) NOT NULL UNIQUE,
  password          VARCHAR(80) NOT NULL, -- 100 because we will save SHA-256 HASHes (they are 64-char long)
  is_dealer         BOOLEAN NOT NULL,
  is_admin          BOOLEAN NOT NULL,
  auctions_won      INT NOT NULL DEFAULT 0,
  rating            INT NOT NULL DEFAULT 0,
  num_reviews       INT NOT NULL DEFAULT 0,
  PRIMARY KEY (ID),
  FOREIGN KEY (user_info_ID) REFERENCES UserInfo(ID)
);
