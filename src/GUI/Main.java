package GUI;

import Entity.Article;
import Entity.ArticleCommande;
import Entity.Client;
import Entity.Commande;
import Logic.ArticleCommandeService;
import Logic.ArticleService;
import Logic.ClientService;
import Logic.CommandeService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        boolean testClients = true;
        boolean testArticle = true;
        boolean testCommande= true;
        boolean testphase = false;
        if (testphase){

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
            Commande com1 = new Commande(clients.get(0).getId());
            Commande com2 = new Commande(clients.get(1).getId());
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
        // CLIENT :
        Client cl1 = new Client("Doe" , "Jhon");
        Client cl2 = new Client("Smith " , "Will");
        Client cl3 = new Client("Dujardin " , "Jean");
        // AJout en DB
        ClientService cls = new ClientService();
        cls.add(cl1);
        cls.add(cl2);
        cls.add(cl3);

        // ARTICLE :
        Article coca = new Article("Coca", "Boisson1" , 12.55, 10);
        Article fanta = new Article("Fanta", "Boisson2" , 12.55, 10);
        Article icetea = new Article("IceTea", "Boisson3" , 10.55, 20);
        Article sprite = new Article("Sprite", "Boisson gazeuse citronnée", 11.50, 15);
        Article orangina = new Article("Orangina", "Boisson pétillante à l'orange", 13.00, 12);
        Article pepsi = new Article("Pepsi", "Boisson gazeuse cola", 12.00, 18);
        Article oasis = new Article("Oasis", "Boisson aux fruits sans bulles", 9.80, 25);
        Article redbull = new Article("Red Bull", "Boisson énergisante", 18.90, 8);
        Article perrier = new Article("Perrier", "Eau gazeuse naturelle", 7.50, 30);
        Article badoit = new Article("Badoit", "Eau minérale gazeuse", 6.90, 20);
        //Ajout en DB
        ArticleService as = new ArticleService();
        as.add(coca);
        as.add(fanta);
        as.add(icetea);
        as.add(sprite);
        as.add(orangina);
        as.add(pepsi);
        as.add(oasis);
        as.add(redbull);
        as.add(perrier);
        as.add(badoit);

        CommandeService cs = new CommandeService();
        // Client 1 :
        Commande cmd1 = new Commande(cl1.getId());
        cs.add(cmd1);
        cs.addArticle(coca,cmd1,2);
        cs.addArticle(fanta,cmd1,1);
        cs.addArticle(pepsi,cmd1,3);

        // CLient 2 :
        Commande cmd2 = new Commande(cl1.getId());
        cs.add(cmd1);
        cs.addArticle(sprite,cmd1,2);
        cs.addArticle(oasis,cmd1,1);
        cs.addArticle(redbull,cmd1,3);

        // CLient 3 :
        Commande cmd3 = new Commande(cl1.getId());
        cs.add(cmd1);
        cs.addArticle(sprite,cmd1,1);
        cs.addArticle(badoit,cmd1,5);
        cs.addArticle(perrier,cmd1,3);

        // Id 3 :

        Client client = cls.getById(3);
        System.out.println(client);

        // Delete :
        cls.delete(3);

        // ID 2 :
        Client client2 = cls.getById(2);
        client2.setNom("DimitriLeGrand");
        cls.update(client2);

        // Liste :
        ArticleCommandeService acs = new ArticleCommandeService();
        for (Client c : cls.getAll()) {
            System.out.println(c);
            System.out.println("Client print");
            for (Commande cmd : cs.getClientCommand(c)){
                System.out.println("Jusqu'ici sa marche");
                System.out.println(cmd);
                System.out.println("ID COMMANDE");
                System.out.println(cmd.getId());
                for (ArticleCommande ac : acs.getAll(cmd.getId())){
                    System.out.println("Aricle print");
                    System.out.println(ac);
                }
            }

        }


    }
}