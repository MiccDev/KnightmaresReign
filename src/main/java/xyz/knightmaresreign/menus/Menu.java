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
import xyz.knightmaresreign.utils.PlayerRunnable;

public class Menu {
	public static int toInventoryPosition(int x, int y) {
		return y * 9 + x;
	}
	
	public static final int PAGE_1 = 0;
	public static final int PAGE_2 = 1;
	
    private final Inventory inventory;
    @Nullable
    protected Player player;
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

    public Menu(Integer inventorySize, @Nullable String name) {
        if (Objects.isNull(name)) {
            inventory = Bukkit.createInventory(null, inventorySize);
        } else {
            inventory = Bukkit.createInventory(null, inventorySize, Component.text(name));
        }
    }

    public Menu(Integer inventorySize, @Nullable String name, Player player) {
        if (Objects.isNull(name)) {
            inventory = Bukkit.createInventory(null, inventorySize);
        } else {
            inventory = Bukkit.createInventory(null, inventorySize, Component.text(name));
        }
        this.player = player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItem(ItemStack itemStack, Integer slot, @Nullable PlayerRunnable clickcallback) {
        MenuItem menuItem = new MenuItem(itemStack, slot, clickcallback);
        getInventory().setItem(slot, menuItem.getItemStack());
        menuItems.put(slot, menuItem);
    }

    public void addItem(MenuItem menuItem, Integer slot) {
        getInventory().setItem(slot, menuItem.getItemStack());
        menuItems.put(slot, menuItem);
    }

    public void clickOnItem(Integer slot, Player player, ItemStack item) {
        if(menuItems.containsKey(slot) && menuItems.get(slot).getItemStack().equals(item)) {
            menuItems.get(slot).Click(player);
        }
    }

    public boolean isPlayer(Player player) {
        if(Objects.isNull(this.player)) return true;
        return player.equals(this.player);
    }
}
