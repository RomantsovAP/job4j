CREATE DATABASE orders;

CREATE TABLE rule (
id serial PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE role (
id serial PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE _user (
id serial PRIMARY KEY,
name VARCHAR(255),
role_id integer REFERENCES role (id)
);

CREATE TABLE role_rules (
id serial PRIMARY KEY,
role_id integer REFERENCES role (id),
rule_id integer REFERENCES rule (id)
);

CREATE TABLE order_state (
id serial PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE order_category (
id serial PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE _order (
id serial PRIMARY KEY,
name VARCHAR(255),
state_id integer REFERENCES order_state (id),
category_id integer REFERENCES order_category (id),
user_id integer REFERENCES _user (id)
);

CREATE TABLE attachment (
id serial PRIMARY KEY,
name VARCHAR(255),
order_id integer REFERENCES _order(id)
);

CREATE TABLE comment (
id serial PRIMARY KEY,
name VARCHAR(255),
order_id integer REFERENCES _order(id)
);

INSERT INTO "rule" (name) values
	('rule1'),
	('rule2'),
	('rule3'),
	('rule4');

INSERT INTO "role" (name) values
	('role1'),
	('role2'),
	('role3'),
	('role4');

INSERT INTO "role_rules" (role_id, rule_id) values
	('1', '1'),
	('2', '2'),
	('2', '3'),
	('2', '4');

INSERT INTO "_user" (name, role_id) values
	('user1', '1'),
	('user2', '1'),
	('user3', '2'),
	('user4', '2');

INSERT INTO "order_state" (name) values
	('state1'),
	('state2'),
	('state3'),
	('state4');

INSERT INTO "order_category" (name) values
	('cat1'),
	('cat2'),
	('cat3'),
	('cat4');

INSERT INTO "_order" (name, state_id, category_id, user_id) values
	('order1', 1, 1, 1),
	('order2', 2, 1, 1),
	('order3', 1, 1, 2),
	('order4', 1, 2, 2);

INSERT INTO "comment" (name, order_id) values
	('comment1', 1),
	('comment2', 1),
	('comment3', 1),
	('comment4', 1);

INSERT INTO "attachment" (name, order_id) values
	('att1', 1),
	('att2', 1),
	('att3', 2),
	('att4', 1);