package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Connexion.Connexion;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
public class ClientDashController {
	 @FXML
	    private TableView<Client> table2;

	    @FXML
	    private TableColumn<Client, Integer> idC;

	    @FXML
	    private TableColumn<Client, String> nom;

	    @FXML
	    private TableColumn<Client, String> prenom;

	    @FXML
	    private TableColumn<Client, String> tel;

	    @FXML
	    private TableColumn<Client, String> adresse;

	    public List<Client> findAllclients() {
	        List<Client> clientList = new ArrayList<>();
	        try {
	            Connection conn = Connexion.getConn();
	            Statement stmt = conn.createStatement();
	            String query = "SELECT * FROM client";
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	                int id = rs.getInt("idC");
	                String nom = rs.getString("nom");
	                String prenom = rs.getString("prenom");
	                String tel = rs.getString("tel");
	                String adresse = rs.getString("adresse");

	                Client client = new Client(id, nom, prenom, tel, adresse);
	                clientList.add(client);
	            }
	        } catch (SQLException e) {
	            System.err.println("Error executing query: " + e.getMessage());
	        }
	        return clientList;
	    }

	    public void initialize() {
	        idC.setCellValueFactory(new PropertyValueFactory<>("idC"));
	        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
	        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
	        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

	        table2.getItems().addAll(findAllclients());
	    }
	    
	    @FXML
	    protected void handleeSubmitButtonAction(ActionEvent event) {
	        
	        Node sourceNode = (Node) event.getSource();
	        
	      
	        Stage currentStage = (Stage) sourceNode.getScene().getWindow();

	        currentStage.close();
	    }
	    
	    
	    public void export(ActionEvent event) {
	        Client selectedCli = table2.getSelectionModel().getSelectedItem();
	        if (selectedCli != null) {
	            int id = selectedCli.getIdC();
//	            System.out.println(selectedCli.toString());
	           
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("clientmodif.fxml"));
	            Parent root;
	            try {
	                root = loader.load();
	                AdminController controller = loader.getController();
	               
	                controller.initDataClient(id);
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));
	                stage.show();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } else {
	            
	            System.out.println("Aucune client sélectionnée.");
	        }
	    }
}
