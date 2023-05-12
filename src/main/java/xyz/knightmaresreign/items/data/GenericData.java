package xyz.knightmaresreign.items.data;

public class GenericData extends ItemData {
	private String name;
	
	public GenericData(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ItemData setName(String name) {
		this.name = name;
		return this;
	}
	
}
