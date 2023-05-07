package xyz.knightmaresreign.entities;

import org.bukkit.NamespacedKey;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.data.EntityData;

public enum Entities {

	NONE("none"),
	TEST_BOI("test_boi", EntityType.SLIME);
	
	private final NamespacedKey key;
	private final EntityData data;
	private final EntityType<? extends Entity> type;
	
	Entities(final String key) {
		// Defaults entity type to Zombie.
		this(key, EntityType.ZOMBIE);
	}
	
	Entities(final String key, final EntityType<? extends Entity> type) {
		this(key, type, new EntityData());
	}
	
	Entities(final String key, final EntityType<? extends Entity> type, final EntityData data) {
		this.key = new NamespacedKey(KnightmaresReign.KNIGHTMARES_REIGN, key);
		this.type = type;
		this.data = data;
	}
	
	public EntityType<? extends Entity> getType() {
		return type;
	}
	
	public NamespacedKey getKey() {
		return key;
	}
	
	public EntityData getData() {
		return data;
	}
	
}
