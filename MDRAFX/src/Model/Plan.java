package Model;

import java.sql.Date;

public class Plan {

	private int id;
	private String name;
	private Date dateCreation;
	private Date dateModification;
	private Projet projet;
	private Gamme gamme;

	public Plan(int id, String name, Date dateCreation, Date dateModification, Projet projet, Gamme gamme) {
		this.id = id;
		this.name = name;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.projet = projet;
		this.gamme = gamme;
	}

	public Plan(String name, Date dateCreation, Date dateModification, Projet projet, Gamme gamme) {
		this.name = name;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.projet = projet;
		this.gamme = gamme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Gamme getGamme() {
		return gamme;
	}

	public void setGamme(Gamme gamme) {
		this.gamme = gamme;
	}

}
