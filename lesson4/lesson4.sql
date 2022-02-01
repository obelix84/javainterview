CREATE SCHEMA `cinema` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `cinema`.`film` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `duration_id` INT NULL,
  PRIMARY KEY (`id`));

-- Считаем, что у нас фиксированные длительности фильмов 90, 120
-- вынес их в отдельную таблицу, но это не всегда нужно (2 НФ)

  CREATE TABLE `cinema`.`duration` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `duration` INT NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `cinema`.`film` 
ADD INDEX `FK_duration_idx` (`duration_id` ASC) VISIBLE;
ALTER TABLE `cinema`.`film` 
ADD CONSTRAINT `FK_duration`
  FOREIGN KEY (`duration_id`)
  REFERENCES `cinema`.`duration` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


-- вот тут есть нюансы, стоимость билета зависит от сеанса
-- и куда тут пихать стоимость в сеанс или в билет...
-- т.е. стоимость билета на конкретный сеанс зависит от времени и от фильма
-- оставим стоимость билета
-- еще надо бы добавить билет на какое место, но опустим...
-- наверное правильнее стоимость запихнуть в сеанс, а в билет добавить его 
-- атрибуты: место в зале


CREATE TABLE `cinema`.`session` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start` DATETIME NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`));

--забыл film_id
ALTER TABLE `cinema`.`session` 
ADD COLUMN `film_id` INT NOT NULL AFTER `price`,
ADD INDEX `FK_film_idx` (`film_id` ASC) VISIBLE;
ALTER TABLE `cinema`.`session` 
ADD CONSTRAINT `FK_film`
  FOREIGN KEY (`film_id`)
  REFERENCES `cinema`.`films` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  CREATE TABLE `cinema`.`tickets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `session_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_session_idx` (`session_id` ASC) VISIBLE,
  CONSTRAINT `FK_session`
    FOREIGN KEY (`session_id`)
    REFERENCES `cinema`.`session` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `cinema`.`duration` (`id`, `duration`) VALUES ('1', '60');
INSERT INTO `cinema`.`duration` (`id`, `duration`) VALUES ('2', '90');
INSERT INTO `cinema`.`duration` (`id`, `duration`) VALUES ('3', '120');

INSERT INTO `cinema`.`film` (`id`, `name`, `duration_id`) VALUES ('1', 'film1', '1');
INSERT INTO `cinema`.`film` (`id`, `name`, `duration_id`) VALUES ('2', 'film2', '2');
INSERT INTO `cinema`.`film` (`id`, `name`, `duration_id`) VALUES ('3', 'film3', '3');
INSERT INTO `cinema`.`film` (`id`, `name`, `duration_id`) VALUES ('4', 'film4', '2');
INSERT INTO `cinema`.`film` (`id`, `name`, `duration_id`) VALUES ('5', 'film5', '3');

INSERT INTO `cinema`.`session` (`id`, `start`, `price`, `film_id`) VALUES ('1', '2022-01-01 15:00:00', '100', '1');
INSERT INTO `cinema`.`session` (`id`, `start`, `price`, `film_id`) VALUES ('3', '2022-01-01 16:00:00', '100', '3');
INSERT INTO `cinema`.`session` (`id`, `start`, `price`, `film_id`) VALUES ('4', '2022-01-02 11:00:00', '100', '4');
INSERT INTO `cinema`.`session` (`id`, `start`, `price`, `film_id`) VALUES ('5', '2022-01-02 12:00:00', '300', '5');

INSERT INTO `cinema`.`tickets` (`id`, `session_id`) VALUES ('1', '1');
INSERT INTO `cinema`.`tickets` (`id`, `session_id`) VALUES ('2', '1');
INSERT INTO `cinema`.`tickets` (`id`, `session_id`) VALUES ('3', '1');
INSERT INTO `cinema`.`tickets` (`id`, `session_id`) VALUES ('4', '2');
INSERT INTO `cinema`.`tickets` (`id`, `session_id`) VALUES ('5', '3');
INSERT INTO `cinema`.`tickets` (`id`, `session_id`) VALUES ('6', '4');
INSERT INTO `cinema`.`tickets` (`id`, `session_id`) VALUES ('7', '5');

-- 1

with intervals as (
select s.film_id, 
s.id as session_id, 
f.name, 
s.start,
DATE_ADD(s.start, interval d.duration MINUTE) as end
from session s 
inner join films f on s.film_id = f.id 
inner join duration d on f.duration_id = d.id )

select * from intervals i1, intervals i2 where i1.start < i2.end AND i1.end > i2.start 
and i1.session_id <> i2.session_id
and i1.session_id < i2.session_id
