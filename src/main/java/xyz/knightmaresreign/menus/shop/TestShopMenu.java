package xyz.knightmaresreign.menus.shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.menus.MenuItem;

public class TestShopMenu extends Shop {
    public TestShopMenu(Player player) {
        //Initialize the Shop
        super(27, "TestShop", player);

        this.addPage(0, MenuItem.makeItemStack(Material.EMERALD, "&7&lPage &3&l1", new String[]{}), "Page1");

        this.addItem( 0, CustomItem.SENOR_BONKERS.getItem(), 13, () -> {
            player.getInventory().addItem(CustomItem.SENOR_BONKERS.getItem());
        }, 10);

        this.addPage(1, MenuItem.makeItemStack(Material.GOLD_INGOT, "&7&lPage &9&l2", new String[]{}), "Page2");

        this.addItem( 1, CustomItem.TEST_MAGIC_WEAPON.getItem(), 13, () -> {
            player.getInventory().addItem(CustomItem.SENOR_BONKERS.getItem());
        }, 10);

    }
}
