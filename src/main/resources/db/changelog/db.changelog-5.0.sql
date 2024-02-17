--liquibase formatted sql

--changeset ayushchenko:1
INSERT INTO client_status (name, created_by)
VALUES ('active', 'YAE'),
       ('passive', 'YAE');

--changeset ayushchenko:2
INSERT INTO client_status_localization (client_status_id, language_code, localized_name)
VALUES (1, 'en', 'active'),
       (1, 'ru', 'активный'),
       (2, 'en', 'passive'),
       (2, 'ru', 'пассивный');

--changeset ayushchenko:3
INSERT INTO shipment_status (name, created_by)
VALUES ('Checking order data', 'YAE'),
       ('Attention(!)', 'YAE'),
       ('Clarifying order details with the sender', 'YAE'),
       ('Sent vehicle to the sender''s warehouse', 'YAE'),
       ('Picked up and en route to the consolidation warehouse', 'YAE'),
       ('consolidation warehouse (Europe)', 'YAE'),
       ('consolidation warehouse (China)', 'YAE'),
       ('consolidation warehouse (USA)', 'YAE'),
       ('In transit (sea)', 'YAE'),
       ('In transit (air)', 'YAE'),
       ('In transit (Europe)', 'YAE'),
       ('Crossing the border', 'YAE'),
       ('Customs clearance', 'YAE'),
       ('Arriving at the pickup warehouse', 'YAE'),
       ('Ready for pickup', 'YAE'),
       ('In storage', 'YAE'),
       ('Delivered', 'YAE'),
       ('Cancelled', 'YAE');

--changeset ayushchenko:4
INSERT INTO shipment_status_localization (shipment_status_id, language_code, localized_name)
VALUES
       (1, 'ru', 'Проверяем данные заявки'),
       (2, 'ru', 'Внимание(!)'),
       (3, 'ru', 'Уточняем детали заказа с отправителем'),
       (4, 'ru', 'Отправили машину на склад отправителя'),
       (5, 'ru', 'Забран и едет на склад консолидации'),
       (6, 'ru', 'Склад консолидации (Европа)'),
       (7, 'ru', 'Склад консолидации (Китай)'),
       (8, 'ru', 'Склад консолидации (США)'),
       (9, 'ru', 'В пути (море)'),
       (10, 'ru', 'В пути (Самолет)'),
       (11, 'ru', 'В пути (Европа)'),
       (12, 'ru', 'Проходит границу'),
       (13, 'ru', 'Таможенное оформление'),
       (14, 'ru', 'Прибывает на склад выдачи'),
       (15, 'ru', 'Готов к выдаче'),
       (16, 'ru', 'На хранении'),
       (17, 'ru', 'Доставлен'),
       (18, 'ru', 'Отменен');

--changeset ayushchenko:5
INSERT INTO administrator (username, email, firstname, lastname, role, created_by)
VALUES ('COM', 'com@u-tlc.ru', 'Алексей', 'Ющенко', 'ADMIN', 'YAE'),
       ('USER', '13yae13@gmail.com', 'Василий', 'Николаевич', 'USER', 'YAE');

