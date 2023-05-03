package xyz.knightmaresreign.events.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.menus.MenuManager;

public class MenuListener extends CustomEvent {

    @EventHandler
    public void onGUIClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        if (!MenuManager.isInventoryOpenMenu(inv)) return;
        //check if item has been clicked
        //get callback for that item and excecute
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        if (!MenuManager.isInventoryOpenMenu(inv)) return;
        MenuManager.removeInventoryFromOpenList(inv);
    }
}
