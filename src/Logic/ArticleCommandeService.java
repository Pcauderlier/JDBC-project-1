package Logic;

import Data.ArticleCommandeDAO;
import Entity.Article;
import Entity.ArticleCommande;

import java.util.ArrayList;

public class ArticleCommandeService implements IService<ArticleCommande> {
    private final ArticleCommandeDAO articleCommandeDAO = new ArticleCommandeDAO();

    public ArrayList<ArticleCommande> getAll() {
        return articleCommandeDAO.getAll();
    }

    public ArticleCommande getById(int id) {
        return null;
    }

    public ArrayList<ArticleCommande> getAll(int commandeId) {
        return articleCommandeDAO.getAll(commandeId);
    }

    public ArticleCommande add(ArticleCommande articleCommande) {
        // Vérifier la quantité et le stock avant d'ajouter la ligne de commande
        ArticleService as = new ArticleService();
        Article a = articleCommande.getArticle();
        int stock = a.getStock();
        if ( stock < articleCommande.getQuantity()) {
            System.out.println("Il ne reste plus asser de stock pour l'article "+a.getNom());
            return null;
        }
        else{
            a.setStock( stock - articleCommande.getQuantity());
            as.update(a);
        }
        int affected = articleCommandeDAO.add(articleCommande);
        if (affected > 0) {
            System.out.println("Article ajouter a la commande");
            System.out.println(articleCommande);
        }
        return articleCommande;
    }

    public ArticleCommande update(ArticleCommande articleCommande) {
        int affected = articleCommandeDAO.update(articleCommande);
        if (affected > 0) {
            System.out.println("Ligne mise a jour");
            System.out.println(articleCommande);
            return articleCommande;
        }
        return null;
    }

    public boolean delete(int commandeId) {
        int affected = articleCommandeDAO.delete(commandeId);
        if (affected > 0) {
            System.out.println("Tous les articles supprimée pour la commande  " + commandeId);
            return true;
        }
        return false;
    }
}
