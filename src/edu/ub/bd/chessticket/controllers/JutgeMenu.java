package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.services.JutgeService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JutgeMenu extends Menu
{
    
    private JutgeService jutgeService = new JutgeService();

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
                    jutgeService.consultarPartides(usuariDni);
                    break;
                case 2:
                    tancarPartida();
                    break;
                case 3:
                    goBack = true;
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("\nValor incorrecte. Si us plau, torna a seleccionar la opció desitjada.\n");
            }
        }
    }

    @Override
    public void mostrarOpcions() throws Exception
    {
        System.out.println("******************************\n         MENÚ JUTGE\n******************************\n"
                + "\n1-Consultar Partides"
                + "\n2-Tancar Partida"
                + "\n3-Tancar sessió"
                + "\n4-Sortir");
    }
    
    // P = Padding, B = Blanques, N = Negres, T = Taules
    private String[] resultats = { "P", "B", "N", "T" };
    
    private void tancarPartida() throws Exception
    {
        List<Integer> partidaIds = jutgeService.consultarPartides(usuariDni, true);
        
        if ( partidaIds.size() > 0 )
        {
            System.out.print("\nSelecciona el identificador de la partida a tancar: ");
            waitNextInt();
            int partidaId = sc.nextInt();
            
            if ( partidaIds.indexOf(partidaId) != -1 )
            {
                if ( volFitxerPartida() )
                {
                    if ( importarFitxer(partidaId) )
                        System.out.println("\nS'ha importat amb exit la llista de moviments per a la partida [" + partidaId + "].\n");
                    else
                        System.out.println("\nNo s'ha pogut importar la llista de moviments per a la partida [" + partidaId + "].\n");
                }
                else
                {
                    if ( tancarPartida(partidaId) )
                        System.out.println("\nS'ha tancat la partida [" + partidaId + "] amb exit.\n");
                    else
                        System.out.println("\nNo es pot tancar la partida [" + partidaId + "].\n");
                }
            }
            else
            {
                System.out.println("\nIdentificador de partida invalid.\n");
            }
        }
        else
        {
            System.out.println("\nNo hi ha cap partida disponible per ser tancada.\n");
        }
    }
    
    private boolean volFitxerPartida()
    {
        System.out.println("Vol importar un fitxer de moviments de la partida?\n"
                + "\n1-Si"
                + "\n2-No");
        System.out.print("\nOPCIO: ");
        waitNextInt();
        return sc.nextInt() == 1;
    }
    
    private boolean importarFitxer(int partidaId) throws Exception
    {
        String path = c.readLine("\nEscriu el nom del fitxer: ");
        
        List<String> moviments = llegirFitxerMoviments(path);
        
        return jutgeService.omplirMoviments(partidaId, moviments);
    }
    
    private List<String> llegirFitxerMoviments(String path) throws Exception
    {
        String line;
        List<String> lines = new ArrayList<String>();
        FileInputStream fstream = new FileInputStream(path);
        InputStreamReader instream = new InputStreamReader(fstream, "UTF-8");
        BufferedReader d = new BufferedReader(instream);
        line = d.readLine();
        while ( line != null )
        {
            lines.add(line);
            line = d.readLine();
        }
        
        return lines;
        
    }
    
    private boolean tancarPartida(int partidaId)
    {
        System.out.println("Resultats possibles:\n"
                + "\n1-Blanques"
                + "\n2-Negres"
                + "\n3-Taules");
        System.out.print("\nOPCIO: ");
        waitNextInt();
        int resultId = sc.nextInt();

        if ( resultId >= 1 && resultId <= 3 )
            return jutgeService.tancarPartida(usuariDni, partidaId, resultats[resultId]);
        else
            System.out.println("Valor de resultat invalid.");
        
        return false;
    }
    
}
