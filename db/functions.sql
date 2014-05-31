
-- -----------------------------------------------------
-- ENTRADES_DISPONIBLES
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION ENTRADES_DISPONIBLES(VARCHAR, VARCHAR, INTEGER) RETURNS INTEGER AS $$
DECLARE
    hotel ALIAS FOR $1;
    sala ALIAS FOR $2;
    jornada ALIAS FOR $3;
    entrades_disponibles INTEGER;
BEGIN
    SELECT
        INTO entrades_disponibles S."AFORAMENT" - SUM("ENTRADES_VENUDES")
    FROM
        "PARTIDA" P, "SALA" S
    WHERE
        P."HOTEL" = hotel AND
        P."SALA" = sala AND
        P."JORNADA" = jornada AND
        P."HOTEL" = S."HOTEL" AND
        P."SALA" = S."NOM"
    GROUP BY
        S."HOTEL", S."NOM";
    RETURN entrades_disponibles;
END;
$$ LANGUAGE 'plpgsql';

-- -----------------------------------------------------
-- COMPRAR_ENTRADA
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION COMPRAR_ENTRADA(VARCHAR, VARCHAR, INTEGER, VARCHAR) RETURNS INTEGER AS $$
DECLARE
    hotel ALIAS FOR $1;
    sala ALIAS FOR $2;
    jornada ALIAS FOR $3;
    dni_taquiller ALIAS FOR $4;
    ent_disp INTEGER;
    partida INTEGER;
BEGIN
    SELECT "entrades_disponibles" INTO ent_disp FROM ENTRADES_DISPONIBLES(hotel, sala, jornada);
    IF ent_disp > 0 THEN
        SELECT "ID" INTO partida FROM "PARTIDA" WHERE "JORNADA" = jornada ORDER BY "ID" ASC LIMIT 1;
        UPDATE "PARTIDA" SET "ENTRADES_VENUDES" = "ENTRADES_VENUDES" + 1 WHERE "ID" = partida;
        UPDATE "TAQUILLER" SET "ENTRADES_VENUDES" = "ENTRADES_VENUDES" + 1 WHERE "DNI" = dni_taquiller;
        RETURN '0';
    END IF;
    RETURN '-1';
END;
$$ LANGUAGE 'plpgsql';

-- -----------------------------------------------------
-- ENTRADES_JORNADA
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION ENTRADES_JORNADA(INTEGER)
RETURNS TABLE("HOTEL" VARCHAR, "SALA" VARCHAR, "ENTRADES" INTEGER) AS $$
DECLARE
    jornada ALIAS FOR $1;
BEGIN
    RETURN QUERY
        SELECT
            *
        FROM (
            SELECT
                S."HOTEL", S."NOM",
                ENTRADES_DISPONIBLES(S."HOTEL", S."NOM", jornada) AS "ENTRADES"
            FROM
                "SALA" S
            ) AS P
        WHERE
            P."ENTRADES" IS NOT NULL;
END;
$$ LANGUAGE 'plpgsql';

-- -----------------------------------------------------
-- FINALISTES
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION FINALISTES()
RETURNS TABLE("NOM" VARCHAR, "PAIS" VARCHAR, "PARTIDES_GUANYADES" INTEGER) AS $$
BEGIN
    RETURN QUERY
        SELECT
            "NOM", "PAIS", "PARTIDES_GUANYADES"
        FROM
            "JUGADOR"
        ORDER BY
            "PARTIDES_GUANYADES" DESC
        LIMIT 3;
END;
$$ LANGUAGE 'plpgsql';

-- -----------------------------------------------------
-- MAX_PAIS_VICTORIES
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION MAX_PAIS_VICTORIES()
RETURNS TABLE("PAIS" VARCHAR, "VICTORIES" INTEGER) AS $$
BEGIN
    RETURN QUERY
        SELECT
            "PAIS", SUM("PARTIDES_GUANYADES") AS VICTORIES
        FROM
            "JUGADOR"
        GROUP BY
            "PAIS"
        ORDER BY
            "VICTORIES" DESC
        LIMIT 1;
END;
$$ LANGUAGE 'plpgsql';

-- -----------------------------------------------------
-- MAX_DURACIO_PARTIDA
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION MAX_DURACIO_PARTIDA()
RETURNS TABLE("PARTIDA" VARCHAR, "QUANTITAT_MOVIMENTS" INTEGER) AS $$
BEGIN
    RETURN QUERY
        SELECT
            "PARTIDA", COUNT(*) AS "QUANTITAT_MOVIMENTS"
        FROM
            "MOVIMENT"
        GROUP BY
            "PARTIDA"
        ORDER BY
            "QUANTITAT_MOVIMENTS" DESC
        LIMIT 1;
END;
$$ LANGUAGE 'plpgsql';

-- -----------------------------------------------------
-- TANCAR_PARTIDA
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION TANCAR_PARTIDA(VARCHAR, INTEGER, VARCHAR) RETURNS VOID AS $$
DECLARE
    jutgeDni ALIAS FOR $1;
    partidaId ALIAS FOR $2;
    resultat ALIAS FOR $3;
    partida "PARTIDA"%ROWTYPE;
BEGIN
    UPDATE "PARTIDA" SET "RESULTAT" = resultat WHERE "ID" = partidaId;
    SELECT * INTO partida FROM "PARTIDA" WHERE "ID" = partidaId;
    IF resultat = 'B' THEN
        UPDATE "JUGADOR" SET "PARTIDES_GUANYADES" = "PARTIDES_GUANYADES" + 1 WHERE "DNI" = partida."BLANQUES";
        UPDATE "JUGADOR" SET "PARTIDES_PERDUDES" = "PARTIDES_PERDUDES" + 1 WHERE "DNI" = partida."NEGRES";
        UPDATE "JUTGE" SET "VICTORIES_BLANQUES" = "VICTORIES_BLANQUES" + 1 WHERE "DNI" = partida."JUTGE";
    END IF;
    
    IF resultat = 'N' THEN
        UPDATE "JUGADOR" SET "PARTIDES_GUANYADES" = "PARTIDES_GUANYADES" + 1 WHERE "DNI" = partida."NEGRES";
        UPDATE "JUGADOR" SET "PARTIDES_PERDUDES" = "PARTIDES_PERDUDES" + 1 WHERE "DNI" = partida."BLANQUES";
        UPDATE "JUTGE" SET "VICTORIES_BLANQUES" = "VICTORIES_NEGRES" + 1 WHERE "DNI" = partida."JUTGE";
    END IF;
    
    IF resultat = 'T' THEN
        UPDATE "JUTGE" SET "TAULES" = "TAULES" + 1 WHERE "DNI" = partida."JUTGE";
    END IF;
END;
$$ LANGUAGE 'plpgsql';

-- -----------------------------------------------------
-- SIMPLE_ENTRADES_JORNADA
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION SIMPLE_ENTRADES_JORNADA(INTEGER) RETURNS INTEGER AS $$
DECLARE
    jornada ALIAS FOR $1;
    entrades INTEGER;
BEGIN
    SELECT SUM(ENTRADES_DISPONIBLES(S."HOTEL", S."NOM", jornada)) INTO entrades FROM "SALA" S;
    IF entrades IS NULL THEN return '0'; END IF;
    RETURN entrades;
END;
$$ LANGUAGE 'plpgsql';
