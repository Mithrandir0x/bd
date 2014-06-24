package edu.ub.bd.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase abstracta requerida per fer qualsevol transaccio atomica contra la base de dades.
 * 
 * Aquesta clase permet encapsular tota la logica necessaria per fer una transaccio
 * contra la base de dades, fent que sigui l'unic punt on s'hagi de controlar la
 * connexio, i la gestio dels recursos que s'usin en la transaccio.
 * 
 * El tipus generic, tot i que s'obliga a declarar-ne un, no es necessari per totes les
 * transaccions (com per exemple UPDATEs o DELETEs). En aquests casos, simplement es
 * declara com a tipus Object si es vol simplificar la implementacio.
 * 
 * @param <T> Tipus de dada retornat per la transaccio
 */
public abstract class PostgreTransaction<T>
{

    protected Connection C;

    protected PostgreTransaction()
    {
    }

    /**
     * Execucio de la transaccio.
     * 
     * @return Tipus generic si tot ha anat be, o null.
     */
    public synchronized T execute()
    {
        try
        {
            C = getConnection();
            
            if ( C != null )
            {
                T obj = run();
                C.close();

                return obj;
            }
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Qualsevol accio que es vulgui fer durant la transaccio s'ha
     * d'implementar en aquest metode.
     * 
     * @return
     * @throws Exception 
     */
    public abstract T run() throws Exception;

    private Connection getConnection() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1/vagrant", "postgres", "password");
    }

}
