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
import xyz.knightmaresreign.items.data.ItemData;
import xyz.knightmaresreign.items.data.WeaponData;

public class CustomItem {
	public static NamespacedKey ITEM = new NamespacedKey(KnightmaresReign.KNIGHTMARES_REIGN, "item");
	public static HashMap<String, CustomItem> items = new HashMap<String, CustomItem>();
	
	public static CustomItem TEST_SWORD = new CustomItem(Items.TEST_SWORD)
			.setData(new WeaponData(100).setName("&7Test Sword"));
	public static CustomItem TEST_MAGIC_WEAPON = new CustomItem(Items.MAGIC_WEAPON)
			.setData(new WeaponData(50, 10).setName("&7Magic Weapon"));
	public static CustomItem SENOR_BONKERS = new CustomItem(Items.SENOR_BONKERS)
			.setData(new WeaponData(250000).setName("&o&bSeñor Bonkers"));
	public static CustomItem DATA_BOOK = new CustomItem(Items.DATA_BOOK)
			.setData(new ItemData().setName("&eData Book"));
	
	
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
	private ItemData data;

	public CustomItem(Items type) {
		super();
		this.type = type;
		this.data = type.getData();
		items.put(type.getKey().getKey(), this);
	}

	public ItemStack getItem() {
		KnightmaresReign plugin = KnightmaresReign.getInstance();

		ItemStack item = new ItemStack(type.getMaterial(), 1);
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(CustomItem.ITEM, PersistentDataType.STRING, type.getKey().getKey());
		meta.displayName(plugin.toComponent(data.getName()));

		List<Component> components = new ArrayList<Component>();
		if (data instanceof WeaponData)
			components.add(plugin.toComponent("&2" + (int) ((WeaponData) data).getDamage() + " attack damage."));
		meta.lore(components);

		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS,
				ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

		meta.setUnbreakable(true);

		item.setItemMeta(meta);
		return item;
	}

	public Items getType() {
		return type;
	}

	public CustomItem setType(Items type) {
		this.type = type;
		return this;
	}

	public ItemData getData() {
		return data;
	}

	public CustomItem setData(ItemData data) {
		this.data = data;
		return this;
	}

}
