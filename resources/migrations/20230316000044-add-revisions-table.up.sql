CREATE TABLE revisions (
  ID INT NOT NULL AUTO_INCREMENT,
  id_car INT NOT NULL,
  last_revision_date DATE NOT NULL,
  created_at DATE NOT NULL,
  km INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (id_car) REFERENCES cars (id)
);