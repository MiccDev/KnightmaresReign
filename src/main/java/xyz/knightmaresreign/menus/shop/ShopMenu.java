package xyz.knightmaresreign.menus.shop;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;

public class ShopMenu extends Menu {

    public ShopMenu(InventoryType inventoryType, @Nullable String name, Player player) {
        super(inventoryType, name, player);
    }

    public void addItem(ItemStack itemStack, Integer slot, @Nullable Runnable clickcallback, Integer cost) {
        ShopItem shopItem = new ShopItem(itemStack, slot, clickcallback, cost);
        getInventory().setItem(slot, shopItem.getItemStack());
        menuItems.put(slot, shopItem);
    }
}
