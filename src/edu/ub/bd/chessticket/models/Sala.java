package edu.ub.bd.chessticket.models;

public class Sala
{
    
    private String hotel;
    private String nom;
    
    public Sala(String nom, String hotel)
    {
        this.hotel = hotel;
        this.nom = nom;
    }

    public String getHotel()
    {
        return hotel;
    }

    public void setHotel(String hotel)
    {
        this.hotel = hotel;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    @Override
    public String toString()
    {
        return "[ Hotel: [" + hotel + "] Sala: [" + nom  + "] ]";
    }
    
}
