package edu.ub.bd.chessticket.controllers;

public class OrganitzadorMenu extends Menu
{

    @Override
    public void executar() throws Exception
    {
        boolean goBack = false;
        
        while ( !goBack )
        {
            mostrarOpcions();
            
            waitNextInt();
            
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    organitzarPartida();
                    break;
                case 2:
                    goBack = true;
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
            }
        }
    }

    @Override
    public void mostrarOpcions() throws Exception
    {
        System.out.println("******************************\n         MENÚ ADMIN\n******************************\n"
            + "\n1-Organitzar Partida"
            + "\n2-Tancar sessio"
            + "\n3-Sortir");
    }
    
    private void organitzarPartida() throws Exception
    {
    }
    
}
