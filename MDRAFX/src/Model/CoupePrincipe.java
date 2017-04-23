package Model;

import java.io.IOException;
import java.io.InputStream;

import Utils.GlobalUtils;
import Utils.JavaFXUtils;
import javafx.scene.image.Image;

public class CoupePrincipe {

	private int id;
	private String name;
	private InputStream imageStream;

	public CoupePrincipe(int id, String name, InputStream imageStream) {
		this.id = id;
		this.name = name;
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

}
