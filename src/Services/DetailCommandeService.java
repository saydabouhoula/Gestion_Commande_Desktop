/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.DetailCommande;
import Entities.User;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class DetailCommandeService implements IService<DetailCommande>{
    
    Connection conn;
    PreparedStatement ste;

    public DetailCommandeService() {
        conn=MyConnection.getInstance().getConnection();
    }

    @Override
    public void add(DetailCommande entity) {
        String sql = "insert into detail_commande(commande_id,produit_id,qte) Values(?,?,?)";
        try {
            ste=conn.prepareStatement(sql);
            ste.setInt(3, entity.getQte());
            ste.setInt(2, entity.getProduit_id());
            ste.setInt(1, entity.getCommande_id());
               
            
            ste.executeUpdate();
            System.out.println("detail_commande Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void update(DetailCommande entity) {
        String sql = "update  detail_commande set commande_id=?,produit_id=?,qte=? where id=?";
        try {
            ste=conn.prepareStatement(sql);
            ste=conn.prepareStatement(sql);
            ste.setInt(3, entity.getQte());
            ste.setInt(2, entity.getProduit_id());
            ste.setInt(1, entity.getCommande_id());
            ste.setInt(4, entity.getId());
               
            System.out.println(ste.toString()+"************");
            ste.executeUpdate();
            System.out.println("detail_commande modifie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void Delete(int id) {
         String sql = "DELETE from detail_commande where id= '"+id+"' "; 
        try{

            
           Statement st= conn.createStatement();       
           st.executeUpdate(sql);
           System.out.println("Commande supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public List<DetailCommande> Show() {
        ObservableList<DetailCommande> Equipelist = FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
        String query = "select * from detail_commande";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        DetailCommande eq;
        while (rs.next()) {
           eq = new DetailCommande(rs.getInt("id"),rs.getInt("commande_id"),rs.getInt("produit_id"),rs.getInt("qte")
                 ); 
            Equipelist.add(eq);
            
            System.out.println(eq.toString());

        }
         return Equipelist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Equipelist;
    }
    
    public List<DetailCommande> ShowByCommande(int com) {
        ObservableList<DetailCommande> Equipelist = FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
        String query = "select * from detail_commande where commande_id="+com+"";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        DetailCommande eq;
        while (rs.next()) {
           eq = new DetailCommande(rs.getInt("id"),rs.getInt("commande_id"),rs.getInt("produit_id"),rs.getInt("qte")
                 ); 
           eq.setNom(getNomProduit(eq.getProduit_id()));
            System.out.println(getByidProduit(eq.getProduit_id())+" PrixUniiiiiiit");
           eq.setPrixUni(getByidProduit(eq.getProduit_id()));
            System.out.println(eq.getQte()+" Qteeeeeeee");
           eq.setTotal(eq.getPrixUni()*eq.getQte());
            Equipelist.add(eq);
            System.out.println(eq.toString());

        }
         return Equipelist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Equipelist;
    }
    
    public double Somme(int com) {
        ObservableList<DetailCommande> Equipelist = FXCollections.observableArrayList();
        double somme=0;
        try{
        Statement st= conn.createStatement();
        String query = "select * from detail_commande where commande_id="+com+"";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        DetailCommande eq;
        while (rs.next()) {
           eq = new DetailCommande(rs.getInt("id"),rs.getInt("commande_id"),rs.getInt("produit_id"),rs.getInt("qte")
                 ); 
            Equipelist.add(eq);
            System.out.println(eq.toString());

        }
        
        for(int i=0;i<Equipelist.size();i++){
           somme =somme+(getByidProduit(Equipelist.get(i).getProduit_id())*Equipelist.get(i).getQte());
        }
        return somme;
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return somme;
    }
    
    

    @Override
    public DetailCommande getById(int id) {
        
        DetailCommande eq=new DetailCommande();
        try{
        Statement st= conn.createStatement();
        String query = "select * from detail_commande where id="+id+"";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
           eq = new DetailCommande(rs.getInt("id"),rs.getInt("commande_id"),rs.getInt("produit_id"),rs.getInt("qte")); 
           eq.setNom(getNomProduit(eq.getProduit_id()));

        }
         return eq;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return eq;
    }
    
    public String getNomProduit(int id) {
        
        String eq="";
        try{
        Statement st= conn.createStatement();
        String query = "select nom from produit where id="+id+"";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
           eq = rs.getString(1);

        }
         return eq;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return eq;
    }
    
     
    public  User  getUserById ( int  userId ) {
         User utilisateur = new User() ;
        try {
            String  qry = "SELECT * FROM `user` WHERE id = " + userId + ";" ;
             Statement stm = conn.createStatement();
              ResultSet rs;
                rs = stm.executeQuery(qry);
            while (rs.next()) {
                utilisateur.setId ( rs . getInt ( 1 ));
                utilisateur.setName ( rs . getString ( 5 ));
                utilisateur.setEmail ( rs . getString ( 2 ));
                utilisateur.setPassword ( rs . getString ( 4 ));
                System.out.println(utilisateur.getName()+"*********");
                // user.setRoles(roles.valueOf(rs.getString("roles")));
            }
            //"SELECT * FROM utilisateurs WHERE email = ?"
        } catch ( SQLException  ex ) {
           
        }
        return  utilisateur ;
    }
    public double getByidProduit(int id) {
        
        int eq=0;
        double prix=0;
        double prixFinale=0;
        try{
        Statement st= conn.createStatement();
        String query = "select * from produit where id="+id+"";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
            try{
           eq = rs.getInt(10);
           }catch(Exception e){
              eq=0; 
           }
            prix=rs.getDouble(9);
           prixFinale=(prix*(100-eq))/100;
        }
         return prixFinale;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return prixFinale;
    }
    
    
    public int getByNom(String id) {
        
        int eq=0;
        try{
        Statement st= conn.createStatement();
        String query = "select id from produit where nom='"+id+"'";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
           eq = rs.getInt(1);

        }
         return eq;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return eq;
    }
    
    public List<String> getProduit() {
        ObservableList<String> Equipelist = FXCollections.observableArrayList();
        
        try{
        Statement st= conn.createStatement();
        String query = "select nom from produit";
        
            ResultSet rs;
        rs = st.executeQuery(query);
        while (rs.next()) {
            String eq="";
           eq = rs.getString(1);
           Equipelist.add(eq);

        }
         return Equipelist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Equipelist;
    }
    
    
    
    
}
