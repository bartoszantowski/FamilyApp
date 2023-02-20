CREATE TABLE FamilyDB.family_seq (next_val INT NOT NULL);
INSERT INTO family_seq VALUES (1);

CREATE TABLE FamilyDB.Family (
  id INT NOT NULL AUTO_INCREMENT,
  family_Name VARCHAR(45) NOT NULL,
  nr_Of_Adults INT NOT NULL,
  nr_Of_Children INT NOT NULL,
  nr_Of_Infants INT NOT NULL,
  PRIMARY KEY (id));