--changeset ayushchenko:6
INSERT INTO country (name, code)
VALUES ('AUSTRALIA', 'AU'),
       ('AUSTRIA', 'AT'),
       ('BELGIUM', 'BE'),
       ('BELARUS', 'BY'),
       ('BOSNIA & HERZEGOVINA', 'BA'),
       ('BRASIL', 'BR'),
       ('BRITISH VIRGIN ISLANDS', 'BV'),
       ('BULGARIA', 'BG'),
       ('BANGLADESH', 'BD'),
       ('CANADA', 'CA'),
       ('CHINA', 'CN'),
       ('CROATIA', 'HR'),
       ('CYPRUS', 'CY'),
       ('CZECH REPUBLIC', 'CZ'),
       ('DENMARK', 'DM'),
       ('ENGLAND', 'UK'),
       ('ESTONIA', 'EE'),
       ('FINLAND', 'FI'),
       ('FRANCE', 'FR'),
       ('GERMANY', 'DE'),
       ('GREECE', 'GR'),
       ('HUNGARY', 'HU'),
       ('INDIA', 'IN'),
       ('INDONESIA', 'ID'),
       ('IRELAND', 'IR'),
       ('ISRAIL', 'IL'),
       ('ITALY', 'IT'),
       ('JAPAN', 'JP'),
       ('KAZAHSTAN', 'KZ'),
       ('LATVIA', 'LV'),
       ('LIECHTENSTEIN', 'LI'),
       ('LITHUANIA', 'LT'),
       ('MALAYSIA', 'MY'),
       ('MONTENEGRO', 'MO'),
       ('NEPAL', 'NP'),
       ('NETHERLANDS', 'NL'),
       ('NEW ZEALAND', 'NZ'),
       ('PAKISTAN', 'PK'),
       ('PHILIPPINES', 'PH'),
       ('POLAND', 'PL'),
       ('PORTUGAL', 'PT'),
       ('RUSSIA', 'RU'),
       ('SLOVAKIA', 'SK'),
       ('SLOVENIA', 'SL'),
       ('SPAIN', 'ES'),
       ('SWEDEN', 'SE'),
       ('SWITZERLAND', 'CH'),
       ('TAIWAN', 'TW'),
       ('THAILAND', 'TH'),
       ('TURKEY', 'TR'),
       ('UNITED ARABIAN EMIRATES', 'AE'),
       ('UNITED STATES OF AMERICA', 'US'),
       ('UZBEKISTAN', 'UZ'),
       ('VIETNAM', 'VN');

