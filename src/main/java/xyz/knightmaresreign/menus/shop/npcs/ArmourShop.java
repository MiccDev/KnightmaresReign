package xyz.knightmaresreign.menus.shop.npcs;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.menus.shop.Shop;

public class ArmourShop extends Shop {

	public ArmourShop(Player player) {
		super(9 * 4, "Armour Shop", player);
		
		this.addPage(PAGE_1, "Page 1");
		this.addPage(PAGE_2, "Page 2");
		
		this.addItem(PAGE_1, CustomItem.PEASANT_HAT.getItem(), Menu.toInventoryPosition(0, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.PEASANT_HAT.getItem());
		}, 10);
		this.addItem(PAGE_1, CustomItem.PEASANT_SHIRT.getItem(), Menu.toInventoryPosition(1, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.PEASANT_SHIRT.getItem());
		}, 10);
		this.addItem(PAGE_1, CustomItem.PEASANT_PANTS.getItem(), Menu.toInventoryPosition(2, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.PEASANT_PANTS.getItem());
		}, 10);
		this.addItem(PAGE_1, CustomItem.PEASANT_SHOES.getItem(), Menu.toInventoryPosition(3, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.PEASANT_SHOES.getItem());
		}, 10);
		
		this.addItem(PAGE_1, CustomItem.WAR_HELMET.getItem(), Menu.toInventoryPosition(0, 1), (Player plr) -> {
			player.getInventory().addItem(CustomItem.WAR_HELMET.getItem());
		}, 25);
		this.addItem(PAGE_1, CustomItem.WAR_CHESTPLATE.getItem(), Menu.toInventoryPosition(1, 1), (Player plr) -> {
			player.getInventory().addItem(CustomItem.WAR_CHESTPLATE.getItem());
		}, 25);
		this.addItem(PAGE_1, CustomItem.WAR_LEGGINGS.getItem(), Menu.toInventoryPosition(2, 1), (Player plr) -> {
			player.getInventory().addItem(CustomItem.WAR_LEGGINGS.getItem());
		}, 25);
		this.addItem(PAGE_1, CustomItem.WAR_BOOTS.getItem(), Menu.toInventoryPosition(3, 1), (Player plr) -> {
			player.getInventory().addItem(CustomItem.WAR_BOOTS.getItem());
		}, 25);

		this.addNextPageIcon(Menu.toInventoryPosition(8, 3), MenuItem.makeItemStack(Material.ARROW, "Next Page", new String[]{}));
        this.addPreviousPageIcon(Menu.toInventoryPosition(0, 3), MenuItem.makeItemStack(Material.ARROW, "Previous Page", new String[]{}));
//        this.addItem(MenuItem.makeItemStack(Material.BARRIER, "Close", new String[] {}), Menu.toInventoryPosition(4, 3), (Player plr) -> {
//        	plr.closeInventory(Reason.PLUGIN);
//        });
	}
	
}
