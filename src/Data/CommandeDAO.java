package Data;

import Entity.Client;
import Entity.Commande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommandeDAO implements IData<Commande> {
    public ArrayList<Commande> getAll(){
        PreparedStatement stmt = null;
        ResultSet res = null;
        ArrayList<Commande> commandes = new ArrayList<>();
        Connection con = Conexion.getConnection();
        System.out.println("Check1");
        try{
            // Préparation de la requête SQL avec un type de ResultSet défilable
            stmt = con.prepareStatement(
                    "SELECT * FROM commande"
            );
            res = stmt.executeQuery() ;
            System.out.println("Check2");

            while(res.next()){
                // Accéder aux colonnes par nom ou index
                Commande commande = new Commande(
                        res.getInt(1),
                        res.getInt(2),
                        res.getTimestamp(3),
                        res.getInt(4)
                );
                commandes.add(commande);
                System.out.println("Check3");

            }
        }
        catch(Exception e){
            System.out.println("Check4");

            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, res);
        }
        System.out.println("Check5");

        return commandes;
    }
    public Commande getById(int id){
        PreparedStatement stmt = null;
        ResultSet res = null;
        Commande commande = null;

        try {
            // Préparation de la requête SQL avec un type de ResultSet défilable
            Connection con = Conexion.getConnection();
            stmt = con.prepareStatement(
                    "SELECT * FROM commande WHERE ID_COMMANDE = ?"
            );
            stmt.setInt(1, id);
            res = stmt.executeQuery() ;
            if (res.next()){
                // Accéder aux colonnes par nom ou index
                commande = new Commande(
                        res.getInt(1),
                        res.getInt(2),
                        res.getTimestamp(3),
                        res.getInt(4)
                );
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt, res);
        }
        return commande;
    }
    public int add(Commande commande){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = Conexion.getConnection();
        int generatedId = -1;
        try{
            String statement = "INSERT INTO Commande (NUM_COMMANDE,DATE_COMMANDE,CLIENT_ID) VALUES (? , ? , ?)";
            stmt = con.prepareStatement(statement, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,commande.getNum_commande());
            stmt.setTimestamp(2,commande.getDate_commande());
            stmt.setDouble(3,commande.getClient_id());
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
    public int update(Commande commande){
        PreparedStatement stmt = null;
        Connection con = Conexion.getConnection();
        int affectedRows = 0;
        try{
            String statement = "UPDATE commande SET NUM_COMMANDE = ?, DATE_COMMANDE = ? , CLIENT_ID = ? WHERE ID_COMMANDE = ?";
            stmt = con.prepareStatement(statement);
            stmt.setInt(1,commande.getNum_commande());
            stmt.setTimestamp(2,commande.getDate_commande());
            stmt.setInt(3,commande.getClient_id());
            stmt.setInt(4,commande.getId());
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
            String statement = "DELETE FROM Commande WHERE ID_COMMANDE = ?";
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
    public ArrayList<Commande> getClientCommande(Client client){
        if (client==null){
            System.out.println("pas de client");
            return null;
        }
        int client_id = client.getId();
        if (client_id == 0){
            System.out.println("Le client n'est pas encore enregistrer");
        }
        PreparedStatement stmt = null;
        ResultSet res = null;
        ArrayList<Commande> commandes = new ArrayList<>();
        Connection con = Conexion.getConnection();
        try{
            System.out.println("RECHERCHE COMMANDE AVEC CLIENT ID = " + client_id);
            stmt = con.prepareStatement("SELECT * FROM commande WHERE CLIENT_ID = ?");
            stmt.setInt(1,client_id);
            res = stmt.executeQuery() ;
            while(res.next()){
                Commande commande = new Commande(
                        res.getInt(1),
                        res.getInt(2),
                        res.getTimestamp(3),
                        res.getInt(4)
                );
                commandes.add(commande);
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            Conexion.closeEverything(stmt,res);
        }
        return commandes;
    }
}
