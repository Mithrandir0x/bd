package edu.ub.bd.chessticket.services;

import edu.ub.bd.utils.PostgreTransaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Consultes utilitzades per JutgeMenu i OrganitzadorMenu.
 * 
 */
public class JutgeService
{
    
    /**
     * Mostra per pantalla els jutges disponibles a la base de dades.
     * 
     * @return Llista de cadenes de caracters dels DNIs dels jutges.
     */
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
                System.out.println("\nLlistat dels jutges que poden arbitrar partides:\n");
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
    
    /**
     * Mostra per pantalla la llista de partides, amb resultat i nom del jugadors, arbitrades
     * pel jutge amb DNI pasat per parametre.
     * 
     * @param jutgeDni DNI del jutge del que s'ha de llistar les partides.
     * @param mostrarNomesSenseResultat Boolea per indicar si mostrar nomes aquelles partides sense resultat.
     * @return Llista d'identificadors numerics de les partides mostrades per pantalla.
     */
    public List<Integer> consultarPartides(final String jutgeDni, final boolean mostrarNomesSenseResultat)
    {
        return new PostgreTransaction<List<Integer>>(){

            @Override
            public List<Integer> run() throws Exception
            {
                List<Integer> l = new ArrayList<Integer>();
                // SELECT "ID", b."NOM" AS BLANQUES, n."NOM" AS NEGRES, "JORNADA", "RESULTAT" FROM "JUGADOR" b, "JUGADOR" n, "PARTIDA" WHERE b."DNI" = "BLANQUES" AND n."DNI" = "NEGRES" AND "JUTGE" = ? ORDER BY "JORNADA" ASC
                String query = "SELECT \"ID\", b.\"NOM\" AS BLANQUES, n.\"NOM\" AS NEGRES, \"JORNADA\", \"RESULTAT\" FROM \"JUGADOR\" b, \"JUGADOR\" n, \"PARTIDA\" WHERE b.\"DNI\" = \"BLANQUES\" AND n.\"DNI\" = \"NEGRES\" AND \"JUTGE\" = ? ORDER BY \"JORNADA\" ASC";
                
                PreparedStatement s = C.prepareStatement(query);
                
                if ( mostrarNomesSenseResultat )
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
                    
                    if ( mostrarNomesSenseResultat )
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
    
    /**
     * Mostra per pantalla la llista de partides, amb resultat i nom del jugadors, arbitrades
     * pel jutge amb DNI pasat per parametre, que SI que tenen inputat el resultat.
     * 
     * @param jutgeDni DNI del jutge del que s'ha de llistar les partides.
     * @return Llista d'identificadors numerics de les partides mostrades per pantalla.
     */
    public List<Integer> consultarPartides(final String jutgeDni)
    {
        return consultarPartides(jutgeDni, false);
    }

    /**
     * Tanca la partida arbitrada pel jutge amb un resultat pasat per parametre.
     * 
     * @param jutgeDni DNI del jutge que arbitra la partida.
     * @param partida Identificador numeric de la partida.
     * @param resultat Resultat de la partida. 
     * @return Boolea indicant si la transaccio ha funcionat correctament.
     */
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
    
    /**
     * Donada una llista de moviments, s'afegeixen a la base de dades per
     * la partida pasada per parametre.
     * 
     * @param partidaId Identificador numeric de la partida.
     * @param moviments Llista de cadenes de caracter amb cadascun dels moviments de la partida.
     * @return Boolea indicant si la transaccio ha anat be o no.
     */
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
