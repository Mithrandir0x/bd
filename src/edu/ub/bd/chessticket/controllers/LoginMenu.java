package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.models.Usuari;
import edu.ub.bd.chessticket.services.LoginService;

public class LoginMenu extends Menu
{
    
    private TaquillerMenu taquillerMenu = new TaquillerMenu();
    private JutgeMenu jutgeMenu = new JutgeMenu();
    private OrganitzadorMenu organitzadorMenu = new OrganitzadorMenu();
    
    private LoginService loginService = new LoginService();
    
    private Menu menu;
    private Usuari usuari;

    @Override
    public void executar() throws Exception
    {
        boolean goBack = false;
        
        while ( !goBack )
        {
            if ( usuari != null )
            {
                // Aquesta condicio es necessaria quan es torna de qualsevol
                // altre menu instanciat des d'aquest punt. S'ha de reiniciar
                // l'estat d'aquestes dues variables.
                usuari = null;
                menu = null;
                break;
            }
            
            mostrarOpcions();
            
            String dni = c.readLine("Introdueixi el DNI: ");
            
            menu = ferLogin(dni);
            
            if ( menu != null )
            {
                menu.setUsuariDni(usuari.getDni());
                menu.executar();
            }
            else
            {
                goBack = true;
            }
        }
    }

    @Override
    public void mostrarOpcions() throws Exception
    {
        System.out.println("******************************\n         LOGIN\n******************************\n");
    }
    
    private Menu ferLogin(String dni) throws Exception
    {
        usuari = loginService.verificarUsuari(dni);
        
        if ( usuari != null )
        {
            System.out.println(String.format("Benvingut, %s [Rol: %s]", usuari.getNom(), usuari.getRol()));
            
                 if ( usuari.getRol().equals("TAQUILLER") )    { return taquillerMenu; }
            else if ( usuari.getRol().equals("JUTGE") )        { return jutgeMenu; }
            else if ( usuari.getRol().equals("ORGANITZADOR") ) { return organitzadorMenu; }
            else
            {
                System.out.println("Rol desconegut. Aixo no hauria de pasar");
            }
        }
        else
        {
            System.out.println("\nADVERTENCIA: Usuari no reconegut.\n");
        }
        
        return null;
    }
    
}
