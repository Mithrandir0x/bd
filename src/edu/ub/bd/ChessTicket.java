package edu.ub.bd;

import edu.ub.bd.chessticket.controllers.VisitantMenu;

public class ChessTicket
{
    
    public static void main(String[] args)
    {     
        try
        {
            new VisitantMenu().executar();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }

}
