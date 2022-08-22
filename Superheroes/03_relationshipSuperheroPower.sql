DROP TABLE IF EXISTS superhero_power;

CREATE TABLE superhero_power (
	hero_id int REFERENCES superhero,
	power_id int REFERENCES power,
	PRIMARY KEY (hero_id, power_id)
);