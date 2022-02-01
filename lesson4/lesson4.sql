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
  `start_time` DATETIME NOT NULL,
  `film_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_film_idx` (`film_id` ASC) VISIBLE,
  CONSTRAINT `FK_film`
    FOREIGN KEY (`film_id`)
    REFERENCES `cinema`.`film` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `cinema`.`ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(10,2) NULL,
  `price`
  PRIMARY KEY (`id`));

-- таблица связывающая сеансы и билеты
-- 
CREATE TABLE `cinema`.`sessions_tickets` (
  `session_id` INT NOT NULL,
  `ticket_id` INT NOT NULL,
  PRIMARY KEY (`session_id`, `ticket_id`),
  INDEX `FK_ticket_idx` (`ticket_id` ASC) VISIBLE,
  CONSTRAINT `FK_ticket`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `cinema`.`ticket` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
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

  CREATE TABLE `cinema`.`tikets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `session_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_session_idx` (`session_id` ASC) VISIBLE,
  CONSTRAINT `FK_session`
    FOREIGN KEY (`session_id`)
    REFERENCES `cinema`.`session` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `cinema`.`session` (`id`, `start`, `price`, `film_id`) VALUES ('1', '2022-01-01 15:00:00', '100', '1');
INSERT INTO `cinema`.`session` (`id`, `start`, `price`, `film_id`) VALUES ('3', '2022-01-01 16:00:00', '100', '3');
INSERT INTO `cinema`.`session` (`id`, `start`, `price`, `film_id`) VALUES ('4', '2022-01-02 11:00:00', '100', '4');
INSERT INTO `cinema`.`session` (`id`, `start`, `price`, `film_id`) VALUES ('5', '2022-01-02 12:00:00', '300', '5');


