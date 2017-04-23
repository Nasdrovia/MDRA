package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Client;
import Utils.GlobalUtils;

public class ClientDAO extends DAO<Client> {

	public ClientDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Client obj) {
		try {
			PreparedStatement ps = this.connect.prepareStatement(
					"INSERT INTO client (nom, prenom, adresse, codePostal, ville, telephone, email, dateCreation, dateModification) VALUES (?,?,?,?,?,?,?,?,?)");

			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setString(3, obj.getAdresse());
			ps.setString(4, obj.getCodePostal());
			ps.setString(5, obj.getVille());
			ps.setString(6, obj.getTelephone());
			ps.setString(7, obj.getEmail());
			ps.setDate(8, GlobalUtils.getCurrentDate());
			ps.setDate(9, GlobalUtils.getCurrentDate());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Client obj) {
		try {
			Statement st = this.connect.createStatement();
			st.executeUpdate("DELETE FROM client WHERE id=" + obj.getId());
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Client obj) {
		try {
			PreparedStatement ps = this.connect.prepareStatement(
					"UPDATE client SET nom = ?, prenom = ?, adresse = ?, codePostal = ?, ville = ?, telephone = ?, email = ?, dateModification = ? WHERE id = ?");

			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setString(3, obj.getAdresse());
			ps.setString(4, obj.getCodePostal());
			ps.setString(5, obj.getVille());
			ps.setString(6, obj.getTelephone());
			ps.setString(7, obj.getEmail());
			ps.setDate(8, GlobalUtils.getCurrentDate());
			ps.setInt(9, obj.getId());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Client findById(int id) {
		Client client = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM client WHERE id =" + id);

			if (result.first())
				client = new Client(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
						result.getString(5), result.getString(6), result.getString(7), result.getString(8),
						result.getDate(9), result.getDate(10));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;

	}

	public List<Client> findAllClients() {
		List<Client> list = new ArrayList<>();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM client");

			while (result.next()) {
				list.add(new Client(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
						result.getString(5), result.getString(6), result.getString(7), result.getString(8),
						result.getDate(9), result.getDate(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
