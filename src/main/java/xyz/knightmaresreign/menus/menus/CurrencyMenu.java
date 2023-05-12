package xyz.knightmaresreign.menus.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;

public class CurrencyMenu extends Menu {

    public CurrencyMenu(Player player) {
        super(InventoryType.CHEST, "Currency Menu", player);

        addItem(MenuItem.makeItemStack(Material.EMERALD_BLOCK, "Add 100", new String[]{}), 11, (Player plr)->{
            KnightmaresReign.getInstance().playerManager.addCurrency(player, 100);
        });
        addItem(MenuItem.makeItemStack(Material.REDSTONE_BLOCK, "Remove 100", new String[]{}), 15, (Player plr)->{
            KnightmaresReign.getInstance().playerManager.removeCurrency(player, 100);
        });
        addItem(MenuItem.makeItemStack(Material.EMERALD, "Add 10", new String[]{}), 12, (Player plr)->{
            KnightmaresReign.getInstance().playerManager.addCurrency(player, 10);
        });
        addItem(MenuItem.makeItemStack(Material.REDSTONE, "Remove 10", new String[]{}), 14, (Player plr)->{
            KnightmaresReign.getInstance().playerManager.removeCurrency(player, 10);
        });
        addItem(MenuItem.makeItemStack(Material.BARRIER, "Reset", new String[]{}), 13, (Player plr) -> {
            KnightmaresReign.getInstance().playerManager.setCurrency(player, 0);
        });
        addItem(MenuItem.makeItemStack(Material.PAPER, "Custom...", new String[]{}), 22, (Player plr) -> {
            KnightmaresReign.getInstance().playerManager.setCurrency(player, 0);
        });
        
    }

}
