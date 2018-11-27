/*
1 Создать структур данных в базе.
Таблицы.
   Кузов. Двигатель, Коробка передач.
*/

-- создаем таблицу кузов - bodyes
create table if not exists "bodyes" (
 id_body serial primary key,
 name_body character varying(99) unique not null,
 description character varying(99),
);

-- создаем таблицу двигатель - engines
create table if not exists "engines" (
 id_engine serial primary key,
 name_engine character varying(99) not null,
 description character varying(99),
);

-- создаем таблицу коробка передач - transmissions
create table if not exists "transmissions" (
 id_transmission serial primary key,
 name_transmission character varying(99) not null,
 description character varying(99),
 automate boolean default false,
 number_gears integer not null default 1
);

/*
2 Создать структуру Машина. Машина не может существовать без данных из п.1.
*/
-- создаем таблицу автомобили - cars
create table if not exists "cars" (
 id_car serial primary key,
 name_car character varying(99) not null,
 description character varying(99),
 id_body integer not null,
 id_engine integer not null,
 id_transmission integer not null
);

/*
3 Создать структуру Машина. Машина не может существовать без данных из п.1.
*/
-- доабвляем внешний ключ для таблицу bodyes,transmissions,engines для связи с таблицей cars ( многие к 1)
 ALTER TABLE cars ADD FOREIGN KEY (id_body) REFERENCES bodyes (id_body);
 ALTER TABLE cars ADD FOREIGN KEY (id_engine) REFERENCES engines (id_engine);
 ALTER TABLE cars ADD FOREIGN KEY (id_transmission) REFERENCES transmissions (id_transmission);


-- заполянем таблицу двигатель
insert into engines(name_engine, description) values ('Двигатель L3', '2.5 л'),
											      ('Двигатель X25D1', '1.8 л'),
												  ('Двигатель Cube', '1.5 л'),
												  ('Двигатель 1JZ', '2.0 л'),
												  ('Двигатель M57N306D', '2.0 л'),
												  ('Двигатель SAA4D', 'нет информации'),
												  ('Двигатель Spark', '2.0 л'),
												  ('Двигатель Opirus', '1.6 л');

-- заполянем таблицу кузов
insert into bodyes(name_body, description) values ('Кузов Audi', 'оцинкованный' ),
											      ('Кузов BMV', 'оцинкованный' ),
												  ('Кузов Chevrolet', 'нет информации' ),
												  ('Кузов Ford', 'оцинкованный' ),
												  ('Кузов Honda', 'не оцинкованный' ),
												  ('Кузов Kia', 'модель 2009 года' ),
												  ('Кузов Lexus', 'не оцинкованный' ),
												  ('Кузов Mazda', 'произыодство Япония' );


-- заполянем таблицу коробка передач
insert into transmissions(name_transmission, description, automate, number_gears) values ('Полный привод', 'на складе', true, 3),
																						 ('Задний привод', 'в магазине №2', false, 5),
																						 ('Передний привод', 'в магазине №9', true, 7),
																						 ('Передний привод', 'отсутвствует', true, 3),
																						 ('Передний привод', 'на складе', true, 4),
																						 ('Передний привод', 'на складе', true, 2),
																					     ('Полный привод', 'на складе', true, 5),
																						 ('Полный привод', 'на складе', true, 3);

-- заполянем таблицу автомобили
insert into cars(name_car, description, id_body, id_transmission, id_engine) values ('Audi A4', 'красный зеленый', 1, 8, 7),
																					('Audi Q3', '', 1, 4, 3),
																					('BMW i3', '', 2, 3, 2),
																					('Ford Capri', '', 4, 6, 3),
																					('Mitsubushi Attrage', '', 5, 5, 1),
																					('Ваз 2103', '', 6, 6, 4),
																					('Ваз 2109', '', 7, 1, 1);




