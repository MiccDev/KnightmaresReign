package xyz.knightmaresreign.menus.menus;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.utils.PlayerRunnable;

public class HashMapRenderer extends Menu {

    public HashMapRenderer(Player player, HashMap<Object, Object> hashtable) {
        super(InventoryType.CHEST, "HashMap", player);

        int i = 0;
        for (Object key : hashtable.keySet()) {
            String[] list = {String.valueOf(key), String.valueOf(hashtable.get(key))};

            PlayerRunnable run;
            Material material = Material.PAPER;
            if (hashtable.get(key) instanceof Menu) {
                run = (Player plr) -> {
                    Inventory inventory = (Inventory) key;
                    player.openInventory(inventory);
                };
            } else {
                run = (Player plr) -> {};
            }

            this.addItem(MenuItem.makeItemStack(material, String.valueOf(i), list), i, run);
            i++;
        }

    }
}
