use `serials_db`;

create table users
(
    `id`       INTEGER      NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(255) NOT NULL UNIQUE,
    `password` CHAR(32)     NOT NULL,
    /*
     * 0 - администратор (Role.ADMINISTRATOR)
     * 1 - модератор (Role.MODERATOR)
     * 2 - пользователь (Role.USER)
     */
    `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2)),
    PRIMARY KEY (`id`)
);

create table shows
(
    `id`     INTEGER      NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(255) NOT NULL,
    `rating` double,
    primary key (`id`)
);

create table season
(
    `id`                 INTEGER NOT NULL AUTO_INCREMENT,
    `shows_id`           integer not null,
    `number_of_season`   INTEGER NOT NULL,
    `number_of_episodes` integer,
    `release_date`       date,
    primary key (`id`),
    foreign key (`shows_id`)
        references shows (`id`)
);

create table series
(
    `id`               INTEGER NOT NULL AUTO_INCREMENT,
    `show_id`          INTEGER NOT NULL,
    `season_id`        INTEGER NOT NULL,
    `number_of_series` INTEGER NOT NULL,
    `rating`           double,
    `release_date`     date,
    `description`      VARCHAR(255),
    `viewed`           boolean,
    primary key (`id`),
    foreign key (`show_id`)
        references shows (`id`),
    foreign key (`season_id`)
        references season (`id`)
);

create table viewer
(
    `id`    INTEGER      NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL UNIQUE,
    primary key (`id`)
);

create table wathcing
(
    `viewer_id` INTEGER NOT NULL,
    `show_id`   INTEGER NOT NULL
);

create table show_country
(
    `show_id`    INTEGER NOT NULL,
    `country_id` INTEGER NOT NULL
);

create table show_genre
(
    `show_id`  INTEGER NOT NULL,
    `genre_id` INTEGER NOT NULL
);

/*
 Таблицы справочники
*/

create table country
(
    `id`   INTEGER      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

create table genre
(
    `id`   INTEGER      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);