package xyz.knightmaresreign.menus.shop;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.utils.PlayerRunnable;

public class ShopMenu extends Menu {

    private ItemStack Icon;

    public ShopMenu(InventoryType inventoryType, @Nullable String name, Player player) {
        super(inventoryType, name, player);
    }

    public ShopMenu(InventoryType inventoryType, @Nullable String name, Player player, ItemStack icon) {
        super(inventoryType, name, player);
        this.Icon = icon;
    }

    public ShopMenu(Integer inventorySize, @Nullable String name, Player player) {
        super(inventorySize, name, player);
    }

    public ShopMenu(Integer inventorySize, @Nullable String name, Player player, ItemStack icon) {
        super(inventorySize, name, player);
        this.Icon = icon;
    }

    public void addItem(ItemStack itemStack, Integer slot, @Nullable PlayerRunnable clickcallback, Integer cost) {
        ShopItem shopItem = new ShopItem(itemStack, slot, clickcallback, cost);
        getInventory().setItem(slot, shopItem.getItemStack());
        menuItems.put(slot, shopItem);
    }

    public ItemStack Icon() {
        return Icon;
    }
}
