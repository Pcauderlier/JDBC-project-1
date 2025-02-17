package Entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class Commande implements IEntity {
    int id;
    int num_commande;
    Timestamp date_commande;
    int client_id;
    public Commande(int client_id, int num_commande) {
        this.client_id = client_id;
        date_commande = Timestamp.from(Instant.now());
        this.num_commande = num_commande ;

    }
    public Commande(int id ,int num_commande, Timestamp date_commande, int client_id ) {
        this.id = id;
        this.num_commande = num_commande;

        this.date_commande = date_commande;
        this.client_id = client_id;
    }
    public String toString() {
        return "ID : " + id + " Num commande: " + num_commande + " Date commande: " + date_commande + " Client ID: " + client_id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNum_commande() {
        return num_commande;
    }
    public void setNum_commande(int num_commande) {
        this.num_commande = num_commande;
    }
    public Timestamp getDate_commande() {
        return date_commande;
    }
    public void setDate_commande(Timestamp date_commande) {
        this.date_commande = date_commande;
    }
    public int getClient_id() {
        return client_id;
    }
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
}
