/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Services.CommandeService;
import Utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.activation.DataSource;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BackController implements Initializable {

    @FXML
    private Label dashboard;
    @FXML
    private Button user;
    @FXML
    private Button produit;
    @FXML
    private Button commande;
    @FXML
    private Button livraison;
    @FXML
    private Button reclamation;
    @FXML
    private Button evenement;
    @FXML
    private Label idcommandes;
    @FXML
    private TableView<Commande> tableviewcom;
    @FXML
    private TableColumn<Commande, Integer> colcom;
    @FXML
    private TableColumn<Commande, String> coluser;
    @FXML
    private TableColumn<Commande, Date> coldate;
    @FXML
    private TableColumn<Commande, String> colstatus;
//    @FXML
//    private Button idstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         fonShow();
    }    

    @FXML
    private void btnuser(ActionEvent event) {
    }

    @FXML
    private void btnproduit(ActionEvent event) {
    }

    @FXML
    private void btncommande(ActionEvent event) {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("GestionCommandes.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("GestionCommandes.fxml"));Scene Calculautoscene = new Scene (CalautoSc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
        private void fonShow() {
    // Créer une liste de commande
    CommandeService sr = new CommandeService();
    ObservableList<Commande> list = FXCollections.observableArrayList(sr.Show());

    // Lier l'objet ObservableList<Commande> à la table
    tableviewcom.setItems(list);

    // Lier chaque colonne à un champ de données spécifique de la classe Commande
    colcom.setCellValueFactory(new PropertyValueFactory<>("id"));
    coluser.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
    coldate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
    colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
        
//    @FXML
//    private void stat(ActionEvent event) {
//         try {
//            Parent parent = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
//            Scene scene = new Scene(parent);
//
//            Stage stage = new Stage();
//
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.UTILITY);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private List<Commande> LoadDataEvent() {
//        List<Commande> EventList = new ArrayList<>();
//        try {
//            String requete = "SELECT status from commande";
//            Connection conn = MyConnection.getInstance().getConnection();
//            ResultSet rs = conn.executeQuery(requete);
//            while (rs.next()) {
//                Commande r = new Commande();
//               
//                
//                 r.setStatus(rs.getString(4));
//                
//                EventList.add(r);
//            }
//        } catch (SQLException ex) {
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLSTATE: " + ex.getSQLState());
//            System.out.println("VnedorError: " + ex.getErrorCode());
//        }
//        return EventList;}   

        

    @FXML
    private void btnlivraison(ActionEvent event) {
    }

    @FXML
    private void btnreclamation(ActionEvent event) {
    }

    @FXML
    private void btnevenement(ActionEvent event) {
    }

    
    
}
