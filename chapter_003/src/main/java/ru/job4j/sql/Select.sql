-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT pr.name, tp.name 
FROM product pr 
	left join "type" tp ON pr.type_id = tp.id
WHERE tp.name = 'СЫР'

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT *
FROM product pr 
WHERE lower(pr.name) like '%мороженное%'

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT *
FROM product pr 
WHERE extract(month from pr.expired_date ) = extract(month from (now() + interval '1 month'))

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * 
FROM product pr
where pr.price in (SELECT MAX (price) FROM product)

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT count(pr.name), tp.name 
FROM product pr left join type tp On pr.type_id = tp.id
GROUP BY tp.name

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT pr.name, tp.name 
FROM product pr 
	left join "type" tp ON pr.type_id = tp.id
WHERE tp.name = 'СЫР' or tp.name = 'МОЛОКО'

7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT count(pr.id), tp.name 
FROM product pr 
	left join "type" tp ON pr.type_id = tp.id
GROUP BY tp.id
HAVING count(pr.id) < 10

8. Вывести все продукты и их тип.
SELECT pr.name as product, tp.name as type
FROM product pr 
	left join "type" tp ON pr.type_id = tp.id