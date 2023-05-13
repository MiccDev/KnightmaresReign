package xyz.knightmaresreign.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;

import net.kyori.adventure.text.Component;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.items.data.DefenseData;
import xyz.knightmaresreign.items.data.EnchantedData;
import xyz.knightmaresreign.items.data.GenericData;
import xyz.knightmaresreign.items.data.ItemData;
import xyz.knightmaresreign.items.data.PotionMaterialData;
import xyz.knightmaresreign.items.data.ProjectileWeaponData;
import xyz.knightmaresreign.items.data.WeaponData;
import xyz.knightmaresreign.projectile.data.DamageProjectileData;

public class CustomItem {
	public static NamespacedKey ITEM = new NamespacedKey(KnightmaresReign.KNIGHTMARES_REIGN, "item");
	public static HashMap<String, CustomItem> items = new HashMap<String, CustomItem>();

	public static CustomItem TEST_SWORD = new CustomItem(Items.TEST_SWORD).addData(new WeaponData(100))
			.addData(new GenericData("Test Sword"));
	public static CustomItem TEST_MAGIC_WEAPON = new CustomItem(Items.MAGIC_WEAPON)
			.addData(new ProjectileWeaponData(DamageProjectileData.TEST_MAGIC_WEAPON, 10))
			.addData(new GenericData("&7Magic Weapon"));
	public static CustomItem SENOR_BONKERS = new CustomItem(Items.SENOR_BONKERS)
			.addData(new WeaponData(250000))
			.addData(new GenericData("&o&bSeñor Bonkers"));
	public static CustomItem DATA_BOOK = new CustomItem(Items.DATA_BOOK).addData(new GenericData("&eData Book"));

	/*
	 * ######################################### SPAWN AREA
	 * #########################################
	 */

	public static CustomItem PEASANT_HAT = new CustomItem(Items.PEASANT_HAT)
			.addData(new DefenseData(1))
			.addData(new GenericData("&fPeasant Hat"));
	public static CustomItem PEASANT_SHIRT = new CustomItem(Items.PEASANT_SHIRT)
			.addData(new DefenseData(2))
			.addData(new GenericData("&fPeasant Shirt"));
	public static CustomItem PEASANT_PANTS = new CustomItem(Items.PEASANT_PANTS)
			.addData(new DefenseData(2))
			.addData(new GenericData("&fPeasant Pants"));
	public static CustomItem PEASANT_SHOES = new CustomItem(Items.PEASANT_SHOES)
			.addData(new DefenseData(1))
			.addData(new GenericData("&fPeasant Shoes"));
	public static CustomItem PEASANTS_SWORD = new CustomItem(Items.PEASANTS_SWORD)
			.addData(new WeaponData(1))
			.addData(new GenericData("&fPeasant Sword"));
	public static CustomItem PEASANTS_HATCHET = new CustomItem(Items.PEASANTS_HATCHET)
			.addData(new WeaponData(3))
			.addData(new GenericData("&fPeasant Hatchet"));
	
	public static CustomItem WAR_HELMET = new CustomItem(Items.WAR_HELMET)
			.addData(new DefenseData(2))
			.addData(new GenericData("&fWar Helmet"));
	public static CustomItem WAR_CHESTPLATE = new CustomItem(Items.WAR_CHESTPLATE)
			.addData(new DefenseData(3))
			.addData(new GenericData("&fWar Chestplate"));
	public static CustomItem WAR_LEGGINGS = new CustomItem(Items.WAR_LEGGINGS)
			.addData(new DefenseData(2))
			.addData(new GenericData("&fWar Leggings"));
	public static CustomItem WAR_BOOTS = new CustomItem(Items.WAR_BOOTS)
			.addData(new DefenseData(2))
			.addData(new GenericData("&fWar Boots"));
	public static CustomItem WAR_LONGSWORD = new CustomItem(Items.WAR_LONGSWORD)
			.addData(new WeaponData(4))
			.addData(new GenericData("&fWar Longsword"));
	public static CustomItem WAR_HATCHET = new CustomItem(Items.WAR_HATCHET)
			.addData(new WeaponData(5))
			.addData(new GenericData("&fWar Hatchet"));
	
