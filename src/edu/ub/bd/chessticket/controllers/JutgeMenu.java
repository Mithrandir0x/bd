package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.services.JutgeService;
import java.util.List;

public class JutgeMenu extends Menu
{
    
    private JutgeService jutgeService = new JutgeService();

    @Override
    public void run() throws Exception
    {
        boolean goBack = false;
        
        while ( !goBack )
        {
            showOptions();
            
            waitNextInt();
            
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    jutgeService.consultarPartides(usuariDni);
                    break;
                case 2:
                    tancarPartida();
                    break;
                case 3:
                    jutgeService.afegirMoviments();
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
    public void showOptions() throws Exception
    {
        System.out.println("******************************\n         MENÚ JUTGE\n******************************\n"
                + "\n1-Consultar Partides"
                + "\n2-Tancar Partida"
                + "\n3-Afegir Moviments a Partida"
                + "\n4-Tancar sessió"
                + "\n5-Sortir");
    }
    
    private String[] resultats = { "B", "N", "T" };
    
    private void tancarPartida() throws Exception
    {
        List<Integer> partidaIds = jutgeService.consultarPartides(usuariDni, true);
        
        if ( partidaIds.size() > 0 )
        {
            System.out.println("Selecciona la partida:");
            waitNextInt();
            int arrayId = sc.nextInt();
            
            if ( partidaIds.indexOf(arrayId) != -1 )
            {
                mostrarResultatsPossibles();
                waitNextInt();
                int resultId = sc.nextInt();
                
                if ( resultId >= 1 && resultId <= 3 )
                {
                    jutgeService.tancarPartida(usuariDni, partidaIds.get(arrayId), resultats[resultId]);
                }
                else
                {
                    System.out.println("Identificador de resultat invalid.");
                }
            }
            else
            {
                System.out.println("Identificador de partida invalid.");
            }
        }
        else
        {
            System.out.println("No hi ha cap partida disponible per ser tancada.");
        }
    }
    
    private void mostrarResultatsPossibles()
    {
        System.out.println("Resultats possibles:\n"
                + "\n1-Blanques"
                + "\n2-Negres"
                + "\n3-Taules");
    }
    
}
