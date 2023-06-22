insert into authors(author_id, author_surname, author_name)
values ('9a985344-a850-42dc-8685-b9c30ea35c04', 'Gaiman', 'Neil')
on conflict do nothing;
insert into authors(author_id, author_surname, author_name)
values ('4ebf8bd0-271c-424e-bfd6-b465c298747a', 'Bennett', 'Brit')
on conflict do nothing;
insert into authors(author_id, author_surname, author_name)
values ('927a2c48-e46e-434b-9e81-eea37d6bcf68', 'Bardugo', 'Leigh')
on conflict do nothing;
insert into authors(author_id, author_surname, author_name)
values ('bc867430-b040-43fa-b716-100f4aafea83', 'King', 'Stephen')
on conflict do nothing;
insert into authors(author_id, author_surname, author_name)
values ('465bead8-bcfa-4be0-aca9-f1851246fd4d', 'Достоевский', 'Федор')
on conflict do nothing;
insert into authors(author_id, author_surname, author_name)
values ('29f336cf-19b2-4a9e-af7c-7334343430b6', 'Пушкин', 'Александр')
on conflict do nothing;
insert into authors(author_id, author_surname, author_name)
values ('ddf22139-7552-441a-9d72-6ec986f6d1ac', 'Шаламов', 'Варлаам')
on conflict do nothing;
insert into authors(author_id, author_surname, author_name)
values ('87843c0f-0963-4b3c-9e4c-043c6b03fb95', 'Лермонтов', 'Михаил')
on conflict do nothing;
insert into authors(author_id, author_surname, author_name)
values ('c5ffea88-4d5a-4a0a-ae24-52a5a0089a24', 'Flaubert', 'Gustave')
on conflict do nothing;
insert into authors(author_id, author_surname, author_name)
values ('410a20e7-700a-4026-a050-eb3cfe8cd225', 'Twain', 'Mark')
on conflict do nothing;

insert into genres(genre_id, genre_name)
values ('b729fb96-c2f5-4569-a2e8-0f4e9c2a7b06', 'Fantasy')
on conflict do nothing;
insert into genres(genre_id, genre_name)
values ('18f750b6-2290-46ff-abca-726a3d3d83dc', 'Science Fiction')
on conflict do nothing;
insert into genres(genre_id, genre_name)
values ('fae85afa-ea02-42d7-baac-71f726e8c46d', 'Dystopian')
on conflict do nothing;
insert into genres(genre_id, genre_name)
values ('c9364904-960c-41d8-bbd6-722cd0ed8572', 'Action & Adventure')
on conflict do nothing;
insert into genres(genre_id, genre_name)
values ('0d18f7b8-1dbe-4c3b-8f4a-cd7c2c7aade1', 'Mystery')
on conflict do nothing;

insert into books (book_id, book_name, author_id, genre_id)
values ('3ca0bb29-9f4f-404f-9347-c58cd0c87c2d', 'American Gods', '9a985344-a850-42dc-8685-b9c30ea35c04',
        'b729fb96-c2f5-4569-a2e8-0f4e9c2a7b06')
on conflict do nothing;
insert into books (book_id, book_name, author_id, genre_id)
values ('adb6c2ee-1c32-4fb3-9cae-c29f7b013f4c', 'The Masters of Wisdom', '4ebf8bd0-271c-424e-bfd6-b465c298747a',
        '18f750b6-2290-46ff-abca-726a3d3d83dc')
on conflict do nothing;
insert into books (book_id, book_name, author_id, genre_id)
values ('f9a8c40e-151a-48ec-9455-f1302d2df539', 'Demon in the wood', '927a2c48-e46e-434b-9e81-eea37d6bcf68',
        '18f750b6-2290-46ff-abca-726a3d3d83dc')
on conflict do nothing;
insert into books (book_id, book_name, author_id, genre_id)
values ('d0db734e-3008-46ef-ab20-4895310c3124', 'Misery', 'bc867430-b040-43fa-b716-100f4aafea83',
        'c9364904-960c-41d8-bbd6-722cd0ed8572')
on conflict do nothing;
insert into books (book_id, book_name, author_id, genre_id)
values ('e9ad3d8f-5aee-42de-8b78-0e565c99b44d', 'Идиот', '465bead8-bcfa-4be0-aca9-f1851246fd4d',
        'c9364904-960c-41d8-bbd6-722cd0ed8572')
on conflict do nothing;

