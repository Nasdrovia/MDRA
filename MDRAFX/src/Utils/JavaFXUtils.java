package Utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class JavaFXUtils {
	
	/**
	 * Method used to switch between JavaFX scenes more easily, and cleaner within the code.
	 * @param stage
	 * @param loader
	 */

	public static void switchScene(Stage stage, FXMLLoader loader) {
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (root != null && stage != null) {
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	/**
	 * Will convert an array of byte into an JavaFX image of specified width and height
	 * @param raw
	 * @param width
	 * @param height
	 * @return the JavaFX image
	 */
	public static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
		WritableImage image = new WritableImage(width, height);
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(raw);
			BufferedImage read = ImageIO.read(bis);
			image = SwingFXUtils.toFXImage(read, null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return image;
	}
}
