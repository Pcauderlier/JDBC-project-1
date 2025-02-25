package Data;

import Data.IData;
import Entity.Article;
import Entity.ArticleCommande;
import Entity.Commande;
import Logic.ArticleService;
import Logic.CommandeService;

import java.sql.*;
import java.util.ArrayList;

public class ArticleCommandeDAO implements IArticleCommandeDAO {

    public ArrayList<ArticleCommande> getAll() {
        PreparedStatement stmt = null;
        ResultSet res = null;
        ArrayList<ArticleCommande> articleCommandes = new ArrayList<>();
        Connection con = Conexion.getConnection();

        try {
            stmt = con.prepareStatement("SELECT * FROM ArticleCommande");
            res = stmt.executeQuery();

            while (res.next()) {
                ArticleService as = new ArticleService();
                Article a = as.getById( res.getInt("ARTICLE_ID"));
                CommandeService cs = new CommandeService();
                Commande c = cs.getById(res.getInt("COMMANDE_ID"));
                ArticleCommande articleCommande = new ArticleCommande(
                        a,
                        c,
                        res.getInt("QUANTITE")
                );
                articleCommandes.add(articleCommande);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.closeEverything(stmt, res);
        }
        return articleCommandes;
    }

    public ArrayList<ArticleCommande> getAll(int commandeId) {
        PreparedStatement stmt = null;
        ResultSet res = null;
        ArrayList<ArticleCommande> articleCommandes = new ArrayList<>();
        Connection con = Conexion.getConnection();

        try {
            stmt = con.prepareStatement("SELECT * FROM ArticleCommande WHERE COMMANDE_ID = ?");
            stmt.setInt(1, commandeId);
            res = stmt.executeQuery();

            while (res.next()) {
                ArticleService as = new ArticleService();
                Article a = as.getById( res.getInt("ARTICLE_ID"));
                System.out.println("Article OK");
                CommandeService cs = new CommandeService();
                Commande c = cs.getById(res.getInt("COMMANDE_ID"));
                System.out.println("COMMANDE OK");
                ArticleCommande articleCommande = new ArticleCommande(
                        a,
                        c,
                        res.getInt("QUANTITE")
                );
                articleCommandes.add(articleCommande);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.closeEverything(stmt, res);
        }
        return articleCommandes;
    }

    public int add(ArticleCommande articleCommande) {
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        int affectedRows = 0;

        try {
            String statement = "INSERT INTO ArticleCommande (ARTICLE_ID, COMMANDE_ID, QUANTITE) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(statement);
            stmt.setInt(1, articleCommande.getArticle().getId());
            stmt.setInt(2, articleCommande.getCommande().getId());
            stmt.setInt(3, articleCommande.getQuantity());

            affectedRows = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.closeEverything(stmt, null);
        }
        return affectedRows;
    }

    public int update(ArticleCommande articleCommande) {
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        int affectedRows = 0;

        try {
            String statement = "UPDATE ArticleCommande SET QUANTITE = ? WHERE ARTICLE_ID = ? AND COMMANDE_ID = ?";
            stmt = con.prepareStatement(statement);
            stmt.setInt(1, articleCommande.getQuantity());
            stmt.setInt(2, articleCommande.getArticle().getId());
            stmt.setInt(3, articleCommande.getCommande().getId());

            affectedRows = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.closeEverything(stmt, null);
        }
        return affectedRows;
    }

    public int delete(int commandeId) {
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        int affectedRows = 0;

        try {
            String statement = "DELETE FROM ArticleCommande WHERE COMMANDE_ID = ?";
            stmt = con.prepareStatement(statement);
            stmt.setInt(1, commandeId);

            affectedRows = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.closeEverything(stmt, null);
        }
        return affectedRows;
    }
}