--changeset ayushchenko:7
INSERT INTO country_localization (country_id, language_code, localized_name)
VALUES (1, 'en', 'AUSTRALIA'),
       (1, 'ru', 'АВСТРАЛИЯ'),
       (2, 'en', 'AUSTRIA'),
       (2, 'ru', 'АВСТРИЯ'),
       (3, 'en', 'BELGIUM'),
       (3, 'es', 'BELGICA'),
       (3, 'it', 'BELGIO'),
       (3, 'ru', 'БЕЛЬГИЯ'),
       (4, 'en', 'BELARUS'),
       (4, 'ru', 'БЕЛАРУСЬ'),
       (5, 'en', 'BOSNIA & HERZEGOVINA'),
       (5, 'ru', 'БОСНИЯ И ГЕРЦЕГОВИНА'),
       (6, 'en', 'BRAZIL'),
       (6, 'ru', 'БРАЗИЛИЯ'),
       (7, 'en', 'BRITISH VIRGIN ISLANDS'),
       (7, 'ru', 'БРИТАНСКИЕ ВИРГИНСКИЕ ОСТРОВА'),
       (8, 'en', 'BULGARIA'),
       (8, 'ru', 'БОЛГАРИЯ'),
       (9, 'en', 'BANGLADESH'),
       (9, 'ru', 'БАНГЛАДЕШ'),
       (10, 'en', 'CANADA'),
       (10, 'ru', 'КАНАДА'),
       (11, 'en', 'CHINA'),
       (11, 'ru', 'КИТАЙ'),
       (12, 'en', 'CROATIA'),
       (12, 'ru', 'ХОРВАТИЯ'),
       (13, 'en', 'CYPRUS'),
       (13, 'ru', 'КИПР'),
       (14, 'en', 'CZECH REPUBLIC'),
       (14, 'ru', 'ЧЕШСКАЯ РЕСПУБЛИКА'),
       (15, 'en', 'DENMARK'),
       (15, 'ru', 'ДАНИЯ'),
       (16, 'en', 'ENGLAND'),
       (16, 'ru', 'АНГЛИЯ'),
       (17, 'en', 'ESTONIA'),
       (17, 'ru', 'ЭСТОНИЯ'),
       (18, 'en', 'FINLAND'),
       (18, 'ru', 'ФИНЛЯНДИЯ'),
       (19, 'en', 'FRANCE'),
       (19, 'ru', 'ФРАНЦИЯ'),
       (20, 'en', 'GERMANY'),
       (20, 'ru', 'ГЕРМАНИЯ'),
       (21, 'en', 'GREECE'),
       (21, 'ru', 'ГРЕЦИЯ'),
       (22, 'en', 'HUNGARY'),
       (22, 'ru', 'ВЕНГРИЯ'),
       (23, 'en', 'INDIA'),
       (23, 'ru', 'ИНДИЯ'),
       (24, 'en', 'INDONESIA'),
       (24, 'ru', 'ИНДОНЕЗИЯ'),
       (25, 'en', 'IRELAND'),
       (25, 'ru', 'ИРЛАНДИЯ'),
       (26, 'en', 'ISRAEL'),
       (26, 'ru', 'ИЗРАИЛЬ'),
       (27, 'en', 'ITALY'),
       (27, 'ru', 'ИТАЛИЯ'),
       (28, 'en', 'JAPAN'),
       (28, 'ru', 'ЯПОНИЯ'),
       (29, 'en', 'KAZAHSTAN'),
       (29, 'ru', 'КАЗАХСТАН'),
       (30, 'en', 'LATVIA'),
       (30, 'ru', 'ЛАТВИЯ'),
       (31, 'en', 'LIECHTENSTEIN'),
       (31, 'ru', 'ЛИХТЕНШТЕЙН'),
       (32, 'en', 'LITHUANIA'),
       (32, 'ru', 'ЛИТВА'),
       (33, 'en', 'MALAYSIA'),
       (33, 'ru', 'МАЛАЙЗИЯ'),
       (34, 'en', 'MONTENEGRO'),
       (34, 'ru', 'ЧЕРНОГОРИЯ'),
       (35, 'en', 'NEPAL'),
       (35, 'ru', 'НЕПАЛ'),
       (36, 'en', 'NETHERLANDS'),
       (36, 'ru', 'НИДЕРЛАНДЫ'),
       (37, 'en', 'NEW ZEALAND'),
       (37, 'ru', 'НОВАЯ ЗЕЛАНДИЯ'),
       (38, 'en', 'PAKISTAN'),
       (38, 'ru', 'ПАКИСТАН'),
       (39, 'en', 'PHILIPPINES'),
       (39, 'ru', 'ФИЛИППИНЫ'),
       (40, 'en', 'POLAND'),
       (40, 'ru', 'ПОЛЬША'),
       (41, 'en', 'PORTUGAL'),
       (41, 'ru', 'ПОРТУГАЛИЯ'),
       (42, 'en', 'RUSSIA'),
       (42, 'ru', 'РОССИЯ'),
       (43, 'en', 'SLOVAKIA'),
       (43, 'ru', 'СЛОВАКИЯ'),
       (44, 'en', 'SLOVENIA'),
       (44, 'ru', 'СЛОВЕНИЯ'),
       (45, 'en', 'SPAIN'),
       (45, 'ru', 'ИСПАНИЯ'),
       (46, 'en', 'SWEDEN'),
       (46, 'ru', 'ШВЕЦИЯ'),
       (47, 'en', 'SWITZERLAND'),
       (47, 'ru', 'ШВЕЙЦАРИЯ'),
       (48, 'en', 'TAIWAN'),
       (48, 'ru', 'ТАЙВАНЬ'),
       (49, 'en', 'THAILAND'),
       (49, 'ru', 'ТАИЛАНД'),
       (50, 'en', 'TURKEY'),
       (50, 'ru', 'ТУРЦИЯ'),
       (51, 'en', 'UNITED ARABIAN EMIRATES'),
       (51, 'ru', 'ОБЪЕДИНЕННЫЕ АРАБСКИЕ ЭМИРАТЫ'),
       (52, 'en', 'UNITED STATES OF AMERICA'),
       (52, 'ru', 'СОЕДИНЕННЫЕ ШТАТЫ АМЕРИКИ'),
       (53, 'en', 'UZBEKISTAN'),
       (53, 'ru', 'УЗБЕКИСТАН'),
       (54, 'en', 'VIETNAM'),
       (54, 'ru', 'ВЬЕТНАМ');

--changeset ayushchenko:8
INSERT INTO manufacturer (name, country_id, created_by)
VALUES ('ALTAMODA', 27, 'YAE'),
       ('ANGELO CAPPELLINI', 27, 'YAE');

