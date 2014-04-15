
-- Selecciona les sales utilitzades en les partides d'una jornada
SELECT s."ID", s."NOM", s."AFORAMENT" FROM "JORNADA" j, "PARTIDA" p, "SALA" s WHERE p."JORNADA_ID" = j."ID" AND j."ID" = '?';
