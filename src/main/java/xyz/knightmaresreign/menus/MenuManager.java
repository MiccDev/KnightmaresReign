package xyz.knightmaresreign.menus;

import org.bukkit.inventory.Inventory;

import java.util.Hashtable;

public class MenuManager {
    private static Hashtable<Inventory, Menu> OpenMenuList= new Hashtable<>();

    public static Menu getMenuFromInventory(Inventory inventory) {
        return OpenMenuList.get(inventory);
    }

    public static boolean isInventoryOpenMenu(Inventory inventory) {
        return OpenMenuList.containsKey(inventory);
    }

    public static void addMenuToOpenList(Menu menu) {
        OpenMenuList.put(menu.getInventory(), menu);
    }

    public static void removeInventoryFromOpenList(Inventory inventory) {
        if (isInventoryOpenMenu(inventory)) {
            OpenMenuList.remove(inventory);
        }
    }
}
