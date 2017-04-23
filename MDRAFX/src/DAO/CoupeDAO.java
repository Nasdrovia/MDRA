package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.CoupePrincipe;

public class CoupeDAO extends DAO<CoupePrincipe> {

	public CoupeDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(CoupePrincipe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CoupePrincipe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CoupePrincipe obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<CoupePrincipe> findAllCoupes() {
		List<CoupePrincipe> list = new ArrayList<>();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM coupe_principe");

			while (result.next()) {
				list.add(new CoupePrincipe(result.getInt("id"), result.getString("name"),
						result.getBinaryStream("image")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
