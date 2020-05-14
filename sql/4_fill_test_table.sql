INSERT into user (`login`, `password`, `avatar`, `role`)
values ('admin',
        '$2a$10$FYgd8Lqg0fV4BZGDqopbruwZTuhJrWDwU2lJrKWntV3535KlGOPLq',
        'noAvatar.png',
        0),
       ('moder',
        '$2a$10$PbjWnDHPv9W2UfCArCuZUeCusxcGhH/GBu7AlRLmd/YyEKfRapC9y',
        'noAvatar.png',
        1),
       ('user',
        '$2a$10$LuRX/n8yB/R6EK7FOBVKqOoVkYDLhwkhIj9uUM.gSWoaIu61qcfEK',
        'noAvatar.png',
        2);

insert into country (`name`)
values ('США'),
       ('Великобритания'),
       ('Испания');

insert into genre (`name`)
values ('Драма'),
       ('Мистика'),
       ('Научная фантастика');

insert into studio (`name`)
values ('HBO'),
       ('Antena 3'),
       ('BBC Two');

insert into serial (`name`, `description`, `logo`, `full_logo`, `release_date`, `country_id`, `studio_id`)
values ('Мир Дикого Запада', 'В футуристическом парке развлечений «Мир Дикого Запада» специально сконструированные андроиды выполняют любые прихоти посетителей,
чтобы те чувствовали безнаказанность и полную свободу действий.', 'img/wworld.jpg', 'img/ww_full.jpg', '2016-10-2', 1,
        1),
       ('Бумажный домик',
        'История о преступниках, решивших ограбить Королевский монетный двор Испании и украсть 2,4 млрд евро.',
        'img/papel.jpg', 'img/lcdp_full.jpg', '2017-5-2', 3, 2),
       ('Острые козырьки', 'Британский сериал о криминальном мире Бирмингема 20-х годов прошлого века, в котором многолюдная семья Шелби стала одной из самых жестоких и влиятельных гангстерских банд послевоенного времени.
       Фирменным знаком группировки, промышлявшей грабежами и азартными играми, стали зашитые в козырьки лезвия.',
        'img/breb.jpg', 'img/pb_full.jpg', '2013-09-12', 2, 3);

insert into comment ()
values (DEFAULT, 1, 1, 'Коммннтарий1', DEFAULT),
       (DEFAULT, 3, 2, 'Коммннтарий2', DEFAULT),
       (DEFAULT, 2, 1, 'Коммннтарий3', DEFAULT);


insert into viewed (`user_id`, `serial_id`)
values (1, 1),
       (2, 3),
       (3, 2);

insert into liked (`user_id`, `serial_id`)
values (1, 3),
       (2, 1),
       (3, 2);


insert into serial_genre (serial_id, genre_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 2);