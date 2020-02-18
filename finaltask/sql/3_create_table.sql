use `serials_db`;

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

create table studio
(
    `id`   INTEGER      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

create table user
(
    `id`       INTEGER     NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(12) NOT NULL UNIQUE,
    `password` CHAR(32)    NOT NULL,
    /*
     * 0 - администратор (Role.ADMINISTRATOR)
     * 1 - модератор (Role.MODERATOR)
     * 2 - пользователь (Role.USER)
     */
    `role`     TINYINT     NOT NULL CHECK (`role` IN (0, 1, 2)),
    CONSTRAINT pk_users PRIMARY KEY (`id`)
);

create table serial
(
    `id`           INTEGER      NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45)  NOT NULL UNIQUE,
    `description`  VARCHAR(512) NOT NULL,
    `logo`         VARCHAR(20), -- TODO как буду хранить карттинки?
    `full_logo`    VARCHAR(20),
    `release_date` DATE         NOT NULL,
    `count_like`   INTEGER DEFAULT 0,
    `studio_id`    INTEGER      NOT NULL,
    CONSTRAINT pk_shows PRIMARY KEY (`id`)
);

create table comment
(
    `id`               INTEGER      NOT NULL AUTO_INCREMENT,
    `user_id`          INTEGER      NOT NULL,
    `serial_id`        INTEGER      NOT NULL,
    `commentary`       VARCHAR(512) NOT NULL,
    `publication_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_comment PRIMARY KEY (`id`),
    CONSTRAINT fk_comment_user FOREIGN KEY (`user_id`)
        REFERENCES user (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_comment_serial FOREIGN KEY (`serial_id`)
        REFERENCES serial (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

create table viewed
(
    `user_id`   INTEGER NOT NULL,
    `serial_id` INTEGER NOT NULL,
    /*
     * 0 - не смотрю (WatchStatus.DONT_WATCH) -- TODO нужен ли? если не смотрит то его просто не будет в этой ттаблице
     * 1 - буду смотреть (WatchStatus.WILL_WATCH)
     * 2 - смотрю (WatchStatus.WATCH)
     */
    `status`    TINYINT NOT NULL CHECK (`status` IN (0, 1, 2)),
    CONSTRAINT fk_viewed_user FOREIGN KEY (`user_id`)
        REFERENCES user (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_viewed_serial FOREIGN KEY (`serial_id`)
        REFERENCES serial (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE

);

create table serial_country
(
    `serial_id`  INTEGER NOT NULL,
    `country_id` INTEGER NOT NULL,
    CONSTRAINT fk_serial_country_serial FOREIGN KEY (`serial_id`)
        REFERENCES serial (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_serial_country_serial_country FOREIGN KEY (`country_id`)
        REFERENCES country (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

create table serial_genre
(
    `serial_id` INTEGER NOT NULL,
    `genre_id`  INTEGER NOT NULL,
    CONSTRAINT fk_serial_genre_serial FOREIGN KEY (`serial_id`)
        REFERENCES serial (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_serial_genre_genre FOREIGN KEY (`genre_id`)
        REFERENCES genre (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

create table serial_studio
(
    `serial_id` INTEGER NOT NULL UNIQUE,
    `studio_id` INTEGER NOT NULL,
    CONSTRAINT fk_serial_studio_serial FOREIGN KEY (`serial_id`)
        REFERENCES serial (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_serial_studio_studio FOREIGN KEY (`studio_id`)
        REFERENCES studio (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

