package edu.ub.bd;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessTicket
{
    /**
     * @param controlador the controller of the program
     * @param sys the main system
     * @param idUsuariActual the id of the current user
     * @param medallerActualitzat the boolean that idicates if the medal is updated
     * @param args the command line arguments
     */
    Scanner sc = new Scanner(System.in);
    private final static ChessTicket sys = new ChessTicket();
    private int idUsuariActual;
    private boolean medallerActualitzat=false;
    /**
     * Constructor
     * @param args 
     */

    public static void main(String[] args)
    {     
        //sys.inicialitzar();
        sys.menuVisitant();     
    }
    
    /**
    * First menu that appears in when the software opens
    */
    private void menuVisitant(){
        //sc.nextLine();
        subMenuVisitant();
        try{
            int choice=sc.nextInt();
            if (choice!=1 && choice!=2 && choice!=3 && choice!=4){
                System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
                menuVisitant();
            }
            switch (choice){
                case 1:
                    logIn();
                    break;
                case 2:
                    consultarJornada();
                    break;
                case 3:
                    consultarClassificacio();
                    break;
                case 4:
                    System.exit(0);
            }
        }catch(Exception e){
            System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
            sc.nextLine();
            menuVisitant();
        }      
    }
    
     /**
    * Show the options of the Visitant menu 
    */
    private void subMenuVisitant(){
        System.out.println("******************************\n           XGAMES\n******************************\n"
                + "\n1-Log In"
                + "\n2-Consultar Jornada"
                + "\n3-Consultar Classificacio"
                + "\n4-Sortir");
    }
    
    /**
    * Locker menu
    */
    private void menuTaquiller() {
        subMenuTaquiller();
        try{
            int choice=sc.nextInt();
            if (choice!=1 && choice!=2){
                System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
                menuVisitant();
            }
            switch (choice){
                case 1:
                    vendreEntrada();
                    break;
                case 2:
                    menuVisitant();
                    break;
            }
        }catch(Exception e){
            System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
            sc.nextLine();
            menuTaquiller();
        }
    }
    /**
    * Show the options of the Locker menu
    */
    private void subMenuTaquiller(){
        System.out.println("******************************\n         MENÚ TAQUILLER\n******************************\n"
                + "\n1-Vendre Entrada"
                + "\n2-Sortir");
    }
    
    /**
    * Jutge menu
    */
    private void menuJutge() {
        subMenuJutge();
        try{
            int choice=sc.nextInt();
            if (choice!=1 && choice!=2 && choice!=3 && choice!=4){
                System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
                menuVisitant();
            }
            switch (choice){
                case 1:
                    consultarPartides();
                    break;
                case 2:
                    tancarPartida();
                    menuJutge();
                    break;
                case 3:
                    afegirMoviments();
                    menuJutge();
                    break;
                case 4:
                    menuVisitant();
                    break;
            }
        }catch(Exception e){
            System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
            sc.nextLine();
            menuJutge();
        }
    }
    /**
    * Show the options of the Jutge menu
    */
    private void subMenuJutge(){
        System.out.println("******************************\n         MENÚ JUTGE\n******************************\n"
                + "\n1-Consultar Partides"
                + "\n2-Tancar Partida"
                + "\n3-Afegir Moviments a Partida"
                + "\n4-Sortir");
    }
    
    /**
    * Admin menu
    */
    private void menuAdmin() {
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
    private void subMenuAdmin(){
        System.out.println("******************************\n         MENÚ ADMIN\n******************************\n"
                + "\n1-Organitzar Partida"
                + "\n2-Sortir");
    }

    private void logIn() {
        System.out.println("Nom d'usuari: ");
        String username = sc.next();
        System.out.println("Contrassenya: ");
        String password = sc.next();
    }

    private void consultarJornada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void consultarClassificacio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void vendreEntrada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void consultarPartides() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void tancarPartida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void afegirMoviments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void organitzarPartida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
