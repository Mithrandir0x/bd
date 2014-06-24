package edu.ub.bd.chessticket.services;

import edu.ub.bd.chessticket.models.Sala;
import edu.ub.bd.utils.PostgreTransaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Consultes emprades per TaquillerMenu.
 * 
 */
public class TaquillerService extends VisitantService
{
    
    /**
     * Persisteix a la base de dades la venta d'una entrada feta per un
     * taquiller per la sala i jornada pasades per parametre.
     * 
     * @param sala Sala on es fa la partida.
     * @param jornada Jornada durant la qual es fa la partida.
     * @param taquillerDni DNI del taquiller que ha fet la venta.
     * @return Retorna un boolea indicant si s'ha fet o no la venta.
     */
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
    
    /**
     * Mostra per pantalla les sales d'hotels disponibles per la jornada pasada per parametre.
     * 
     * @param jornada Identificador de la jornada.
     * @return Llista de sales d'hotels.
     */
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
                
                System.out.println("\nSales amb entrades disponibles:\n");
                System.out.println(" ID | HOTEL                    | SALA                     | ENTRADES DISPONIBLES");
                System.out.println("----+--------------------------+--------------------------+----------------------");
                
                ResultSet rs = s.executeQuery();
                while ( rs.next() )
                {
                    String hotel = rs.getString(1);
                    String sala = rs.getString(2);
                    int entradesDisponibles = rs.getInt(3);
                    if ( entradesDisponibles > 0 )
                    {
                        System.out.println(String.format(" %2d | %24s | %24s | %20d", i, hotel, sala, entradesDisponibles));
                        l.add(new Sala(sala, hotel));
                        i++;
                    }
                }
                
                return l;
            }
            
        }.execute();
    }
    
    /**
     * Mostra per pantalla les jornades disponibles per les que es pot vendre entrades.
     * 
     * @return Llista d'identificadors numerics de les jornades.
     */
    public List<Integer> consultarJornadesDisponibles()
    {
        return new PostgreTransaction<List<Integer>>(){

            @Override
            public List<Integer> run() throws Exception
            {
                List<Integer> l = new ArrayList<Integer>();
                // SELECT "ID", "DATA_REALITZACIO", ENTRADES_JORNADA("ID") FROM "JORNADA"
                String query = "SELECT \"ID\", \"DATA_REALITZACIO\", SIMPLE_ENTRADES_JORNADA(\"ID\") AS EJ FROM \"JORNADA\"";
                
                PreparedStatement s = C.prepareStatement(query);
                ResultSet rs = s.executeQuery();
                
                System.out.println("\nJornades amb sales disponibles per vendre entrades:\n");
                System.out.println(" ID | DATA REALITZACIO | ENTRADES DISPONIBLES");
                System.out.println("----+------------------+----------------------");
                
                while ( rs.next() )
                {
                    int id = rs.getInt(1);
                    Date data_realitzacio = rs.getDate(2);
                    int entrades = rs.getInt(3);
                    
                    if ( entrades > 0 )
                    {
                        System.out.println(String.format(" %2d | %16s | %20d", id, data_realitzacio, entrades));
                        l.add(id);
                    }
                }
                
                return l;
            }
            
        }.execute();
    }
    
    /**
     * Mostra per pantalla les jornades existents a la base de dades.
     * 
     * @return Llista d'identificadors numerics de les jornades.
     */
    public List<Integer> consultarTotesJornades()
    {
        return new PostgreTransaction<List<Integer>>(){

            @Override
            public List<Integer> run() throws Exception
            {
                List<Integer> l = new ArrayList<Integer>();
                String query = "SELECT \"ID\", \"DATA_REALITZACIO\" FROM \"JORNADA\"";
                
                PreparedStatement s = C.prepareStatement(query);
                
                System.out.println("\nJornades existents:\n");
                System.out.println(" ID | DATA REALITZACIO");
                System.out.println("----+------------------");
                
                ResultSet rs = s.executeQuery();
                while ( rs.next() )
                {
                    int id = rs.getInt(1);
                    Date data_realitzacio = rs.getDate(2);
                    
                    System.out.println(String.format(" %2d | %16s", id, data_realitzacio));
                    l.add(id);
                }
                
                return l;
            }
            
        }.execute();
    }
    
}
