package edu.ub.bd.chessticket.services;

import edu.ub.bd.utils.PostgreTransaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JugadorService
{
    
    public List<String> consultarTotsJugadors()
    {
        return new PostgreTransaction<List<String>>(){

            @Override
            public List<String> run() throws Exception
            {
                List<String> l = new ArrayList<String>();
                int i = 0;
                // SELECT * FROM "JUGADOR
                String query = "SELECT * FROM \"JUGADOR\"";
                
                PreparedStatement s = C.prepareStatement(query);
                ResultSet rs = s.executeQuery();
                while ( rs.next() )
                {
                    String dni = rs.getString(1);
                    String nom = rs.getString(2);
                    String pais = rs.getString(9);
                    
                    System.out.println(String.format("[%2d] %32s | %32s | %32s", i, dni, nom, pais));
                    
                    l.add(dni);
                }
                
                return l;
            }
            
        }.execute();
    }
    
}
