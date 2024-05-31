/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.activation.DataSource;


/**
 * FXML Controller class
 *
 * @author user
 */
public class PieChartController implements Initializable {

    @FXML
    private PieChart idpiechart;
    @FXML
    private Label idstat;
    
    // DataSource pour se connecter à la base de données
    private java.sql.Connection conn = MyConnection.getInstance().getConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Charger les données et les ajouter au graphique
        addData();
    }    
    
        // Requête SQL pour récupérer le nombre de commandes par statut
    private final String req = "SELECT status, COUNT(*) as nbre FROM commande GROUP BY status";

    
     private void addData() {
        // Créer une liste observable pour stocker les données du graphique
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        try {
            // Obtenir une connexion à la base de données
            Connection conn = MyConnection.getInstance().getConnection();

            // Exécuter la requête pour obtenir les statistiques de statut
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);

            // Ajouter les données récupérées à la liste observable
            while (rs.next()) {
                String status = rs.getString("status");
                int nbre = rs.getInt("nbre");
                pieChartData.add(new PieChart.Data(status, nbre));
            }

            // Fermer la connexion
            st.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }

        // Ajouter les données du graphique
        idpiechart.setData(pieChartData);

        // Ajouter un gestionnaire d'événements pour afficher le pourcentage
        for (final PieChart.Data data : idpiechart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                int total = 0;
                for (PieChart.Data d : idpiechart.getData()) {
                    total += d.getPieValue();
                }
                String text = String.format("%.1f%%", 100 * data.getPieValue() / total);

                idstat.setVisible(true);
                idstat.setText("Pourcentage : " + text);
            });
    
}
     }
}
