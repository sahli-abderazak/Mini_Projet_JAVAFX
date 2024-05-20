package application;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Connexion.Connexion;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class VoitureController {

	@FXML
	private TableView<Voiture> table1;
	@FXML
	private TableColumn<Voiture, Integer> id;

	@FXML
	private TableColumn<Voiture, String> marque;
	@FXML
	private TableColumn<Voiture, String> modele;

	@FXML
	private TableColumn<Voiture, Integer> nbV;

	@FXML
	private TableColumn<Voiture, Float> tarif;

	@FXML
	private TableColumn<Voiture, String> image;

	ObservableList<Voiture> list1 = FXCollections.observableArrayList();

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
				int nbV = rs.getInt("nbV");
				float tarif = rs.getFloat("tarif");
				String imgV = rs.getString("imgV");

				Voiture voiture = new Voiture(id, marque, modele, tarif, imgV, nbV);
				voitureList.add(voiture);
			}
		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}
		return voitureList;
	}

	public void initialize() {

		modele.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getModele()));
		marque.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarque()));
		tarif.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(cellData.getValue().getTarif()));
		nbV.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getNbV()));
		id.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getId()));

		image.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImgV()));
		image.setCellFactory(column -> new ImageTableCell());

		list1.addAll(findAll());
		table1.setItems(list1);
	}

	@FXML
	protected void remove(ActionEvent event) {
		Voiture selectedVoiture = table1.getSelectionModel().getSelectedItem();
		if (selectedVoiture != null) {
			try {
				Connection conn = Connexion.getConn();
				conn.setAutoCommit(false); //regrouper plusieurs instructions en une seule transaction

				Statement stmt = conn.createStatement();

				String deleteReservationsQuery = "DELETE FROM reservation WHERE idV = " + selectedVoiture.getId();
				stmt.executeUpdate(deleteReservationsQuery);

				String deleteVoitureQuery = "DELETE FROM voiture WHERE idV = " + selectedVoiture.getId();
				stmt.executeUpdate(deleteVoitureQuery);

				conn.commit();

			} catch (SQLException e) {
				System.err.println("Error executing query: " + e.getMessage());
				return;
			}

			list1.remove(selectedVoiture);
		} else {
			System.out.println("Aucune voiture sélectionnée à supprimer.");
		}
	}

	@FXML
	protected void handleeSubmitButtonAction(ActionEvent event) {
		Node sourceNode = (Node) event.getSource();

		Stage currentStage = (Stage) sourceNode.getScene().getWindow();

		currentStage.close();
	}

	@FXML
	public void export(ActionEvent event) {
		String fxmlFile = null;
		if (event.getSource() instanceof javafx.scene.control.Button) {
			javafx.scene.control.Button clickedButton = (javafx.scene.control.Button) event.getSource();
			switch (clickedButton.getId()) {
			case "ajoutButton":
				System.out.println("test ajout");
				fxmlFile = "voitureajout.fxml";
				if (fxmlFile != null) {
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
						Parent root = loader.load();
						Stage stage = new Stage();
						stage.setScene(new Scene(root));
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;
			case "modifButton":
				System.out.println("allloooo");
				Voiture selectedVoiture = table1.getSelectionModel().getSelectedItem();
				int id = selectedVoiture.getId();
				fxmlFile = "voituremodif.fxml";
				if (fxmlFile != null) {
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
						Parent root = loader.load();
						if ("modifButton".equals(clickedButton.getId())) {
							AdminController controller = loader.getController();
							controller.initData(id);
						}
						Stage stage = new Stage();
						stage.setScene(new Scene(root));
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
			}

		}
	}

}