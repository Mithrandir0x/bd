package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.models.Sala;
import edu.ub.bd.chessticket.services.JutgeService;
import edu.ub.bd.chessticket.services.OrganitzadorService;
import edu.ub.bd.chessticket.services.TaquillerService;
import java.util.List;

public class OrganitzadorMenu extends Menu
{
    
    private JutgeService jutgeService = new JutgeService();
    private OrganitzadorService organitzadorService = new OrganitzadorService();
    private TaquillerService taquillerService = new TaquillerService();

    @Override
    public void executar() throws Exception
    {
        boolean goBack = false;
        
        while ( !goBack )
        {
            mostrarOpcions();
            
            System.out.print("\nOPCIO: ");
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
                    System.out.println("\nValor incorrecte. Si us plau, torna a seleccionar la opció desitjada.\n");
            }
        }
    }

    @Override
    public void mostrarOpcions() throws Exception
    {
        System.out.println("******************************\n         MENÚ ORGANITZADOR\n******************************\n"
            + "\n1-Organitzar Partida"
            + "\n2-Tancar sessio"
            + "\n3-Sortir");
    }
    
    private void organitzarPartida()
    {
        int jornada;
        int sala;
        int jugador_blanques;
        int jugador_negres;
        int jutge;
        
        List<Sala> sales;
        List<String> jugadors;
        List<String> jutges;
        
        try
        {
            taquillerService.consultarTotesJornades();
            if ( volCrearNovaJornada() )
            {
                jornada = demanar("Introdueixi el identificador de la nova jornada:");
                organitzadorService.crearJornada(jornada);
            }
            else
            {
                jornada = demanar("Introdueixi la jornada existent:");
            }

            sales = organitzadorService.consultarTotesSales();
            sala = demanar("Introdueixi la sala:");
            validarValor(sala, sales);

            jugadors = organitzadorService.consultarTotsJugadors();
            jugador_blanques = demanar("Introdueixi el jugador de les blanques:");
            validarValor(jugador_blanques, jugadors);
            jugador_negres = demanar("Introdueixi el jugador de les negres:");
            validarValor(jugador_negres, jugadors);
            validarJugadors(jugador_blanques, jugador_negres);

            jutges = jutgeService.consultarTotsJutges();
            jutge = demanar("Introdueixi el jutge de la partida:");
            validarValor(jutge, jutges);

            Integer result = organitzadorService.crearPartida(jugadors.get(jugador_blanques), jugadors.get(jugador_negres), jutges.get(jutge), jornada, sales.get(sala));
            if ( result != null )
            {
                System.out.println("\nS'ha creat la nova partida amb exit.\n");
            }
            else
            {
                System.out.println("\nNo s'ha pogut crear la partida.\n");
            }
        }
        catch ( Exception ex )
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private boolean volCrearNovaJornada()
    {
        System.out.println("Vol crear una nova jornada o vol utilitzar una jornada existent?\n"
            + "\n1-Crear una nova jornada"
            + "\n2-Utilitzar una jornada existent");
        System.out.print("\nOPCIO: ");
        waitNextInt();
        return sc.nextInt() == 1;
    }
    
    private int demanar(String enquiry)
    {
        System.out.print(enquiry + " ");
        waitNextInt();
        return sc.nextInt();
    }
    
    private void validarValor(int valor, List llista) throws Exception
    {
        if ( valor >= 0 && valor < llista.size() )
            return;
        
        throw new Exception("No es valid el camp obtingut.");
    }
    
    private void validarJugadors(int blanques, int negres) throws Exception
    {
        if ( blanques != negres )
            return;
        
        throw new Exception("No es pot fer una partida amb els mateixos jugadors.");
    }
    
}
