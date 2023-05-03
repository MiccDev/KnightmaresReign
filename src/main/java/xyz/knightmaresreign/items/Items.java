package xyz.knightmaresreign.items;

import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.items.data.ItemData;

public enum Items implements Keyed {

	NONE("none", Material.AIR),
	TEST_SWORD("test_sword", Material.IRON_SWORD),
	MAGIC_WEAPON("magic_weapon", Material.STICK),
	SENOR_BONKERS("senor_bonkers", Material.STICK);
	
	private final NamespacedKey key;
	private final Material material;
	private final ItemData data;
	
	private Items(final String key) {
		this(key, Material.DIRT);
	}
	
	private Items(final String key, final Material material) {
		this(key, material, new ItemData());
	}
	
	private Items(final String key, final ItemData data) {
		this(key, Material.DIRT, data);
	}
	
	private Items(final String key, final Material material, final ItemData data) {
		this.key = new NamespacedKey(KnightmaresReign.KNIGHTMARES_REIGN, key);
		this.material = material;
		this.data = data;
	}
	
	public NamespacedKey getKey() {
		return key;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public ItemData getData() {
		return data;
	}
	
}
