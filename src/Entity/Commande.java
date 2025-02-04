package Entity;

import java.util.Date;

public class Commande implements IEntity {
    int id;
    int num_commande;
    Date date_commande;
    int client_id;
    public Commande(int client_id) {
        this.client_id = client_id;
        date_commande = new Date();

    }
    public Commande(int num_commande, Date date_commande, int client_id, int id) {
        this.num_commande = num_commande;
        this.date_commande = date_commande;
        this.client_id = client_id;
        this.id = id;
    }
}
