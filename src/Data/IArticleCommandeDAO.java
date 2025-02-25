package Data;

import Entity.ArticleCommande;

import java.util.ArrayList;

public interface IArticleCommandeDAO {
    ArrayList<ArticleCommande> getAll();
    ArrayList<ArticleCommande> getAll(int commandeId);
    int add(ArticleCommande articleCommande);
    int update(ArticleCommande articleCommande);
    int delete(int commandeId);
}
