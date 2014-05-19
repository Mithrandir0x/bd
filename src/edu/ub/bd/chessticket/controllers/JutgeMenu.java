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
                    System.out.println("Valor incorrecte. Si us plau, torna a seleccionar la opció desitjada.");
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
            System.out.println("Selecciona la partida:");
            waitNextInt();
            int partidaId = sc.nextInt();
            
            if ( partidaIds.indexOf(partidaId) != -1 )
            {
                if ( volFitxerPartida() )
                {
                    if ( importarFitxer(partidaId) )
                        System.out.println("S'ha importat amb exit la llista de moviments.");
                    else
                        System.out.println("No s'ha pogut importar la llista de moviments.");
                }
                else
                {
                    if ( tancarPartida(partidaId) )
                        System.out.println("S'ha tancat la partida amb exit.");
                    else
                        System.out.println("No es pot tancar la partida.");
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
    
    private boolean volFitxerPartida()
    {
        System.out.println("Vol importar un fitxer de moviments de la partida?\n"
                + "\n1-Si"
                + "\n2-No");
        waitNextInt();
        return sc.nextInt() == 1;
    }
    
    private boolean importarFitxer(int partidaId) throws Exception
    {
        String path = c.readLine("Escriu el nom del fitxer: ");
        
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
        waitNextInt();
        int resultId = sc.nextInt();

        if ( resultId >= 1 && resultId <= 3 )
            return jutgeService.tancarPartida(usuariDni, partidaId, resultats[resultId]);
        else
            System.out.println("Valor de resultat invalid.");
        
        return false;
    }
    
}
