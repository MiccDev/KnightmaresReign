package xyz.knightmaresreign.projectile.data;

import org.bukkit.Particle;

import xyz.knightmaresreign.sound.SoundData;

public class ProjectileData {
	private Particle trailParticle = Particle.CLOUD;
	private Particle collideParticle = Particle.EXPLOSION_NORMAL;
	private double speed = 1;
	private int maxDistance = 25;
	
	private SoundData soundData;

	public ProjectileData() {
		
	}

	public Particle getTrailParticle() {
		return trailParticle;
	}

	public ProjectileData setTrailParticle(Particle trailParticle) {
		this.trailParticle = trailParticle;
		return this;
	}

	public Particle getCollideParticle() {
		return collideParticle;
	}

	public ProjectileData setCollideParticle(Particle collideParticle) {
		this.collideParticle = collideParticle;
		return this;
	}

	public double getSpeed() {
		return speed;
	}

	public ProjectileData setSpeed(double speed) {
		this.speed = speed;
		return this;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public ProjectileData setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
		return this;
	}
	
	public SoundData getSoundData() {
		return soundData;
	}

	public ProjectileData setSoundData(SoundData soundData) {
		this.soundData = soundData;
		return this;
	}
	
}
