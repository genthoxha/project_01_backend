DROP TABLE IF EXISTS server;
CREATE TABLE server(id serial PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255), username VARCHAR(255), password VARCHAR(255), email VARCHAR(255));
INSERT INTO server(id, first_name, last_name, username, password, email) VALUES (1, 'Filan', 'Fisteku', 'FilanFisteku', 'Filan123', 'filanfisteku@gmail.com');
INSERT INTO server(id, first_name, last_name, username, password, email) VALUES (1, 'Filane', 'Fisteku', 'FilaneFisteku', 'Filane123', 'filanefisteku@gmail.com');
