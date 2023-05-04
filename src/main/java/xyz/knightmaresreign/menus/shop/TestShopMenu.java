package xyz.knightmaresreign.menus.shop;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import xyz.knightmaresreign.items.CustomItem;

public class TestShopMenu extends ShopMenu{
    public TestShopMenu(Player player) {
        super(InventoryType.CHEST, "TestShop", player);

        this.addItem(CustomItem.SENOR_BONKERS.getItem(), 13, () -> {
            player.getInventory().addItem(CustomItem.SENOR_BONKERS.getItem());
        }, 10);

    }
}
