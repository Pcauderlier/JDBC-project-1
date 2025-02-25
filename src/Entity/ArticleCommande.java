package Entity;

public class ArticleCommande implements IEntity{
    private Article article;
    private Commande commande;
    private int quantity;
    public ArticleCommande(Article article, Commande cmd, int quantity) {
        this.article = article;
        this.commande = cmd;
        this.quantity = quantity;
    }


    public String toString() {
        String str = new String();
        str += "Article: \n";
        str += article.toString();
        str+= " Quantité : " + quantity;
        return str;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticleId( Article art) {
        this.article= art;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande cmd) {
        this.commande = cmd;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
