package ViewController;

import java.util.Iterator;
import java.util.List;

import DAO.MDRAConnection;
import DAO.ProjetDAO;
import Model.Projet;
import Utils.JavaFXUtils;
import application.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class ProjetsManagerController {
	@FXML
	private Label labelCurrentUser;

	@FXML
	private Button buttonNouveau;

	@FXML
	private ImageView imageLogo;

	@FXML
	private TilePane tilePane;

	@FXML
	public void initialize() {
		if (labelCurrentUser != null)
			labelCurrentUser.setText(Session.getCurrentUserLabel());

		if (MDRAConnection.getInstance() == null)
			imageLogo.setImage(new Image(getClass().getResourceAsStream("/Utils/logo_offline.png")));
		else
			imageLogo.setImage(new Image(getClass().getResourceAsStream("/Utils/logo.png")));

		createElements();

		buttonNouveau.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
				JavaFXUtils.switchScene(stage,
						new FXMLLoader(getClass().getResource("/View/ProjetCreatorDialog.fxml")));
			}
		});

	}

	private void createElements() {
		tilePane.getChildren().clear();
		ProjetDAO projetDAO = new ProjetDAO(MDRAConnection.getInstance());

		List<Projet> finds = projetDAO.findAllProjets();
		for (Iterator iterator = finds.iterator(); iterator.hasNext();) {
			Projet projet = (Projet) iterator.next();
			tilePane.getChildren().add(createElement(projet));
		}
	}

	private Button createElement(Projet projet) {
		Image image = new Image(getClass().getResourceAsStream("/Utils/folder.png"));
		ImageView imageView = new ImageView(image);
		Button button = new Button(projet.getNom(), imageView);
		button.setPrefSize(160, 70);

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) labelCurrentUser.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ProjetViewerDialog.fxml"));
				loader.setController(new ProjetViewerController(projet));
				JavaFXUtils.switchScene(stage, loader);
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
