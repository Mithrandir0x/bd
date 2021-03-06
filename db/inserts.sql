INSERT INTO "PAIS" ("NOM") VALUES ('Alemanya');
INSERT INTO "PAIS" ("NOM") VALUES ('Anglaterra');
INSERT INTO "PAIS" ("NOM") VALUES ('Espanya');
INSERT INTO "PAIS" ("NOM") VALUES ('Estats Units');
INSERT INTO "PAIS" ("NOM") VALUES ('França');
INSERT INTO "PAIS" ("NOM") VALUES ('Gales');
INSERT INTO "PAIS" ("NOM") VALUES ('Itàlia');
INSERT INTO "PAIS" ("NOM") VALUES ('Polònia');
INSERT INTO "PAIS" ("NOM") VALUES ('Rússia');
INSERT INTO "PAIS" ("NOM") VALUES ('Xina');
INSERT INTO "PAIS" ("NOM") VALUES ('Índia');

INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Antemare');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Caesar Palace');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Calipolis');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Capri');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Flamingo');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Hilton');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Holiday Inn');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Melià');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Miracle');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Montserrat');
INSERT INTO "HOTEL" ("NOM") VALUES ('Hotel Terramar');

INSERT INTO "SALA" ("HOTEL", "NOM", "AFORAMENT") VALUES ('Hotel Capri', 'Gènova', '25');
INSERT INTO "SALA" ("HOTEL", "NOM", "AFORAMENT") VALUES ('Hotel Antemare', 'Venecia', '10');
INSERT INTO "SALA" ("HOTEL", "NOM", "AFORAMENT") VALUES ('Hotel Montserrat', 'Victòria', '20');
INSERT INTO "SALA" ("HOTEL", "NOM", "AFORAMENT") VALUES ('Hotel Calipolis', 'Victòria', '15');
INSERT INTO "SALA" ("HOTEL", "NOM", "AFORAMENT") VALUES ('Hotel Montserrat', 'Ducci', '12');
INSERT INTO "SALA" ("HOTEL", "NOM", "AFORAMENT") VALUES ('Hotel Montserrat', 'Artemisa', '30');

INSERT INTO "USUARI" ("DNI", "CONTRASENYA", "ROL", "NOM", "GENERE", "PAIS") VALUES ('42516878R','NGLrByu6wcbHzkNI','ORGANITZADOR','Salvador Puig','Sr.','Espanya');

INSERT INTO "TAQUILLER" ("DNI", "CONTRASENYA", "ROL", "NOM", "TELEFON", "GENERE", "PAIS", "ENTRADES_VENUDES") VALUES ('32013847R','ReNQuSz+6IsJuxjd','TAQUILLER','Maria','683372388','Sra.','Espanya','0');
INSERT INTO "TAQUILLER" ("DNI", "CONTRASENYA", "ROL", "NOM", "TELEFON", "GENERE", "PAIS", "ENTRADES_VENUDES") VALUES ('38227338T','5peVLAjGD5cKuYzb','TAQUILLER','Pere','633728377','Sr.','Espanya','0');
INSERT INTO "TAQUILLER" ("DNI", "CONTRASENYA", "ROL", "NOM", "TELEFON", "GENERE", "PAIS", "ENTRADES_VENUDES") VALUES ('43382738F','cx1NDPDt49FvOPyG','TAQUILLER','Gertrudis','602833740','Sra.','Espanya','0');

INSERT INTO "JUTGE" ("DNI", "CONTRASENYA", "ROL", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "TAULES", "VICTORIES_BLANQUES", "VICTORIES_NEGRES") VALUES ('20100393R','7rZdIO2GnayzQf0o','JUTGE','Benjumea Azlatiletamendi',NULL,'Sr.','Hotel Capri','França','0','0','0');
INSERT INTO "JUTGE" ("DNI", "CONTRASENYA", "ROL", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "TAULES", "VICTORIES_BLANQUES", "VICTORIES_NEGRES") VALUES ('20200288L','sDlHbxGBUpR+Zmfq','JUTGE','Illiech Vladimir',NULL,'Sra.','Hotel Capri','Estats Units','0','0','0');
INSERT INTO "JUTGE" ("DNI", "CONTRASENYA", "ROL", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "TAULES", "VICTORIES_BLANQUES", "VICTORIES_NEGRES") VALUES ('20300643G','h1KrYzZxglRDJaqK','JUTGE','Pérez Lucián',NULL,'Sr.','Hotel Capri','Rússia','0','0','0');
INSERT INTO "JUTGE" ("DNI", "CONTRASENYA", "ROL", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "TAULES", "VICTORIES_BLANQUES", "VICTORIES_NEGRES") VALUES ('20400453H','wf+EVZAO6AKTJjju','JUTGE','Roberto Mandini',NULL,'Sr.','Hotel Capri','Itàlia','0','0','0');

INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11800','Alexander Morozevich',NULL,'Sr.','Hotel Capri','Rússia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10300','Anatoli Karpov',NULL,'Sr.','Hotel Antemare','Rússia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12700123A','Anish Girl',NULL,'Sr.','Hotel Melià','Gales','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10000456H','Bobby Fischer',NULL,'Sr.','Hotel Hilton','Estats Units','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11500678S','Boris Gelfand',NULL,'Sr.','Hotel Antemare','Anglaterra','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11000901D','Charles Stanley',NULL,'Sr.','Hotel Calipolis','Estats Units','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12800234A','Emanuel Lasker',NULL,'Sr.','Hotel Caesar Palace','Estats Units','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11900147M','Étienne Bacrot',NULL,'Sra.','Hotel Flamingo','França','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11700269G','Evgeny Alekseev',NULL,'Sr.','Hotel Flamingo','Rússia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11600283R','Fabiano Caruana',NULL,'Sr.','Hotel Holiday Inn','Itàlia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10100383F','Garry Kasparov',NULL,'Sr.','Hotel Capri','Rússia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11200393J','George Henry Mackenzie',NULL,'Sr.',NULL,'Anglaterra','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('13000838P','José Raúl Capablanca',NULL,'Sr.','Hotel Hilton','Espanya','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('13100121L','Judit Polgar',NULL,'Sra.','Hotel Melià','Anglaterra','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12500778U','Larry Christiansen',NULL,'Sr.','Hotel Caesar Palace','Estats Units','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12000922R','Levon Aronian',NULL,'Sr.','Hotel Antemare','França','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11400212V','Magnus Carlsen',NULL,'Sr.','Hotel Terramar','Anglaterra','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12900232W','Max Euwe',NULL,'Sr.','Hotel Antemare','Anglaterra','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10900331F','Maya Chiburdanidze',NULL,'Sra.','Hotel Flamingo','Rússia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10400222T','Mijail Botvínnik',NULL,'Sr.',NULL,'Rússia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10200442T','Mijail Tahl',NULL,'Sr.','Hotel Miracle','Rússia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10600993N','Paul Keres',NULL,'Sr.','Hotel Terramar','França','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11100199M','Paul Morphy',NULL,'Sr.','Hotel Miracle','Estats Units','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12400229R','Ray Robson',NULL,'Sr.','Hotel Holiday Inn','Anglaterra','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12600221Y','Robert Hess',NULL,'Sr.','Hotel Terramar','Estats Units','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('11300443Y','S. Lipschütz',NULL,'Sr.','Hotel Miracle','Alemanya','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12200333P','Shakhriyar Mamedyarov',NULL,'Sr.',NULL,'Rússia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10700775L','Topalov',NULL,'Sr.','Hotel Capri','Rússia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12300335K','Viktor Laznicka',NULL,'Sr.','Hotel Montserrat','Polònia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10500332J','Viswanathan Anand',NULL,'Sr.','Hotel Montserrat','Índia','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('12100887M','Wang Hao',NULL,'Sr.','Hotel Holiday Inn','Xina','0','0');
INSERT INTO "JUGADOR" ("DNI", "NOM", "TELEFON", "GENERE", "HOTEL", "PAIS", "PARTIDES_PERDUDES", "PARTIDES_GUANYADES") VALUES ('10800332K','Wilhelm Steinitz',NULL,'Sr.',NULL,'Alemanya','0','0');

INSERT INTO "JORNADA" ("ID", "DATA_REALITZACIO") VALUES ('1', '1996-02-10 09:00:00');
INSERT INTO "JORNADA" ("ID", "DATA_REALITZACIO") VALUES ('2', '1996-02-11 09:00:00');
INSERT INTO "JORNADA" ("ID", "DATA_REALITZACIO") VALUES ('3', '1996-02-12 09:00:00');
INSERT INTO "JORNADA" ("ID", "DATA_REALITZACIO") VALUES ('4', '1996-02-13 09:00:00');
INSERT INTO "JORNADA" ("ID", "DATA_REALITZACIO") VALUES ('5', '1996-02-14 09:00:00');

INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('1',NULL,'20100393R','10000456H','10100383F','1','Hotel Capri','Gènova','25');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('1', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('1', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('1', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('1', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('1', '4', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('2',NULL,'20100393R','10200442T','10300','1','Hotel Capri','Gènova','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('2', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('2', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('2', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('2', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('3',NULL,'20100393R','10400222T','10500332J','1','Hotel Capri','Gènova','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('3', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('3', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('3', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('3', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('4',NULL,'20200288L','10600993N','10700775L','1','Hotel Capri','Gènova','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('4', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('4', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('4', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('4', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('5',NULL,'20100393R','10800332K','10900331F','1','Hotel Capri','Gènova','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('5', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('5', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('5', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('5', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('5', '4', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('6',NULL,'20100393R','11000901D','11100199M','1','Hotel Capri','Gènova','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('6', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('6', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('6', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('6', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('6', '4', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('7',NULL,'20100393R','11200393J','11300443Y','1','Hotel Capri','Gènova','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('7', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('7', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('7', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('7', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('8',NULL,'20100393R','11400212V','11500678S','1','Hotel Capri','Gènova','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('8', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('8', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('8', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('8', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('9',NULL,'20100393R','11600283R','11700269G','1','Hotel Antemare','Venecia','10');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('9', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('9', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('9', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('9', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('9', '4', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('10',NULL,'20200288L','11800','11900147M','1','Hotel Antemare','Venecia','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('10', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('10', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('10', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('10', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('11',NULL,'20200288L','12000922R','12100887M','1','Hotel Antemare','Venecia','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('11', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('11', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('11', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('11', '3', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('12',NULL,'20200288L','12200333P','12300335K','1','Hotel Antemare','Venecia','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('12', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('12', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('12', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('12', '3', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('13',NULL,'20300643G','12400229R','12500778U','1','Hotel Antemare','Venecia','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('13', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('13', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('13', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('13', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('13', '4', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('14',NULL,'20300643G','12600221Y','12700123A','1','Hotel Antemare','Venecia','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('14', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('14', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('14', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('14', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('14', '4', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('15',NULL,'20300643G','12800234A','12900232W','1','Hotel Antemare','Venecia','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('15', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('15', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('15', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('15', '3', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('16',NULL,'20300643G','13000838P','13100121L','1','Hotel Antemare','Venecia','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('16', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('16', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('16', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('16', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('17',NULL,'20400453H','10300','10000456H','2','Hotel Montserrat','Victòria','18');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('17', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('17', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('17', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('17', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('17', '4', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('18',NULL,'20400453H','10500332J','10700775L','2','Hotel Montserrat','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('18', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('18', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('18', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('18', '3', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('19',NULL,'20400453H','11000901D','10800332K','2','Hotel Montserrat','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('19', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('19', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('19', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('19', '3', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('20',NULL,'20400453H','11300443Y','11500678S','2','Hotel Montserrat','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('20', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('20', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('20', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('20', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('21',NULL,'20300643G','11600283R','11900147M','2','Hotel Montserrat','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('21', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('21', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('21', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('21', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('21', '4', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('22',NULL,'20200288L','12000922R','12200333P','2','Hotel Montserrat','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('22', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('22', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('22', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('22', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('22', '4', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('23',NULL,'20300643G','12500778U','12700123A','2','Hotel Montserrat','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('23', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('23', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('23', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('23', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('24',NULL,'20300643G','12800234A','13100121L','2','Hotel Montserrat','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('24', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('24', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('24', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('24', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('25',NULL,'20300643G','10000456H','10500332J','3','Hotel Calipolis','Victòria','8');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('25', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('25', '1', 'Df3,Cc6');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('25', '2', 'Ac4,Ac5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('25', '3', 'f7++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('25', '4', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('26',NULL,'20300643G','11000901D','11500678S','3','Hotel Calipolis','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('26', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('26', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('26', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('26', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('27',NULL,'20300643G','11600283R','12000922R','3','Hotel Calipolis','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('27', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('27', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('27', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('27', '3', '1-0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('28',NULL,'20400453H','12700123A','13100121L','3','Hotel Calipolis','Victòria','0');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('28', '0', 'e4,e5');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('28', '1', 'b3,h4');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('28', '2', 'Re2,e4++');
INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES ('28', '3', '0-1');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('29',NULL,'20400453H','11500678S','10000456H','4','Hotel Montserrat','Ducci','0');
INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES ('30',NULL,'20300643G','13100121L','11600283R','4','Hotel Montserrat','Ducci','0');

