package ViewController;

import DAO.MDRAConnection;
import DAO.UserDAO;
import Model.User;
import Utils.JavaFXUtils;
import application.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginDialogController {
	@FXML
	private TextField userName;
	@FXML
	private TextField userPass;
	@FXML
	private Pane pane;
	@FXML
	private Button loginButton;
	@FXML
	private ImageView imageView;

	@FXML
	public void initialize() {
		if (MDRAConnection.getInstance() == null)
			imageView.setImage(new Image(getClass().getResourceAsStream("/Utils/logo_offline.png")));
		else
			imageView.setImage(new Image(getClass().getResourceAsStream("/Utils/logo.png")));
	}

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		UserDAO userDAO = new UserDAO(MDRAConnection.getInstance());
		User user = userDAO.findByEmail(userName.getText());
		Stage stage = (Stage) loginButton.getScene().getWindow();
		JavaFXUtils.switchScene(stage, new FXMLLoader(getClass().getResource("/View/HomeDialog.fxml")));
		if (user != null) {
			Session.user = user;

		} else {
			// TODO PRINT ERROR LOGGIN FAILED
		}

	}
}
