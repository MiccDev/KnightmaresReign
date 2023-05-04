package xyz.knightmaresreign.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.managers.PlayerManager;

import java.util.*;
import java.util.concurrent.Callable;

public class Menu {
    private final Inventory inventory;
    private final Hashtable<Integer, MenuItem> menuItems = new Hashtable<>();

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

    public void addItem(ItemStack itemStack, Integer slot, @Nullable Runnable clickcallback) {
        MenuItem menuItem = new MenuItem(itemStack, slot, clickcallback);
        getInventory().setItem(slot, menuItem.getItemStack());
        menuItems.put(slot, menuItem);
    }

    public void clickOnItem(Integer slot) {
        menuItems.get(slot).Click();
    }



    public static Menu DataMenu(Player player) {
        Menu menu = new Menu(InventoryType.CHEST, "Data Menu");

        Double defense = KnightmaresReign.getInstance().playerManager.getPlayerData(player).defense;
        List<String> defenselst = new ArrayList<>();
        defenselst.add(String.valueOf(defense));

        menu.addItem(MenuItem.makeItemStack(Material.DIAMOND_CHESTPLATE, "Defense", defenselst), 0, () -> {
            player.sendMessage("Click on Defense");
        });

        Double strength = KnightmaresReign.getInstance().playerManager.getPlayerData(player).strength;
        List<String> strengthlst = new ArrayList<>();
        strengthlst.add(String.valueOf(strength));

        menu.addItem(MenuItem.makeItemStack(Material.IRON_SWORD, "Strength", strengthlst), 1, () -> {
            player.openInventory(Bukkit.createInventory(null, InventoryType.ANVIL));
        });

        return menu;
    }
}
