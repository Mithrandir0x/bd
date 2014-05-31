package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.models.Sala;
import edu.ub.bd.chessticket.services.TaquillerService;
import edu.ub.bd.chessticket.services.VisitantService;
import java.util.List;

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
            
            System.out.print("\nOPCIO: ");
            waitNextInt();
            
            int choice=sc.nextInt();
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
    
    private void vendreEntrada()
    {
        List<Integer> jornades = taquillerService.consultarJornadesDisponibles();
        
        System.out.print("Introdueixi el identificador de la jornada: ");
        waitNextInt();
        int jornada = sc.nextInt();
        
        if ( !jornades.contains(jornada) )
        {
            System.out.println("La jornada [" + jornada + "] no es valida.");
            return;
        }
        
        List<Sala> salesDisponibles = taquillerService.consultarSalesDisponibles(jornada);
        
        if ( salesDisponibles.size() > 0 )
        {
            System.out.print("\nIntrodueixi la sala: ");
            waitNextInt();
            int salaId = sc.nextInt();
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
