package application;

import Model.User;

public class Session {
	public static User user = null;

	/**
	 * Used to check if the user is currently logged in
	 * 
	 * @return the user
	 */
	public static User getSession() {
		return user;
	}

	public static String getCurrentUserLabel() {
		if (user == null)
			return "Utilisation hors connexion";

		return user.getDisplayName() + " " + user.getGroup().getName();
	}

	public static boolean isLoggedIn() {
		return user == null ? false : true;
	}
}
