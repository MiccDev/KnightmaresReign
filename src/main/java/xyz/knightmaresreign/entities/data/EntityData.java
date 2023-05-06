package xyz.knightmaresreign.entities.data;

import xyz.knightmaresreign.entities.Entities;

public class EntityData implements Cloneable {
	private Entities type;
	private String name;
	
	public EntityData() {
		this(Entities.NONE);
	}
	
	public EntityData(Entities type) {
		this(type, "Entity");
	}
	
	public EntityData(Entities type, String name) {
		this.type = type;
		this.name = name;
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
	
}
