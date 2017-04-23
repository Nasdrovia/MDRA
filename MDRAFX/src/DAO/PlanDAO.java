package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Gamme;
import Model.Plan;
import Model.Projet;

public class PlanDAO extends DAO<Plan> {

	public PlanDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Plan obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Plan obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Plan obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Plan> findProjetPlans(Projet projet) {
		List<Plan> list = new ArrayList<>();
		GammeDAO gammeDAO = new GammeDAO(this.connect);
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM plan WHERE id_projet =" + projet.getId());

			while (result.next()) {
				Gamme gamme = gammeDAO.findById(result.getInt(6));
				list.add(new Plan(result.getInt(1), result.getString(2), result.getDate(3), result.getDate(4), projet,
						gamme));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
