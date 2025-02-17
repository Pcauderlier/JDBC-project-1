package Logic;

import Data.ClientDAO;
import Entity.Client;

import java.util.ArrayList;

public class ClientService implements IService<Client> {
    private final ClientDAO clientDAO = new ClientDAO();
    public ArrayList<Client> getAll(){
        return clientDAO.getAll();
    }
    public Client getById(int id){
        return clientDAO.getById(id);
    }
    public Client add(Client client){
        int id = clientDAO.add(client);
        if (id > 0){
            client.setId(id);
            System.out.println("Client added successfully");
            System.out.println(client);
        }
        return client;
    }
    public Client update(Client client){
        int affected = clientDAO.update(client);
        if (affected > 0){
            System.out.println("Client updated successfully : ");
            System.out.println(client);
            return client;
        }
        return null;
    }
    public boolean delete(int id){
        // Vérifier si une commande est lié a l'utilisateur
        CommandeService cms = new CommandeService();
        for (Integer cmdId : cms.getClientCommand(id)){
            System.out.println("Suppression de la commande : " + cmdId + " avant de supprimer le client : " + id);
            cms.delete(cmdId);
        }
        int affected = clientDAO.delete(id);
        System.out.println("Client deleted: " + id);
        return affected > 0;
    }
}
