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
            VisitantMenu menu = new VisitantMenu();
            menu.run();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }
    
    /**
    * Admin menu
    */
    /* private void menuAdmin() {
        subMenuAdmin();
        try{
            int choice=sc.nextInt();
            if (choice!=1 && choice!=2){
                System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
                menuVisitant();
            }
            switch (choice){
                case 1:
                    organitzarPartida();
                    break;
               case 2:
                    menuVisitant();
                    break;
            } 
        }catch(Exception e){
            System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
            sc.nextLine();
            menuAdmin();
        }
    }
    /**
    * Show the options of the Admin menu
    */
    /* private void subMenuAdmin(){
        System.out.println("******************************\n         MENÚ ADMIN\n******************************\n"
                + "\n1-Organitzar Partida"
                + "\n2-Sortir");
    }

    private void organitzarPartida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } */

}
