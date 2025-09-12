INSERT INTO Pais (nombrePais) VALUES
('Argentina'), ('Bolivia'), ('Brasil'), ('Chile'), ('Colombia'),
('Costa Rica'), ('Cuba'), ('Ecuador'), ('El Salvador'), ('Guatemala'),
('Honduras'), ('México'), ('Nicaragua'), ('Panamá'), ('Paraguay'),
('Perú'), ('República Dominicana'), ('Uruguay'), ('Venezuela'),
('Alemania'), ('España'), ('Francia'), ('Italia'), ('Portugal'),
('Reino Unido'), ('Países Bajos'), ('Bélgica'), ('Suiza'), ('Suecia'),
('Noruega'), ('Dinamarca'), ('Finlandia'), ('Austria'), ('Grecia'),
('Polonia'), ('Ucrania'), ('Rusia'), ('Irlanda'), ('Turquía'),
('Hungría'), ('República Checa'), ('Rumanía'), ('Bulgaria'), ('Croacia'),
('Estados Unidos'), ('Canadá'), ('Puerto Rico'), ('Jamaica'),
('Trinidad y Tobago'), ('Bahamas'), ('Barbados'), ('Haití'),
('China'), ('Japón'), ('Corea del Sur'), ('India'), ('Indonesia'),
('Tailandia'), ('Vietnam'), ('Malasia'), ('Filipinas'), ('Singapur'),
('Israel'), ('Arabia Saudita'), ('Emiratos Árabes Unidos'), ('Qatar'),
('Pakistán'), ('Bangladés'), ('Sri Lanka'), ('Nepal'), ('Kazajistán'),
('Egipto'), ('Sudáfrica'), ('Nigeria'), ('Kenia'), ('Marruecos'),
('Argelia'), ('Túnez'), ('Ghana'), ('Etiopía'), ('Tanzania'),
('Australia'), ('Nueva Zelanda'), ('Fiyi'), ('Papúa Nueva Guinea'),
('Islandia'), ('Luxemburgo'), ('Mónaco'), ('Andorra'), ('San Marino'),
('Chipre'), ('Malta'), ('Liechtenstein'), ('Estonia'), ('Letonia'),
('Lituania'), ('Eslovaquia'), ('Eslovenia'), ('Serbia'), ('Bosnia y Herzegovina'),
('Albania'), ('Macedonia del Norte'), ('Montenegro'), ('Kosovo'), ('Bielorrusia'),
('Moldavia'), ('Georgia'), ('Armenia'), ('Azerbaiyán'), ('Kirguistán'),
('Turkmenistán'), ('Tayikistán'), ('Uzbekistán'), ('Mongolia'), ('Bután'),
('Myanmar'), ('Camboya'), ('Laos'), ('Brunei'), ('Timor Oriental'), ('Maldivas'),
('Omán'), ('Kuwait'), ('Bahréin'), ('Jordania'), ('Líbano'), ('Siria'), ('Irak'),
('Irán'), ('Afganistán'), ('Yemen'), ('Angola'), ('Mozambique'), ('Zambia'),
('Zimbabue'), ('Botsuana'), ('Namibia'), ('Uganda'), ('Ruanda'), ('Burundi'),
('Malaui'), ('Madagascar'), ('Mauricio'), ('Seychelles'), ('Senegal'), ('Camerún'),
('Costa de Marfil'), ('Liberia'), ('Sierra Leona'), ('Togo'), ('Benín'), ('Níger'),
('Chad'), ('Sudán'), ('Sudán del Sur'), ('Somalia'), ('Yibuti'), ('Eritrea'),
('Libia'), ('Mauritania'), ('Malí'), ('Burkina Faso'), ('Guinea'), ('Guinea-Bisáu'),
('Guinea Ecuatorial'), ('Gabón'), ('República del Congo'), ('República Democrática del Congo'),
('República Centroafricana'), ('Santo Tomé y Príncipe'), ('Cabo Verde'), ('Gambia'),
('Lesoto'), ('Suazilandia'), ('Comoras'), ('Samoa'), ('Tonga'), ('Vanuatu'),
('Islas Salomón'), ('Kiribati'), ('Tuvalu'), ('Palaos'), ('Micronesia'), ('Marshall'),
('Nauru'), ('Belice'), ('Guyana'), ('Surinam'), ('Guayana Francesa'), ('Granada'),
('San Vicente y las Granadinas'), ('Santa Lucía'), ('Antigua y Barbuda'), ('Dominica'),
('San Cristóbal y Nieves'), ('Anguila'), ('Bermudas'), ('Islas Caimán'), ('Turcas y Caicos'),
('Islas Vírgenes Británicas'), ('Islas Vírgenes Americanas'), ('Groenlandia'), ('San Pedro y Miquelón');

INSERT INTO Categoria (nombreCategoria) VALUES
('Tecnología'), ('Arte'), ('Música'), ('Cine'), ('Diseño'),
('Moda'), ('Comida'), ('Publicación'), ('Juegos'), ('Educación'),
('Salud'), ('Deportes'), ('Medio Ambiente'), ('Animales'), ('Comunidad');

INSERT INTO usuario (email, password, nombre, apellido, telefono, fechaNacimiento) 
VALUES 
('agus@gmail.com', '$2a$10$1nARN7oTNvK1LwhoHmKpJuqcHTtXf5EWfeFjTrvzMY0T7By2KsIwm', 'Agustin', 'Santinelli', NULL, NULL),
('marto@gmail.com', '$2a$10$DgodzZftMiI13cQRHzQmCuIMGFuT/UM4CB/SVFr1LQvJFBOLaWf5e', 'Martin', 'Ratti', NULL, NULL),
('sole@gmail.com', '$2a$10$iaSK7kr6L33AA0RdaST7UeGr.fEIkb55b6rN2tmmbfXCdnofneZqO', 'Soledad', 'Pastorutti', '+54934109898', '1904-01-07'),
('joaco@gmail.com', '$2a$10$k9/iC4x8FTSx272T50gZ.e.gPDBtdu/9rNwDiQcTlHfihjfmdUbfG', 'Joaquin', 'Peralta', '4939292922', '2000-11-11');



INSERT INTO contacto (nombre, email, asunto, mensaje, fecha, visto) VALUES
('María González', 'maria.gonzalez@email.com', 'Consulta sobre servicios', 'Buenos días, me gustaría recibir información sobre los servicios que ofrecen y sus precios. ¿Podrían enviarme un catálogo?', '2024-01-15 09:30:00', FALSE),
('Carlos Rodríguez', 'carlos.rod@empresa.com', 'Problema con mi cuenta', 'Hola, no puedo acceder a mi cuenta desde ayer. Me aparece error de contraseña aunque estoy seguro de que la estoy escribiendo bien.', '2024-01-16 14:22:15', FALSE),
('Ana Martínez', 'ana.martinez@gmail.com', 'Solicitud de presupuesto', 'Estoy interesada en el servicio premium para mi negocio. Necesito un presupuesto detallado para presentarlo a mi junta directiva.', '2024-01-17 11:45:30', FALSE);