package Entity;

public class Client implements IEntity {
    private int id = 0;
    private String nom;
    private String prenom;

    public Client(String nom, String prenom , int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }
    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    public String toString() {
        return "ID : " + id+" / NOM : " + nom + " / PRENOM : " + prenom;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
