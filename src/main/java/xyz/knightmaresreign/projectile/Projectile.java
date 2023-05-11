package xyz.knightmaresreign.projectile;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.knightmaresreign.KnightmaresReign;

import java.util.Objects;

public class Projectile{

    private Vector step;
    private Integer maxDistance;
    private Location originalLocation;
    private Location location;
    private Particle trailParticle;
    private Particle collideParticle;
    private Double damage;

    public Projectile(Location location, Vector step, Integer tickDelay, Integer stepPerTick, Integer maxDistance, Double damage, Particle trailParticle, Particle collideParticle) {
        this.trailParticle = trailParticle;
        this.collideParticle = collideParticle;
        make(location, step, tickDelay, stepPerTick, maxDistance,damage);
    }

    public Projectile(Location location, Vector step, Integer tickDelay, Integer stepPerTick, Integer maxDistance, Double damage) {
        make(location, step, tickDelay, stepPerTick, maxDistance, damage);
    }

    private void make(Location location, Vector step, Integer tickDelay, Integer stepPerTick, Integer maxDistance, Double damage){
        this.step = step;
        this.location = location;
        this.originalLocation = location.clone();
        this.maxDistance = maxDistance;
        this.damage = damage;
        new BukkitRunnable() {
            @Override
            public void run() {
                for(int i = 0; i < stepPerTick; i++) {
                    if (tick()) cancel();
                }
            }
        }.runTaskTimer(KnightmaresReign.getInstance(), 0, tickDelay);
    }

    public boolean tick() {
        location.add(step);
        if(!Objects.isNull(trailParticle)) location.getWorld().spawnParticle(trailParticle, location, 2);

        for (Entity entity : location.getWorld().getNearbyEntities(location, 1, 1, 1)) {
            if (entity instanceof LivingEntity && entity.getBoundingBox().contains(location.toVector())) {
                if(!Objects.isNull(collideParticle)) location.getWorld().spawnParticle(collideParticle, location, 2);
                ((LivingEntity) entity).damage(damage); // Deal damage to the entity
                return true;
            }
        }

        if (!location.getWorld().getBlockAt(location).getType().equals(Material.AIR)) {
            if(!Objects.isNull(collideParticle)) location.getWorld().spawnParticle(collideParticle, location, 2);
            return true;
        }

        if (location.distance(originalLocation) > maxDistance) return true;
        return false;
    }


}
