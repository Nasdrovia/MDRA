package Model;

public enum StatusDevis {
	FINI("Fini"), CONFLIT("Conflit"), BROUILLON("Brouillon");

	private final String name;

	private StatusDevis(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
