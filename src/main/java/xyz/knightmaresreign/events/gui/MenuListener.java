package xyz.knightmaresreign.events.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuManager;
import xyz.knightmaresreign.menus.menus.DataMenu;

public class MenuListener extends CustomEvent {

    @EventHandler
    public void onGUIClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        if (!MenuManager.isInventoryOpenMenu(inv)) return;
        if (!MenuManager.getMenuFromInventory(inv).isPlayer((Player) event.getWhoClicked())) return;
        Menu menu = MenuManager.getMenuFromInventory(inv);
        menu.clickOnItem(event.getSlot());
        event.setCancelled(true);
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        if (!MenuManager.isInventoryOpenMenu(inv)) return;
        if (!MenuManager.getMenuFromInventory(inv).isPlayer((Player) event.getPlayer())) return;
        MenuManager.removeInventoryFromOpenList(inv);
    }

    @EventHandler
    public void onBookClick(PlayerInteractEvent event) {
        if (event.hasItem() && event.getItem().equals(CustomItem.DATA_BOOK.getItem())){
            MenuManager.OpenMenu(new DataMenu(event.getPlayer()), event.getPlayer());
        }
    }
}
