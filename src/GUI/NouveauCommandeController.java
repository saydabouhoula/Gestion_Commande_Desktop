/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.DetailCommande;
import Services.CommandeService;
import Services.DetailCommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class NouveauCommandeController implements Initializable {

    @FXML
    private TextField qte;
    @FXML
    private Button btnAutre;
    @FXML
    private Button btnSauvegarder;
    @FXML
    private ComboBox<String> lbProduit;
    public static int commande_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        DetailCommandeService sr=new DetailCommandeService();
        try{
        for (String comp : sr.getProduit()) {
            valuesList.add(comp);
        }
        }catch(java.lang.NullPointerException e){
            
        }
        lbProduit.setItems(valuesList);
        // TODO
    }    

    @FXML
    private void fnAutre(ActionEvent event) {
        DetailCommandeService sr=new DetailCommandeService();
        DetailCommande dc=new DetailCommande();
        dc.setProduit_id(sr.getByNom(lbProduit.getValue()));
        try{
         dc.setQte(Integer.parseInt(qte.getText()));
         dc.setCommande_id(commande_id);
            List<DetailCommande> lis=sr.ShowByCommande(commande_id);
            boolean exist=false;
            DetailCommande ddd=new DetailCommande();
            for(int i =0 ;i<lis.size();i++){
                if(lbProduit.getValue().equals(lis.get(i).getNom())){
                    exist=true;
                    ddd=lis.get(i);
                }
            }
            if(exist){
                ddd.setQte(ddd.getQte()+Integer.parseInt(qte.getText()));
                sr.update(ddd);
            }else{
                sr.add(dc);
            }
        
        lbProduit.setValue("");
        qte.setText("");
        }catch(Exception e){
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText("le champs quantite accept que des entiers !!");
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
             
        }
    }
        
        
    }

    @FXML
    private void fnSauvegarder(ActionEvent event) {
        DetailCommandeService sr=new DetailCommandeService();
        DetailCommande dc=new DetailCommande();
        dc.setProduit_id(sr.getByNom(lbProduit.getValue()));
        try{
         dc.setQte(Integer.parseInt(qte.getText()));
         dc.setCommande_id(commande_id);
        List<DetailCommande> lis=sr.ShowByCommande(commande_id);
            boolean exist=false;
            DetailCommande ddd=new DetailCommande();
            for(int i =0 ;i<lis.size();i++){
                if(lbProduit.getValue().equals(lis.get(i).getNom())){
                    exist=true;
                    ddd=lis.get(i);
                }
            }
            if(exist){
                ddd.setQte(ddd.getQte()+Integer.parseInt(qte.getText()));
                sr.update(ddd);
            }else{
                sr.add(dc);
            }
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Commande.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("Commande.fxml"));Scene Calculautoscene = new Scene (CalautoSc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }catch(Exception e){
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Alert.AlertType type=Alert.AlertType.CONFIRMATION;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText("le champs quantite accept que des entiers !! clicker Ok pour finaliser votre commande Ou Cancel pour ajouter un autre produit");
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
             FXMLLoader loader=new FXMLLoader(getClass().getResource("Commande.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("Commande.fxml"));Scene Calculautoscene = new Scene (CalautoSc);
        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setScene(Calculautoscene);
        windows.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(result.get()==ButtonType.CANCEL){
            
        }
    }
        
        
    }
    
}
