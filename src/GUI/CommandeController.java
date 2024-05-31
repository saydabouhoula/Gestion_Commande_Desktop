/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Entities.DetailCommande;
import Services.CommandeService;
import Services.DetailCommandeService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class CommandeController implements Initializable {

    @FXML
    private ComboBox<String> tfMethod;
    @FXML
    private TextField tfBrand;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnCommander;
    @FXML
    private TableView<DetailCommande> tvDetail;
    @FXML
    private TableColumn<DetailCommande, String> colProduit;
    @FXML
    private TableColumn<DetailCommande, Integer> colQt;
    @FXML
    private Label lbSomme;
    @FXML
    private Label lbIdDetail;
    @FXML
    private HBox hboxPorduit;
    @FXML
    private Label lbNomProduit;
    @FXML
    private Button btnMinus;
    @FXML
    private Label lbQuantite;
    @FXML
    private Button btnPlus;
    @FXML
    private Button btnRetour;
    @FXML
    private Label lbBrandChoice;
    @FXML
    private TableColumn<DetailCommande, Double> colPrixUni;
    @FXML
    private TableColumn<DetailCommande, Double> colTotal;
    @FXML
    private Button bntLigne;
    @FXML
    private Button btnSuivant;
    @FXML
    private Label lbMethodEtape;
    @FXML
    private HBox hboxBrand;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fnDetailCommande(NouveauCommandeController.commande_id+"");
        Services.DetailCommandeService sr=new DetailCommandeService();
        lbSomme.setText(sr.Somme(NouveauCommandeController.commande_id)+" Dt");
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        
        try{
           
            valuesList.add("Card");
            valuesList.add("Cheque");
            valuesList.add("Cash");
        
        }catch(java.lang.NullPointerException e){
            
        }
        tfMethod.setItems(valuesList);
        
        // TODO
    }    
    
    public void fnDetailCommande(String status){
        Services.DetailCommandeService sr=new DetailCommandeService();
         ObservableList<DetailCommande> list = FXCollections.observableArrayList(sr.ShowByCommande(Integer.parseInt(status)));;
    
     colProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));       
     colQt.setCellValueFactory(new PropertyValueFactory<>("qte"));      
     colPrixUni.setCellValueFactory(new PropertyValueFactory<>("prixUni"));       
     colTotal.setCellValueFactory(new PropertyValueFactory<>("total")); 

        
     tvDetail.setItems(list);
}

    @FXML
    private void fnAnnuler(ActionEvent event) {
        CommandeService cs=new CommandeService();
        cs.Delete(NouveauCommandeController.commande_id);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Front.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("Front.fxml"));Scene Calculautoscene = new Scene (CalautoSc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hboxBrand.setVisible(false);
        hboxPorduit.setVisible(false);
    }

    @FXML
    private void fnCommander(ActionEvent event) {
        CommandeService cs=new CommandeService();
        Commande c=cs.getById(NouveauCommandeController.commande_id);
        try{
        c.setPayment_method(tfMethod.getValue());
        if(tfMethod.getValue().equals("Cash")){
            c.setPayement_brand("");
        }else{
          c.setPayement_brand(tfBrand.getText()); 
           if (tfBrand.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Il faut remplir Le Numéro ...  !");
        alert.showAndWait();
        return;
       }
        }
        
       
        if (tvDetail.getItems().isEmpty()) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText(null);
          alert.setContentText("Le détail de cette commande est vide !");
          alert.showAndWait();
        return;
      }

       
    
        
        cs.update(c);
        hboxBrand.setVisible(false);
        hboxPorduit.setVisible(false);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("MesCommandes.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("MesCommandes.fxml"));Scene Calculautoscene = new Scene (CalautoSc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch(Exception e){
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText("Il faut remplir les deux champs !!");
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
             
        }
        }
        
        
        
    }

    @FXML
    private void fnSelectedDetail(MouseEvent event) {
        DetailCommande rec= tvDetail.getSelectionModel().getSelectedItem();
        lbIdDetail.setText(rec.getId()+"");
        hboxPorduit.setVisible(true);
        lbNomProduit.setText(rec.getNom());
        lbQuantite.setText(rec.getQte()+"");
    }

    @FXML
    private void fnMinus(ActionEvent event) {
        DetailCommandeService ds=new DetailCommandeService();
        
        
        DetailCommande dc=ds.getById(Integer.parseInt(lbIdDetail.getText()));
        dc.setQte(dc.getQte()-1);
        ds.update(dc);
        fnDetailCommande(NouveauCommandeController.commande_id+"");
        lbQuantite.setText(dc.getQte()+"");
        lbSomme.setText(ds.Somme(NouveauCommandeController.commande_id)+" Dt");
        
    }

    @FXML
    private void fnPlus(ActionEvent event) {
        DetailCommandeService ds=new DetailCommandeService();
        
        DetailCommande dc=ds.getById(Integer.parseInt(lbIdDetail.getText()));
        dc.setQte(dc.getQte()+1);
        ds.update(dc);
        fnDetailCommande(NouveauCommandeController.commande_id+"");
        lbQuantite.setText(dc.getQte()+"");
        lbSomme.setText(ds.Somme(NouveauCommandeController.commande_id)+" Dt");
    }

    @FXML
    private void fnRetour(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("NouveauCommande.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("NouveauCommande.fxml"));Scene Calculautoscene = new Scene (CalautoSc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hboxBrand.setVisible(false);
        hboxPorduit.setVisible(false);
    }

    @FXML
    private void fnSuppLigne(ActionEvent event) {
         DetailCommandeService ds=new DetailCommandeService();
         ds.Delete(Integer.parseInt(lbIdDetail.getText()));
         fnDetailCommande(NouveauCommandeController.commande_id+"");
        hboxPorduit.setVisible(false);
        lbSomme.setText(ds.Somme(NouveauCommandeController.commande_id)+" Dt");
    }

    @FXML
    private void fnSuivant(ActionEvent event) {
        if(tfMethod.getValue().equals("Cash")){
            
        }else if(tfMethod.getValue().equals("Chéque")){
            lbBrandChoice.setText("Numéro de chéque");
            hboxBrand.setVisible(true);
        }else{
            lbBrandChoice.setText("Numéro de carte");
            hboxBrand.setVisible(true);
        }
    }
    
   

            }

    

