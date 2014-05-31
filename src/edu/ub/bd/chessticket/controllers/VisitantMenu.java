package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.services.VisitantService;

public class VisitantMenu extends Menu
{
    
    private VisitantService visitantService = new VisitantService();
    private LoginMenu loginMenu = new LoginMenu();

    @Override
    public void executar() throws Exception 
    {
        while ( true )
        {
            mostrarOpcions();
            
            System.out.print("\nOPCIO: ");
            waitNextInt();
            
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    loginMenu.executar();
                    break;
                case 2:
                    visitantService.consultarJornada();
                    break;
                case 3:
                    visitantService.consultarClassificacio();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("\nValor incorrecte. Si us plau, torna a seleccionar la opció desitjada.\n");
            }
        }
    }

    @Override
    public void mostrarOpcions() throws Exception
    {
        System.out.println("******************************\n           CHESS TICKET\n******************************\n"
                + "\n1-Log In"
                + "\n2-Consultar Jornada"
                + "\n3-Consultar Classificacio"
                + "\n4-Sortir");
    }
    
}
