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
	SENOR_BONKERS("senor_bonkers", Material.STICK),

	DATA_BOOK("data_book", Material.BOOK),
	
	PEASANT_HAT("peasant_hat", Material.LEATHER_HELMET),
	PEASANT_SHIRT("peasant_shirt", Material.LEATHER_CHESTPLATE),
	PEASANT_PANTS("peasant_pants", Material.LEATHER_LEGGINGS),
	PEASANT_SHOES("peasant_shoes", Material.LEATHER_BOOTS);
	
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
