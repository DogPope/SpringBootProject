INSERT INTO musician VALUES
(1,'Claude','DeBussy','FRANCE','CLASSICAL','MALE','1862-08-22','1918-03-25'),
(2,'Wolfgang Amadeus','Mozart','AUSTRIA','CLASSICAL','MALE','1756-01-27','1791-12-05'),
(3,'Chopin','Frederic','POLAND','CLASSICAL','MALE','1810-03-01','1849-10-17'),
(4,'Ludwig','van Beethoven','GERMANY','CLASSICAL','MALE','1770-12-17','1827-03-26'),
(5,'Clara','Schumann','GERMANY','CLASSICAL','FEMALE','1819-09-13','1896-05-20'),
(6,'Martha','Argerich','ARGENTINA','CLASSICAL','FEMALE','1941-06-05',NULL),
(7,'Sergei','Rachmaninoff','RUSSIA','CLASSICAL','MALE','1873-04-01','1943-03-28');

INSERT INTO composition VALUES
(1,'Clair De Lune',1905,1),
(2,'Marriage of Figaro',1786,2),
(3,'Waltz in D-Flat Major',1847,3),
(4,'Moonlight Sonata', 1801, 4),
(5,'Quatre Polonaises pour le pianoforte',1831,5),
(6,'Piano Concerto No.3',1909,6);

INSERT INTO instrument VALUES
(1,'ACCORDION'),
(2,'BAGPIPES'),
(3,'BANJO'),
(4,'CLARINET'),
(5,'DRUMS'),
(6,'FLUTE'),
(7,'GUITAR'),
(8,'HARMONICA'),
(9,'ORGAN'),
(10,'PIANO'),
(11,'PIPE'),
(12,'TRUMPET'),
(13,'UKULELE'),
(14,'VIOLIN');

INSERT INTO musician_instrument VALUES
(1,4),
(1,2),
(1,8),
(1,12),
(2,5);