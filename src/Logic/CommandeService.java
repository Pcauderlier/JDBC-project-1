package Logic;

import Data.ArticleCommandeDAO;
import Data.CommandeDAO;
import Entity.*;
import Entity.Commande;

import java.util.ArrayList;

public class CommandeService implements IService<Commande> {
    private final CommandeDAO commandeDAO = new CommandeDAO();
    public ArrayList<Commande> getAll(){
        return commandeDAO.getAll();
    }
    public Commande getById(int id){
        return commandeDAO.getById(id);
    }
    public Commande add(Commande commande){
        int id = commandeDAO.add(commande);
        if (id > 0){
            commande.setId(id);
            System.out.println("Commande added successfully");
            System.out.println(commande);
        }
        return commande;
    }
    public Commande update(Commande commande){
        int affected = commandeDAO.update(commande);
        if (affected > 0){
            System.out.println("Commande updated successfully : ");
            System.out.println(commande);
            return commande;
        }
        return null;
    }
    public boolean delete(int id){
        int affected = commandeDAO.delete(id);
        System.out.println("Commande deleted: " + id);
        return affected > 0;
    }
    public ArrayList<Commande> getClientCommand(Client client){
        return commandeDAO.getClientCommande(client);
    }
    public void addArticle(Article article , Commande commande, int quantity){
        ArticleCommande ac = new ArticleCommande (article, commande, quantity);
        ArticleCommandeService acs = new ArticleCommandeService();
        acs.add(ac);
    }
}
