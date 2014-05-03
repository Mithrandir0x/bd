
-- -----------------------------------------------------
-- ENTRADES_DISPONIBLES
-- -----------------------------------------------------
DROP FUNCTION IF EXISTS ENTRADES_DISPONIBLES(VARCHAR, VARCHAR, INTEGER);
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
-- ENTRADES_JORNADA
-- -----------------------------------------------------
DROP FUNCTION IF EXISTS ENTRADES_JORNADA(INTEGER);
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
DROP FUNCTION IF EXISTS FINALISTES();
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
DROP FUNCTION IF EXISTS MAX_PAIS_VICTORIES();
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
DROP FUNCTION IF EXISTS MAX_DURACIO_PARTIDA();
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
