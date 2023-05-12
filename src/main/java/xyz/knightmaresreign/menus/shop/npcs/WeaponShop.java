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
		
		this.addItem(PAGE_1, CustomItem.TEST_MAGIC_WEAPON.getItem(), Menu.toInventoryPosition(0, 0), (Player plr) -> {
			player.getInventory().addItem(CustomItem.TEST_MAGIC_WEAPON.getItem());
		}, 10);
		
		this.addNextPageIcon(Menu.toInventoryPosition(0, 3), MenuItem.makeItemStack(Material.ARROW, "Next Page", new String[]{}));
        this.addPreviousPageIcon(Menu.toInventoryPosition(8, 3), MenuItem.makeItemStack(Material.ARROW, "Previous Page", new String[]{}));
        this.addItem(MenuItem.makeItemStack(Material.BARRIER, "Close", new String[] {}), Menu.toInventoryPosition(4, 3), (Player plr) -> {
        	plr.closeInventory();
        });
	}
	
}