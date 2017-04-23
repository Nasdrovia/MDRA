package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Group;
import Model.User;

public class UserDAO extends DAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(User obj) {
		return false;
	}

	@Override
	public boolean delete(User obj) {
		return false;
	}

	@Override
	public boolean update(User obj) {
		return false;
	}

	public User findById(int id) {
		User user = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM user WHERE id =" + id);

			if (result.first())
				user = new User(result.getInt("id"), result.getString("nom"), result.getString("prenom"),
						result.getString("email"), result.getString("password"), Group.COMMERCIAL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	public User findByEmail(String email) {
		User user = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM user WHERE email = \"" + email + "\"");

			if (result.first())
				user = new User(result.getInt("id"), result.getString("nom"), result.getString("prenom"),
						result.getString("email"), result.getString("password"), Group.COMMERCIAL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

}
