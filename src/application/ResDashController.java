package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Connexion.Connexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ResDashController {
	@FXML
	private TableView<Reservation> table3;

	@FXML
	private TableColumn<Reservation, Integer> idR;

	@FXML
	private TableColumn<Reservation, Integer> idVoi;

	@FXML
	private TableColumn<Reservation, Integer> idCli;

	@FXML
	private TableColumn<Reservation, Date> DB;
	@FXML
	private TableColumn<Reservation, Date> DF;

	@FXML
	private TableColumn<Reservation, Float> total;

	public List<Reservation> findAllresas() {
		List<Reservation> resaList = new ArrayList<>();
		try {
			Connection conn = Connexion.getConn();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM reservation";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				long idR = rs.getLong("idR");
				long idV = rs.getLong("idV");
				long idC = rs.getLong("idC");
				Date DB = rs.getDate("date_debut");
				Date DF = rs.getDate("date_fin");
				Float total = rs.getFloat("total");

				Reservation resa = new Reservation(idR, idV, idC, DB, DF, total);

				resaList.add(resa);
			}
		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}
		return resaList;
	}

	public void initialize() {
		idR.setCellValueFactory(new PropertyValueFactory<>("idr"));
		idVoi.setCellValueFactory(new PropertyValueFactory<>("idv"));
		idCli.setCellValueFactory(new PropertyValueFactory<>("idc"));
		DB.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
		DF.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
		total.setCellValueFactory(new PropertyValueFactory<>("total"));

		table3.getItems().addAll(findAllresas());
	}

	@FXML
	protected void remove(ActionEvent event) {
		Reservation selectedResa = table3.getSelectionModel().getSelectedItem();
		if (selectedResa != null) {

			try {
				Connection conn = Connexion.getConn();
				Statement stmt = conn.createStatement();
				String query = "DELETE FROM reservation WHERE idR = " + selectedResa.getIdr();
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				System.err.println("Error executing query: " + e.getMessage());
				return;
			}

			table3.getItems().remove(selectedResa);
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
}
