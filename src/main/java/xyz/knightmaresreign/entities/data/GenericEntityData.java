package xyz.knightmaresreign.entities.data;

public class GenericEntityData extends EntityData {
	private String name;
	
	public GenericEntityData(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public GenericEntityData setName(String name) {
		this.name = name;
		return this;
	}
	
}