insert into comments (comment_id, book_id, content)
values (gen_random_uuid(), '3ca0bb29-9f4f-404f-9347-c58cd0c87c2d', 'Брал автора первый раз наугад, исходя их рецензий и краткого соавторства Геймана с глубокоуважаемым Пратчеттом. Неожиданно, роман оказался довольно грязный. Я не ханжа, но детальное описание половых актов с мифическими существами или описание где у женщины-зомби завелись трупные черви, мне претят. Меня не интересует вкус спермы джина и если мне захочется почитать что то подобное, наверно, я выберу произведения с картинками совсем другого жанра. Все разговоры о том, что роман совсем не про это, а наоборот полон метафор и философии, остаются только разговорами.
Боги в романе редко работают по специальности, верша судьбы людей. В основном они аппетитно что то едят, спят с людьми, ностальгируют, мошенничают, тырят мелочь, бухают и безостановочно матерятся. Кстати, есть в книге и русские (славянские) боги, которые курят, зажав в кулак заскорузлыми пальцами, папиросы без фильтра и едят борщ. Вобщем опять клюква.
Опуская «философию» произведения, роман оставляет массу сюжетных вопросов. Так если появление Одина еще можно объяснить экспансией норманнов, то как в Америке появились древнеегипетские и другие языческие боги, культ к-рых исчез задолго до начала массовой колонизации Америки европейцами? С таким же успехом по штатам могли бы колесить шумерские боги. Почему кучка эмигрантов заставляет переселиться тут же и их бога, оставив основную паству на родине? Тема клонирования бога по территориальному принципу, прозвучавшая в постскриптуме, звучит, как попытка просто замазать подобные сюжетные ляпы.
Почему практически нет библейских и христианских персонажей? Отсутствие Христа и аллюзии на тему распятия лишь только подтверждают слабую проработку темы и общий кризис идеи. Жажда богов к вере людей сведена к жажде жертвоприношений. Дальше этой глубокой мысли, божий промысл (в данном случае промыслы богов, божков, леприконов и иже с ними), как и мысли автора не идут и становятся туманны, если не сказать бессвязны.
Читается легко, но вся сюжетная канва легко уместилась бы и в четверть от написанного. Концовка ни о чем, в духе средненького детектива, когда автор, не в силах дальше тянуть, просто выкладывает всю правду в монологе героя, к-рому вдруг, по сути, беспричинно, открылось кто же настоящий злодей и убийца.
Задумка интереса, но не нова и автором, на мой взгляд, полностью провалена. На тему сосуществования людей и богов различных религий есть гораздо более интересные вещи. Например, у того же Желязного, к-рому автор посвящает данный роман, есть (совм. с Р.Шекли) отличная трилогия про демона Аззи. Увы, «Американские боги» Геймана - это добротный продукт ремесленника. Вместо красивой вазы тонкой работы для камина в гостиной, к-рую не стыдно показать гостям, получился хороший крепкий ночной горшок, стоящий понятно где.
Отдельно хотелось бы отметить работу переводчиков, не только за отличный перевод всех выкрутасов автора (чего стоят «велосипежья матерь» или «веточность ветки»), но и энциклопедичность знаний как в области религиоведения и культурологии, так и истории штатов.
Прозвучавшие в комментах претензии о тонкой бумаге и отсутствие твердой обложки, считаю скорее большим плюсом для такого талмуда на 600 с лишним страниц. Малый вес, хороший шрифт, удобно читать.

Резюме: Издательству и переводчикам респект. По произведению фатальный незачет. Детям и женщинам к прочтению противопоказано.')
on conflict do nothing;

insert into comments (comment_id, book_id, content)
values (gen_random_uuid(), '3ca0bb29-9f4f-404f-9347-c58cd0c87c2d', 'Долго лежала книга на полке, я к ней присматривалась, большая, толстая...
Уже читала Геймана и ожидала бурного полета фантазии. Американские боги меня не разочаровала. Сама идея живущих богов по сей день среди нас не нова, но как обыгран сюжет! Тень - такой в начале невзрачный с безрадостным детством тюремщик и такой герой в конце...!
Лукавые боги, хитрые и корыстные! и, все равно, что-то в них трогает, почему-то они симпатичны, и даже проникаешься к ним жалостью.
Очень интересно складывается пазл в конце, все мелкие детали, незначительные события, абсолютно всё играет значение, всё взаимосвязано.
Очень интересно.
Конечно, местами жаргончик шокирует, но уж такие видимо реалии этой современной жизни...
Оформление, мягкий переплет, не разваливается, читать не очень удобно, но не представляю сколько бы она весила в твердом переплете...
Рекомендую к прочтению.')
on conflict do nothing;

insert into comments (comment_id, book_id, content)
values (gen_random_uuid(), '3ca0bb29-9f4f-404f-9347-c58cd0c87c2d',
        'Америка - страна иммигрантов. Каждый, кто ехал сюда - по своей ли воле, или нет - тащил за собой своих богов. Это было единственное, что связывало людей с родиной, некая нить, позволяющая думать, что ты не одинок на чужой и неприветливой земле. Поселенцы заселили Америку, обжились, сменились поколения и старые боги оказались не нужны. Появились новые - боги интернета, телевидения и пр. Что делать старым богам? Правильно, начать войну с новыми за свою "паству". Боги у Геймана жадны, циничны и завистливы, добры, обидчивы и сентиментальны. Они обманывают и изворачиваются, но могут любить и могут помочь. Они такие же, как и обычные смертные, но разве что, обладают некой силой. Мне они напоминают греческих богов - те тоже зачастую спускались с Олимпа и хулиганили среди людей. Но у Геймана все не так светло, как у древних греков. Те, хотя могли и поспорить со своими богами, но все же поклонялись им. А здесь - порванные связи, забытые предки, порушенные традиции... Куда катится мир?.. Книга читается очень легко, я вообще люблю Геймана. Издание все же не очень понравилось: красивая мягкая обложка, но пока читала и носила с собой, уголки стали заминаться. Форма книги квадратная, и когда держишь - не очень удобно: из-за того, что обложка мягкая - закрывается, где не нужно, распадается и пр. Заказывала в Лабиринте через подругу - книга с браком: страница с дырой посредине (хорошо, что в "комментариях"). Но это неприятно. Вообще, брак стал встречаться все чаще.')
on conflict do nothing;

insert into roles (role, description)
values ('ROLE_ADMIN', 'Администратор')
on conflict do nothing;
insert into roles (role, description)
values ('ROLE_USER', 'Пользователь')
on conflict do nothing;

-- user 12345, admin 54321
insert into users (login, password, role)
values ('user', '$2a$12$bu.EVyjo8A4yoG3HDQ.Xz.2GByEAdGl0LjYQpiqD3eq1UnfXTVbzu', 'ROLE_USER')
on conflict do nothing;
insert into users (login, password, role)
values ('admin', '$2a$12$mdhw2QY2foV4/ogsAOr8wed6rlsCBbqI2uxeJ5DIxN1EuP.9KuTAO', 'ROLE_ADMIN')
on conflict do nothing;
