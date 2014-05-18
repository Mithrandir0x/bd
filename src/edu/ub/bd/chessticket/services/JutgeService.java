package edu.ub.bd.chessticket.services;

import edu.ub.bd.utils.PostgreTransaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JutgeService
{
    
    public List<Integer> consultarPartides(final String jutgeDni, final boolean mostrarNomesNules)
    {
        return new PostgreTransaction<List<Integer>>(){

            @Override
            public List<Integer> run() throws Exception
            {
                List<Integer> l = new ArrayList<Integer>();
                // SELECT "ID", NOM_JUGADOR("BLANQUES") AS BLANQUES, NOM_JUGADOR("NEGRES") AS NEGRES, "JORNADA", "RESULTAT" FROM "PARTIDA" WHERE "JUTGE" = ?
                String query = "SELECT \"ID\", NOM_JUGADOR(\"BLANQUES\") AS BLANQUES, NOM_JUGADOR(\"NEGRES\") AS NEGRES, \"JORNADA\", \"RESULTAT\" FROM \"PARTIDA\" WHERE \"JUTGE\" = ?";
                
                PreparedStatement s = C.prepareStatement(query);
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
                            System.out.println(String.format("[%2d] %2d | %1s | %32s | %32s", id, jornada, resultat, blanques, negres));
                    }
                    else
                    {
                        System.out.println(String.format("[%2d] %2d | %1s | %32s | %32s", id, jornada, resultat, blanques, negres));
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

    public void tancarPartida(final String jutgeDni, final int partida, final String resultat)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void afegirMoviments()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
