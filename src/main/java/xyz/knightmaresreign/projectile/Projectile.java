package xyz.knightmaresreign.projectile;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.projectile.data.DamageProjectileData;
import xyz.knightmaresreign.projectile.data.ProjectileData;
import xyz.knightmaresreign.sound.SoundData;

public class Projectile {
	
	private Entity caster;
	private Vector direction;
    private Location originalLocation;
    private Location location;
 
    private ProjectileData data;

    public Projectile(Entity caster, Vector direction, Location location, int tickDelay, ProjectileData data) {
    	this.data = data;
        make(caster, direction, location, tickDelay);
    }

    private void make(Entity caster, Vector direction, Location location, int tickDelay){
    	this.caster = caster;
    	this.direction = direction;
        this.location = location;
        this.originalLocation = location.clone();
        new BukkitRunnable() {
            @Override
            public void run() {
            	if (tick()) cancel();
            }
        }.runTaskTimer(KnightmaresReign.getInstance(), 0, tickDelay);
    }

    public boolean tick() {
        location.add(direction.multiply(data.getSpeed()));
        if(!Objects.isNull(data.getTrailParticle())) location.getWorld().spawnParticle(data.getTrailParticle(), location, 2);

        double radius = data instanceof DamageProjectileData ? ((DamageProjectileData) data).getRadius() : 10;
        
        BoundingBox bounds = BoundingBox.of(location.toVector(), radius, radius, radius);
        
        for (LivingEntity entity : location.getNearbyLivingEntities(radius)) {
        	if(entity.equals(caster)) continue;
            if (bounds.overlaps(entity.getBoundingBox())) {
            	spawnCollisionParticle();
                if(data instanceof DamageProjectileData) {
                	entity.damage(((DamageProjectileData) data).getDamage()); // Deal damage to the entity
                }
                playImpactSound(location);
                return true;
            }
        }

        if (!location.getWorld().getBlockAt(location).getType().equals(Material.AIR)) {
        	spawnCollisionParticle();
            playImpactSound(location);
            return true;
        }

        if (location.distance(originalLocation) > data.getMaxDistance()) return true;
        return false;
    }
    
    private void spawnCollisionParticle() {
    	if(!Objects.isNull(data.getCollideParticle())) location.getWorld().spawnParticle(data.getCollideParticle(), location, 2);
    }
    
    private void playImpactSound(Location location) {
    	SoundData soundData = data.getSoundData() != null ? data.getSoundData() : SoundData.DEFAULT;
    	location.getWorld().playSound(location, soundData.getSound(), soundData.getVolume(), soundData.getPitch());
    }

	public ProjectileData getData() {
		return data;
	}

	public Projectile setData(ProjectileData data) {
		this.data = data;
		return this;
	}

}
