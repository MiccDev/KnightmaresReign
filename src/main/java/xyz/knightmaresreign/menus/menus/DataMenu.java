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
import xyz.knightmaresreign.menus.shop.TestShopMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class DataMenu extends Menu {
    public DataMenu(Player player) {
        super(InventoryType.CHEST, "Data Menu", player);

        Double defense = KnightmaresReign.getInstance().playerManager.getPlayerData(player).defense;
        String[] defenselst = {String.valueOf(defense)};

        this.addItem(MenuItem.makeItemStack(Material.DIAMOND_CHESTPLATE, "Defense", defenselst), 12, () -> {
            player.sendMessage("Click on Defense");
        });

        Double strength = KnightmaresReign.getInstance().playerManager.getPlayerData(player).strength;
        String[] strengthlst = {String.valueOf(defense)};

        this.addItem(MenuItem.makeItemStack(Material.IRON_SWORD, "Strength", strengthlst), 13, () -> {
            player.openInventory(Bukkit.createInventory(null, InventoryType.ANVIL));
        });

        Double speed = KnightmaresReign.getInstance().playerManager.getPlayerData(player).speed;
        String[] speedlst = {String.valueOf(speed)};

        this.addItem(MenuItem.makeItemStack(Material.RABBIT_FOOT, "Speed", speedlst), 14, () -> {
            player.openInventory(Bukkit.createInventory(null, InventoryType.ANVIL));
        });

        this.addItem(MenuItem.makeItemStack(Material.EMERALD, "Shop", new String[]{}), 19, () -> {
            MenuManager.OpenMenu(new TestShopMenu(player), player);
        });

        this.addItem(MenuItem.makeItemStack(Material.DIAMOND, "Currency", new String[]{}), 20, () -> {
            MenuManager.OpenMenu(new CurrencyMenu(player), player);
        });

        this.addItem(MenuItem.makeItemStack(Material.BOOK, "OpenMenuHashMap", new String[]{}), 26, () -> {
            HashMap<Object, Object> hashtable = new HashMap<>(MenuManager.getHashMap());
            MenuManager.OpenMenu(new HashMapRenderer(player, hashtable), player);
        });

        this.addItem(MenuItem.makeItemStack(Material.BOOK, "ItemsHashMap", new String[]{}), 25, () -> {
            HashMap<Object, Object> hashtable = new HashMap<>(CustomItem.getHashMap());
            MenuManager.OpenMenu(new HashMapRenderer(player, hashtable), player);
        });



        for (int i = 0; i<27; i++) {
            if(this.menuItems.containsKey(i)) continue;
            this.addItem(MenuItem.makeItemStack(Material.GRAY_STAINED_GLASS_PANE, null, new String[]{}), i, ()->{});
        }
    }
}
