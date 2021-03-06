
-- -----------------------------------------------------
-- DECIDIR_GUANYADOR
--   In Chess Algebraic Notation, the winner is noted:
--
--     1-0 : White wins
--     0-1 : Black wins
--
--   For noting ties, simply use 0-0.
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION DECIDIR_GUANYADOR() RETURNS TRIGGER AS $$
DECLARE
    winner TEXT;
    partida "PARTIDA"%ROWTYPE;
BEGIN
    SELECT * INTO partida FROM "PARTIDA" WHERE "ID" = NEW."PARTIDA";
    IF NEW."DESCRIPCIO" = '1-0' THEN
        UPDATE "JUGADOR" SET "PARTIDES_GUANYADES" = "PARTIDES_GUANYADES" + 1 WHERE "DNI" = partida."BLANQUES";
        UPDATE "JUGADOR" SET "PARTIDES_PERDUDES" = "PARTIDES_PERDUDES" + 1 WHERE "DNI" = partida."NEGRES";
        UPDATE "JUTGE" SET "VICTORIES_BLANQUES" = "VICTORIES_BLANQUES" + 1 WHERE "DNI" = partida."JUTGE";
        UPDATE "PARTIDA" SET "RESULTAT" = 'B' WHERE "ID" = NEW."PARTIDA";
    END IF;
    
    IF NEW."DESCRIPCIO" = '0-1' THEN
        UPDATE "JUGADOR" SET "PARTIDES_GUANYADES" = "PARTIDES_GUANYADES" + 1 WHERE "DNI" = partida."NEGRES";
        UPDATE "JUGADOR" SET "PARTIDES_PERDUDES" = "PARTIDES_PERDUDES" + 1 WHERE "DNI" = partida."BLANQUES";
        UPDATE "JUTGE" SET "VICTORIES_BLANQUES" = "VICTORIES_NEGRES" + 1 WHERE "DNI" = partida."JUTGE";
        UPDATE "PARTIDA" SET "RESULTAT" = 'N' WHERE "ID" = NEW."PARTIDA";
    END IF;
    
    IF NEW."DESCRIPCIO" = '0-0' THEN
        UPDATE "JUTGE" SET "TAULES" = "TAULES" + 1 WHERE "DNI" = partida."JUTGE";
        UPDATE "PARTIDA" SET "RESULTAT" = 'T' WHERE "ID" = NEW."PARTIDA";
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER DECIDEIX_GUANYADOR
    AFTER INSERT ON "MOVIMENT"
    FOR EACH ROW EXECUTE PROCEDURE DECIDIR_GUANYADOR();

-- -----------------------------------------------------
-- VERIFICAR_NACIONALITATS
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION VERIFICAR_NACIONALITATS() RETURNS TRIGGER AS $$
DECLARE
    nacionalitat_jutge TEXT;
    nacionalitat_blanques TEXT;
    nacionalitat_negres TEXT;
BEGIN
    SELECT INTO nacionalitat_jutge "PAIS" FROM "JUTGE" WHERE "DNI" = NEW."JUTGE";
    SELECT INTO nacionalitat_blanques "PAIS" FROM "JUGADOR" WHERE "DNI" = NEW."BLANQUES";
    SELECT INTO nacionalitat_negres "PAIS" FROM "JUGADOR" WHERE "DNI" = NEW."NEGRES";
    
    IF nacionalitat_jutge = nacionalitat_blanques THEN
        RAISE EXCEPTION 'BLANQUES TÉ LA MATEIXA NACIONALITAT QUE EL JUTGE';
    END IF;

    IF nacionalitat_jutge = nacionalitat_negres THEN
        RAISE EXCEPTION 'NEGRES TÉ LA MATEIXA NACIONALITAT QUE EL JUTGE';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER VERIFICA_NACIONALITATS
    BEFORE INSERT ON "PARTIDA"
    FOR EACH ROW EXECUTE PROCEDURE VERIFICAR_NACIONALITATS();
