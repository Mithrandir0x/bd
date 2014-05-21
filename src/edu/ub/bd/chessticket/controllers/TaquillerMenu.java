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
                    System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
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
        taquillerService.consultarTotesJornades();
        System.out.println("Introdueixi la jornada:");
        waitNextInt();
        int jornada = sc.nextInt();
        List<Sala> salesDisponibles = taquillerService.consultarSalesDisponibles(jornada);
        
        if ( salesDisponibles.size() > 0 )
        {
            System.out.println("Introdueixi la sala:");
            waitNextInt();
            int salaId = sc.nextInt();
            if ( salaId >= 0 && salaId < salesDisponibles.size() )
            {
                Sala sala = salesDisponibles.get(salaId);

                boolean r = taquillerService.vendreEntrada(sala, jornada, usuariDni);
                if ( r )
                {
                    System.out.println("S'ha venut l'entrada per a la sala " + sala);
                    taquillerService.consultarSalesDisponibles(jornada);
                }
                else
                {
                    System.out.println("S'ha intentat vendre una entrada per una sala on no hi han entrades disponibles.");
                    return;
                }
            }
            else
            {
                System.out.println("La sala seleccionada no és vàlida.");
            }
        }
        else
        {
            System.out.println("No hi han sales disponibles per aquesta jornada.");
        }
    }
    
}
