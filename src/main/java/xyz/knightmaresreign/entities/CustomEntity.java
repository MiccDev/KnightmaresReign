package xyz.knightmaresreign.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;

import net.minecraft.world.entity.Entity;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.data.DeathData;
import xyz.knightmaresreign.entities.data.EntityData;
import xyz.knightmaresreign.entities.data.GenericEntityData;
import xyz.knightmaresreign.entities.data.SpawnData;
import xyz.knightmaresreign.entities.forest.Bee;
import xyz.knightmaresreign.entities.spawn.Cow;

public class CustomEntity {
	public static NamespacedKey ENTITY = new NamespacedKey(KnightmaresReign.KNIGHTMARES_REIGN, "entity");
	public static HashMap<String, CustomEntity> entities = new HashMap<String, CustomEntity>();

//	public static CustomEntity TEST_BOI = new CustomEntity(Entities.TEST_BOI, TestBoi.class)
//			.setData(new EntityData().setName("&6Test Boi"));
	public static CustomEntity COW = new CustomEntity(Entities.COW, Cow.class)
			.addData(new GenericEntityData("&fCow"))
			.addData(new SpawnData(Material.GRASS_BLOCK))
			.addData(new DeathData(1, 5));
	
	public static CustomEntity BEE = new CustomEntity(Entities.BEE, Bee.class)
			.addData(new GenericEntityData("&fBee"))
			.addData(new SpawnData(Material.GRASS_BLOCK, Material.BEE_NEST, Material.BEEHIVE))
			.addData(new DeathData(1, 5));

	public static boolean isCustomEntity(LivingEntity entity) {
		if (entity == null)
			return false;
		return entity.getPersistentDataContainer().has(CustomEntity.ENTITY);
	}

	public static CustomEntity getEntity(NamespacedKey key) {
		return getEntity(key.getKey());
	}

	public static CustomEntity getEntity(String key) {
		if (!entities.containsKey(key))
			return null;
		return entities.get(key);
	}

	public static CustomEntity toEntity(LivingEntity entity) {
		if (entity == null)
			return null;
		if (!isCustomEntity(entity))
			return null;

		String key = entity.getPersistentDataContainer().get(CustomEntity.ENTITY, PersistentDataType.STRING);
		return getEntity(key);
	}

	private Entities type;
	private List<EntityData> data;
	private Class<? extends Entity> entity;

	public CustomEntity(Entities type, Class<? extends Entity> entity) {
		this.type = type;
		this.data = new ArrayList<EntityData>();
		this.entity = entity;
		addData(type.getData());
		entities.put(type.getKey().getKey(), this);
	}

	public Entity getEntity(Location location) {
		Entity ent = null;
		try {
			ent = entity.getConstructor(Location.class).newInstance(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ent == null)
			return null;
		ent.getBukkitEntity().getPersistentDataContainer().set(CustomEntity.ENTITY, PersistentDataType.STRING, type.getKey().getKey());
		if(getDataOfType(GenericEntityData.class) != null)
			ent.b(KnightmaresReign.getInstance().toMcComponent(getDataOfType(GenericEntityData.class).getName()));
		ent.n(true);
		return ent;
	}

	public Entities getType() {
		return type;
	}

	public CustomEntity setType(Entities type) {
		this.type = type;
		return this;
	}

	public List<EntityData> getData() {
		return data;
	}

	public CustomEntity addData(EntityData data) {
		this.data.add(data);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends EntityData> T getDataOfType(Class<? extends T> klass) {
		EntityData a = this.data.stream().filter((data) -> klass.isAssignableFrom(data.getClass())).findAny()
				.orElse(null);
		return (T) a;
	}

	public static HashMap<String, CustomEntity> getHashMap() {
		// Added so can be Visualised
		return entities;
	}
}
