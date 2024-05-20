package application;

import java.io.IOException;


import java.sql.Connection;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import IDao.IDao;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Connexion.Connexion;

public class ClientController implements IDao<Voiture> {
    @FXML
    private TableView<Voiture> table;

    @FXML
    private TableColumn<Voiture, String> tmodele;

    @FXML
    private TableColumn<Voiture, String> tnom;

    @FXML
    private TableColumn<Voiture, Float> ttarif;

    @FXML
    private TableColumn<Voiture, String> timage; // Ajout de la colonne d'image

    @FXML
    private Button espadmin;

    @Override
    public List<Voiture> findAll() {
        List<Voiture> voitureList = new ArrayList<>();
        try {
            Connection conn = Connexion.getConn();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM voiture";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("idV");
                String marque = rs.getString("marque");
                String modele = rs.getString("modele");
                float tarif = rs.getFloat("tarif");
                String imgV = rs.getString("imgV"); // Récupération du chemin de l'image

                Voiture voiture = new Voiture(id, marque, modele,tarif, imgV);
                voitureList.add(voiture);
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        return voitureList;
    }

    public void initialize() {
        tmodele.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModele()));
        tnom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarque()));
        ttarif.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(cellData.getValue().getTarif()));

        // Configuration de la colonne d'image
        timage.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImgV()));
        timage.setCellFactory(column -> new ImageTableCell());

        table.getItems().addAll(findAll());
    }
    //passer voiture selctionnee en parametre
    public void export(ActionEvent event) {
        Voiture selectedVoiture = table.getSelectionModel().getSelectedItem();
        if (selectedVoiture != null) {
            int id = selectedVoiture.getId();
            System.out.println(selectedVoiture.toString());
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
            Parent root;
            try {
                root = loader.load();
                ReservationController controller = loader.getController();

                controller.initData(id);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
          
            System.out.println("Aucune voiture sélectionnée.");
        }
    }
    public void redirectToLogin(ActionEvent event) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

           
            Scene scene = new Scene(root);

       
            Stage currentStage = (Stage) espadmin.getScene().getWindow();

            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void handleeSubmitButtonAction(ActionEvent event)
    {
   	 Platform.exit();
    }
}