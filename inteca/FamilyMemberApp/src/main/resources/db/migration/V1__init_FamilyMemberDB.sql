CREATE TABLE Family_Member (
  id INT NOT NULL AUTO_INCREMENT,
  family_Id INT NOT NULL,
  family_Name VARCHAR(45) NOT NULL,
  given_Name VARCHAR(45) NOT NULL,
  age INT NOT NULL,
  PRIMARY KEY (id));