package edu.ub.bd.chessticket.controllers;

import java.util.Scanner;

/**
 * Clase base de la interficie d'usuari per mostrar un menu.
 * 
 * Dona una serie de metodes utils per llegir informacio de l'usuari.
 * 
 */
public abstract class Menu
{
    protected static Scanner sc = new Scanner(System.in);
    
    protected String usuariDni;
    
    /**
     * Configura el DNI d'un usuari que ha iniciat sessio.
     * 
     * @param usuariDni 
     */
    public void setUsuariDni(String usuariDni)
    {
        this.usuariDni = usuariDni;
    }
    
    /**
     * Permet llegir pel CEE un valor numeric, i verifica que aixi ho sigui.
     * 
     * @param message Etiqueta a escriure pel CES abans de llegir el CEE.
     * @return El valor enter que hagi introduit l'usuari.
     */
    protected int readint(String message)
    {
        System.out.print(message);
        while ( !sc.hasNextInt() )
        {
            System.out.println("Valor incorrecte.");
            System.out.print(message);
            sc.next();
        }
        
        return sc.nextInt();
    }
    
    /**
     * Permet llegir pel CEE una cadena de caracters.
     * 
     * @param message Etiqueta a escriure pel CES abans de llegir el CEE.
     * @return Cadena de caracters acabada en salt de linea introduida per l'usuari.
     */
    protected String readline(String message)
    {
        System.out.print(message);
        return sc.next();
    }
    
    /**
     * Aquest metode s'ha d'encarregar d'executar les accions que tingui que fer el menu,
     * com llegir les opcions de l'usuari, o decidir si passar a un altre menu.
     * 
     * @throws Exception 
     */
    public abstract void executar() throws Exception;
    
    /**
     * Aquest metode s'ha d'encarregar de mostrar les opcions que tindria el menu.
     * 
     * @throws Exception 
     */
    public abstract void mostrarOpcions() throws Exception;
    
}
