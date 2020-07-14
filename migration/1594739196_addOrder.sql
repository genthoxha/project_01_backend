DROP TABLE IF EXISTS order;
CREATE TABLE order(id serial PRIMARY KEY, amount double precision, is_payed BOOLEAN);
INSERT INTO order(id, amount, is_payed) VALUES(1,'3.5',TRUE);
INSERT INTO order(id,amount, is_payed) VALUES (2, '0', FALSE);