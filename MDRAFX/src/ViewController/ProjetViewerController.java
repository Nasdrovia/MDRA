package ViewController;

import DAO.MDRAConnection;
import Model.Projet;
import Model.User;
import Utils.JavaFXUtils;
import application.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ProjetViewerController {

	@FXML
	private Label labelCurrentUser;

	@FXML
	private ImageView imageLogo;

	@FXML
	private DatePicker datePicker;

	@FXML
	private ComboBox<User> comboBox;

	@FXML
	private TextArea textDescription;

	@FXML
	private Button buttonSauvegarder;

	@FXML
	private Button buttonSupprimer;

	@FXML
	private Button buttonOuvrir;

	@FXML
	private Button buttonNouveauPlan;

	private final Projet projet;

	public ProjetViewerController(Projet projet) {
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

		datePicker.setDisable(true);
		datePicker.setValue(projet.getDateCreation().toLocalDate());

		comboBox.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
			@Override
			public ListCell<User> call(ListView<User> p) {

				ListCell<User> cell = new ListCell<User>() {

					@Override
					protected void updateItem(User t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getDisplayName());
						} else {
							setText("");
						}
					}

				};
				return cell;
			}
		});

		comboBox.setValue(projet.getUser());
		textDescription.setText(projet.getDescription());

		buttonSauvegarder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

			}
		});

		buttonSupprimer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

			}
		});

		buttonOuvrir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ConceptionDialog.fxml"));
				loader.setController(new ConceptionController(projet));
				JavaFXUtils.switchScene(stage, loader);
			}
		});

		buttonNouveauPlan.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PlanCreatorDialog.fxml"));
				loader.setController(new PlanCreatorController(projet));
				JavaFXUtils.switchScene(stage, loader);
			}
		});
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
