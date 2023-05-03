package xyz.knightmaresreign.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class Menu {
    private Inventory inventory;
    private Hashtable<ItemStack, MenuItem> menuItems;

    public Menu(InventoryType inventoryType, @Nullable String name) {
        if (Objects.isNull(name)) {
            inventory = Bukkit.createInventory(null, inventoryType);
        } else {
            inventory = Bukkit.createInventory(null, inventoryType, Component.text(name));
        }

    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean addItem(ItemStack itemStack, Integer slot, @Nullable Runnable clickcallback) {
        //create a Menu Item class with this Data
        //return success
        return true;
    }

    public void clickOnItem(ItemStack itemStack) {
        // get item from menuItems and run the clickcallback
    }
}
