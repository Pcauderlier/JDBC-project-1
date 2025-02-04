package Data;

import Entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDAO implements IData<Client> {
    // Data parle uniquement a la DB
    public Client getById(int id) {
        PreparedStatement stmt = null;
        ResultSet resultats = null;
        Client client = null;

        try {
            // Préparation de la requête SQL avec un type de ResultSet défilable
            Connection con = Conexion.getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM client WHERE ID_CLIENT = ?"
            );
            stmt.setInt(1, id);
            resultats = stmt.executeQuery() ;
            if (resultats.next()){
                // Accéder aux colonnes par nom ou index
                int clientId = resultats.getInt("ID_CLIENT"); // Remplacez par le nom de votre colonne
                String nom = resultats.getString("NOM");
                String prenom = resultats.getString("PRENOM");
                client = new Client(nom,prenom,clientId);
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
        finally{
            Conexion.closeEverything(stmt, resultats);
        }
        return client;
    }
    public ArrayList<Client> getAll() {
        PreparedStatement stmt = null;
        ResultSet resultats = null;
        ArrayList<Client> clients = new ArrayList<>();
        Connection con = Conexion.getConnection();
        try{
            // Préparation de la requête SQL avec un type de ResultSet défilable
            stmt = con.prepareStatement(
                    "SELECT * FROM client"
            );
            resultats = stmt.executeQuery() ;
            while(resultats.next()){
                // Accéder aux colonnes par nom ou index
                int clientId = resultats.getInt("ID_CLIENT"); // Remplacez par le nom de votre colonne
                String nom = resultats.getString("NOM");
                String prenom = resultats.getString("PRENOM");
                Client cl = new Client(nom,prenom,clientId);
                clients.add(cl);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, resultats);
        }
        return clients;
    }

    public int add(Client client) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = Conexion.getConnection();
        int generatedId = -1;
        try{
            String statement = "INSERT INTO client (NOM,PRENOM) VALUES (? , ?)";
            stmt = con.prepareStatement(statement, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,client.getNom());
            stmt.setString(2,client.getPrenom());
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
    public int update(Client client) {
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        int affectedRows = 0;
        try{
            String statement = "UPDATE Client SET NOM = ?, PRENOM = ? WHERE ID_CLIENT = ?";
            stmt = con.prepareStatement(statement);
            stmt.setString(1,client.getNom());
            stmt.setString(2,client.getPrenom());
            stmt.setInt(3,client.getId());
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
    public int delete(int id) {
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        int affectedRows = 0;
        try{
            String statement = "DELETE FROM client WHERE ID_CLIENT = ?";
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
