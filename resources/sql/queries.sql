-- INSERT A NEW CAR
-- :name insert-car! :! :n
-- :doc Insert a new car into the cars table
INSERT INTO cars (model, year, brand)
VALUES (:model, :year, :brand);

-- UPDATE A CAR
-- :name update-car! :! :n
-- :doc Update an existing car record in the cars table
UPDATE cars
SET model = :model, year = :year, brand = :brand
WHERE id = :id;

-- DELETE A CAR
-- :name delete-car! :! :n
-- :doc Delete an existing car record from the cars table
DELETE FROM cars
WHERE id = :id;

-- GET ALL CARS
-- :name get-all-cars :? :0
-- :doc Retrieve all cars from the cars table
SELECT * FROM cars;

-- INSERT A NEW REVISION
-- :name insert-revision! :! :n
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
WHERE id_car = :id_car;