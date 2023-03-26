-- INSERT A NEW CAR
-- :name create-car :! :n
-- :doc Insert a new car into the cars table
INSERT INTO cars (model, year, brand)
VALUES (:model, :year, :brand);

-- UPDATE A CAR
-- :name update-car :! :n
-- :doc Update an existing car record in the cars table
UPDATE cars
SET model = :model, year = :year, brand = :brand
WHERE id = :id;

-- DELETE A CAR
-- :name delete-car :! :n
-- :doc Delete an existing car record from the cars table
DELETE FROM cars
WHERE id = :id;

-- GET ALL CARS
-- :name get-all-cars :? :0
-- :doc Retrieve all cars from the cars table
SELECT * FROM cars;

-- GET CAR BY ID
-- :name get-car-by-id :? :1
-- :doc retrieve a car given the id.
SELECT * FROM cars
WHERE id = :id;

-- INSERT A NEW REVISION
-- :name create-revision :! :n
-- :doc Insert a new revision into the revisions table
INSERT INTO revisions (id_car, last_revision_date, created_at, km)
VALUES (:id_car, :last_revision_date, :created_at, :km);

-- UPDATE A REVISION
-- :name update-revision! :! :n
-- :doc Update an existing revision record in the revisions table
UPDATE revisions
SET last_revision_date = :last_revision_date, created_at = :created_at, km = :km
WHERE id = :id;

-- DELETE A REVISION
-- :name delete-revision! :! :n
-- :doc Delete an existing revision record from the revisions table
DELETE FROM revisions
WHERE id = :id;

-- GET ALL REVISIONS FOR A CAR
-- :name get-revisions-for-car :? :1
-- :doc Retrieve all revisions for a specific car from the revisions table
SELECT * FROM revisions
WHERE id_car = :id_car
ORDER BY last_revision_date DESC
LIMIT 1;

-- GET REVISION LIMITS ITEMS FOR A CAR
-- :name get-revision-limits-items-for-car :? :0
-- :doc Retrieve all revision limits items for a specific car from the revision_car_items_limits table
SELECT * FROM revision_car_items_limits WHERE id_car = :id_car;

-- GET REVISION LIMITS ITEMS FOR A CAR
-- :name get-all-revision-limits :? :0
-- :doc Retrieve all revision limits items for a specific car from the revision_car_items_limits table
SELECT * FROM revision_car_items_limits;