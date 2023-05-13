package xyz.knightmaresreign.menus.shop.npcs;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.menus.shop.Shop;

public class WeaponShop extends Shop {
	
	public WeaponShop(Player player) {
		super(9 * 4, "Weapon Shop", player);
		
		this.addPage(PAGE_1, "Page 1");
		this.addPage(PAGE_2, "Page 2");
		
		this.addItem(PAGE_1, CustomItem.PEASANTS_SWORD.getItem(), Menu.toInventoryPosition(0, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.PEASANTS_SWORD.getItem());
		}, 10);
		this.addItem(PAGE_1, CustomItem.WAR_LONGSWORD.getItem(), Menu.toInventoryPosition(1, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.WAR_LONGSWORD.getItem());
		}, 30);
		this.addItem(PAGE_1, CustomItem.FARMERS_SICKLE.getItem(), Menu.toInventoryPosition(2, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.FARMERS_SICKLE.getItem());
		}, 40);

		this.addItem(PAGE_1, CustomItem.PEASANTS_HATCHET.getItem(), Menu.toInventoryPosition(0, 2), (Player plr) -> {
			player.getInventory().addItem(CustomItem.PEASANTS_HATCHET.getItem());
		}, 20);
		this.addItem(PAGE_1, CustomItem.WAR_HATCHET.getItem(), Menu.toInventoryPosition(1, 2), (Player plr) -> {
			player.getInventory().addItem(CustomItem.WAR_HATCHET.getItem());
		}, 35);

		this.addNextPageIcon(Menu.toInventoryPosition(8, 3), MenuItem.makeItemStack(Material.ARROW, "Next Page", new String[]{}));
        this.addPreviousPageIcon(Menu.toInventoryPosition(0, 3), MenuItem.makeItemStack(Material.ARROW, "Previous Page", new String[]{}));
//        this.addItem(MenuItem.makeItemStack(Material.BARRIER, "Close", new String[] {}), Menu.toInventoryPosition(4, 3), (Player plr) -> {
//        	plr.closeInventory();
//        });
	}
	
}