--changeset ayushchenko:9
INSERT INTO warehouse (name, country_id, created_by)
VALUES ('P & B Terminal', 30, 'YAE'),
       ('ABX Terminal', 30, 'YAE'),
       ('ANDREETTO & ZANON', 27, 'YAE'),
       ('AN TRANSPORTS', 30, 'YAE'),
       ('ARROW', 30, 'YAE'),
       ('EXCELLENCE', 45, 'YAE'),
       ('IHS', 20, 'YAE'),
       ('KONEKTA CARGO', 32, 'YAE'),
       ('LASL', 52, 'YAE'),
       ('MINISPED', 45, 'YAE'),
       ('MPG INTERNATIONAL', 20, 'YAE'),
       ('N LOGISTIKS', 30, 'YAE'),
       ('NTZ', 20, 'YAE'),
       ('RESKO', 32, 'YAE'),
       ('ROSTERMINALS', 30, 'YAE'),
       ('STO EXPRESS', 42, 'YAE'),
       ('SVO GROUP UAB', 30, 'YAE'),
       ('TRUST LINE', 42, 'YAE'),
       ('Ю-ТЛК МОСКВА', 42, 'YAE'),
       ('VINGES', 32, 'YAE'),
       ('VLADIVOSTOK 1', 42, 'YAE');

--changeset ayushchenko:10
INSERT INTO pick_up_point (name, country_id, address)
VALUES ('UTL MOSCOW', 42, 'Russia, Korolyov, Yaroslavsky proezd, 3а'),
       ('UTL SAINT-PETERSBURG', 42, 'Russia, Saint-Petersburg, Moscovskaya ul., 13');

--changeset ayushchenko:11
INSERT INTO pick_up_point_localization(pick_up_point_id, language_code, localized_name, localized_address)
VALUES (1, 'ru', 'Ю-ТЛК Москва', 'Россия, г. Королёв, Ярославский проезд, 3а'),
       (2, 'ru', 'Ю-ТЛК Санкт-Петербург', 'Россия, Санкт-Петербург, ул. Московская, 13');

--changeset ayushchenko:12
INSERT INTO business_type (name, description, created_by)
VALUES ('Microenterprise', 'Up to 15 employees or turnover up to 120 million rubles', 'YAE'),
       ('Small Business', '16-100 employees or turnover up to 800 million rubles', 'YAE'),
       ('Medium Business', '101-250 employees or turnover up to 2 billion rubles', 'YAE'),
       ('Large Business', 'More than 251 employees or turnover from 2 billion rubles', 'YAE'),
       ('Individual', 'Private individual making purchases for personal, non-commercial purposes', 'YAE');

--changeset ayushchenko:13
INSERT INTO business_type_localization(business_type_id, language_code, localized_name, localized_description)
VALUES (1, 'ru','Микропредприятие', 'до 15 человек или оборот до 120 млн рублей'),
       (2, 'ru','Малый бизнес', '16-100 человек или оборот до 800 млн рублей'),
       (3, 'ru','Средний бизнес', '101-250 человек или оборот до 2 млрд рублей'),
       (4, 'ru','Крупный бизнес', '251< человек или оборот от 2 млрд рублей'),
       (5, 'ru','Физлицо', 'Частное лицо, совершающее покупки для личных, некоммерческих целей');

--changeset ayushchenko:14
INSERT INTO industry_type (name, description, created_by)
VALUES ('Manufacturing', 'The industry involved in the production of goods', 'YAE'),
       ('Retail', 'The sale of goods directly to end consumers', 'YAE'),
       ('Pharmaceuticals', 'The industry engaged in the development, production, and marketing of drugs', 'YAE'),
       ('Automotive Industry', 'The industry involved in the production of automobiles', 'YAE'),
       ('Technology and Electronics', 'The industry related to the development and production of technological and electronic devices', 'YAE'),
       ('Construction', 'The industry involved in the building of structures and buildings', 'YAE'),
       ('Food Industry', 'The industry involved in the production of food products', 'YAE'),
       ('eCommerce', 'Electronic commerce, associated with buying and selling goods or services over the internet', 'YAE'),
       ('Agricultural Sector', 'The industry involved in agriculture and processing of agricultural products', 'YAE');

