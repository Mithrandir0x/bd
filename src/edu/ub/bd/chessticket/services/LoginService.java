package edu.ub.bd.chessticket.services;

import edu.ub.bd.chessticket.models.Usuari;
import edu.ub.bd.utils.PostgreTransaction;
import edu.ub.bd.utils.XXTEA;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService
{
    
    private static String key = "cursbd1314/.";
    
    /**
     * Donat un DNI i una contrasenya, retorna l'usuari amb aquest DNI o NULL si no existeix.
     * 
     * @param dni 
     * @param password
     * @return 
     * @throws Exception 
     */
    public Usuari verificarUsuari(final String dni, final String password) throws Exception
    {
        return new PostgreTransaction<Usuari>(){

            @Override
            public Usuari run() throws Exception
            {
                String query =  "SELECT \"NOM\", \"ROL\" FROM \"USUARI\" WHERE \"DNI\" = ? AND \"CONTRASENYA\" = ?";
                
                String encryptedPassword = XXTEA.encryptString(password, key);
                
                PreparedStatement s = C.prepareStatement(query);
                s.setString(1, dni);
                s.setString(2, encryptedPassword);
                
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
