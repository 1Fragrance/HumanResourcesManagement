INSERT `position` (id, title, minSalary, maxSalary) VALUES
(1, 'Разработчик', 300, 4000),
(2, 'Тестировщик', 200, 2000),
(3, 'Менеджер проекта', 500, 5000),
(4, 'Бизнес-аналитик', 250, 2500),
(5, 'Рекрутер', 200, 2000),
(6, 'Руководитель департамента', 3000, 10000),
(7, 'Менеджер по продажам', 200, 1500),
(8, 'Юрист', 200, 2000),
(9, 'Менеджер по персоналу', 150, 1000),
(10, 'Менеджер по работе с клиентами', 200, 1500),
(11, 'Менеджер по развитию бизнеса', 400, 3000);
 
INSERT `office` (id, internalName, streetAddress, postalCode, country, city) VALUES
(1, 'ТВ-13', 'ул. Тверская, дом 13', '125009', 'Россия', 'Москва'),
(2, 'ЧГ-40', 'ул. Чигорина, дом 40, офис №3', '630066', 'Украина', 'Киев'),
(3, 'СР-15', 'ул. Сторожовская, дом 15', '220002', 'Республика Беларусь', 'Минск');

INSERT `department` (id, name, officeId) VALUES
(1, 'Отдел разработки программного обеспечения', 3),
(2, 'Отдел маркетинга и PR', 2),
(3, 'HR-отдел', 3),
(4, 'Юридический отдел', 1),
(5, 'Отдел развития', 1);


-- id = 1, 6, 12 are admins
INSERT `employee` (id, firstname, lastname, patronymic, phonenumber, hireDate, salary,
				   email, username, password, isAdmin, status, positionId, departmentId) VALUES

(1, 'Иосиф', 'Быков', 'Аркадьевич', '+7 (955) 378-66-40', (SELECT CURRENT_DATE - interval 200 DAY), 3000, 'IosifBykov689@gmail.com', 'IosifBykov689', 'SkCr0hEvIyP8', 1, 1, 1, 1),
(2, 'Елизавета', 'Русина', 'Егоровна', '+7 (911) 271-74-99', (SELECT CURRENT_DATE - interval 40 DAY), 1000, 'ElizavetaRusina513@gmail.com', 'ElizavetaRusina513', 'YYayUUOUyztc', 0, 1, 2, 1),
(3, 'Кристина ', 'Островская ', 'Григорьевна', '+7 (959) 957-92-25', (SELECT CURRENT_DATE - interval 25 DAY), 3000, 'KristinaOstrovskaya472@gmail.com', 'KristinaOstrovskaya472', 'tPrCGjv1o3GU', 0, 1, 3, 1),
(4, 'Ефим', 'Чехов', 'Матвеевич', '+7 (996) 205-44-44', (SELECT CURRENT_DATE - interval 100 DAY), 2000, 'SimonChehov510@gmail.com', 'SimonChehov510', 'Pn9h2gfgmzYG', 0, 1, 4, 1),
(5, 'Софья', 'Сидорова', 'Петровна', '+7 (913) 865-29-56', (SELECT CURRENT_DATE - interval 100 DAY), 1500, 'SofyaSidorova504@gmail.com', 'SofyaSidorova504', '4saq1AdSnPsa', 0, 1, 5, 3),
(6, 'Василиса', 'Прокофьева', 'Львовна', '+7 (946) 997-22-57', (SELECT CURRENT_DATE - interval 200 DAY), 5000, 'VasilisaProkofeva808@gmail.com', 'VasilisaProkofeva808', 'kVpnkdUxkvpP', 1, 1, 6, 1),
(7, 'Феликс', 'Игнатьев', 'Матвеевич', '+7 (967) 351-80-09', (SELECT CURRENT_DATE - interval 15 DAY), 300, 'FeliksIgnatev963@gmail.com', 'FeliksIgnatev963', 'gOMQlgta68Y7', 0, 1, 7, 5),
(8, 'Ермолай', 'Царев', 'Валерьевич', '+7 (920) 530-17-30', (SELECT CURRENT_DATE - interval 34 DAY), 1000, 'ErmolayTsarev834@gmail.com', 'ErmolayTsarev834', 'wHYmmTmALw6z', 0, 1, 8, 4),
(9, 'Катерина', 'Чистякова ', 'Викторовна', '+7 (997) 103-85-65', (SELECT CURRENT_DATE - interval 56 DAY), 500, 'KaterinaChistyakova639@gmail.com', 'KaterinaChistyakova639', 'wHYmmTmALw6z', 0, 1, 9, 3),
(10, 'Ольга', 'Павлова', 'Робертовна', '+7 (992) 986-64-56', (SELECT CURRENT_DATE - interval 23 DAY), 700, 'OlgaPavlova438@gmail.com', 'OlgaPavlova438', 'ggoreVPUDgi7', 0, 1, 10, 2),
(11, 'Кондрат', 'Михайлов', 'Андреевич', '+7 (902) 635-97-13', (SELECT CURRENT_DATE - interval 33 DAY), 3000, 'KondratMihaylov744@gmail.com', 'KondratMihaylov744', '7Je3dioZz62A', 0, 1, 11, 5),
(12, 'admin', 'admin', 'admin', '+7 (777) 777-77-77', (SELECT CURRENT_DATE - interval 1 DAY), 3000, 'admin@gmail.com', 'admin', 'admin', 1, 1, 1, 1);


INSERT `positionHistory` (id, startDate, endDate, employeeId, positionId, departmentId) VALUES
(1,(SELECT CURRENT_DATE - interval 200 DAY), NULL, 1, 1, 1),
(2,(SELECT CURRENT_DATE - interval 40 DAY), NULL, 2, 2, 1),
(3,(SELECT CURRENT_DATE - interval 25 DAY), NULL, 3, 3, 1),
(4,(SELECT CURRENT_DATE - interval 100 DAY), NULL, 4, 4, 1),
(5,(SELECT CURRENT_DATE - interval 100 DAY), NULL, 5, 5, 3),
(6,(SELECT CURRENT_DATE - interval 200 DAY), NULL, 6, 6, 1),
(7,(SELECT CURRENT_DATE - interval 15 DAY), NULL, 7, 7, 5),
(8,(SELECT CURRENT_DATE - interval 34 DAY), NULL, 8, 8, 4),
(9,(SELECT CURRENT_DATE - interval 56 DAY), NULL, 9, 9, 3),
(10,(SELECT CURRENT_DATE - interval 23 DAY), NULL, 10, 10, 2),
(11,(SELECT CURRENT_DATE - interval 33 DAY), NULL, 11, 11, 5),
(12,(SELECT CURRENT_DATE - interval 1 DAY), NULL, 12, 1, 1);


