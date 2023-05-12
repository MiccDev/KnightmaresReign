package xyz.knightmaresreign.projectile.data;

import org.bukkit.Particle;

public class DamageProjectileData extends ProjectileData {
	public static DamageProjectileData TEST_MAGIC_WEAPON = (DamageProjectileData) new DamageProjectileData().setDamage(25)
			.setCollideParticle(Particle.EXPLOSION_NORMAL).setMaxDistance(50).setTrailParticle(Particle.ASH).setSpeed(1.25);
	
	private double damage = 1;
	private double radius = 2;

	public double getRadius() {
		return radius;
	}

	public DamageProjectileData setRadius(double radius) {
		this.radius = radius;
		return this;
	}

	public double getDamage() {
		return damage;
	}

	public DamageProjectileData setDamage(double damage) {
		this.damage = damage;
		return this;
	}
	
}
