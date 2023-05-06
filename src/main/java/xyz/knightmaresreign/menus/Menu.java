package xyz.knightmaresreign.menus;

import java.util.Hashtable;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;

public class Menu {
    private final Inventory inventory;
    private @Nullable Player player;
    protected final Hashtable<Integer, MenuItem> menuItems = new Hashtable<>();

    public Menu(InventoryType inventoryType, @Nullable String name) {
        if (Objects.isNull(name)) {
            inventory = Bukkit.createInventory(null, inventoryType);
        } else {
            inventory = Bukkit.createInventory(null, inventoryType, Component.text(name));
        }
    }

    public Menu(InventoryType inventoryType, @Nullable String name, Player player) {
        if (Objects.isNull(name)) {
            inventory = Bukkit.createInventory(null, inventoryType);
        } else {
            inventory = Bukkit.createInventory(null, inventoryType, Component.text(name));
        }
        this.player = player;

    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItem(ItemStack itemStack, Integer slot, @Nullable Runnable clickcallback) {
        MenuItem menuItem = new MenuItem(itemStack, slot, clickcallback);
        getInventory().setItem(slot, menuItem.getItemStack());
        menuItems.put(slot, menuItem);
    }

    public void clickOnItem(Integer slot, Player player) {
        if(menuItems.containsKey(slot)) {
            menuItems.get(slot).Click(player);
        }
    }



    public static Menu DataMenu(Player player) {
        Menu menu = new Menu(InventoryType.CHEST, "Data Menu");

        return menu;
    }

    public boolean isPlayer(Player player) {
        if(Objects.isNull(this.player)) return true;
        return player.equals(this.player);
    }
}
