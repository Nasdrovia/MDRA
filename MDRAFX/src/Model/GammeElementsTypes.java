package Model;

public enum GammeElementsTypes {
	INTERIEUR("Interieur"), COUVERTURE("Couverture"), ISOLANT("Isolant"), OSSATURE("Ossature"), QUALITE(
			"Qualite"), EXTERIEUR("Exterieur");

	private final String name;

	private GammeElementsTypes(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
