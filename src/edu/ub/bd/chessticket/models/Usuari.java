package edu.ub.bd.chessticket.models;

public class Usuari
{

    private String dni;
    private String nom;
    private String rol;
    
    public Usuari(String dni, String nom, String rol)
    {
        this.dni = dni;
        this.nom = nom;
        this.rol = rol;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
