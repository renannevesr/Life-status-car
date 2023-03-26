CREATE TABLE revision_car_items_limits (
  id INT NOT NULL AUTO_INCREMENT,
  id_car INT NOT NULL,
  item VARCHAR(50) NOT NULL,
  limit_km INT NOT NULL,
  limit_date_months INT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (id_car) REFERENCES cars (id) ON DELETE CASCADE
);