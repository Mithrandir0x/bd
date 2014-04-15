
-- ----------------------------------------------------------------------------------------------------------
-- START:[DROPS]

-- Tables
DROP TABLE IF EXISTS "ENTRADA";
DROP TABLE IF EXISTS "PARTIDES_JUGADORS";
DROP TABLE IF EXISTS "MOVIMENT";
DROP TABLE IF EXISTS "PARTIDA";
DROP TABLE IF EXISTS "JORNADA";
DROP TABLE IF EXISTS "TORNEIG";
DROP TABLE IF EXISTS "SALA";
DROP TABLE IF EXISTS "HOTEL";
DROP TABLE IF EXISTS "USUARI_TAQUILLER";
DROP TABLE IF EXISTS "USUARI_JUTGE";
DROP TABLE IF EXISTS "USUARI_JUGADOR";
DROP TABLE IF EXISTS "USUARI";
DROP TABLE IF EXISTS "CIUTAT";
DROP TABLE IF EXISTS "ESTAT";
-- Functions
-- Triggers

-- END:[DROPS]
-- ----------------------------------------------------------------------------------------------------------


-- ----------------------------------------------------------------------------------------------------------
-- START:[COMMON]

-- -----------------------------------------------------
-- "ESTAT"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "ESTAT" (
  "ID" INTEGER NOT NULL,
  "NOM" VARCHAR(45) NOT NULL,
  "CODI_ISO" VARCHAR(2) NOT NULL,
  PRIMARY KEY ("ID")
);

-- -----------------------------------------------------
-- "CIUTAT"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "CIUTAT" (
  "ID" INTEGER NOT NULL,
  "NOM" VARCHAR(45) NULL,
  "ESTAT_ID" INTEGER NOT NULL,
  PRIMARY KEY ("ID"),
  FOREIGN KEY ("ESTAT_ID") REFERENCES "ESTAT" ("ID")
);

-- END:[COMMON]
-- ----------------------------------------------------------------------------------------------------------


-- ----------------------------------------------------------------------------------------------------------
-- START:[USER SPACE]

-- -----------------------------------------------------
-- "USUARI"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "USUARI" (
  "ID" INTEGER NOT NULL,
  "NOM" VARCHAR(32) NOT NULL,
  "COGNOMS" VARCHAR(32) NOT NULL,
  "NOM_SISTEMA" VARCHAR(32) NOT NULL,
  "CONTRASENYA" VARCHAR(32) NOT NULL,
  "TIPUS_USUARI" VARCHAR(32) NOT NULL,
  "ESTAT_ID" INTEGER NOT NULL,
  PRIMARY KEY ("ID"),
  FOREIGN KEY ("ESTAT_ID") REFERENCES "ESTAT" ("ID")
);

-- -----------------------------------------------------
-- "JUGADOR"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "USUARI_JUGADOR" (
  "USUARI_ID" INTEGER NOT NULL,
  "PARTIDES_GUANYADES" INTEGER DEFAULT 0,
  "PARTIDES_PERDUDES" INTEGER DEFAULT 0,
  FOREIGN KEY ("USUARI_ID") REFERENCES "USUARI" ("ID")
);

-- -----------------------------------------------------
-- "JUTGE"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "USUARI_JUTGE" (
  "USUARI_ID" INTEGER NOT NULL,
  "VICTORIES_BLANQUES" INTEGER DEFAULT 0,
  "VICTORIES_NEGRES" INTEGER DEFAULT 0,
  "VICTORIES_TAULES" INTEGER DEFAULT 0,
  FOREIGN KEY ("USUARI_ID") REFERENCES "USUARI" ("ID")
);

-- -----------------------------------------------------
-- "TAQUILLER"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "USUARI_TAQUILLER" (
  "USUARI_ID" INTEGER NOT NULL,
  "ENTRADES_VENUDES" INTEGER,
  FOREIGN KEY ("USUARI_ID") REFERENCES "USUARI" ("ID")
);

-- END:[USER SPACE]
-- ----------------------------------------------------------------------------------------------------------


-- ----------------------------------------------------------------------------------------------------------
-- START:[HOTELS]

-- -----------------------------------------------------
-- "HOTEL"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "HOTEL" (
  "ID" INTEGER NOT NULL,
  "NOM" VARCHAR(45) NULL,
  "CIUTAT_ID" INTEGER NOT NULL,
  PRIMARY KEY ("ID"),
  FOREIGN KEY ("CIUTAT_ID") REFERENCES "CIUTAT" ("ID")
);

