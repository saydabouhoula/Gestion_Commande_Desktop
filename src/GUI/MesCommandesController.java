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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class MesCommandesController implements Initializable {

    @FXML
    private TableView<Commande> tvComande;
    @FXML
    private TableColumn<Commande, Date> colDateCreat;
    @FXML
    private TableColumn<Commande, String> colStatus;
    @FXML
    private TableColumn<Commande, String> colPaymentMeth;
    @FXML
    private TableColumn<Commande, String> colPaymentBrand;
    @FXML
    private TableColumn<Commande, String> colPaymentStatus;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDetails;
    @FXML
    private Label lbidComnade;
    @FXML
    private TextField tfRechercher;
    @FXML
    private ComboBox<String> cbStatus;
    @FXML
    private Button btnFilter;
    @FXML
    private Button btnReset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        CommandeService sr=new CommandeService();
        try{
        for (String comp : sr.fnStatus()) {
            valuesList.add(comp);
        }
        }catch(java.lang.NullPointerException e){
            
        }
        cbStatus.setItems(valuesList);
        fnShow();
        // TODO
    }    
    
    // pour afficher les commandes qui trouvé dans la base de données
 public void fnShow(){
        Services.CommandeService sr=new CommandeService();
         ObservableList<Commande> list = FXCollections.observableArrayList(sr.Show());;
    
     colDateCreat.setCellValueFactory(new PropertyValueFactory<>("date_creation"));       
     colPaymentBrand.setCellValueFactory(new PropertyValueFactory<>("payement_brand"));        
     colPaymentMeth.setCellValueFactory(new PropertyValueFactory<>("payment_method"));   
     colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("payment_status")); 
     colStatus.setCellValueFactory(new PropertyValueFactory<>("status")); 
     

        
     tvComande.setItems(list);
     tvComande.setRowFactory(tv -> new TableRow<Commande>() {
    @Override
    protected void updateItem(Commande item, boolean empty) {
        super.updateItem(item, empty);
        
    }
});
      
    FilteredList<Commande> filteredData = new FilteredList<>(list, b -> true);
		
		tfRechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Event -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Event.getDate_creation().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if(Event.getPayement_brand().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}else if(Event.getPayment_method().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}else if(Event.getPayment_status().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commande> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvComande.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvComande.setItems(sortedData);}
 
 
    // pour afficher la liste de filrage seulement pour le status
  public void fnEventShowStatus(String status){
        Services.CommandeService sr=new CommandeService();
         ObservableList<Commande> list = FXCollections.observableArrayList(sr.ShowByStatus(status));;
    
     colDateCreat.setCellValueFactory(new PropertyValueFactory<>("date_creation"));       
     colPaymentBrand.setCellValueFactory(new PropertyValueFactory<>("payement_brand"));        
     colPaymentMeth.setCellValueFactory(new PropertyValueFactory<>("payment_method"));   
     colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("payment_status")); 
     colStatus.setCellValueFactory(new PropertyValueFactory<>("status")); 
     

        
     tvComande.setItems(list);
     tvComande.setRowFactory(tv -> new TableRow<Commande>() {
    @Override
    protected void updateItem(Commande item, boolean empty) {
        super.updateItem(item, empty);
        
    }
});
      
    FilteredList<Commande> filteredData = new FilteredList<>(list, b -> true);
		
		tfRechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Event -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Event.getDate_creation().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if(Event.getPayement_brand().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}else if(Event.getPayment_method().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}else if(Event.getPayment_status().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commande> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvComande.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvComande.setItems(sortedData);}
  
     // lorsqu'on clique sur la commande et cliquez sur le bouton détail, affiché la détail de cette commande
    @FXML
    private void fnCommandeSelected(MouseEvent event) {
        Commande rec= tvComande.getSelectionModel().getSelectedItem();
        lbidComnade.setText(rec.getId()+"");
        btnDetails.setVisible(true);
    }
   
    // pour retouner à l'affichage de front
    @FXML
    private void fnRetour(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Front.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("Front.fxml"));
            Scene Calculautoscene = new Scene (CalautoSc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // lorsqu' on clique sur la commande puis cilquez sur le bouton détail affiché les détails seulement pour ce commande
    @FXML
    private void fnDetails(ActionEvent event) {
        DetailCommandeController.commandId=lbidComnade.getText();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DetailCommande.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("DetailCommande.fxml"));Scene Calculautoscene = new Scene (CalautoSc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // pour filtrer seulement le champs status 
    @FXML
    private void fnFiltrer(ActionEvent event) {
        String filter=cbStatus.getValue();
        fnEventShowStatus(filter);
        tfRechercher.setText("");
         btnDetails.setVisible(true);
    }

    // pour retourner à l'affichage de commandes aprés l'execution de filtrage
    @FXML
    private void fnReset(ActionEvent event) {
        fnShow();
        tfRechercher.setText("");
         btnDetails.setVisible(true);
    }
    
}
