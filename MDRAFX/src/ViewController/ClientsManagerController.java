package ViewController;

import DAO.ClientDAO;
import DAO.MDRAConnection;
import Model.Client;
import Utils.JavaFXUtils;
import application.Session;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ClientsManagerController {
	@FXML
	private Label labelCurrentUser;

	@FXML
	private ListView<Client> listView;

	@FXML
	private TextField textNom;

	@FXML
	private TextField textPrenom;

	@FXML
	private TextField textAdresse;

	@FXML
	private TextField textCodePostal;

	@FXML
	private TextField textVille;

	@FXML
	private TextField textTelephone;

	@FXML
	private TextField textEmail;

	@FXML
	private Button buttonSauvegarder;

	@FXML
	private Button buttonSupprimer;

	@FXML
	private Button buttonLogOut;

	@FXML
	private Button buttonNewClient;
	@FXML
	private ImageView imageLogo;

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
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
			public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
				updateClientPanel(newValue);
			}
		});
		listView.setItems(FXCollections.observableArrayList(clientDAO.findAllClients()));

		buttonLogOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

			}
		});

		buttonNewClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				resetClientPanel();
			}
		});

		listView.getSelectionModel().select(0);
	}

	@FXML
	protected void handleAccueilAction(ActionEvent event) {
		Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
		JavaFXUtils.switchScene(stage, new FXMLLoader(getClass().getResource("/View/HomeDialog.fxml")));
	}

	@FXML
	protected void handleConceptionAction(ActionEvent event) {
		Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ConceptionDialog.fxml"));
		loader.setController(new ConceptionController());
		JavaFXUtils.switchScene(stage, loader);
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

	private void resetClientPanel() {
		textNom.clear();
		textPrenom.clear();
		textAdresse.clear();
		textCodePostal.clear();
		textVille.clear();
		textTelephone.clear();
		textEmail.clear();
		buttonSupprimer.setVisible(false);

		buttonSauvegarder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ClientDAO clientDAO = new ClientDAO(MDRAConnection.getInstance());
				Client client = new Client();
				client.setNom(textNom.getText());
				client.setPrenom(textPrenom.getText());
				client.setAdresse(textAdresse.getText());
				client.setCodePostal(textCodePostal.getText());
				client.setVille(textVille.getText());
				client.setTelephone(textTelephone.getText());
				client.setEmail(textEmail.getText());
				clientDAO.create(client);
				listView.getItems().add(client);
				ObservableList<Client> t = listView.getItems();
				listView.setItems(t);
			}
		});
	}

	private void updateClientPanel(Client client) {
		if (!buttonSupprimer.isVisible())
			buttonSupprimer.setVisible(true);

		textNom.setText(client.getNom());
		textPrenom.setText(client.getPrenom());
		textAdresse.setText(client.getAdresse());
		textCodePostal.setText(client.getCodePostal());
		textVille.setText(client.getVille());
		textTelephone.setText(client.getTelephone());
		textEmail.setText(client.getEmail());

		buttonSauvegarder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ClientDAO clientDAO = new ClientDAO(MDRAConnection.getInstance());
				client.setNom(textNom.getText());
				client.setPrenom(textPrenom.getText());
				client.setAdresse(textAdresse.getText());
				client.setCodePostal(textCodePostal.getText());
				client.setVille(textVille.getText());
				client.setTelephone(textTelephone.getText());
				client.setEmail(textEmail.getText());
				clientDAO.update(client);
				listView.refresh();
			}
		});

		buttonSupprimer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ClientDAO clientDAO = new ClientDAO(MDRAConnection.getInstance());
				clientDAO.delete(client);
				listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
				ObservableList<Client> t = listView.getItems();
				listView.setItems(t);
			}
		});
	}
}
