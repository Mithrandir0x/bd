package edu.ub.bd.chessticket.controllers;

public class LoginMenu extends Menu
{
    
    private TaquillerMenu taquillerMenu = new TaquillerMenu();
    private JutgeMenu jutgeMenu = new JutgeMenu();

    @Override
    public void run() throws Exception
    {
        boolean goBack = false;
        
        while ( !goBack )
        {
            showOptions();
            
            waitNextInt();
            
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    taquillerMenu.setUsuariDni("43382738F");
                    taquillerMenu.run();
                    break;
                case 2:
                    jutgeMenu.setUsuariDni("20400453H");
                    jutgeMenu.run();
                    break;
                case 3:
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
    public void showOptions() throws Exception
    {
        System.out.println("******************************\n         LOGIN\n******************************\n"
                + "\n1-Taquiller"
                + "\n2-Jutge"
                + "\n3-Organitzador"
                + "\n4-Anònim"
                + "\n5-Sortir");
    }
    
}
