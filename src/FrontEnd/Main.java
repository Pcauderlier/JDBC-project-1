package FrontEnd;

import Data.Conexion;
import Entity.Client;
import Logic.ClientService;

public class Main {
    public static void main(String[] args) {

        ClientService clientService = new ClientService();
        // Update :
        Client cli1 = new Client("JHONY","B",1);
        clientService.update(cli1);
        // Delete
        clientService.delete(2);
        //ADD
        Client cli2 = new Client("JBHONETTE","BBB");
        clientService.add(cli2);
        System.out.println("PRINT DE TOUS LES CLIENT : ");
        clientService.getAll().forEach(System.out::println);



    }
}