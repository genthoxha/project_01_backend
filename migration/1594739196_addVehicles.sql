DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle(id serial PRIMARY KEY, name VARCHAR(255), model VARCHAR(255), fuelType VARCHAR(255), status BOOLEAN);
INSERT INTO vehicle(id, name, model, fuel_Type, status) VALUES(1,'Vet1', 'Suzuki', 'Diesel', True);
INSERT INTO vehicle(id, name, model, fuel_Type, status) VALUES(2,'Vet2', 'Suzuki', 'Diesel', False);
INSERT INTO vehicle(id, name, model, fuel_Type, status) VALUES(3,'Vet3', 'Suzuki', 'Diesel', True);
INSERT INTO vehicle(id, name, model, fuel_Type, status) VALUES(4,'Vet4', 'Suzuki', 'Diesel', False);
INSERT INTO vehicle(id, name, model, fuel_Type, status) VALUES(5,'Vet5', 'Suzuki', 'Diesel', True);


