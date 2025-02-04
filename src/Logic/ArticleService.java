package Logic;

import Data.ArticleDAO;
import Data.ClientDAO;
import Entity.Article;
import Entity.Client;

import java.util.ArrayList;

public class ArticleService implements IService<Article> {
    private final ArticleDAO articleDAO = new ArticleDAO();
    public ArrayList<Article> getAll(){
        return articleDAO.getAll();
    }
    public Article getById(int id){
        return articleDAO.getById(id);
    }
    public Article add(Article article){
        int id = articleDAO.add(article);
        if (id > 0){
            article.setId(id);
            System.out.println("Article added successfully");
            System.out.println(article);
        }
        return article;
    }
    public Article update(Article article){
        int affected = articleDAO.update(article);
        if (affected > 0){
            System.out.println("Article updated successfully : ");
            System.out.println(article);
            return article;
        }
        return null;
    }
    public boolean delete(int id){
        int affected = articleDAO.delete(id);
        System.out.println("Article deleted: " + id);
        return affected > 0;
    }
}
