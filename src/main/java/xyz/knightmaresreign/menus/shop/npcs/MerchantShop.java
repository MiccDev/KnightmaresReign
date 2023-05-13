package xyz.knightmaresreign.menus.shop.npcs;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.menus.shop.Shop;

public class MerchantShop extends Shop {

	public MerchantShop(Player player) {
		super(9 * 4, "Kaden's Shop", player);
		
		this.addPage(PAGE_1, "Page 1");
		this.addPage(PAGE_2, "Page 2");
		
		this.addItem(PAGE_1, CustomItem.SMALL_HEALING_POTION.getItem(), Menu.toInventoryPosition(0, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.SMALL_HEALING_POTION.getItem());
		}, 10);
		this.addItem(PAGE_1, CustomItem.SMALL_MANA_POTION.getItem(), Menu.toInventoryPosition(1, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.SMALL_MANA_POTION.getItem());
		}, 30);
		this.addItem(PAGE_1, CustomItem.MAGIC_LILY.getItem(), Menu.toInventoryPosition(0, 2), (Player plr) -> {
			player.getInventory().addItem(CustomItem.MAGIC_LILY.getItem());
		}, 40);
		
		this.addNextPageIcon(Menu.toInventoryPosition(8, 3), MenuItem.makeItemStack(Material.ARROW, "Next Page", new String[]{}));
        this.addPreviousPageIcon(Menu.toInventoryPosition(0, 3), MenuItem.makeItemStack(Material.ARROW, "Previous Page", new String[]{}));
//        this.addItem(MenuItem.makeItemStack(Material.BARRIER, "Close", new String[] {}), Menu.toInventoryPosition(4, 3), (Player plr) -> {
//        	plr.closeInventory();
//        });
	}
	
}
