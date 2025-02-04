package Logic;

import Data.ArticleDAO;
import Entity.Article;

import java.util.ArrayList;

public class ArticleService implements IService<Article> {
    private ArticleDAO articleDAO = new ArticleDAO();
    public ArrayList<Article> getAll(){
    }
    public Article getById(int id){
        return articleDAO.getById(id);

    }
    public Article add(Article entity){

    }
    public Article update(Article entity){

    }
    public boolean delete(int id){

    }
}
