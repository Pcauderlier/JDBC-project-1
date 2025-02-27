package Data;

import Entity.Article;
import Entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ArticleDAO implements IData<Article> {
    public ArrayList<Article> getAll(){
        PreparedStatement stmt = null;
        ResultSet res = null;
        ArrayList<Article> articles = new ArrayList<>();
        Connection con = Conexion.getConnection();
        try{
            // Préparation de la requête SQL avec un type de ResultSet défilable
            stmt = con.prepareStatement(
                    "SELECT * FROM article"
            );
            res = stmt.executeQuery() ;
            while(res.next()){
                // Accéder aux colonnes par nom ou index
                Article article = new Article(
                    res.getString(2),
                    res.getString(3),
                    res.getDouble(4),
                    res.getInt(5),
                    res.getInt(1)
                );
                articles.add(article);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, res);
        }
        return articles;
    }
    public Article getById(int id){
        PreparedStatement stmt = null;
        ResultSet res = null;
        Article article = null;
        System.out.println("Article ID: " + id);

        try {
            // Préparation de la requête SQL avec un type de ResultSet défilable
            Connection con = Conexion.getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM article WHERE ID_ARTICLE = ?"
            );
            stmt.setInt(1, id);
            res = stmt.executeQuery() ;
            if (res.next()){
                // Accéder aux colonnes par nom ou index
                article = new Article(
                        res.getString(2),
                        res.getString(3),
                        res.getDouble(4),
                        res.getInt(5),
                        res.getInt(1)
                );
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, res);
        }
        return article;
    }
    public int add(Article article){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = Conexion.getConnection();
        int generatedId = -1;
        try{
            String statement = "INSERT INTO article (NOM,DESCRIPTION,PRIX,STOCK) VALUES (? , ? , ? , ?)";
            stmt = con.prepareStatement(statement, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,article.getNom());
            stmt.setString(2,article.getDescription());
            stmt.setDouble(3,article.getPrix());
            stmt.setInt(4,article.getStock());
            if (stmt.executeUpdate() == 1){
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    generatedId = rs.getInt(1);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            Conexion.closeEverything(stmt, rs);
        }
        return generatedId;
    }
    public int update(Article article){
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        int affectedRows = 0;
        try{
            String statement = "UPDATE article SET NOM = ?, DESCRIPTION = ? , PRIX = ? , STOCK = ? WHERE ID_ARTICLE = ?";
            stmt = con.prepareStatement(statement);
            stmt.setString(1,article.getNom());
            stmt.setString(2,article.getDescription());
            stmt.setDouble(3,article.getPrix());
            stmt.setInt(4,article.getStock());
            stmt.setInt(5,article.getId());
            if (stmt.executeUpdate() == 1){
                affectedRows++;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            Conexion.closeEverything(stmt,null);
        }
        return affectedRows;
    }
    public int delete(int id){
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        int affectedRows = 0;
        try{
            String statement = "DELETE FROM article WHERE ID_ARTICLE = ?";
            stmt = con.prepareStatement(statement);
            stmt.setInt(1,id);
            if (stmt.executeUpdate() == 1){
                affectedRows++;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            Conexion.closeEverything(stmt,null);
        }
        return affectedRows;
    }
}
