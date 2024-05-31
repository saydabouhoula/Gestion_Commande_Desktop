/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Services.CommandeService;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.ByteMatrix;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import org.apache.commons.lang3.RandomStringUtils;
import Services.SendEmailWthImage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestionCommandesController implements Initializable {

    @FXML
    private TableView<Commande> tablecommande;
    @FXML
    private TableColumn<Commande, Integer> colidcommande;
    @FXML
    private TableColumn<Commande, String> colutilisateur;
    @FXML
    private TableColumn<Commande, Date> coldate;
    @FXML
    private TableColumn<Commande, String> colstatus;
    @FXML
    private Label labcommande;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnsup;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btndetail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fonShow();
        // TODO
    }    

    @FXML
    private void fonRetour(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Back.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("Back.fxml"));
            Scene Calculautoscene = new Scene (CalautoSc);
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
    tablecommande.setItems(list);

    // Lier chaque colonne à un champ de données spécifique de la classe Commande
    colidcommande.setCellValueFactory(new PropertyValueFactory<>("id"));
    colutilisateur.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
    coldate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
    colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
}

    @FXML
private void fonsupp(ActionEvent event) {
    // Récupérer la ligne sélectionnée
    int selectedIndex = tablecommande.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        // Afficher une alerte pour confirmer la suppression de la commande
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Pouvez-vous confirmer si vous souhaitez annuler cette commande ?");
        
        // Ajouter les boutons OK et Annuler à l'alerte
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        
        // Afficher l'alerte et attendre la réponse
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK) {
            // Supprimer la commande de la base de données
            CommandeService sr = new CommandeService();
            Commande selectedCommande = tablecommande.getSelectionModel().getSelectedItem();
            CommandeService commandeService = new CommandeService();
            Commande c=sr.getById(selectedCommande.getId());
            c.setStatus("Commande Annulée");
            commandeService.update(c);
            new SendEmailWthImage(
                    c.getUser().getEmail(),
                    "Commande Annulée",
                "Commande: Votre commande est annulée  id : "+c.getId(),"");

            // Supprimer la ligne sélectionnée de la TableView
            fonShow();
        }
    } else {
        // Aucune ligne sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune ligne sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
        alert.showAndWait();
    }
}

    @FXML
private void fonmodifier(ActionEvent event) {
    // Récupérer la ligne sélectionnée
    int selectedIndex = tablecommande.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        // Afficher une alerte pour confirmer la suppression de la commande
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Pouvez-vous confirmer si vous souhaitez bien passer cette commande ?");
        
        // Ajouter les boutons OK et Annuler à l'alerte
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        
        // Afficher l'alerte et attendre la réponse
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK) {
            // Supprimer la commande de la base de données
            CommandeService sr = new CommandeService();
            Commande selectedCommande = tablecommande.getSelectionModel().getSelectedItem();
            CommandeService commandeService = new CommandeService();
            Commande c=sr.getById(selectedCommande.getId());
            c.setStatus("Commande Confirmée");
            commandeService.update(c);
            Charset charset = Charset.forName("UTF-8");
            CharsetEncoder encoder = charset.newEncoder();
    byte[] b = null;
    try {
        // Convert a string to UTF-8 bytes in a ByteBuffer
        String resulta = RandomStringUtils.random(8, true, true);
        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap("Ceci est votre code de confirmation de commande : "+resulta));
        b = bbuf.array();
    } catch (CharacterCodingException sa) {
        System.out.println(sa.getMessage());
    }

    String data;
    try {
        data = new String(b, "UTF-8");
        // get a byte matrix for the data
        ByteMatrix matrix = null;
        int h = 100;
        int w = 100;
        com.google.zxing.Writer writer = new MultiFormatWriter();
        try {
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(2);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            matrix = writer.encode(data,
            com.google.zxing.BarcodeFormat.QR_CODE, w, h, hints);
        } catch (com.google.zxing.WriterException sb) {
            System.out.println(sb.getMessage());
        }

        // change this path to match yours (this is my mac home folder, you can use: c:\\qr_png.png if you are on windows)
                String filePath = "C:/Users/user/Desktop/PI/Saida/Saida/qr_Image/qr_png.png";
   
        File file = new File(filePath);
        try {
            MatrixToImageWriter.writeToFile(matrix, "PNG", file);
            System.out.println("printing to " + file.getAbsolutePath());
        } catch (IOException sd) {
            System.out.println(sd.getMessage());
        }
    } catch (UnsupportedEncodingException ae) {
        System.out.println(ae.getMessage());
    }
    String filePath = "C:/Users/user/Desktop/PI/Saida/Saida/qr_Image/qr_png.png";
            new SendEmailWthImage(
                    c.getUser().getEmail(),
                    "Commande Confirmée",
                "Commande: Votre commande est confirmée  id : "+c.getId(),filePath);

            // Supprimer la ligne sélectionnée de la TableView
            fonShow();
        }
    } else {
        // Aucune ligne sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune ligne sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
        alert.showAndWait();
    }
    }


    @FXML
    private void fondetail(ActionEvent event) {
         DetailCommandeBackController.commandId=tablecommande.getSelectionModel().getSelectedItem().getId();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DetailCommandeBack.fxml"));
                Parent CalautoSc;
        try {
            CalautoSc = (Parent)loader.load(getClass().getResource("DetailCommandeBack.fxml"));Scene Calculautoscene = new Scene (CalautoSc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}