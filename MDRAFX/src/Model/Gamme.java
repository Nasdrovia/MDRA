package Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import Utils.GlobalUtils;
import Utils.JavaFXUtils;
import javafx.scene.image.Image;

public class Gamme {

	private int id;
	private String name;
	private List<GammeElements> gammeElements;
	private InputStream imageStream;

	public Gamme(int id, String name, InputStream imageStream, List<GammeElements> gammeElements) {
		this.id = id;
		this.name = name;
		this.gammeElements = gammeElements;
		this.imageStream = imageStream;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Image getImage() {
		try {
			return JavaFXUtils.convertToJavaFXImage(GlobalUtils.toByteArray(imageStream), 180, 95);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GammeElements> getGammeElements() {
		return gammeElements;
	}

	public void setGammeElements(List<GammeElements> gammeElements) {
		this.gammeElements = gammeElements;
	}

	public void addGammeElements(List<GammeElements> gammeElements) {
		this.gammeElements.addAll(gammeElements);
	}

}
