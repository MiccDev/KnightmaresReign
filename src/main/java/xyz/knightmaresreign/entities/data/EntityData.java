package xyz.knightmaresreign.entities.data;

import xyz.knightmaresreign.entities.Entities;

public class EntityData implements Cloneable {
	private Entities type;
	private String name;
	private double health;
	
	public EntityData() {
		this(Entities.NONE);
	}
	
	public EntityData(Entities type) {
		this(type, "Entity");
	}
	
	public EntityData(Entities type, String name) {
		this(type, name, 20);
	}
	
	public EntityData(Entities type, String name, double health) {
		this.type = type;
		this.name = name;
		this.health = health;
	}

	public Entities getType() {
		return type;
	}

	public EntityData setType(Entities type) {
		this.type = type;
		return this;
	}

	public String getName() {
		return name;
	}

	public EntityData setName(String name) {
		this.name = name;
		return this;
	}

	public double getHealth() {
		return health;
	}

	public EntityData setHealth(double health) {
		this.health = health;
		return this;
	}
	
}
