package edu.ub.bd.chessticket.controllers;

import java.io.Console;
import java.util.Scanner;

public abstract class Menu
{
    protected static Scanner sc = new Scanner(System.in);
    protected static Console c = System.console();
    
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
    
    public abstract void executar() throws Exception;
    
    public abstract void mostrarOpcions() throws Exception;
    
}
