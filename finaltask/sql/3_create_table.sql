use `serials_db`;

create table users
(
    `id`       INTEGER AUTO_INCREMENT,
    `login`    VARCHAR(255) NOT NULL UNIQUE,
    `password` CHAR(32)     NOT NULL,
    /*
     * 0 - администратор (Role.ADMINISTRATOR)
     * 1 - модератор (Role.MODERATOR)
     * 2 - пользователь (Role.USER)
     */
    `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2)),
    CONSTRAINT pk_users PRIMARY KEY (`id`)
);

create table shows
(
    `id`     INTEGER AUTO_INCREMENT,
    `name`   VARCHAR(255) NOT NULL,
    `rating` DOUBLE CHECK ( rating BETWEEN 0 AND 10),
    CONSTRAINT pk_shows PRIMARY KEY (`id`)
);

create table season
(
    `show_id`            INTEGER,
    `number_of_season`   INTEGER CHECK ( number_of_season > 0 ),
    `amount_of_episodes` INTEGER CHECK ( amount_of_episodes > 0 ),
    `release_date`       DATE,
    CONSTRAINT pk_season PRIMARY KEY (`show_id`, `number_of_season`),
    CONSTRAINT fk_season_shows FOREIGN KEY (`show_id`)
        REFERENCES shows (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

create table series
(
    `show_id`          INTEGER NOT NULL,
    `season_id`        INTEGER NOT NULL,
    `number_of_series` INTEGER CHECK ( number_of_series > 0 ),
    `rating`           DOUBLE CHECK ( rating BETWEEN 0 AND 10),
    `release_date`     DATE,
    `description`      VARCHAR(255),
    CONSTRAINT pk_series PRIMARY KEY (`show_id`, `season_id`, `number_of_series`),
    CONSTRAINT fk_test FOREIGN KEY (`show_id`, `season_id`)
        REFERENCES season (`show_id`, `number_of_season`)
        ON DELETE CASCADE
        ON UPDATE CASCADE


#     CONSTRAINT fk_series_show FOREIGN KEY (`show_id`)
#         REFERENCES shows (`id`)
#         ON DELETE CASCADE
#         ON UPDATE CASCADE,
#     CONSTRAINT fk_series_season FOREIGN KEY (`season_id`)
#         REFERENCES season (`number_of_season`)
#         ON DELETE CASCADE
#         ON UPDATE CASCADE
);

create table viewer
(
    `id`    INTEGER AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT pk_viewer PRIMARY KEY (`id`)
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