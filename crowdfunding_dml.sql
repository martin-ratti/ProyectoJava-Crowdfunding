LOCK TABLES `categoria` WRITE;

INSERT INTO `categoria` VALUES 
(1,  'Tecnología'),
(2,  'Arte'),
(3,  'Música'),
(4,  'Cine'),
(5,  'Diseño'),
(6,  'Moda'),
(7,  'Comida'),
(8,  'Publicación'),
(9,  'Juegos'),
(10, 'Educación'),
(11, 'Salud'),
(12, 'Deportes'),
(13, 'Medio Ambiente'),
(14, 'Animales'),
(15, 'Comunidad');

UNLOCK TABLES;

LOCK TABLES `comentario` WRITE;

LOCK TABLES `contacto` WRITE;

INSERT INTO `contacto` VALUES 
(1, 'María González',    'maria.gonzalez@email.com',    'Consulta sobre servicios',      'Buenos días, me gustaría recibir información sobre los servicios que ofrecen y sus precios. ¿Podrían enviarme un catálogo?', '2024-01-15 09:30:00', 0),
(2, 'Carlos Rodríguez',  'carlos.rod@empresa.com',      'Problema con mi cuenta',        'Hola, no puedo acceder a mi cuenta desde ayer. Me aparece error de contraseña aunque estoy seguro de que la estoy escribiendo bien.', '2024-01-16 14:22:15', 0),
(3, 'Ana Martínez',      'ana.martinez@gmail.com',      'Solicitud de presupuesto',      'Estoy interesada en el servicio premium para mi negocio. Necesito un presupuesto detallado para presentarlo a mi junta directiva.', '2024-01-17 11:45:30', 0);

UNLOCK TABLES;



LOCK TABLES `pais` WRITE;

INSERT INTO `pais` VALUES 
(1,   'Argentina'),
(2,   'Bolivia'),
(3,   'Brasil'),
(4,   'Chile'),
(5,   'Colombia'),
(6,   'Costa Rica'),
(7,   'Cuba'),
(8,   'Ecuador'),
(9,   'El Salvador'),
(10,  'Guatemala'),
(11,  'Honduras'),
(12,  'México'),
(13,  'Nicaragua'),
(14,  'Panamá'),
(15,  'Paraguay'),
(16,  'Perú'),
(17,  'República Dominicana'),
(18,  'Uruguay'),
(19,  'Venezuela'),
(20,  'Alemania'),
(21,  'España'),
(22,  'Francia'),
(23,  'Italia'),
(24,  'Portugal'),
(25,  'Reino Unido'),
(26,  'Países Bajos'),
(27,  'Bélgica'),
(28,  'Suiza'),
(29,  'Suecia'),
(30,  'Noruega'),
(31,  'Dinamarca'),
(32,  'Finlandia'),
(33,  'Austria'),
(34,  'Grecia'),
(35,  'Polonia'),
(36,  'Ucrania'),
(37,  'Rusia'),
(38,  'Irlanda'),
(39,  'Turquía'),
(40,  'Hungría'),
(41,  'República Checa'),
(42,  'Rumanía'),
(43,  'Bulgaria'),
(44,  'Croacia'),
(45,  'Estados Unidos'),
(46,  'Canadá'),
(47,  'Puerto Rico'),
(48,  'Jamaica'),
(49,  'Trinidad y Tobago'),
(50,  'Bahamas'),
(51,  'Barbados'),
(52,  'Haití'),
(53,  'China'),
(54,  'Japón'),
(55,  'Corea del Sur'),
(56,  'India'),
(57,  'Indonesia'),
(58,  'Tailandia'),
(59,  'Vietnam'),
(60,  'Malasia'),
(61,  'Filipinas'),
(62,  'Singapur'),
(63,  'Israel'),
(64,  'Arabia Saudita'),
(65,  'Emiratos Árabes Unidos'),
(66,  'Qatar'),
(67,  'Pakistán'),
(68,  'Bangladés'),
(69,  'Sri Lanka'),
(70,  'Nepal'),
(71,  'Kazajistán'),
(72,  'Egipto'),
(73,  'Sudáfrica'),
(74,  'Nigeria'),
(75,  'Kenia'),
(76,  'Marruecos'),
(77,  'Argelia'),
(78,  'Túnez'),
(79,  'Ghana'),
(80,  'Etiopía'),
(81,  'Tanzania'),
(82,  'Australia'),
(83,  'Nueva Zelanda'),
(84,  'Fiyi'),
(85,  'Papúa Nueva Guinea'),
(86,  'Islandia'),
(87,  'Luxemburgo'),
(88,  'Mónaco'),
(89,  'Andorra'),
(90,  'San Marino'),
(91,  'Chipre'),
(92,  'Malta'),
(93,  'Liechtenstein'),
(94,  'Estonia'),
(95,  'Letonia'),
(96,  'Lituania'),
(97,  'Eslovaquia'),
(98,  'Eslovenia'),
(99,  'Serbia'),
(100, 'Bosnia y Herzegovina'),
(101, 'Albania'),
(102, 'Macedonia del Norte'),
(103, 'Montenegro'),
(104, 'Kosovo'),
(105, 'Bielorrusia'),
(106, 'Moldavia'),
(107, 'Georgia'),
(108, 'Armenia'),
(109, 'Azerbaiyán'),
(110, 'Kirguistán'),
(111, 'Turkmenistán'),
(112, 'Tayikistán'),
(113, 'Uzbekistán'),
(114, 'Mongolia'),
(115, 'Bután'),
(116, 'Myanmar'),
(117, 'Camboya'),
(118, 'Laos'),
(119, 'Brunei'),
(120, 'Timor Oriental'),
(121, 'Maldivas'),
(122, 'Omán'),
(123, 'Kuwait'),
(124, 'Bahréin'),
(125, 'Jordania'),
(126, 'Líbano'),
(127, 'Siria'),
(128, 'Irak'),
(129, 'Irán'),
(130, 'Afganistán'),
(131, 'Yemen'),
(132, 'Angola'),
(133, 'Mozambique'),
(134, 'Zambia'),
(135, 'Zimbabue'),
(136, 'Botsuana'),
(137, 'Namibia'),
(138, 'Uganda'),
(139, 'Ruanda'),
(140, 'Burundi'),
(141, 'Malaui'),
(142, 'Madagascar'),
(143, 'Mauricio'),
(144, 'Seychelles'),
(145, 'Senegal'),
(146, 'Camerún'),
(147, 'Costa de Marfil'),
(148, 'Liberia'),
(149, 'Sierra Leona'),
(150, 'Togo'),
(151, 'Benín'),
(152, 'Níger'),
(153, 'Chad'),
(154, 'Sudán'),
(155, 'Sudán del Sur'),
(156, 'Somalia'),
(157, 'Yibuti'),
(158, 'Eritrea'),
(159, 'Libia'),
(160, 'Mauritania'),
(161, 'Malí'),
(162, 'Burkina Faso'),
(163, 'Guinea'),
(164, 'Guinea-Bisáu'),
(165, 'Guinea Ecuatorial'),
(166, 'Gabón'),
(167, 'República del Congo'),
(168, 'República Democrática del Congo'),
(169, 'República Centroafricana'),
(170, 'Santo Tomé y Príncipe'),
(171, 'Cabo Verde'),
(172, 'Gambia'),
(173, 'Lesoto'),
(174, 'Suazilandia'),
(175, 'Comoras'),
(176, 'Samoa'),
(177, 'Tonga'),
(178, 'Vanuatu'),
(179, 'Islas Salomón'),
(180, 'Kiribati'),
(181, 'Tuvalu'),
(182, 'Palaos'),
(183, 'Micronesia'),
(184, 'Marshall'),
(185, 'Nauru'),
(186, 'Belice'),
(187, 'Guyana'),
(188, 'Surinam'),
(189, 'Guayana Francesa'),
(190, 'Granada'),
(191, 'San Vicente y las Granadinas'),
(192, 'Santa Lucía'),
(193, 'Antigua y Barbuda'),
(194, 'Dominica'),
(195, 'San Cristóbal y Nieves'),
(196, 'Anguila'),
(197, 'Bermudas'),
(198, 'Islas Caimán'),
(199, 'Turcas y Caicos'),
(200, 'Islas Vírgenes Británicas'),
(201, 'Islas Vírgenes Americanas'),
(202, 'Groenlandia'),
(203, 'San Pedro y Miquelón');

