package xyz.knightmaresreign.menus.shop.npcs;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.menus.shop.Shop;

public class WeaponShop extends Shop {

	private int PAGE_1 = 0;
	private int PAGE_2 = 1;
	
	public WeaponShop(Player player) {
		super(9 * 3, "Weapon Shop", player);
		
		this.addPage(PAGE_1, MenuItem.makeItemStack(Material.PAPER, "Page 1", new String[] {}), "Page1");
		this.addPage(PAGE_2, MenuItem.makeItemStack(Material.PAPER, "Page 2", new String[] {}), "Page2");
		
		this.addItem(PAGE_1, CustomItem.TEST_MAGIC_WEAPON.getItem(), 0, () -> {
			player.getInventory().addItem(CustomItem.TEST_MAGIC_WEAPON.getItem());
		}, 10);
	}
	
}