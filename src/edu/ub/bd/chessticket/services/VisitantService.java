package edu.ub.bd.chessticket.services;

import edu.ub.bd.utils.PostgreTransaction;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Consultes fetes per VisitantMenu i TaquillerMenu.
 * 
 */
public class VisitantService
{
    
    /**
     * Mostra per pantalla la llista de partides fetes fins al moment.
     * 
     * @throws Exception 
     */
    public void consultarJornada() throws Exception
    {
        new PostgreTransaction<Object>(){

            @Override
            public Object run() throws Exception
            {
                // SELECT p."JORNADA", b."NOM" AS blanques, n."NOM" AS negres, p."HOTEL", p."SALA", p."RESULTAT" FROM "JUGADOR" b, "JUGADOR" n, "PARTIDA" p WHERE b."DNI" = p."BLANQUES" AND n."DNI" = p."NEGRES" ORDER BY p."JORNADA" ASC
                String query =  "SELECT p.\"JORNADA\", b.\"NOM\" AS blanques, n.\"NOM\" AS negres, p.\"HOTEL\", p.\"SALA\", p.\"RESULTAT\" FROM \"JUGADOR\" b, \"JUGADOR\" n, \"PARTIDA\" p WHERE b.\"DNI\" = p.\"BLANQUES\" AND n.\"DNI\" = p.\"NEGRES\" ORDER BY p.\"JORNADA\" ASC";
                
                Statement s = C.createStatement();
                
                System.out.println("\nSales amb entrades disponibles:\n");
                System.out.println(" JORNADA | BLANQUES                 | NEGRES                   | HOTEL                    | SALA                     | RESULTAT");
                System.out.println("---------+--------------------------+--------------------------+--------------------------+--------------------------+----------");
                
                ResultSet rs = s.executeQuery(query);
                while ( rs.next() )
                {
                    int jornada = rs.getInt(1);
                    String blanques = rs.getString(2);
                    String negres = rs.getString(3);
                    String hotel = rs.getString(4);
                    String sala = rs.getString(5);
                    String resultat = rs.getString(6);
                    if ( resultat == null ) resultat = "";
                    System.out.println(String.format(" %7d | %24s | %24s | %24s | %24s | %8s", jornada, blanques, negres, hotel, sala, resultat));
                }
                
                return null;
            }
            
        }.execute();
    }

    /**
     * Mostra la classificacio actual del campionat.
     * 
     * @throws Exception 
     */
    public void consultarClassificacio() throws Exception
    {
        new PostgreTransaction<Object>(){

            @Override
            public Object run() throws Exception
            {
                // SELECT "NOM", "PAIS", "PARTIDES_GUANYADES" FROM "JUGADOR" ORDER BY "PARTIDES_GUANYADES" DESC
                String query =  "SELECT \"NOM\", \"PAIS\", \"PARTIDES_GUANYADES\" FROM \"JUGADOR\" ORDER BY \"PARTIDES_GUANYADES\" DESC";
                
                Statement s = C.createStatement();
                
                System.out.println("\nClassificacio de jugadors:\n");
                System.out.println(" NOM DEL JUGADOR          | PAIS             | PARTIDES GUANYADES");
                System.out.println("--------------------------+------------------+--------------------");
                
                ResultSet rs = s.executeQuery(query);
                while ( rs.next() )
                {
                    String nom = rs.getString(1);
                    String pais = rs.getString(2);
                    int partidesGuanyades = rs.getInt(3);
                    System.out.println(String.format(" %24s | %16s | %18d", nom, pais, partidesGuanyades));
                }
                
                return null;
            }
            
        }.execute();
    }

}
