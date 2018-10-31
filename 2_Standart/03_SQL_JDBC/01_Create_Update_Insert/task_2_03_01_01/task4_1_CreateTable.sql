--создаем табилцы и некторые связи между таблицами

-- таблица ролей
CREATE TABLE "roles" (
   id_role SERIAL PRIMARY KEY,
   name_role CHARACTER VARYING(45) NOT NULL,
   description CHARACTER VARYING(45)
);

-- таблица пользователей
CREATE TABLE "users" (
   id_user SERIAL PRIMARY KEY,
   name_user CHARACTER VARYING(45) NOT NULL,
   login_user CHARACTER VARYING(20) NOT NULL UNIQUE,
   password_user CHARACTER VARYING(15) NOT NULL,
   description CHARACTER VARYING(45),
   id_role INTEGER NOT NULL,                                  -- поле для связи с табилцей roles
   FOREIGN KEY (id_role) REFERENCES roles(id_role)            -- внешний ключ для связи табылий users-roles, связь *-1
);

-- таблица заявок
CREATE TABLE "items" (
   id_item SERIAL PRIMARY KEY,
   name_item CHARACTER VARYING(45) NOT NULL,
   data_create_item TIMESTAMP NOT NULL,
   description CHARACTER VARYING(45),
   id_user INTEGER NOT NULL, 								   --поле для связи 1 - 1 с табилцей users
   id_category INTEGER NOT NULL,							   -- поле для связи items-categoryes *-1
   id_state INTEGER NOT NULL,                                  -- поле для связи items-states *-1 
   FOREIGN KEY (id_user) REFERENCES users(id_user)             -- внешний ключ для связи табылицей users
);



-- таблица прав ролей
CREATE TABLE "rigths" (
   id_rigth SERIAL PRIMARY KEY,
   name_rigth CHARACTER VARYING(45) NOT NULL 
);

--промежуточная таблиц для связи * - * для таблиц roles-rigths
CREATE TABLE "roles_rigths"(
  id_role INTEGER NOT NULL,
  id_rigth INTEGER NOT NULL,
  PRIMARY KEY(id_role, id_rigth)
);

--таблица комментариев
CREATE TABLE "comments"(
  id_comment SERIAL PRIMARY KEY,
  text_comment CHARACTER VARYING(200),
  data_create_comment TIMESTAMP NOT NULL,
  description CHARACTER VARYING,
  id_item INTEGER NOT NULL
);

-- таблица приложенных файлов
CREATE TABLE "attaches"(
  id_attach SERIAL PRIMARY KEY,
  path_to_file CHARACTER VARYING(45) NOT NULL,
  description CHARACTER VARYING(45),
  id_item INTEGER NOT NULL 						-- поле для создание связи с табилцей items
);

--таблица категорий заявки
CREATE TABLE "categoryes"(
  id_category SERIAL PRIMARY KEY,
  name_category CHARACTER VARYING(45) NOT NULL,
  description CHARACTER VARYING(45)
);

--таблица состояний заявки
CREATE TABLE "states"(
  id_state SERIAL PRIMARY KEY,
  name_state CHARACTER VARYING(45) NOT NULL,
  description CHARACTER VARYING(45)
);


