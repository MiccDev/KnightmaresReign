package xyz.knightmaresreign.menus.shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import org.bukkit.inventory.ItemStack;
import xyz.knightmaresreign.items.CustomItem;

public class TestShopMenu extends Shop {
    public TestShopMenu(Player player) {
        super(27, "TestShop", player);

        this.addPage(0, new ItemStack(Material.EMERALD), "Page1");

        this.addItem( 0, CustomItem.SENOR_BONKERS.getItem(), 13, () -> {
            player.getInventory().addItem(CustomItem.SENOR_BONKERS.getItem());
        }, 10);

        this.addPage(1, new ItemStack(Material.GOLD_INGOT), "Page2");

        this.addItem( 1, CustomItem.TEST_MAGIC_WEAPON.getItem(), 13, () -> {
            player.getInventory().addItem(CustomItem.SENOR_BONKERS.getItem());
        }, 10);

    }
}
