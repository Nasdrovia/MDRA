package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Client;
import Model.Projet;
import Model.User;

public class ProjetDAO extends DAO<Projet> {

	public ProjetDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Projet obj) {
		try {
			PreparedStatement ps = this.connect.prepareStatement(
					"INSERT INTO projet (name, description, dateCreation, dateModification, id_user, id_client) VALUES (?,?,?,?,?,?)");

			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getDescription());
			ps.setDate(3, obj.getDateCreation());
			ps.setDate(4, obj.getDateModification());
			ps.setInt(5, obj.getUser().getId());
			ps.setInt(6, obj.getClient().getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Projet obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Projet obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Projet> findAllProjets() {
		List<Projet> list = new ArrayList<>();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM projet");

			while (result.next()) {
				UserDAO userDAO = new UserDAO(this.connect);
				ClientDAO clientDAO = new ClientDAO(this.connect);
				User user = userDAO.findById(result.getInt(5));
				Client client = clientDAO.findById(result.getInt(6));
				list.add(new Projet(result.getInt(1), result.getString(2), result.getString(7), result.getDate(3),
						result.getDate(4), user, client));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
