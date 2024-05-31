/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class CommandeService implements IService<Commande>{

    Connection conn;
    PreparedStatement ste;

    public CommandeService() {
        conn=MyConnection.getInstance().getConnection();
    }
    
    
    @Override
    public void add(Commande entity) {
String sql = "insert into commande(user_id,date_creation,status,payment_method,payment_brand,cc_last,payment_status) Values(?,?,?,?,?,?,?)";
        try {
            ste=conn.prepareStatement(sql);
            ste.setDate(2, entity.getDate_creation());
            ste.setInt(1, entity.getUser_id());
            ste.setString(3, entity.getStatus());
            ste.setString(4, entity.getPayment_method());
            ste.setString(5, entity.getPayement_brand());
            ste.setInt(6, entity.getCc_last());
            ste.setString(7,entity.getPayment_status());
               
            
            ste.executeUpdate();
            System.out.println("commande Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void update(Commande entity) {
        String sql = "update  commande set user_id=?,date_creation=?,status=?,payment_method=?,payment_brand=?,cc_last=?,payment_status=? where id= ?";
        try {
            
            ste=conn.prepareStatement(sql);
            ste.setDate(2, entity.getDate_creation());
            ste.setInt(1, entity.getUser_id());
            ste.setString(3, entity.getStatus());
            ste.setString(4, entity.getPayment_method());
            ste.setString(5, entity.getPayement_brand());
            ste.setInt(6, entity.getCc_last());
            ste.setString(7,entity.getPayment_status());
            ste.setInt(8, entity.getId());
            
            ste.executeUpdate();
            System.out.println("commande Modifie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void Delete(int id) {
         String sql = "DELETE from commande where id= '"+id+"' "; 
        String sql1="DELETE from detail_commande where commande_id= '"+id+"' "; 
        try{

            
           Statement st= conn.createStatement();
           st.executeUpdate(sql1);        
           st.executeUpdate(sql);
           System.out.println("Commande supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public List<Commande> Show() {
        Services.DetailCommandeService sdc=new Services.DetailCommandeService();
        conn=MyConnection.getInstance().getConnection();
        ObservableList<Commande> Competitionlist = FXCollections.observableArrayList();
        try{
        Statement st;
            st = conn.createStatement();
            ///////////user_id=1///////////
        String query = "select * from commande where user_id=1";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Commande comp;
        while (rs.next()) {
           comp = new Commande(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(7),rs.getString(4),"",rs.getString(6),rs.getString(8),rs.getString(5)); 
            Competitionlist.add(comp);
            comp.setUser(sdc.getUserById(comp.getUser_id()));
            comp.setNom_user(comp.getUser().getName());

        }
         return Competitionlist;    
         }catch(Exception ex){
             System.out.println("steeeeeeb");
                         System.out.println(ex.getMessage());

         }
        return Competitionlist;
    }

    
    public List<String> fnStatus() {
        ObservableList<String> Competitionlist = FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
        String query = "select DISTINCT  status from commande";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        String comp;
        while (rs.next()) {
           comp = rs.getString(1);
           
            Competitionlist.add(comp);

        }
         return Competitionlist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Competitionlist;
    }
    @Override
    public Commande getById(int id) {
        Services.DetailCommandeService sdc=new Services.DetailCommandeService();
        Commande comp=new Commande();
        try{
        Statement st= conn.createStatement();
        String query = "select * from commande where id="+id+"";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        
        while (rs.next()) {
           comp = new Commande(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(7),rs.getString(4),"",rs.getString(6),rs.getString(8),rs.getString(5)); 
            
           comp.setUser(sdc.getUserById(comp.getUser_id()));
        }
         return comp;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return comp;
    }
    
    public int getId() {
        int com=0;
        try{
        Statement st= conn.createStatement();
        String query = "SELECT max(id) FROM `commande`";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        
        while (rs.next()) {
           com =rs.getInt(1); 
            

        }
         return com;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return com;
    }
    
        public List<Commande> ShowByStatus(String status) {
        ObservableList<Commande> Competitionlist = FXCollections.observableArrayList();
        try{
        Statement st= conn.createStatement();
               //////////user_id=1///////////
        String query = "select * from commande where user_id=1 and status='"+status+"'";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        Commande comp;
        while (rs.next()) {
           comp = new Commande(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(7),rs.getString(4),"",rs.getString(6),rs.getString(8),rs.getString(5)); 
            Competitionlist.add(comp);

        }
         return Competitionlist;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return Competitionlist;
    }
        
        
        
    
}
