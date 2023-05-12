package xyz.knightmaresreign.items.data;

import xyz.knightmaresreign.items.Items;

public class ItemData implements Cloneable {
	private Items type;
	
	public ItemData() {
		this(Items.NONE);
	}
	
	public ItemData(Items type) {
		this.type = type;
	}
	
	public Items getType() {
		return type;
	}
	
	public ItemData setType(Items type) {
		this.type = type;
		return this;
	}
	
}
