package edu.ub.bd.chessticket.services;

import edu.ub.bd.chessticket.models.Usuari;
import edu.ub.bd.utils.PostgreTransaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService
{
    
    public Usuari verificarUsuari(final String dni) throws Exception
    {
        return new PostgreTransaction<Usuari>(){

            @Override
            public Usuari run() throws Exception
            {
                // SELECT "NOM", "PAIS", "PARTIDES_GUANYADES" FROM "JUGADOR" ORDER BY "PARTIDES_GUANYADES" DESC
                String query =  "SELECT \"NOM\", \"ROL\" FROM \"USUARI\" WHERE \"DNI\" = ?";
                
                PreparedStatement s = C.prepareStatement(query);
                s.setString(1, dni);
                
                ResultSet rs = s.executeQuery();
                if ( rs.next() )
                {
                    String nom = rs.getString(1);
                    String rol = rs.getString(2);
                    
                    return new Usuari(dni, nom, rol);
                }
                
                return null;
            }
            
        }.execute();
    }
    
}
