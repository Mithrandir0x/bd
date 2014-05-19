package edu.ub.bd.chessticket.controllers;

public class LoginMenu extends Menu
{
    
    private TaquillerMenu taquillerMenu = new TaquillerMenu();
    private JutgeMenu jutgeMenu = new JutgeMenu();
    private OrganitzadorMenu organitzadorMenu = new OrganitzadorMenu();

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
                    taquillerMenu.setUsuariDni("43382738F");
                    taquillerMenu.executar();
                    break;
                case 2:
                    jutgeMenu.setUsuariDni("20400453H");
                    jutgeMenu.executar();
                    break;
                case 3:
                    organitzadorMenu.executar();
                    break;
                case 4:
                    goBack = true;
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
            }
        }
    }

    @Override
    public void mostrarOpcions() throws Exception
    {
        System.out.println("******************************\n         LOGIN\n******************************\n"
                + "\n1-Taquiller"
                + "\n2-Jutge"
                + "\n3-Organitzador"
                + "\n4-Visitant"
                + "\n5-Sortir");
    }
    
}
