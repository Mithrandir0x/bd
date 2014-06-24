package edu.ub.bd;

import edu.ub.bd.chessticket.controllers.VisitantMenu;

/**
 * Aplicacio de gestio per al torneig d'escacs.
 * 
 * MANUAL D'US
 * -----------
 * A l'inici, l'aplicacio assumeix que ets un visitant, i tens la opcio d'iniciar sessio.
 * Tots els taquillers, jutges i organitzadors poden accedir al sistema emprant el seu DNI
 * tant com a identificacio d'acces com contrasenya.
 * 
 * USUARIS DE PROVA
 * ----------------
 *
 *     ROL      |    DNI    | CONTRASENYA
 * -------------+-----------+-------------
 * ORGANITZADOR | 42516878R | 42516878R
 * JUTGE        | 20200288L | 20200288L
 * TAQUILLER    | 32013847R | 32013847R
 *
 * El VISITANT no requereix cap mena d'autenticacio.
 * 
 * Per que no van invitar a Deep Blue?
 * 
 */
public class ChessTicket
{
    
    public static void main(String[] args)
    {     
        try
        {
            new VisitantMenu().executar();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }

}
