CREATE TABLE kuzov
(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE dvigatel
(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE corobkaperedach
(
    id serial primary key,
    name varchar(255)
);

CREATE TABLE mashina
(
    id serial primary key,
    name varchar(255),
    kuzov_id integer REFERENCES kuzod (id) ,
	dvigatel_id integer REFERENCES dvigatel (id) ,
	corobkaperedach_id integer REFERENCES corobkaperedach (id)
);

INSERT INTO kuzov (name) VALUES 
('kuzov1'), ('kuzov2'), ('kuzov3'), ('kuzov4');

INSERT INTO dvigatel (name) VALUES 
('dvig1'), ('dvig2'), ('dvig3'), ('dvig4');

INSERT INTO corobkaperedach (name) VALUES 
('corobka1'), ('corobka2'), ('corobka3'), ('corobka4');

INSERT INTO mashina(name, kuzov_id, dvigatel_id, corobkaperedach_id) VALUES
 ('m1', 1, 1, 1),('m1', 1, 1, 1);


select m.name, coalesce(k.name,'') as k_name, coalesce(d.name,'') as d_name, coalesce(c.name,'') as c_name
from mashina m 
left join kuzov k on m.kuzov_id = k.id
left join dvigatel d on m.dvigatel_id = d.id
left join corobkaperedach c on m.corobkaperedach_id = c.id

select k.name as unused
from kuzov k
left join mashina m on m.kuzov_id = k.id
where m.name is null

select d.name as unused
from dvigatel d
left join mashina m on m.dvigatel_id = d.id
where m.name is null

select c.name as unused
from corobkaperedach c
left join mashina m on m.corobkaperedach_id = c.id
where m.name is null