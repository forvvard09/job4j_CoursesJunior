/*
1. Вывести список всех машин и все привязанные к ним детали.
*/
--стандартный через inner join
select name_car as "Марка машины", name_body as "Кузов", name_engine as "Двигатель", automate as "Коробка передач автомат", number_gears as "Количество скоростей", name_transmission as "Привод" from cars c join bodyes b on c.id_body = b.id_body
join engines e on c.id_engine = e.id_engine
join transmissions t on c.id_transmission = t.id_transmission;


/*
2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
*/

--кузов, который не используется LEFT OUTER JOIN
select b.id_body, name_body as "Кузов, который не используется" from bodyes b LEFT OUTER JOIN cars c on c.id_body = b.id_body where c.id_car is null;


--двигатель, который не используется через RIGHT OUTER JOIN
select e.id_engine, name_engine as "Двигатель, который не исплоьзуется" from cars c  RIGHT OUTER  JOIN engines e on c.id_engine = e.id_engine where c.id_car is null;

--коробка передач, которая не используется через LEFT OUTER JOIN
select t.id_transmission, automate as "Автоматическая ли коробка", number_gears as "Кол-во передач", name_transmission as "Привод" from transmissions t
LEFT OUTER JOIN cars c ON t.id_transmission = c.id_transmission where c.id_car is null;


/*

Для проверки:
http://prntscr.com/lnjvp2
https://prnt.sc/lnjtpz
https://prnt.sc/lnjumi
https://prnt.sc/lnjutr

*/


