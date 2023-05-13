package xyz.knightmaresreign.entities.data;

import xyz.knightmaresreign.entities.Entities;

public class EntityData implements Cloneable {
	private Entities type;
	private double health;
	
	public EntityData() {
		this(Entities.NONE);
	}
	
	public EntityData(Entities type) {
		this(type, 20);
	}
	
	public EntityData(Entities type, double health) {
		this.type = type;
		this.health = health;
	}

	public Entities getType() {
		return type;
	}

	public EntityData setType(Entities type) {
		this.type = type;
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
