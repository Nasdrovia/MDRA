package ViewController;

import DAO.MDRAConnection;
import DAO.ProjetDAO;
import Model.Projet;
import Utils.JavaFXUtils;
import application.Session;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ConceptionController {

	private Projet projet;

	@FXML
	private Label labelCurrentUser;

	@FXML
	private ImageView imageLogo;

	@FXML
	private ListView<Projet> listView;

	@FXML
	private Button buttonContinuer;

	public ConceptionController() {
	}

	public ConceptionController(Projet projet) {
		this.projet = projet;
	}

	@FXML
	public void initialize() {
		if (labelCurrentUser != null)
			labelCurrentUser.setText(Session.getCurrentUserLabel());

		if (MDRAConnection.getInstance() == null)
			imageLogo.setImage(new Image(getClass().getResourceAsStream("/Utils/logo_offline.png")));
		else
			imageLogo.setImage(new Image(getClass().getResourceAsStream("/Utils/logo.png")));

		listView.setCellFactory(new Callback<ListView<Projet>, ListCell<Projet>>() {

			@Override
			public ListCell<Projet> call(ListView<Projet> p) {

				ListCell<Projet> cell = new ListCell<Projet>() {

					@Override
					protected void updateItem(Projet t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getNom());
						} else {
							setText("");
						}
					}
				};

				return cell;
			}
		});
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Projet>() {
			public void changed(ObservableValue<? extends Projet> observable, Projet oldValue, Projet newValue) {
				updateTabs(newValue);
			}

		});

		ProjetDAO projetDAO = new ProjetDAO(MDRAConnection.getInstance());
		listView.setItems(FXCollections.observableArrayList(projetDAO.findAllProjets()));
		if (projet != null) {
			for (int i = 0; i < listView.getItems().size(); i++) {
				Projet projet = (Projet) listView.getItems().get(i);
				if (projet.getId() == this.projet.getId()) {
					listView.getSelectionModel().select(i);
					updateTabs(this.projet);
				}
			}
		}
	}

	private void updateTabs(Projet newValue) {

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
}
