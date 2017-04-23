package ViewController;

import java.util.Iterator;
import java.util.List;

import DAO.CoupeDAO;
import DAO.GammeDAO;
import DAO.MDRAConnection;
import Model.CoupePrincipe;
import Model.Gamme;
import Model.Plan;
import Model.Projet;
import Utils.JavaFXUtils;
import application.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlanCreatorController {
	@FXML
	private Label labelCurrentUser;

	@FXML
	private ImageView imageLogo;

	@FXML
	private TextField textName;

	@FXML
	private Button buttonGamme;

	@FXML
	private Button buttonCoupePrincipe;

	@FXML
	private Button buttonLogOut;

	@FXML
	private VBox vBox;
	private Plan plan;

	private Projet projet;

	public PlanCreatorController(Projet projet) {
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

		buttonGamme.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				createGammeElements();
			}
		});
		buttonCoupePrincipe.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				createGammeElements();
			}
		});

	}

	private void createGammeElements() {
		vBox.getChildren().clear();
		GammeDAO gammeDAO = new GammeDAO(MDRAConnection.getInstance());

		List<Gamme> finds = gammeDAO.findAllGammes();
		for (Iterator<Gamme> iterator = finds.iterator(); iterator.hasNext();) {
			Gamme gamme = (Gamme) iterator.next();
			vBox.getChildren().add(createGammeElement(gamme));
		}
	}

	private Button createGammeElement(Gamme gamme) {
		ImageView imageView = new ImageView(gamme.getImage());
		Button button = new Button(gamme.getName(), imageView);
		button.setPrefSize(200, 70);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				buttonGamme.setText("Gamme " + gamme.getName());
			}
		});
		return button;
	}

	private void createCoupeElements() {
		vBox.getChildren().clear();
		CoupeDAO coupeDAO = new CoupeDAO(MDRAConnection.getInstance());

		List<CoupePrincipe> finds = coupeDAO.findAllCoupes();
		for (Iterator<CoupePrincipe> iterator = finds.iterator(); iterator.hasNext();) {
			CoupePrincipe gamme = (CoupePrincipe) iterator.next();
			vBox.getChildren().add(createCoupeElement(gamme));
		}
	}

	private Button createCoupeElement(CoupePrincipe coupe) {
		ImageView imageView = new ImageView(coupe.getImage());
		Button button = new Button(coupe.getName(), imageView);
		button.setPrefSize(200, 70);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			}
		});
		return button;
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
