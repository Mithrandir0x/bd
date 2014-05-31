package edu.ub.bd.chessticket.services;

import edu.ub.bd.utils.PostgreTransaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JutgeService
{
    
    public List<String> consultarTotsJutges()
    {
        return new PostgreTransaction<List<String>>(){

            @Override
            public List<String> run() throws Exception
            {
                List<String> l = new ArrayList<String>();
                int i = 0;
                // SELECT * FROM "JUGADOR
                String query = "SELECT * FROM \"JUTGE\"";
                
                PreparedStatement s = C.prepareStatement(query);
                ResultSet rs = s.executeQuery();
                while ( rs.next() )
                {
                    String dni = rs.getString(1);
                    String nom = rs.getString(2);
                    String pais = rs.getString(9);
                    
                    System.out.println(String.format("[%2d] %32s | %32s | %32s", i, dni, nom, pais));
                    
                    l.add(dni);
                    i++;
                }
                
                return l;
            }
            
        }.execute();
    }
    
    public List<Integer> consultarPartides(final String jutgeDni, final boolean mostrarNomesNules)
    {
        return new PostgreTransaction<List<Integer>>(){

            @Override
            public List<Integer> run() throws Exception
            {
                List<Integer> l = new ArrayList<Integer>();
                // SELECT "ID", b."NOM" AS BLANQUES, n."NOM" AS NEGRES, "JORNADA", "RESULTAT" FROM "JUGADOR" b, "JUGADOR" n, "PARTIDA" WHERE b."DNI" = "BLANQUES" AND n."DNI" = "NEGRES" AND "JUTGE" = ? ORDER BY "JORNADA" ASC
                String query = "SELECT \"ID\", b.\"NOM\" AS BLANQUES, n.\"NOM\" AS NEGRES, \"JORNADA\", \"RESULTAT\" FROM \"JUGADOR\" b, \"JUGADOR\" n, \"PARTIDA\" WHERE b.\"DNI\" = \"BLANQUES\" AND n.\"DNI\" = \"NEGRES\" AND \"JUTGE\" = ? ORDER BY \"JORNADA\" ASC";
                
                PreparedStatement s = C.prepareStatement(query);
                
                if ( mostrarNomesNules )
                    System.out.println("\nPartides per tancar:\n");
                else
                    System.out.println("\nTotes les partides arbitrades:\n");
                
                System.out.println(" ID | JORNADA | RESULTAT | BLANQUES                 | NEGRES");
                System.out.println("----+---------+----------+--------------------------+--------------------------");
                
                s.setString(1, jutgeDni);
                
                ResultSet rs = s.executeQuery();
                while ( rs.next() )
                {
                    int id = rs.getInt(1);
                    String blanques = rs.getString(2);
                    String negres = rs.getString(3);
                    int jornada = rs.getInt(4);
                    String resultat = rs.getString(5);
                    if ( resultat == null ) resultat = "";
                    
                    if ( mostrarNomesNules )
                    {
                        if ( resultat.length() == 0 )
                            System.out.println(String.format(" %2d | %7d | %8s | %24s | %24s", id, jornada, resultat, blanques, negres));
                    }
                    else
                    {
                        System.out.println(String.format(" %2d | %7d | %8s | %24s | %24s", id, jornada, resultat, blanques, negres));
                    }
                    
                    if ( resultat.length() == 0 ) l.add(id);
                }
                
                return l;
            }
            
        }.execute();
    }
    
    public List<Integer> consultarPartides(final String jutgeDni)
    {
        return consultarPartides(jutgeDni, false);
    }

    public boolean tancarPartida(final String jutgeDni, final int partida, final String resultat)
    {
        return new PostgreTransaction<Boolean>(){

            @Override
            public Boolean run() throws Exception
            {
                String query = "SELECT * FROM TANCAR_PARTIDA(?, ?, ?)";
                
                PreparedStatement s = C.prepareStatement(query);
                s.setString(1, jutgeDni);
                s.setInt(2, partida);
                s.setString(3, resultat);
                s.executeQuery();
                
                return true;
            }
            
        }.execute();
    }
    
    public boolean omplirMoviments(final int partidaId, final List<String> moviments)
    {
        return new PostgreTransaction<Boolean>(){

            @Override
            public Boolean run() throws Exception
            {
                // INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES (?, ?, ?)
                String query = "INSERT INTO \"MOVIMENT\" (\"PARTIDA\", \"ORDRE\", \"DESCRIPCIO\") VALUES (?, ?, ?)";
                int ordre = 0;
                
                PreparedStatement s = C.prepareStatement(query);
                
                for ( String moviment : moviments )
                {
                    s.setInt(1, partidaId);
                    s.setInt(2, ordre);
                    s.setString(3, moviment);
                    s.executeUpdate();
                    
                    ordre++;
                }
                
                return true;
            }
            
        }.execute();
    }

}
