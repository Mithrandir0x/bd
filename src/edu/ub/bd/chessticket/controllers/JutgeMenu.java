package edu.ub.bd.chessticket.controllers;

import edu.ub.bd.chessticket.services.JutgeService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Aquest menu ofereix les accions disponibles per un jutge.
 * 
 * Pot consultar aquelles partides en les que ha arbitrat, i tambe pot
 * tancar aquelles partides que hagi arbitrat, pero que no s'ha inputat
 * el resultat.
 * 
 */
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
            
            int choice = readint("\nOPCIO: ");
            
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
    
    /**
     * Demana a l'usuari informacio per poder tancar la partida.
     * 
     * Sempre i quan aquest usuari tingui partides disponibles a ser tancades, el
     * sistema l'hi permetra tancar-les.
     * 
     * L'usuari pot tancar-las de dues maneres:
     * 
     * - Mitjançant una llista de moviments, que en funcio d'aquests, es decidira
     *   si es tanca o no, i de tancar-se, qui es el guanyador d'aquesta partida.
     *
     * - Explicitant qui ha sigut el guanyador, si ha hagut, o si la partida ha
     *   acabat en taules.
     * 
     * @throws Exception 
     */
    private void tancarPartida() throws Exception
    {
        List<Integer> partidaIds = jutgeService.consultarPartides(usuariDni, true);
        
        if ( partidaIds.size() > 0 )
        {
            int partidaId = readint("\nSelecciona el identificador de la partida a tancar: ");
            
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
    
    /**
     * Demana si es vol importar un fitxer de moviments per la partida a ser tancada.
     * 
     * @return Boolea indicant si vol importar-ho o no.
     */
    private boolean volFitxerPartida()
    {
        System.out.println("Vol importar un fitxer de moviments de la partida?\n"
                + "\n1-Si"
                + "\n2-No");
        int opt = readint("OPCIO: ");
        return opt == 1;
    }
    
    /**
     * En funcio de l'identificador de la partida, se li demana a l'usuari que
     * indiqui una ruta del fitxer de moviments de partida a ser importats a
     * la base de dades.
     * 
     * @param partidaId Identificador de la partida a la que importar dades.
     * @return Boolea indicant si la operacio s'ha fet amb exit o no.
     * @throws Exception 
     */
    private boolean importarFitxer(int partidaId) throws Exception
    {
        String path = readline("\nEscriu el nom del fitxer: ");
        
        List<String> moviments = llegirFitxerMoviments(path);
        
        return jutgeService.omplirMoviments(partidaId, moviments);
    }
    
    /**
     * Donat una ruta de fitxer, intenta llegir-ho per obtenir una llista de cadenes
     * de caracter que son els moviments.
     * 
     * S'espera que el fitxer sigui de text pla, on cada linia conte un moviment en
     * format de notacio algebraica d'escacs.
     * 
     * @param path Ruta cap al fitxer.
     * @return Llista de cadenes de caracter amb cadascun dels moviments de la partida.
     * @throws Exception 
     */
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
    
    /**
     * Permet tancar explicitament una partida, amb identificador numeric passat per
     * parametre, indicant qui ha sigut el guanyador o si ha quedat en taules.
     * 
     * @param partidaId Identificador de la partida a tancar.
     * @return Boolea indicant si la operacio s'ha fet correctament.
     */
    private boolean tancarPartida(int partidaId)
    {
        System.out.println("Resultats possibles:\n"
                + "\n1-Blanques"
                + "\n2-Negres"
                + "\n3-Taules");
        int option = readint("OPCIO: ");

        if ( option >= 1 && option <= 3 )
            return jutgeService.tancarPartida(usuariDni, partidaId, resultats[option]);
        else
            System.out.println("Valor de resultat invalid.");
        
        return false;
    }
    
}
