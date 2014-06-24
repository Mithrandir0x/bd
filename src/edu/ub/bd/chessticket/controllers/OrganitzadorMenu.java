package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.models.Sala;
import edu.ub.bd.chessticket.services.JutgeService;
import edu.ub.bd.chessticket.services.OrganitzadorService;
import edu.ub.bd.chessticket.services.TaquillerService;
import java.util.List;

/**
 * Aquest menu ofereix les funcionalitats per un organitzador.
 * 
 * Pot organitzar noves partides.
 * 
 */
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
            
            int choice = readint("\nOPCIO: ");
            
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
    
    /**
     * S'encarrega d'organitzar una nova partida.
     * 
     * Alhora d'organitzar una nova partida, pot fer-la per una jornada
     * existent, o pot crear-ne d'una nova.
     * 
     * Despres li demanara la sala on es fara aquesta partida.
     * 
     * I per ultim demanara els jugadors i el jutge que arbitrara la partida.
     * 
     */
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
            if ( volCrearNovaJornada() )
            {
                jornada = readint("Introdueixi el identificador de la nova jornada: ");
                organitzadorService.crearJornada(jornada);
            }
            else
            {
                taquillerService.consultarTotesJornades();
                jornada = readint("Introdueixi el identificador de la jornada existent: ");
            }

            sales = organitzadorService.consultarTotesSales();
            sala = readint("Introdueixi la sala:");
            validarValor(sala, sales);

            jugadors = organitzadorService.consultarTotsJugadors();
            jugador_blanques = readint("Introdueixi el identificador del jugador de les blanques: ");
            validarValor(jugador_blanques, jugadors);
            jugador_negres = readint("Introdueixi el identificador del jugador de les negres: ");
            validarValor(jugador_negres, jugadors);
            validarJugadors(jugador_blanques, jugador_negres);

            jutges = jutgeService.consultarTotsJutges();
            jutge = readint("Introdueixi el identificador del jutge de la partida: ");
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
    
    /**
     * Demana a l'usuari si vol crear una nova jornada o vol seleccionar una jornada existent.
     * 
     * @return Boolea indicant si vol una nova jornada o no.
     */
    private boolean volCrearNovaJornada()
    {
        System.out.println("Vol crear una nova jornada o vol utilitzar una jornada existent?\n"
            + "\n1-Crear una nova jornada"
            + "\n2-Utilitzar una jornada existent");
        int opt = readint("\nOPCIO: ");
        return opt == 1;
    }
    
    /**
     * Donat un valor numeric i una llista, mira que aquest valor estigui dins el rang
     * entre 0 i la longitud de la llista.
     * 
     * @param valor Valor numeric.
     * @param llista Llista d'elements
     * @throws Exception El valor no esta dins el rang esmentat.
     */
    private void validarValor(int valor, List llista) throws Exception
    {
        if ( valor >= 0 && valor < llista.size() )
            return;
        
        throw new Exception("No es valid el camp obtingut.");
    }
    
    /**
     * Verifica que els identificadors de jugadors no siguin identics.
     * 
     * Aquesta validacio no es del tot necessaria, donat que a la base de dades
     * ja hi ha un TRIGGER que s'encarrega de verificar aquesta condicio.
     *
     * Pero s'ha implementat per donar a l'usuari un feedback mes granulat
     * sobre el resultat del que escriu.
     * 
     * @param blanques Identificador de jugador blanques.
     * @param negres Identificador de jugador negres
     * @throws Exception Tots dos identificadors son els mateixos
     */
    private void validarJugadors(int blanques, int negres) throws Exception
    {
        if ( blanques != negres )
            return;
        
        throw new Exception("No es pot fer una partida amb els mateixos jugadors.");
    }
    
}
