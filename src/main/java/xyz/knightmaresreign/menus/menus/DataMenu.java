package xyz.knightmaresreign.menus.menus;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.menus.MenuManager;
import xyz.knightmaresreign.menus.shop.TestShopMenu;

public class DataMenu extends Menu {
    public DataMenu(Player player) {
        super(InventoryType.CHEST, "Data Menu", player);

        Double defense = KnightmaresReign.getInstance().playerManager.get(player).defense;
        String[] defenselst = {String.valueOf(defense)};

        this.addItem(MenuItem.makeItemStack(Material.DIAMOND_CHESTPLATE, "Defense", defenselst), 12, (Player plr) -> {
            player.sendMessage("Click on Defense");
        });

        Double strength = KnightmaresReign.getInstance().playerManager.get(player).strength;
        String[] strengthlst = {String.valueOf(strength)};

        this.addItem(MenuItem.makeItemStack(Material.IRON_SWORD, "Strength", strengthlst), 13, (Player plr) -> {
            player.openInventory(Bukkit.createInventory(null, InventoryType.ANVIL));
        });

        Double speed = KnightmaresReign.getInstance().playerManager.get(player).speed;
        String[] speedlst = {String.valueOf(speed)};

        this.addItem(MenuItem.makeItemStack(Material.RABBIT_FOOT, "Speed", speedlst), 14, (Player plr) -> {
            player.openInventory(Bukkit.createInventory(null, InventoryType.ANVIL));
        });

        this.addItem(MenuItem.makeItemStack(Material.EMERALD, "Shop", new String[]{}), 19, (Player plr) -> {
            MenuManager.OpenMenu(new TestShopMenu(player), player);
        });

        this.addItem(MenuItem.makeItemStack(Material.DIAMOND, "Currency", new String[]{}), 20, (Player plr) -> {
            MenuManager.OpenMenu(new CurrencyMenu(player), player);
        });

        this.addItem(MenuItem.makeItemStack(Material.BOOK, "OpenMenuHashMap", new String[]{}), 26, (Player plr) -> {
            HashMap<Object, Object> hashtable = new HashMap<>(MenuManager.getHashMap());
            MenuManager.OpenMenu(new HashMapRenderer(player, hashtable), player);
        });

        this.addItem(MenuItem.makeItemStack(Material.BOOK, "ItemsHashMap", new String[]{}), 25, (Player plr) -> {
            HashMap<Object, Object> hashtable = new HashMap<>(CustomItem.getHashMap());
            MenuManager.OpenMenu(new HashMapRenderer(player, hashtable), player);
        });



        for (int i = 0; i<27; i++) {
            if(this.menuItems.containsKey(i)) continue;
            this.addItem(MenuItem.makeItemStack(Material.GRAY_STAINED_GLASS_PANE, null, new String[]{}), i, (Player plr)->{});
        }
    }
}
