package edu.ub.bd.chessticket.services;

import edu.ub.bd.utils.PostgreTransaction;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

public class VisitantService
{
    
    public void consultarJornada() throws Exception
    {
        new PostgreTransaction<Object>(){

            @Override
            public Object run() throws Exception
            {
                // SELECT "JORNADA", NOM_JUGADOR("BLANQUES") AS blanques, NOM_JUGADOR("NEGRES") AS negres, "HOTEL", "SALA", "RESULTAT" FROM "PARTIDA" ORDER BY "JORNADA" ASC
                String query =  "SELECT \"JORNADA\", NOM_JUGADOR(\"BLANQUES\") AS blanques, NOM_JUGADOR(\"NEGRES\") AS negres, \"HOTEL\", \"SALA\", \"RESULTAT\" FROM \"PARTIDA\" ORDER BY \"JORNADA\" ASC";
                
                Statement s = C.createStatement();
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
                    System.out.println(String.format("%2d | %32s | %32s | %24s | %24s | %1s", jornada, blanques, negres, hotel, sala, resultat));
                }
                
                return null;
            }
            
        }.execute();
    }

    public void consultarClassificacio() throws Exception
    {
        new PostgreTransaction<Object>(){

            @Override
            public Object run() throws Exception
            {
                // SELECT "NOM", "PAIS", "PARTIDES_GUANYADES" FROM "JUGADOR" ORDER BY "PARTIDES_GUANYADES" DESC
                String query =  "SELECT \"NOM\", \"PAIS\", \"PARTIDES_GUANYADES\" FROM \"JUGADOR\" ORDER BY \"PARTIDES_GUANYADES\" DESC";
                
                Statement s = C.createStatement();
                ResultSet rs = s.executeQuery(query);
                while ( rs.next() )
                {
                    String nom = rs.getString(1);
                    String pais = rs.getString(2);
                    int partidesGuanyades = rs.getInt(3);
                    System.out.println(MessageFormat.format("%32s | %16s | %2d", nom, pais, partidesGuanyades));
                }
                
                return null;
            }
            
        }.execute();
    }

}
