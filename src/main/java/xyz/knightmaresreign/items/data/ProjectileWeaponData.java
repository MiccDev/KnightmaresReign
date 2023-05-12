package xyz.knightmaresreign.items.data;

import xyz.knightmaresreign.items.Items;
import xyz.knightmaresreign.projectile.data.DamageProjectileData;

public class ProjectileWeaponData extends WeaponData {
	private DamageProjectileData projectileData;
	
	public ProjectileWeaponData(DamageProjectileData projectileData) {
		this(Items.NONE, projectileData, 0);
	}
	
	public ProjectileWeaponData(DamageProjectileData projectileData, double manaCost) {
		this(Items.NONE, projectileData, manaCost);
	}
	
	public ProjectileWeaponData(Items type, DamageProjectileData projectileData) {
		this(type, projectileData, 0);
	}
	
	public ProjectileWeaponData(Items type, DamageProjectileData projectileData, double manaCost) {
		super(type, projectileData.getDamage(), manaCost);
		this.projectileData = projectileData;
	}

	public DamageProjectileData getProjectileData() {
		return projectileData;
	}

	public ProjectileWeaponData setProjectileData(DamageProjectileData projectileData) {
		this.projectileData = projectileData;
		return this;
	}
	
}
