package xyz.knightmaresreign.menus.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;

import java.util.HashMap;

public class HashMapRenderer extends Menu {

    public HashMapRenderer(Player player, HashMap<Object, Object> hashtable) {
        super(InventoryType.CHEST, "HashMap", player);

        int i = 0;
        for (Object key : hashtable.keySet()) {
            String[] list = {String.valueOf(key), String.valueOf(hashtable.get(key))};

            Runnable run;
            Material material = Material.PAPER;
            if (hashtable.get(key) instanceof Menu) {
                run = () -> {
                    Inventory inventory = (Inventory) key;
                    player.openInventory(inventory);
                };
            } else {
                run = () -> {};
            }

            this.addItem(MenuItem.makeItemStack(Material.PAPER, String.valueOf(i), list), i, run);
            i++;
        }

    }
}
