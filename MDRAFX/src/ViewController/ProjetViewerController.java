package ViewController;

import DAO.MDRAConnection;
import DAO.PlanDAO;
import Model.Plan;
import Model.Projet;
import Utils.JavaFXUtils;
import application.Session;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private TextField text_Commercial;

	@FXML
	private TableView tableDevis;

	@FXML
	private TableView tableModalites;

	@FXML
	private ListView<Plan> list_DevisPlans;

	@FXML
	private ListView<Plan> list_ModalitePlans;

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

		text_Commercial.setText(projet.getUser().getDisplayName());

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
		list_DevisPlans.setItems(FXCollections.observableArrayList(planDAO.findProjetPlans(projet)));
		list_ModalitePlans.setItems(FXCollections.observableArrayList(planDAO.findProjetPlans(projet)));
		fillModalitesPaiement();
	}

	private void fillDevis() {

	}

	public static class ModalitePaiement {
		private final SimpleStringProperty palier;
		private final SimpleFloatProperty taux;
		private final SimpleFloatProperty cout;

		private ModalitePaiement(String palier, float taux, float cout) {
			this.palier = new SimpleStringProperty(palier);
			this.taux = new SimpleFloatProperty(taux);
			this.cout = new SimpleFloatProperty(cout);
		}

		public String getPalier() {
			return palier.get();
		}

		public float getTaux() {
			return taux.get();
		}

		public float getCout() {
			return cout.get();
		}

	}

	private void fillModalitesPaiement() {
		TableColumn columnModalites = (TableColumn) tableModalites.getColumns().get(0);
		columnModalites.setCellValueFactory(new PropertyValueFactory<ModalitePaiement, String>("palier"));
		TableColumn columnTaux = (TableColumn) tableModalites.getColumns().get(1);
		columnTaux.setCellValueFactory(new PropertyValueFactory<ModalitePaiement, String>("taux"));
		TableColumn columnCout = (TableColumn) tableModalites.getColumns().get(2);
		columnCout.setCellValueFactory(new PropertyValueFactory<ModalitePaiement, String>("cout"));

		tableModalites.setItems(FXCollections.observableArrayList(new ModalitePaiement("Signature", 3.f, 00.00f),
				new ModalitePaiement("Obtention du permis de construire", 10.f, 00.00f),
				new ModalitePaiement("Ouverture du chantier", 15.f, 00.00f),
				new ModalitePaiement("Achèvement des fondations", 25.f, 00.00f),
				new ModalitePaiement("Achèvement des murs", 40.f, 00.00f),
				new ModalitePaiement("Mise hors d'eaux / air", 75.f, 00.00f),
				new ModalitePaiement("Achèvement des travaux d'équipement", 95.f, 00.00f),
				new ModalitePaiement("Remise des clés", 100.f, 00.00f)));

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
