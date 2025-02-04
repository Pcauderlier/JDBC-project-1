package Data;

import Entity.Article;
import Entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ArticleDAO implements IData<Article> {
    public ArrayList<Article> getAll(){

    }
    public Article getById(int id){
        PreparedStatement stmt = null;
        ResultSet res = null;
        Article article = null;

        try {
            // Préparation de la requête SQL avec un type de ResultSet défilable
            Connection con = Conexion.getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM client WHERE ID_ARTICLE = ?"
            );
            stmt.setInt(1, id);
            res = stmt.executeQuery() ;
            if (res.next()){
                // Accéder aux colonnes par nom ou index
                article = new Article(
                        res.getString(1),
                        res.getString(2),
                        res.getDouble(3),
                        res.getInt(4),
                        res.getInt(0)
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
    public int update(Article entity){

    }
    public int delete(int id){

    }
}