-- -----------------------------------------------------
-- "SALA"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "SALA" (
  "ID" INTEGER NOT NULL,
  "NOM" VARCHAR(45) NULL,
  "AFORAMENT" INTEGER DEFAULT 0,
  "HOTEL_ID" INTEGER NOT NULL,
  PRIMARY KEY ("ID"),
  FOREIGN KEY ("HOTEL_ID") REFERENCES "HOTEL" ("ID")
);

-- END:[HOTELS]
-- ----------------------------------------------------------------------------------------------------------


-- ----------------------------------------------------------------------------------------------------------
-- START:[GAMES]

-- -----------------------------------------------------
-- "TORNEIG"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "TORNEIG" (
  "ID" INTEGER NOT NULL,
  "TITOL" VARCHAR(45) NULL,
  PRIMARY KEY ("ID")
);

-- -----------------------------------------------------
-- "JORNADA"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "JORNADA" (
  "ID" INTEGER NOT NULL,
  "DATA_REALITZACIO" TIMESTAMP NULL,
  "ORDRE" INTEGER NULL,
  "TORNEIG_ID" INTEGER NOT NULL,
  PRIMARY KEY ("ID"),
  FOREIGN KEY ("TORNEIG_ID") REFERENCES "TORNEIG" ("ID")
);

-- -----------------------------------------------------
-- "PARTIDA"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "PARTIDA" (
  "ID" INTEGER NOT NULL,
  "GUANYADOR" INTEGER NULL,
  "VENCUT" INTEGER NULL,
  "DURACIO" INTEGER NULL,
  "JORNADA_ID" INTEGER NOT NULL,
  "SALA_ID" INTEGER NOT NULL,
  PRIMARY KEY ("ID"),
  FOREIGN KEY ("JORNADA_ID") REFERENCES "JORNADA" ("ID"),
  FOREIGN KEY ("SALA_ID") REFERENCES "SALA" ("ID")
);

-- -----------------------------------------------------
-- "MOVIMENT"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "MOVIMENT" (
  "PARTIDA_ID" INTEGER NOT NULL,
  "JUGADOR_ID" INTEGER NOT NULL,
  "ORDRE" INTEGER NOT NULL,
  "DESCRIPCIO" VARCHAR(48) NOT NULL,
  "DURACIO" INTEGER NOT NULL,
  FOREIGN KEY ("PARTIDA_ID") REFERENCES "PARTIDA" ("ID"),
  FOREIGN KEY ("JUGADOR_ID") REFERENCES "JUGADOR" ("ID")
);

-- -----------------------------------------------------
-- "PARTIDES_JUGADORS"
--
-- Domini "COLOR": {BLANC, NEGRE}
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "PARTIDES_JUGADORS" (
  "PARTIDA_ID" INTEGER NOT NULL,
  "JUGADOR_ID" INTEGER NOT NULL,
  "COLOR" VARCHAR(5) NOT NULL,
  FOREIGN KEY ("PARTIDA_ID") REFERENCES "PARTIDA" ("ID"),
  FOREIGN KEY ("JUGADOR_ID") REFERENCES "JUGADOR" ("ID")
);

-- END:[GAMES]
-- ----------------------------------------------------------------------------------------------------------

-- ----------------------------------------------------------------------------------------------------------
-- START:[TRADING]

-- -----------------------------------------------------
-- "ENTRADA"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "ENTRADA" (
  "ID" INTEGER NOT NULL,
  "PREU_VENTA" INTEGER NOT NULL,
  "TIPUS_PAGAMENT" INTEGER NOT NULL,
  "TAQUILLER_ID" INTEGER NOT NULL,
  "JORNADA_ID" INTEGER NOT NULL,
  "SALA_ID" INTEGER NOT NULL,
  FOREIGN KEY ("TAQUILLER_ID") REFERENCES "TAQUILLER" ("ID"),
  FOREIGN KEY ("JORNADA_ID") REFERENCES "JORNADA" ("ID"),
  FOREIGN KEY ("SALA_ID") REFERENCES "SALA" ("ID")
);

-- END:[TRADING]
-- ----------------------------------------------------------------------------------------------------------

-- Selecciona les sales utilitzades en les partides d'una jornada
SELECT s."ID", s."NOM", s."AFORAMENT" FROM "JORNADA" j, "PARTIDA" p, "SALA" s WHERE p."JORNADA_ID" = j."ID" AND j."ID" = '?';
