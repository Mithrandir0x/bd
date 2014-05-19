package edu.ub.bd;

import edu.ub.bd.chessticket.controllers.VisitantMenu;
import java.util.Scanner;

public class ChessTicket
{
    
    Scanner sc = new Scanner(System.in);
    
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
