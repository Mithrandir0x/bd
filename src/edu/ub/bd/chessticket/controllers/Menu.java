package edu.ub.bd.chessticket.controllers;

import java.util.Scanner;

public abstract class Menu
{
    protected static Scanner sc = new Scanner(System.in);
    
    protected String usuariDni;
    
    public void setUsuariDni(String usuariDni)
    {
        this.usuariDni = usuariDni;
    }
    
    protected void waitNextInt()
    {
        while ( !sc.hasNextInt() )
        {
            System.out.println("Valor incorrecte.");
            sc.next();
        }
    }
    
    public abstract void run() throws Exception;
    
    public abstract void showOptions() throws Exception;
    
}
