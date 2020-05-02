use `serials_db`;

create table country
(
    `id`   INTEGER     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL UNIQUE,
    CONSTRAINT pk_country PRIMARY KEY (`id`)
);

create table genre
(
    `id`   INTEGER     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL UNIQUE,
    CONSTRAINT pk_genre PRIMARY KEY (`id`)
);

create table studio
(
    `id`   INTEGER     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL UNIQUE,
    CONSTRAINT pk_studio PRIMARY KEY (`id`)
);

create table user
(
    `id`       INTEGER           NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(12)       NOT NULL UNIQUE,
    `password` CHAR(60)          NOT NULL,
    `avatar`   VARCHAR(100),
    /*
     * 0 - администратор (Role.ADMINISTRATOR)
     * 1 - модератор (Role.MODERATOR)
     * 2 - пользователь (Role.USER)
     */
    `role`     TINYINT DEFAULT 2 NOT NULL CHECK (`role` IN (0, 1, 2)),
    CONSTRAINT pk_user PRIMARY KEY (`id`)
);

create table serial
(
    `id`           INTEGER       NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45)   NOT NULL UNIQUE,
    `description`  VARCHAR(1024) NOT NULL,
    `logo`         VARCHAR(100),
    `full_logo`    VARCHAR(100),
    `release_date` DATE          NOT NULL,
    `count_like`   INTEGER DEFAULT 0,
    `country_id`   INTEGER       NOT NULL,
    `studio_id`    INTEGER       NOT NULL,
    CONSTRAINT pk_serial PRIMARY KEY (`id`),
    CONSTRAINT fk_serial_country FOREIGN KEY (`country_id`)
        REFERENCES country (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_serial_studio FOREIGN KEY (`studio_id`)
        REFERENCES studio (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

create table comment
(
    `id`               INTEGER      NOT NULL AUTO_INCREMENT,
    `user_id`          INTEGER      NOT NULL,
    `serial_id`        INTEGER      NOT NULL,
    `comment`          VARCHAR(512) NOT NULL,
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
    UNIQUE (`user_id`, `serial_id`),
    CONSTRAINT fk_viewed_user FOREIGN KEY (`user_id`)
        REFERENCES user (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_viewed_serial FOREIGN KEY (`serial_id`)
        REFERENCES serial (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE

);

create table liked
(
    `user_id`   INTEGER NOT NULL,
    `serial_id` INTEGER NOT NULL,
    UNIQUE (`user_id`, `serial_id`),
    CONSTRAINT fk_liked_user FOREIGN KEY (`user_id`)
        REFERENCES user (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_liked_serial FOREIGN KEY (`serial_id`)
        REFERENCES serial (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE

);

create table serial_genre
(
    `serial_id` INTEGER NOT NULL,
    `genre_id`  INTEGER NOT NULL,
    UNIQUE (`serial_id`, `genre_id`),
    CONSTRAINT fk_serial_genre_serial FOREIGN KEY (`serial_id`)
        REFERENCES serial (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_serial_genre_genre FOREIGN KEY (`genre_id`)
        REFERENCES genre (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
