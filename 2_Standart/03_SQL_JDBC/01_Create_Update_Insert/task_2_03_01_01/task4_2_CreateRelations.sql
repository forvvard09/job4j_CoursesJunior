--для практики создание связей между базами данных, отдельно от создания таблиц

 -- доабвляем внешний ключ в таблицу items для создания связи с таблицой categoryes 
 ALTER TABLE items ADD FOREIGN KEY (id_category) REFERENCES categoryes(id_category);
 
  -- доабвляем внешний ключ в таблицу items для создания связи с таблиц states
 ALTER TABLE items ADD FOREIGN KEY (id_state) REFERENCES states(id_state);
 
  -- доабвляем внешний ключ в промежуточную таблицу roles_rigths для создания связи с таблицой roles
 ALTER TABLE roles_rigths ADD FOREIGN KEY (id_role) REFERENCES roles(id_role);
 
  -- доабвляем внешний ключ в промежуточную таблицу roles_rigths для создания связи с таблицой rigths
 ALTER TABLE roles_rigths ADD FOREIGN KEY (id_rigth) REFERENCES rigths(id_rigth);  
 
  -- доабвляем внешний ключ в промежуточную таблицу rigths_roles для создания связи с таблицой rigths
 --ALTER TABLE rigths_roles ADD FOREIGN KEY () REFERENCES roles();  
 
  -- доабвляем внешний ключ в таблицу attaches для связи с таблицей items
 ALTER TABLE attaches ADD FOREIGN KEY (id_item) REFERENCES items(id_item);  
 
  -- доабвляем внешний ключ в таблицу comments для связи с таблицей items
 ALTER TABLE comments ADD FOREIGN KEY (id_item) REFERENCES items(id_item);  
 