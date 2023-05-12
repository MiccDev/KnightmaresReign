package xyz.knightmaresreign.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import net.kyori.adventure.text.Component;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.items.data.DefenseData;
import xyz.knightmaresreign.items.data.GenericData;
import xyz.knightmaresreign.items.data.ItemData;
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
		if (getDataOfType(WeaponData.class) != null)
			components.add(
					plugin.toComponent("&2" + (int) getDataOfType(WeaponData.class).getDamage() + " attack damage."));
		if (getDataOfType(DefenseData.class) != null)
			components
					.add(plugin.toComponent("&2" + (int) getDataOfType(DefenseData.class).getDefense() + " defense."));
		meta.lore(components);

		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS,
				ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_UNBREAKABLE);

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
