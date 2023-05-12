package xyz.knightmaresreign.items.data;

import xyz.knightmaresreign.items.Items;

public class DefenseData extends ItemData {
	private double defense = 1;

	public DefenseData(double defense) {
		this(Items.NONE, defense);
	}
	
	public DefenseData(Items type, double defense) {
		super(type);
		this.defense = defense;
	}
	
	public double getDefense() {
		return defense;
	}

	public DefenseData setDefense(double defense) {
		this.defense = defense;
		return this;
	}
	
}
