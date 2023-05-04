package xyz.knightmaresreign.menus;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.knightmaresreign.menus.menus.DataMenu;

import java.util.HashMap;

public class MenuManager {
    private static HashMap<Inventory, Menu> OpenMenuList= new HashMap<>();

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

    public static HashMap<Inventory, Menu> getHashMap() {
        return OpenMenuList;
    }

    public static void OpenMenu(Menu menu, Player player) {
        MenuManager.addMenuToOpenList(menu);
        player.openInventory(menu.getInventory());
    }
}
