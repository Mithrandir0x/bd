package edu.ub.bd.chessticket.services;

import edu.ub.bd.chessticket.models.Sala;
import edu.ub.bd.utils.PostgreTransaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrganitzadorService
{
    
    public Integer crearPartida(final String blanques, final String negres, final String jutge, final int jornada, final Sala sala)
    {
        return new PostgreTransaction<Integer>(){

            @Override
            public Integer run() throws Exception
            {
                Double dId = Math.random() * 10000;
                int id = dId.intValue();
                
                System.out.println("Es creara la PARTIDA [" + id + "], on jugara [" + blanques + "] com a BLANQUES, i [" + negres + "] com a NEGRES, i sera arbitrat per [" + jutge + "].");
                System.out.println("La partida es fara a la SALA [" + sala.getNom() + "] del HOTEL [" + sala.getHotel() + "].");
                
                // INSERT INTO "PARTIDA" ("ID", "RESULTAT", "JUTGE", "BLANQUES", "NEGRES", "JORNADA", "HOTEL", "SALA", "ENTRADES_VENUDES") VALUES (?, NULL, ?, ?, ?, ?, ?, '0')
                String query = "INSERT INTO \"PARTIDA\" (\"ID\", \"RESULTAT\", \"JUTGE\", \"BLANQUES\", \"NEGRES\", \"JORNADA\", \"HOTEL\", \"SALA\", \"ENTRADES_VENUDES\") VALUES (?, NULL, ?, ?, ?, ?, ?, ?, '0')";
                
                PreparedStatement s = C.prepareStatement(query);
                s.setInt(1, id);
                s.setString(2, jutge);
                s.setString(3, blanques);
                s.setString(4, negres);
                s.setInt(5, jornada);
                s.setString(6, sala.getHotel());
                s.setString(7, sala.getNom());
                
                return s.executeUpdate();
            }
            
        }.execute();
    }
    
    public void crearJornada(final int id)
    {
        new PostgreTransaction<Integer>(){

            @Override
            public Integer run() throws Exception
            {
                // INSERT INTO "JORNADA" ("ID", "DATA_REALITZACIO") VALUES (?, ?)
                String query = "INSERT INTO \"JORNADA\" (\"ID\", \"DATA_REALITZACIO\") VALUES (?, ?)";
                
                PreparedStatement s = C.prepareStatement(query);
                s.setInt(1, id);
                s.setTimestamp(2, new Timestamp(Calendar.getInstance().getTimeInMillis()));
                return s.executeUpdate();
            }
            
        }.execute();
    }
    
    public List<String> consultarTotsJugadors()
    {
        return new PostgreTransaction<List<String>>(){

            @Override
            public List<String> run() throws Exception
            {
                List<String> l = new ArrayList<String>();
                int i = 0;
                // SELECT "DNI", "NOM", "PAIS" FROM "JUGADOR"
                String query = "SELECT \"DNI\", \"NOM\", \"PAIS\" FROM \"JUGADOR\"";
                
                PreparedStatement s = C.prepareStatement(query);
                ResultSet rs = s.executeQuery();
                while ( rs.next() )
                {
                    String dni = rs.getString(1);
                    String nom = rs.getString(2);
                    String pais = rs.getString(3);
                    
                    System.out.println(String.format("[%2d] %32s | %32s | %32s", i, dni, nom, pais));
                    
                    l.add(dni);
                    i++;
                }
                
                return l;
            }
            
        }.execute();
    }
    
    public List<Sala> consultarTotesSales()
    {
        return new PostgreTransaction<List<Sala>>(){

            @Override
            public List<Sala> run() throws Exception
            {
                int i = 0;
                List<Sala> l = new ArrayList<Sala>();
                String query = "SELECT * FROM \"SALA\"";
                
                PreparedStatement s = C.prepareStatement(query);
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
    
}
