package xyz.knightmaresreign.menus.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.Nullable;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.menus.MenuManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class DataMenu extends Menu {
    public DataMenu(Player player) {
        super(InventoryType.CHEST, "Data Menu", player);

        Double defense = KnightmaresReign.getInstance().playerManager.getPlayerData(player).defense;
        String[] defenselst = {String.valueOf(defense)};

        this.addItem(MenuItem.makeItemStack(Material.DIAMOND_CHESTPLATE, "Defense", defenselst), 0, () -> {
            player.sendMessage("Click on Defense");
        });

        Double strength = KnightmaresReign.getInstance().playerManager.getPlayerData(player).strength;
        String[] strengthlst = {String.valueOf(defense)};

        this.addItem(MenuItem.makeItemStack(Material.IRON_SWORD, "Strength", strengthlst), 1, () -> {
            player.openInventory(Bukkit.createInventory(null, InventoryType.ANVIL));
        });

        this.addItem(MenuItem.makeItemStack(Material.BOOK, "OpenMenuHashMap", new String[]{}), 26, () -> {
            HashMap<Object, Object> hashtable = new HashMap<>(MenuManager.getHashMap());
            MenuManager.OpenMenu(new HashMapRenderer(player, hashtable), player);
        });

        this.addItem(MenuItem.makeItemStack(Material.BOOK, "ItemsHashMap", new String[]{}), 25, () -> {
            HashMap<Object, Object> hashtable = new HashMap<>(CustomItem.getHashMap());
            MenuManager.OpenMenu(new HashMapRenderer(player, hashtable), player);
        });
    }
}
