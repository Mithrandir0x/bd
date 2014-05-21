package edu.ub.bd.chessticket.services;

import edu.ub.bd.chessticket.models.Sala;
import edu.ub.bd.utils.PostgreTransaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaquillerService extends VisitantService
{
    
    public boolean vendreEntrada(final Sala sala, final int jornada, final String taquillerDni)
    {
        return new PostgreTransaction<Boolean>(){

            @Override
            public Boolean run() throws Exception
            {
                String query = "SELECT * FROM COMPRAR_ENTRADA(?, ?, ?, ?)";
                
                PreparedStatement s = C.prepareStatement(query);
                s.setString(1, sala.getHotel());
                s.setString(2, sala.getNom());
                s.setInt(3, jornada);
                s.setString(4, taquillerDni);
                
                ResultSet rs = s.executeQuery();
                if ( rs.next() )
                {
                    int result = rs.getInt(1);
                    
                    return result == 0;
                }
                
                return false;
            }
            
        }.execute();
    }
    
    public List<Sala> consultarSalesDisponibles(final int jornada)
    {
        return new PostgreTransaction<List<Sala>>(){

            @Override
            public List<Sala> run() throws Exception
            {
                int i = 0;
                List<Sala> l = new ArrayList<Sala>();
                String query = "SELECT * FROM ENTRADES_JORNADA(?)";
                
                PreparedStatement s = C.prepareStatement(query);
                s.setInt(1, jornada);
                
                ResultSet rs = s.executeQuery();
                while ( rs.next() )
                {
                    String hotel = rs.getString(1);
                    String sala = rs.getString(2);
                    int entradesDisponibles = rs.getInt(3);
                    if ( entradesDisponibles > 0 )
                    {
                        System.out.println(String.format("[%2d] %24s | %24s | %3d", i, hotel, sala, entradesDisponibles));
                        l.add(new Sala(sala, hotel));
                        i++;
                    }
                }
                
                return l;
            }
            
        }.execute();
    }
    
    public List<Integer> consultarTotesJornades()
    {
        return new PostgreTransaction<List<Integer>>(){

            @Override
            public List<Integer> run() throws Exception
            {
                int i = 0;
                List<Integer> l = new ArrayList<Integer>();
                String query = "SELECT \"ID\", \"DATA_REALITZACIO\" FROM \"JORNADA\"";
                
                PreparedStatement s = C.prepareStatement(query);
                ResultSet rs = s.executeQuery();
                while ( rs.next() )
                {
                    int id = rs.getInt(1);
                    Date data_realitzacio = rs.getDate(2);
                    
                    System.out.println(String.format("[%2d] %32s", id, data_realitzacio));
                    l.add(id);
                    i++;
                }
                
                return l;
            }
            
        }.execute();
    }
    
}
