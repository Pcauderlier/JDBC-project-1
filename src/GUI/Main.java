package GUI;

import Entity.Article;
import Entity.Client;
import Entity.Commande;
import Logic.ArticleService;
import Logic.ClientService;
import Logic.CommandeService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        boolean testClients = true;
        boolean testArticle = true;
        boolean testCommande= true;
        boolean testCommandeArticle = true;

        if (testClients) {
            ClientService cls = new ClientService();
            for(Client c : cls.getAll()){
                cls.delete(c.getId());
            }

            Client cl1 = new Client("Doe" , "Jhon");
            Client cl2 = new Client("John" , "Smith");
            cls.add(cl1);
            cls.add(cl2);
            // Update :
            Client cli1 = new Client("JHONY","B",1);
            cls.update(cli1);
    //        //ADD
            Client cli2 = new Client("JBHONETTE","BBB");
            cls.add(cli2);
            System.out.println("PRINT DE TOUS LES CLIENT : ");
            for(Client c : cls.getAll()){
                System.out.println(c);
            }
        }
        if (testArticle) {
            ArticleService as = new ArticleService();
            // Suppression des anciens articles
            for (Article a : as.getAll()) {
                as.delete(a.getId());
            }
            Article coca = new Article("Coca", "Boisson1" , 12.55, 10);
            Article fanta = new Article("Fanta", "Boisson2" , 12.55, 10);
            // ADD
            as.add(coca);
            as.add(fanta);
            // UPDATE
            fanta.setDescription("C'est orange");
            fanta.setStock(9);
            as.update(fanta);
            // GET
            System.out.println("PRINT DE TOUS LES ARTICLES : ");
            for (Article a : as.getAll()) {
                System.out.println(a);
            }
        }
        if (testCommande) {
            CommandeService cs = new CommandeService();
            ClientService clis = new ClientService();
            ArrayList<Client> clients = clis.getAll();
            // DELETE
            for (Commande c : cs.getAll()) {
                cs.delete(c.getId());
            }
            // ADD
            Commande com1 = new Commande(clients.get(0).getId(),123456);
            Commande com2 = new Commande(clients.get(1).getId(),1234567);
            cs.add(com1);
            cs.add(com2);

            //UPDATE
            com1.setNum_commande(1212);
            cs.update(com1);

            System.out.println("PRINT DE TOUS LES COMMANDES : ");
            for (Commande c : cs.getAll()) {
                System.out.println(c);
            }
        }
    }
}