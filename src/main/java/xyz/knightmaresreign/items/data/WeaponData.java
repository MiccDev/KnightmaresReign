package xyz.knightmaresreign.items.data;

import xyz.knightmaresreign.items.Items;

public class WeaponData extends ItemData {
	private double damage;
	private double manaCost;

	public WeaponData(double damage) {
		this(Items.NONE, damage, 0);
	}
	
	public WeaponData(double damage, double manaCost) {
		this(Items.NONE, damage, manaCost);
	}
	
	public WeaponData(Items type, double damage) {
		this(type, damage, 0);
	}
	
	public WeaponData(Items type, double damage, double manaCost) {
		super(type);
		this.damage = damage;
		this.manaCost = manaCost;
	}
	
	public double getDamage() {
		return damage;
	}

	public WeaponData setDamage(double damage) {
		this.damage = damage;
		return this;
	}
	
	public double getMana() {
		return manaCost;
	}

	public WeaponData setMana(double manaCost) {
		this.manaCost = manaCost;
		return this;
	}
	
}