UNLOCK TABLES;



LOCK TABLES `usuario` WRITE;

INSERT INTO `usuario` VALUES 
(1, 'agus@gmail.com',  '$2a$10$1nARN7oTNvK1LwhoHmKpJuqcHTtXf5EWfeFjTrvzMY0T7By2KsIwm', 'Agustin', 'Santinelli', NULL,         NULL),
(2, 'marto@gmail.com', '$2a$10$DgodzZftMiI13cQRHzQmCuIMGFuT/UM4CB/SVFr1LQvJFBOLaWf5e', 'Martin',  'Ratti',      NULL,         NULL),
(3, 'sole@gmail.com',  '$2a$10$iaSK7kr6L33AA0RdaST7UeGr.fEIkb55b6rN2tmmbfXCdnofneZqO', 'Soledad', 'Pastorutti', '+54934109898', '1904-01-07'),
(4, 'joaco@gmail.com', '$2a$10$k9/iC4x8FTSx272T50gZ.e.gPDBtdu/9rNwDiQcTlHfihjfmdUbfG', 'Joaquin', 'Peralta',    '4939292922',  '2000-11-11');

UNLOCK TABLES;

LOCK TABLES `proyecto` WRITE;

INSERT INTO `proyecto` VALUES 
(1, 'Donar Agua',                 'Donar agua a africa',                        7000000.00, '2025-09-13', '2030-11-11', 'f371500a-c3d9-4d3c-91c6-d5d6f7599991.jpeg', 'Activo',    64000.00, 166, 3, 11),
(2, 'Horno Volador',              'Estamos diseñando un horno volador. Necesitamos efectivo.', 10000000.00, '2025-09-13', '2026-09-10', 'f3a6bb15-8c29-4229-9fb6-f1a02722ee8c.jpg', 'Activo',    0.00,     1,  4, 1),
(3, 'Buscamos abolir el sistema FIFO', 'El sistema FIFO debe desaparecer',     90000.00,   '2025-09-13', '2025-10-10', 'cf6835e0-c475-4894-aea3-efb9fa289ee4.jpg', 'Pendiente', 0.00,     45, 3, 13);

UNLOCK TABLES;

LOCK TABLES `donacion` WRITE;

INSERT INTO `donacion` VALUES 
(1, 50000.00, 'Ayudemoslosss',       '2025-09-13', 4, 1),
(2, 14000.00, 'Amo este proyecto',   '2025-09-13', 4, 1);

UNLOCK TABLES;

INSERT INTO `comentario` VALUES 
(1, '2025-09-13 01:16:39', 'Gran Proyecto!!!', 1, 4, 'Ignorado'),
(2, '2025-09-13 01:16:47', 'Emocionante',     1, 4, 'Activo');

UNLOCK TABLES;

LOCK TABLES `avance_proyecto` WRITE;

INSERT INTO `avance_proyecto` VALUES 
(1, 1, 'Conseguimos un camion para llevar agua.', '6ecb9bbe-ab70-43d2-a9a4-34556827881e.jpg', '2025-09-13');

UNLOCK TABLES;