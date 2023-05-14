package xyz.knightmaresreign.regions;

public enum Region {
	SPAWN("spawn", "Spawn"),
	FOREST("forest", "Forest"),
	DESERT("desert", "Desert");
	
	private final String name;
	private final String displayName;
	
	Region(final String name) {
		this(name, name);
	}
	
	Region(final String name, final String displayName) {
		this.name = name;
		this.displayName = displayName;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}
	
}
