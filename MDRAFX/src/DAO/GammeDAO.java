package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Gamme;

public class GammeDAO extends DAO<Gamme> {

	public GammeDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Gamme obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Gamme obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Gamme obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public Gamme findById(int id) {
		Gamme gamme = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM gamme WHERE id =" + id);

			if (result.first())
				gamme = new Gamme(result.getInt("id"), result.getString("name"), result.getBinaryStream("image"), null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gamme;
	}

	public List<Gamme> findAllGammes() {
		List<Gamme> list = new ArrayList<>();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM gamme");

			while (result.next()) {
				list.add(new Gamme(result.getInt("id"), result.getString("name"), result.getBinaryStream("image"),
						null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
