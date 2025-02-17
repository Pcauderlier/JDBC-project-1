package Entity;

import java.io.Serializable;

public class Article implements IEntity{
    private int id;
    private String nom;
    private String description;
    private double prix;
    private int stock;
    public Article( String nom, String description, double prix, int stock, int id) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
    }
    public Article( String nom, String description, double prix, int stock) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
    }
    public String toString() {
        return "ID : " + id+" / NOM : " + nom + " / DESCRIPTION : " + description + " / PRIX : " + prix + " / STOCK : " + stock;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
