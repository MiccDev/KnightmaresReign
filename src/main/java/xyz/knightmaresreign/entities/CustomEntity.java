package xyz.knightmaresreign.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import net.kyori.adventure.text.Component;

public abstract class CustomEntity {
	
	private static Map<String, CustomEntity> entities = new HashMap<String, CustomEntity>();
	
	public static void registerEntity(String id, CustomEntity e) {
		entities.put(id, e);
	}
	
	public static List<CustomEntity> getAllEntities() {
		return entities.values().stream().collect(Collectors.toList());
	}

	public String id;
	private Component displayName;
	private EntityType type;
	
	public boolean shouldDropItems = true;
	
	public CustomEntity(String id, Component displayName, EntityType type) {
		this(id, displayName, type, true);
	}
	
	public CustomEntity(String id, Component displayName, EntityType type, boolean shouldDropItems) {
		this.id = id;
		this.type = type;
		this.displayName = displayName;
		this.shouldDropItems = shouldDropItems;
		CustomEntity.registerEntity(id, this);
	}

	public abstract void setup(Entity e);
	public void onDamage(Entity damager) {};
	
	public void setMaxHealth(LivingEntity e, double health) {
		e.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
		e.setHealth(e.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	}
	
	public void removeAllPassengers(LivingEntity e) {
		for(Entity passenger : e.getPassengers()) {
			e.removePassenger(passenger);
		}
	}
	
	public Entity spawnAt(Location location) {
		Entity e = newEntity(location);
		setup(e);
		e.customName(displayName);
		return e;
	}
	
	public Entity newEntity(Location location) {
		location = location != null ? location : new Location(Bukkit.getWorld("world"), 0, -70, 0);
		return location.getWorld().spawnEntity(location, getType());
	}

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public Component getDisplayName() {
		return displayName;
	}

	public void setDisplayName(Component displayName) {
		this.displayName = displayName;
	}
	
}
