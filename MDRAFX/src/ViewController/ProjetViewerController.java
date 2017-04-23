package ViewController;

import DAO.MDRAConnection;
import DAO.PlanDAO;
import Model.Plan;
import Model.Projet;
import Model.User;
import Utils.JavaFXUtils;
import application.Session;
import javafx.collections.FXCollections;
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
	private ListView<Plan> listViewPlans;

	@FXML
	private ComboBox<User> comboBox;

	@FXML
	private TextArea textDescription;

	@FXML
	private Button buttonSauvegarder;

	@FXML
	private Button buttonSupprimer;

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

		buttonNouveauPlan.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PlanCreatorDialog.fxml"));
				loader.setController(new PlanCreatorController(projet));
				JavaFXUtils.switchScene(stage, loader);
			}
		});

		PlanDAO planDAO = new PlanDAO(MDRAConnection.getInstance());
		listViewPlans.setCellFactory(new Callback<ListView<Plan>, ListCell<Plan>>() {

			@Override
			public ListCell<Plan> call(ListView<Plan> p) {

				ListCell<Plan> cell = new ListCell<Plan>() {

					@Override
					protected void updateItem(Plan t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getName());
						} else {
							setText("");
						}
					}

				};

				return cell;
			}
		});
		listViewPlans.setItems(FXCollections.observableArrayList(planDAO.findProjetPlans(projet)));
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
