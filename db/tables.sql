
DROP TABLE IF EXISTS "MOVIMENT";
DROP TABLE IF EXISTS "PARTIDA";
DROP TABLE IF EXISTS "JUTGE";
DROP TABLE IF EXISTS "JUGADOR";
DROP TABLE IF EXISTS "TAQUILLER";
DROP TABLE IF EXISTS "JORNADA";
DROP TABLE IF EXISTS "SALA";
DROP TABLE IF EXISTS "HOTEL";
DROP TABLE IF EXISTS "PAIS";

-- -----------------------------------------------------
-- "PAIS"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "PAIS" (
  "NOM" VARCHAR(32) NOT NULL,
  PRIMARY KEY ("NOM")
);

-- -----------------------------------------------------
-- "HOTEL"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "HOTEL" (
  "NOM" VARCHAR(45) NULL,
  PRIMARY KEY ("NOM")
);

-- -----------------------------------------------------
-- "SALA"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "SALA" (
  "HOTEL" VARCHAR(45) NOT NULL,
  "NOM" VARCHAR(45) NULL,
  "AFORAMENT" INTEGER DEFAULT 0,
  PRIMARY KEY ("HOTEL", "NOM"),
  FOREIGN KEY ("HOTEL") REFERENCES "HOTEL" ("NOM")
);

-- -----------------------------------------------------
-- "JORNADA"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "JORNADA" (
  "ID" INTEGER NOT NULL,
  "ORDRE" INTEGER NULL,
  "DATA_REALITZACIO" TIMESTAMP NULL,
  PRIMARY KEY ("ID")
);

--   "GENERE" DOMAIN {'Sr.', 'Sra.'}

-- -----------------------------------------------------
-- "TAQUILLER"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "TAQUILLER" (
  "DNI" VARCHAR(16) NOT NULL,
  "NOM" VARCHAR(32) NOT NULL,
  "TELEFON" VARCHAR(32) NULL,
  "GENERE" VARCHAR(4) NOT NULL,
  "ENTRADES_VENUDES" INTEGER DEFAULT 0,
  "PAIS" VARCHAR(32) NOT NULL,
  PRIMARY KEY ("DNI"),
  FOREIGN KEY ("PAIS") REFERENCES "PAIS" ("NOM")
);

-- -----------------------------------------------------
-- "JUGADOR"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "JUGADOR" (
  "DNI" VARCHAR(16) NOT NULL,
  "NOM" VARCHAR(32) NOT NULL,
  "TELEFON" VARCHAR(32) NULL,
  "GENERE" VARCHAR(4) NOT NULL,
  "PARTIDES_GUANYADES" INTEGER DEFAULT 0,
  "PARTIDES_PERDUDES" INTEGER DEFAULT 0,
  "HOTEL" VARCHAR(45) NULL,
  "PAIS" VARCHAR(32) NOT NULL,
  PRIMARY KEY ("DNI"),
  FOREIGN KEY ("HOTEL") REFERENCES "HOTEL" ("NOM"),
  FOREIGN KEY ("PAIS") REFERENCES "PAIS" ("NOM")
);

-- -----------------------------------------------------
-- "JUTGE"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "JUTGE" (
  "DNI" VARCHAR(16) NOT NULL,
  "NOM" VARCHAR(32) NOT NULL,
  "TELEFON" VARCHAR(32) NULL,
  "GENERE" VARCHAR(4) NOT NULL,
  "VICTORIES_BLANQUES" INTEGER DEFAULT 0,
  "VICTORIES_NEGRES" INTEGER DEFAULT 0,
  "TAULES" INTEGER DEFAULT 0,
  "HOTEL" VARCHAR(45) NULL,
  "PAIS" VARCHAR(32) NOT NULL,
  PRIMARY KEY ("DNI"),
  FOREIGN KEY ("HOTEL") REFERENCES "HOTEL" ("NOM"),
  FOREIGN KEY ("PAIS") REFERENCES "PAIS" ("NOM")
);

-- -----------------------------------------------------
-- "PARTIDA"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "PARTIDA" (
  "ID" INTEGER NOT NULL,
  "GUANYADOR" INTEGER NULL,
  "JUTGE" VARCHAR(16) NOT NULL,
  "BLANQUES" VARCHAR(16) NOT NULL,
  "NEGRES" VARCHAR(16) NOT NULL,
  "JORNADA" INTEGER NOT NULL,
  "HOTEL" VARCHAR(45) NOT NULL,
  "SALA" VARCHAR(45) NOT NULL,
  "ENTRADES_VENUDES" INTEGER DEFAULT 0,
  PRIMARY KEY ("ID"),
  FOREIGN KEY ("JUTGE") REFERENCES "JUTGE" ("DNI"),
  FOREIGN KEY ("BLANQUES") REFERENCES "JUGADOR" ("DNI"),
  FOREIGN KEY ("NEGRES") REFERENCES "JUGADOR" ("DNI"),
  FOREIGN KEY ("JORNADA") REFERENCES "JORNADA" ("ID"),
  FOREIGN KEY ("HOTEL", "SALA") REFERENCES "SALA" ("HOTEL", "NOM")
);

-- -----------------------------------------------------
-- "MOVIMENT"
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "MOVIMENT" (
  "PARTIDA" INTEGER NOT NULL,
  "ORDRE" INTEGER NOT NULL,
  "DESCRIPCIO" VARCHAR(64) NOT NULL,
  FOREIGN KEY ("PARTIDA") REFERENCES "PARTIDA" ("ID")
);
