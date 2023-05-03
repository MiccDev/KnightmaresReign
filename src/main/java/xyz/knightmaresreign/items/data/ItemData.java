package xyz.knightmaresreign.items.data;

import xyz.knightmaresreign.items.Items;

public class ItemData implements Cloneable {
	private Items type;
	private String name;
	
	public ItemData() {
		this(Items.NONE, "Item");
	}
	
	public ItemData(Items type) {
		this(type, "Item");
	}
	
	public ItemData(Items type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ItemData setName(String name) {
		this.name = name;
		return this;
	}
	
	public Items getType() {
		return type;
	}
	
	public ItemData setType(Items type) {
		this.type = type;
		return this;
	}
	
}
