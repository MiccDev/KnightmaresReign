package xyz.knightmaresreign.menus.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.Nullable;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.managers.CurrencyManager;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;

public class CurrencyMenu extends Menu {

    public CurrencyMenu(Player player) {
        super(InventoryType.CHEST, "Currency Menu", player);

        addItem(MenuItem.makeItemStack(Material.EMERALD_BLOCK, "Add 100", new String[]{}), 11, ()->{
            KnightmaresReign.getInstance().currencyManager.addCurrency(player, 100);
        });
        addItem(MenuItem.makeItemStack(Material.REDSTONE_BLOCK, "Remove 100", new String[]{}), 15, ()->{
            KnightmaresReign.getInstance().currencyManager.removeCurrency(player, 100);
        });
        addItem(MenuItem.makeItemStack(Material.EMERALD, "Add 10", new String[]{}), 12, ()->{
            KnightmaresReign.getInstance().currencyManager.addCurrency(player, 10);
        });
        addItem(MenuItem.makeItemStack(Material.REDSTONE, "Remove 10", new String[]{}), 14, ()->{
            KnightmaresReign.getInstance().currencyManager.removeCurrency(player, 10);
        });
        addItem(MenuItem.makeItemStack(Material.BARRIER, "Reset", new String[]{}), 13, () -> {
            KnightmaresReign.getInstance().currencyManager.setCurrency(player, 0);
        });

    }

}
