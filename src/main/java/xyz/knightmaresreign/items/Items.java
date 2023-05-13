package xyz.knightmaresreign.items;

import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.potion.PotionType;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.items.data.ItemData;
import xyz.knightmaresreign.items.data.PotionMaterialData;

public enum Items implements Keyed {

	NONE("none", Material.AIR),
	TEST_SWORD("test_sword", Material.IRON_SWORD),
	MAGIC_WEAPON("magic_weapon", Material.STICK),
	SENOR_BONKERS("senor_bonkers", Material.STICK),

	DATA_BOOK("data_book", Material.BOOK),
	
	PEASANT_HAT("peasant_hat", Material.LEATHER_HELMET),
	PEASANT_SHIRT("peasant_shirt", Material.LEATHER_CHESTPLATE),
	PEASANT_PANTS("peasant_pants", Material.LEATHER_LEGGINGS),
	PEASANT_SHOES("peasant_shoes", Material.LEATHER_BOOTS),
	PEASANTS_SWORD("peasants_sword", Material.WOODEN_SWORD),
	PEASANTS_HATCHET("peasants_hatchet", Material.WOODEN_AXE),
	
	WAR_HELMET("war_helmet", Material.CHAINMAIL_HELMET),
	WAR_CHESTPLATE("war_chestplate", Material.CHAINMAIL_CHESTPLATE),
	WAR_LEGGINGS("war_leggings", Material.CHAINMAIL_LEGGINGS),
	WAR_BOOTS("war_boots", Material.CHAINMAIL_BOOTS),
	WAR_LONGSWORD("war_longsword", Material.STONE_SWORD),
	WAR_HATCHET("war_hatchet", Material.STONE_AXE),
	
	FARMERS_SICKLE("farmers_sickle", Material.IRON_HOE),
	
	SMALL_HEALING_POTION("small_healing_potion", Material.POTION, new PotionMaterialData(PotionType.INSTANT_HEAL)),
	SMALL_MANA_POTION("small_mana_potion", Material.POTION, new PotionMaterialData(PotionType.WATER)),
	
	MAGIC_LILY("magic_lily", Material.LILY_OF_THE_VALLEY);
	
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