	public static CustomItem FARMERS_SICKLE = new CustomItem(Items.FARMERS_SICKLE)
			.addData(new WeaponData(6))
			.addData(new GenericData("&fFarmers Sickle"));
	
	public static CustomItem SMALL_HEALING_POTION = new CustomItem(Items.SMALL_HEALING_POTION)
			.addData(new GenericData("&fSmall Healing Potion"));
	public static CustomItem SMALL_MANA_POTION = new CustomItem(Items.SMALL_MANA_POTION)
			.addData(new GenericData("&fSmall Mana Potion"));
	public static CustomItem MAGIC_LILY = new CustomItem(Items.MAGIC_LILY)
			.addData(new GenericData("&bMagic Lily"))
			.addData(new EnchantedData());

	public static boolean isCustomItem(ItemStack item) {
		if (item == null)
			return false;
		ItemMeta meta = item.getItemMeta();
		if (meta == null)
			return false;
		return meta.getPersistentDataContainer().has(CustomItem.ITEM);
	}

	public static CustomItem getItem(NamespacedKey key) {
		return getItem(key.getKey());
	}

	public static CustomItem getItem(String key) {
		if (!items.containsKey(key))
			return null;
		return items.get(key);
	}

	public static CustomItem toItem(ItemStack item) {
		if (item == null)
			return null;
		if (!isCustomItem(item))
			return null;

		ItemMeta meta = item.getItemMeta();
		if (meta == null)
			return null;
		String key = meta.getPersistentDataContainer().get(CustomItem.ITEM, PersistentDataType.STRING);
		return getItem(key);
	}

	private Items type;
	private List<ItemData> data;

	public CustomItem(Items type) {
		super();
		this.type = type;
		this.data = new ArrayList<ItemData>();
		data.add(type.getData());
		items.put(type.getKey().getKey(), this);
	}

	public ItemStack getItem() {
		KnightmaresReign plugin = KnightmaresReign.getInstance();

		ItemStack item = new ItemStack(type.getMaterial(), 1);
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(CustomItem.ITEM, PersistentDataType.STRING, type.getKey().getKey());
		if(getDataOfType(GenericData.class) != null)
			meta.displayName(plugin.toComponent(getDataOfType(GenericData.class).getName()));

		List<Component> components = new ArrayList<Component>();
		if (getDataOfType(ProjectileWeaponData.class) != null)
			components.add(
					plugin.toComponent("&2" + (int) getDataOfType(ProjectileWeaponData.class).getDamage() + " projectile damage."));
		else if (getDataOfType(WeaponData.class) != null)
			components.add(
					plugin.toComponent("&2" + (int) getDataOfType(WeaponData.class).getDamage() + " attack damage."));
		if (getDataOfType(DefenseData.class) != null)
			components
					.add(plugin.toComponent("&2" + (int) getDataOfType(DefenseData.class).getDefense() + " defense."));
		if(getDataOfType(EnchantedData.class) != null)
			meta.addEnchant(Enchantment.MENDING, 0, true);
		if(getDataOfType(PotionMaterialData.class) != null)
			((PotionMeta) meta).setBasePotionData(new PotionData(getDataOfType(PotionMaterialData.class).getPotionType()));
		meta.lore(components);

		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS,
				ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ITEM_SPECIFICS, ItemFlag.HIDE_ARMOR_TRIM);

		meta.setUnbreakable(true);

		item.setItemMeta(meta);
		return item;
	}

	public Items getType() {
		return type;
	}

	@SuppressWarnings("unchecked")
	public <T extends ItemData> T getDataOfType(Class<? extends T> klass) {
		ItemData a = this.data.stream().filter((data) -> klass.isAssignableFrom(data.getClass())).findAny()
				.orElse(null);
		return (T) a;
	}

	public CustomItem setType(Items type) {
		this.type = type;
		return this;
	}

	public List<ItemData> getData() {
		return data;
	}

	public CustomItem addData(ItemData data) {
		this.data.add(data);
		return this;
	}

	public CustomItem setData(List<ItemData> data) {
		this.data = data;
		return this;
	}

	public static HashMap<String, CustomItem> getHashMap() {
		// Added so can be Visualised
		return items;
	}

}
