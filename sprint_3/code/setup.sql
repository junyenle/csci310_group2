DROP DATABASE IF EXISTS scollage;
CREATE DATABASE scollage;
USE scollage;

CREATE TABLE users (
	username VARCHAR(50) PRIMARY KEY NOT NULL,
	password VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password) VALUES ('a', '92eb5ffee6ae2fec3ad71c777531578f');