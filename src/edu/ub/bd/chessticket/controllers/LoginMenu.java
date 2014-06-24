package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.models.Usuari;
import edu.ub.bd.chessticket.services.LoginService;

/**
 * Aquest menu s'encarrega de fer la validacio pertinent contra
 * la base de dades per autenticar un usuari i iniciar la seva sessio.
 * 
 */
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
            
            String dni = readline("Introdueixi el DNI: ").trim();
            String password = readline("Introdueixi la contrasenya: ").trim();
            
            menu = ferLogin(dni, password);
            
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
    
    /**
     * En funcio dels credencials donats per parametre, retorna el menu al que ha de donar pas
     * per executar les funcions adients d'aquell usuari.
     * 
     * @param dni El DNI de la persona amb la que s'intenta iniciar sessio.
     * @param password La contrassenya d'aquesta persona.
     * @return L'objecte menu al qual es dona pas si s'ha validad correctament. Si no es aixi, retorna null.
     * @throws Exception 
     */
    private Menu ferLogin(String dni, String password) throws Exception
    {
        usuari = loginService.verificarUsuari(dni, password);
        
        if ( usuari != null )
        {
            System.out.println(String.format("\nBenvingut, %s [Rol: %s]\n", usuari.getNom(), usuari.getRol()));
            
                 if ( usuari.getRol().equals("TAQUILLER") )    { return taquillerMenu; }
            else if ( usuari.getRol().equals("JUTGE") )        { return jutgeMenu; }
            else if ( usuari.getRol().equals("ORGANITZADOR") ) { return organitzadorMenu; }
            else
            {
                System.out.println("\nRol desconegut. Aixo no hauria de pasar\n");
            }
        }
        else
        {
            System.out.println("\nADVERTENCIA: Usuari no reconegut.\n");
        }
        
        return null;
    }
    
}
