package Model;

import java.sql.Date;

public class Projet {

	private int id;
	private String nom;
	private Date dateCreation;
	private Date dateModification;
	private User user;
	private Client client;
	private String description;

	public Projet(int id, String nom, String description, Date dateCreation, Date dateModification, User user,
			Client client) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.user = user;
		this.client = client;
	}

	public Projet() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
