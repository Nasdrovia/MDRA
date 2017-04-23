package Model;

public class Devis {

	private int id;
	private StatusDevis status;
	private String dateCreation;
	private String dateModification;
	private Plan plan;

	public Devis(int id, StatusDevis status, String dateCreation, String dateModification, Plan plan) {
		this.id = id;
		this.status = status;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.plan = plan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StatusDevis getStatus() {
		return status;
	}

	public void setStatus(StatusDevis status) {
		this.status = status;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

}
