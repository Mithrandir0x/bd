package edu.ub.bd.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class PostgreTransaction<T>
{

    protected Connection C;

    protected PostgreTransaction()
    {
    }

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

    public abstract T run() throws Exception;

    private Connection getConnection() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1/vagrant", "postgres", "password");
    }

}
