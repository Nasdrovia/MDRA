package ViewController;

import DAO.ClientDAO;
import DAO.MDRAConnection;
import DAO.ProjetDAO;
import Model.Client;
import Model.Projet;
import Utils.GlobalUtils;
import Utils.JavaFXUtils;
import application.Session;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ProjetCreatorController {

	@FXML
	private Label labelCurrentUser;

	@FXML
	private ImageView imageLogo;

	@FXML
	private Button buttonCreer;

	@FXML
	private TextArea textDescription;

	@FXML
	private TextField textName;

	@FXML
	private ListView<Client> listView;

	@FXML
	public void initialize() {
		if (labelCurrentUser != null)
			labelCurrentUser.setText(Session.getCurrentUserLabel());

		if (MDRAConnection.getInstance() == null)
			imageLogo.setImage(new Image(getClass().getResourceAsStream("/Utils/logo_offline.png")));
		else
			imageLogo.setImage(new Image(getClass().getResourceAsStream("/Utils/logo.png")));

		ClientDAO clientDAO = new ClientDAO(MDRAConnection.getInstance());

		listView.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {

			@Override
			public ListCell<Client> call(ListView<Client> p) {

				ListCell<Client> cell = new ListCell<Client>() {

					@Override
					protected void updateItem(Client t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getNomPrenom());
						} else {
							setText("");
						}
					}

				};

				return cell;
			}
		});
		listView.setItems(FXCollections.observableArrayList(clientDAO.findAllClients()));

		buttonCreer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (listView.getSelectionModel().getSelectedIndex() >= 0) {
					ProjetDAO projetDAO = new ProjetDAO(MDRAConnection.getInstance());
					Projet projet = new Projet();
					projet.setDescription(textDescription.getText());
					projet.setNom(textName.getText());
					projet.setDateCreation(GlobalUtils.getCurrentDate());
					projet.setDateModification(GlobalUtils.getCurrentDate());
					projet.setUser(Session.getSession());
					projet.setClient((Client) listView.getSelectionModel().getSelectedItem());
					projetDAO.create(projet);
				}
			}
		});
	}

	@FXML
	protected void handleAccueilAction(ActionEvent event) {
		Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
		JavaFXUtils.switchScene(stage, new FXMLLoader(getClass().getResource("/View/HomeDialog.fxml")));
	}

	@FXML
	protected void handleProjetAction(ActionEvent event) {
		Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
		JavaFXUtils.switchScene(stage, new FXMLLoader(getClass().getResource("/View/ProjetsManagerDialog.fxml")));
	}

	@FXML
	protected void handleClientAction(ActionEvent event) {
		Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
		JavaFXUtils.switchScene(stage, new FXMLLoader(getClass().getResource("/View/ClientsManagerDialog.fxml")));
	}
}
