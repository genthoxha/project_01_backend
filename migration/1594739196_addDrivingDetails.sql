DROP TABLE IF EXISTS driving_details;
CREATE TABLE driving_details(id serial PRIMARY KEY, current_average_speed double precision, latitude bigserial, longitude bigserial, is_available BOOLEAN);
INSERT INTO driving_details(id, current_average_speed, latitude, longitude , is_available) VALUES (1, '20.9215', '42.661080', '21.163324', TRUE );
INSERT INTO driving_details(id, current_average_speed, latitude, longitude , is_available) VALUES (2, '20.9215', '42.661190', '21.165524', FALSE );