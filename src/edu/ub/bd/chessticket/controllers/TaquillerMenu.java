package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.models.Sala;
import edu.ub.bd.chessticket.services.TaquillerService;
import edu.ub.bd.chessticket.services.VisitantService;
import java.util.List;

/**
 * Aquest menu presenta les funcionalitats disponibles pels taquillers.
 * 
 * Te acces a les funcionalitats d'un usuari no enregistrat, a mes de poder
 * vendre entrades.
 * 
 */
public class TaquillerMenu extends Menu
{

    private VisitantService visitantService = new VisitantService();
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
                    visitantService.consultarJornada();
                    break;
                case 2:
                    visitantService.consultarClassificacio();
                    break;
                case 3:
                    vendreEntrada();
                    break;
                case 4:
                    goBack = true;
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("\nValor incorrecte. Si us plau, torna a seleccionar la opció desitjada.\n");
            }
        }
    }

    @Override
    public void mostrarOpcions() throws Exception
    {
        System.out.println("******************************\n         MENÚ TAQUILLER\n******************************\n"
            + "\n1-Consultar Jornada"
            + "\n2-Consultar Classificacio"
            + "\n3-Vendre Entrada"
            + "\n4-Tancar sessió"
            + "\n5-Sortir");
    }
    
    /**
     * S'encarrega de demanar la informacio necessaria al taquiller per poder
     * fer la venta d'una entrada.
     * 
     */
    private void vendreEntrada()
    {
        List<Integer> jornades = taquillerService.consultarJornadesDisponibles();
        
        int jornada = readint("Introdueixi el identificador de la jornada: ");
        
        if ( !jornades.contains(jornada) )
        {
            System.out.println("La jornada [" + jornada + "] no es valida.");
            return;
        }
        
        List<Sala> salesDisponibles = taquillerService.consultarSalesDisponibles(jornada);
        
        if ( salesDisponibles.size() > 0 )
        {
            int salaId = readint("\nIntrodueixi el identificador de la sala per la que vol vendre l'entrada: ");
            if ( salaId >= 0 && salaId < salesDisponibles.size() )
            {
                Sala sala = salesDisponibles.get(salaId);

                boolean r = taquillerService.vendreEntrada(sala, jornada, usuariDni);
                if ( r )
                {
                    System.out.println("\nS'ha venut l'entrada per a la sala " + sala + ".\n");
                    taquillerService.consultarSalesDisponibles(jornada);
                }
                else
                {
                    System.out.println("\nS'ha intentat vendre una entrada per una sala on no hi han entrades disponibles.\n");
                }
            }
            else
            {
                System.out.println("\nLa sala [" + salaId + "] no és vàlida.\n");
            }
        }
        else
        {
            System.out.println("\nNo hi han sales disponibles per aquesta jornada.\n");
        }
    }
    
}
