package xyz.knightmaresreign.menus.shop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.menus.MenuManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Shop extends Menu {

    private HashMap<Integer, ShopMenu> pages = new HashMap<>();
    private List<MenuItem> PageIcons = new ArrayList<>();

    private Integer inventorySize;
    private Integer openPage;

    public Shop(Integer inventorySize, @Nullable String name, Player player) {
        super(inventorySize, name, player);
        this.inventorySize = inventorySize;
        this.openPage = 0;
    }

    public Inventory getInventory() {
        return pages.get(openPage).getInventory();
    }

    public void addItem(Integer page, ItemStack itemStack, Integer slot, @Nullable Runnable clickcallback, Integer cost) {
        pages.get(page).addItem(itemStack, slot + 9, clickcallback, cost);
    }

    public void addPage(Integer page, ItemStack icon, String name) {
        pages.put(page, new ShopMenu(inventorySize + 9, name, this.player));
        PageIcons.add(new MenuItem(icon, PageIcons.size(), () -> {
            openPage(page);;
        }));
        for (int i = 0; i < PageIcons.size(); i++) {
            MenuItem menuItem = PageIcons.get(i);
            for (ShopMenu shopMenu : pages.values()) {
                shopMenu.addItem(menuItem, i);
            }
        }
    }

    public void clickOnItem(Integer slot, Player player) {
        pages.get(openPage).clickOnItem(slot, player);
    }

    public void openPage(Integer page) {
        if(openPage.equals(page)) return;
        openPage = page;
        MenuManager.OpenMenu(this, this.player);
    }
}
