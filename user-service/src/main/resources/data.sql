INSERT INTO admins (id, name, password, type_of_user, username) VALUES 
(1, 'Ana Martins', 'gorgeous', 'ADMIN', 'alms'),
(2, 'Gabriel Perez', 'gaby99', 'ADMIN', 'gaby'),
(3, 'Rachel Gonzalez', 'mypass123', 'ADMIN', 'rachelie'),
(4, 'Elon Musk', 'tesla4Z', 'ADMIN', 'musky'),
(5, 'Sara Sampaio', 'victoryours', 'ADMIN', 'sarita');

INSERT INTO basic_user (id, name, password, type_of_user, username, city, country, postal_code, street, balance, currency, birth_date, email, number_of_stays, phone_number, registration_date, room_id) VALUES
(1, 'Cristina Alvarez', 'cris123', 'BASIC', 'crisyo', 'Valencia', 'Spain', '25090', 'Calle Beach', '1000', 'USD', '1980-12-09', 'crisalvarez@gmail.com', 1, '676543298', '2020-05-25T12:00:00', null),
(2, 'John Ampere', 'johnhere', 'BASIC', 'bigjohn', 'Barcelona', 'Spain', '22201', 'Calle Monasterio', '3500', 'USD','1969-08-16', 'johnz@gmail.com', 0, '622943298', '2020-04-13T22:00:00', null),
(3, 'Luis Silva', 'luizito', 'BASIC', 'luisforever', 'Madrid', 'Spain', '28030', 'Calle Gran Via', '5250','USD','1972-10-25', 'luissilva@gmail.com', 1, '615654298', '2020-06-15T19:10:00', null),
(4, 'Anne Marie', 'mariemarie', 'BASIC', 'annecool', 'Huelva', 'Spain', '21050', 'Calle Huelva Train', '2400','USD', '1992-03-16', 'annemarie@gmail.com', 2, '628543271', '2020-06-17T15:30:00', null),
(5, 'Jorge Alive', 'jorge17', 'BASIC', 'jorgetito', 'Valencia', 'Spain', '25072', 'Calle Center', '1700','USD', '1991-08-09', 'jorgetito@gmail.com', 3, '618543220', '2020-05-23T11:35:00', null),
(6, 'Victoria Beckman', 'beckmy', 'BASIC', 'victme', 'Toledo', 'Spain', '27280', 'Calle Toledo II', '1090','USD', '1984-07-12', 'victobeauti@gmail.com', 0, '626543250', '2020-05-25T12:00:00', null);


INSERT INTO premium_user (id, name, password, type_of_user, username, city, country, postal_code, street, balance, currency, birth_date, email, number_of_stays, phone_number, registration_date, room_id) VALUES
(1, 'Jennifer Lopez', 'jenifi', 'PREMIUM', 'jeny', 'Sevilla', 'Spain', '26990', 'Calle Isla Magica', '10000', 'USD', '1968-09-09', 'jeny@gmail.com', 4, '696543222', '2020-04-20T10:50:00', null),
(2, 'Mary Simple', 'marymary', 'PREMIUM', 'marysimplify', 'Barcelona', 'Spain', '22009', 'Calle Nearby', '8900', 'USD', '1976-06-06', 'marysimple@gmail.com', 0, '610432981', '2020-05-28T21:32:00', null),
(3, 'Jose Navarro', 'josepequeno', 'PREMIUM', 'josesiempre', 'Madrid', 'Spain', '28033', 'Calle Palacio Real', '7250',  'USD', '1977-11-02', 'josemadrid@gmail.com', 2, '615659673', '2020-07-01T22:10:00', null),
(4, 'Perrie Pie', 'piepie', 'PREMIUM', 'perrie', 'Madrid', 'Spain', '28210', 'Calle Pinar Chamartin', '4800', 'USD', '1994-07-12', 'perrienew@gmail.com', 1, '628500781', '2020-05-27T17:30:00', null),
(5, 'Lauren Cuz', 'cuzlauren', 'PREMIUM', 'lauren', 'Valencia', 'Spain', '25072', 'Calle Vale', '2700', 'USD', '1996-04-09', 'lauren@gmail.com', 3, '618543400', '2020-05-29T09:55:00', null);