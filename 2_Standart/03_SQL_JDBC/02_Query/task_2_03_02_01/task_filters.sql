/*
В системе заданы таблицы

product(id, name, type_id, expired_date, price)

type(id, name)

Задание.

1. Написать запрос получение всех продуктов с типом "СЫР"

2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"

3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

4. Написать запрос, который выводит самый дорогой продукт.

5. Написать запрос, который выводит количество всех продуктов определенного типа.

6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.

8. Вывести все продукты и их тип.
*/

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select distinct * from products as p join types as t on p.id_type = t.id_type where upper (t.name_type) = 'СЫР';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное", заменил на "мороженое", так
-- как в моей таблице нет товаров со словами "мороженное"
select distinct * from products where  upper(name_product) like '%МОРОЖЕНОЕ%';
-- еще можно вот так
--select distinct * from products where  lower(name_product) like '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select distinct * from products  where extract(MONTH from expired_data) = EXTRACT(MONTH FROM current_date  + INTERVAL '1' MONTH);

--4. Написать запрос, который выводит самый дорогой продукт.
select * from products where price = (select MAX(price) from products);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select name_type, count(name_product) as "Count products" from types t join products p on t.id_type = p.id_type group by name_type;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select name_type, name_product as "Count products" from types t join products p on t.id_type = p.id_type where lower(name_type)
IN ('сыр',  'молоко');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук, изменю условие на < 5.
select name_type, count(name_product) as "Count products" from types t join products p on t.id_type = p.id_type group by name_type
HAVING count(name_product) < 5;

--8. Вывести все продукты и их тип.
select name_product as "Продукт", name_type as "Тип" from products p join types t on t.id_type = p.id_type order by name_type;