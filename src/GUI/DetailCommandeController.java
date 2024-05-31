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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class DetailCommandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static String commandId;
    @FXML
    private TableView<DetailCommande> tvDetail;
    @FXML
    private TableColumn<DetailCommande, String> colProduit;
    @FXML
    private TableColumn<DetailCommande, Integer> colQt;
    @FXML
    private Button btnRetour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fnEventShowStatus(commandId);
        // TODO
    }    
    
    

    public void fnEventShowStatus(String status){
        Services.DetailCommandeService sr=new DetailCommandeService();
         ObservableList<DetailCommande> list = FXCollections.observableArrayList(sr.ShowByCommande(Integer.parseInt(status)));;
    
     colProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));       
     colQt.setCellValueFactory(new PropertyValueFactory<>("qte"));      
     

        
     tvDetail.setItems(list);
}
    @FXML
    private void fnRetour(ActionEvent event) {
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
