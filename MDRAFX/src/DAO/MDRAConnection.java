package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MDRAConnection {
	// URL de connexion
	private String url = "jdbc:mysql://localhost:3306/madera?user=root&password=admin";
	// Objet Connection
	private static Connection connect;

	// Constructeur priv�
	private MDRAConnection() {
		try {
			connect = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// M�thode qui va nous retourner notre instance et la cr�er si elle n'existe
	// pas
	public static Connection getInstance() {
		if (connect == null) {
			new MDRAConnection();
		}
		return connect;
	}
}
