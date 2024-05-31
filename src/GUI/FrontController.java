/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class FrontController implements Initializable {

    @FXML
    private Button btnNouveau;
    @FXML
    private Button btnMesCom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
// pour choisir un produit et leur quantité 
    @FXML
    private void fnNouveau(ActionEvent event) {
        CommandeService cs=new CommandeService();
        Commande c=new Commande();
        c.setCard("");
        c.setStatus("Commande Initialisée");
        c.setCc_last(0000);
        c.setDate_creation(Date.valueOf(LocalDate.now()));
        ///////////user_id=1///////////
        c.setUser_id(1);
        ///////////
        c.setPayement_brand("");
        c.setPayment_method("");
        c.setPayment_status("En cours");
        cs.add(c);
        NouveauCommandeController.commande_id=cs.getId();
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
        
        
    }
// pour afficher les commandes qui trouvé dans la base de données
    @FXML
    private void fnMesCom(ActionEvent event) {
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
    
}
