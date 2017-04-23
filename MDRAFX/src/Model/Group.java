package Model;

public enum Group {
	ADMIN("Administrateur"), COMMERCIAL("Commercial"), BUREAU("Bureau d'�tude");

	private final String name;

	private Group(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