--changeset ayushchenko:15
INSERT INTO industry_type_localization(industry_type_id, language_code, localized_name, localized_description)
VALUES
    (1,'ru', 'Производство', 'Отрасль, связанная с производством товаров'),
    (2,'ru', 'Розничная торговля', 'Продажа товаров напрямую конечным потребителям'),
    (3,'ru', 'Фармацевтика', 'Отрасль, занимающаяся разработкой, производством и продажей лекарственных средств'),
    (4,'ru', 'Автомобильная промышленность', 'Отрасль, занимающаяся производством автомобилей'),
    (5,'ru', 'Технологии и электроника', 'Отрасль, связанная с разработкой и производством технологических и электронных устройств'),
    (6,'ru', 'Строительство', 'Отрасль, занимающаяся возведением зданий и сооружений'),
    (7,'ru', 'Пищевая Промышленность', 'Отрасль, занимающаяся производством пищевых продуктов'),
    (8,'ru', 'eCommerce', 'Электронная коммерция, связанная с покупкой и продажей товаров или услуг через интернет'),
    (9,'ru', 'Агропромышленный Сектор', 'Отрасль, занимающаяся сельским хозяйством и обработкой сельскохозяйственной продукции');

--changeset ayushchenko:16
INSERT INTO priority (name, description, created_by)
VALUES ('Standard', 'Normal priority level with standard processing times', 'YAE'),
       ('High', 'Elevated priority level with expedited processing times', 'YAE');

--changeset ayushchenko:17
INSERT INTO priority_localization(priority_id, language_code, localized_name, localized_description)
VALUES
    (1, 'ru', 'Стандартный', 'Обычный уровень приоритета со стандартными сроками обработки'),
    (2, 'ru', 'Высокий', 'Повышенный уровень приоритета с ускоренными сроками обработки');

--changeset ayushchenko:18
INSERT INTO agent (name, phone, commentary)
VALUES ('Test name 1', '+7 383 912 38 58', 'This is a test commentary'),
       ('Test name 2', '+39 5383 912 38 58', 'This is a test commentary');

--changeset ayushchenko:19
INSERT INTO invoice_status (name)
VALUES ('Paid'),
       ('Unpaid'),
       ('Overdue');

--changeset ayushchenko:20
INSERT INTO invoice_status_localization(invoice_status_id, language_code, localized_name)
VALUES
    (1, 'ru', 'Оплачен'),
    (2, 'ru', 'Неоплачен'),
    (3, 'ru', 'Просрочен');

--changeset ayushchenko:21
INSERT INTO route_status (name)
VALUES ('Scheduled'),
       ('Loading'),
       ('Port of departure'),
       ('Port of destination'),
       ('Export Clearance'),
       ('Transit (EU)'),
       ('Transit (Baltics)'),
       ('EU-Russia Border'),
       ('Transit (Asia)'),
       ('Transit (Russia)'),
       ('Import Clearance'),
       ('Completed'),
       ('Cancelled');

--changeset ayushchenko:22
INSERT INTO route_status_localization(route_status_id, language_code, localized_name)
VALUES
    (1, 'ru', 'Заказан'),
    (2, 'ru', 'Погрузка'),
    (3, 'ru', 'Порт отправления'),
    (4, 'ru', 'Порт назначения'),
    (5, 'ru', 'Экспортное таможенное оформление'),
    (6, 'ru', 'Транзит (Европа)'),
    (7, 'ru', 'Транзит (Прибалтика)'),
    (8, 'ru', 'Граница ЕС-Россия'),
    (9, 'ru', 'Транзит (Азия)'),
    (10, 'ru', 'Транзит (Россия)'),
    (11, 'ru', 'Импортное таможенное оформление'),
    (12, 'ru', 'Завершен'),
    (13, 'ru', 'Отменен');

--changeset ayushchenko:23
INSERT INTO client (name, full_name, status_id, business_type_id, industry_type_id, address)
VALUES ('Client 1', 'Client 1 full name', 1, 1, 1, 'Moscow, Tverskaya str., 1');

--changeset ayushchenko:24
INSERT INTO route (identification_number, status_id, transport_type, country_of_departure_id, country_of_destination_id, customs_post)
VALUES ('01-IT-24012024', 1, '90', 1, 2, 'KL');

--changeset ayushchenko:25
INSERT INTO shipment (status_id, client_id, route_id, internal_comment, client_comment, delivery_type, destination_address, client_pcs, client_volume_m3, client_weight_kg, shipment_type, shipment_description, country_of_departure, manufacturer_id, country_of_destination)
VALUES (1, 1, 1, 'Very expensive and fragile crystal statue. Take good care!', 'molto fragile!', 'truck', 'Mosca, Tverkskaya 1', 1, 10, 2000, 'Fragile cargo', 'crystal statue', 1, 1, 2);

