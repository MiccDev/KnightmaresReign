package xyz.knightmaresreign.menus.shop;

import java.util.Collections;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import xyz.knightmaresreign.menus.Menu;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.menus.MenuManager;
import xyz.knightmaresreign.utils.PlayerRunnable;

public class Shop extends Menu {
	
    private HashMap<Integer, ShopMenu> pages = new HashMap<>();
    private HashMap<Integer, MenuItem> PageIcons = new HashMap<>();

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

    public void addItem(Integer page, ItemStack itemStack, Integer slot, @Nullable PlayerRunnable clickcallback, Integer cost) {
        pages.get(page).addItem(itemStack, slot, clickcallback, cost);
    }

    public void addNextPageIcon(Integer slot, ItemStack itemStack) {
        PageIcons.put(slot, new MenuItem(itemStack, slot, (Player player) -> {
            Integer nextpage = openPage + 1;
            if (pages.containsKey(nextpage)){
                openPage(nextpage);
            } else {
                openPage(0);
            }
        }));
        syncPageIcons();
    }

    public void addPreviousPageIcon(Integer slot, ItemStack itemStack) {
        PageIcons.put(slot, new MenuItem(itemStack, slot, (Player player) -> {
            Integer nextpage = openPage - 1;
            if (pages.containsKey(nextpage)){
                openPage(nextpage);
            } else {
                openPage(Collections.max(pages.keySet()));
            }
        }));
        syncPageIcons();
    }

    public void addPage(Integer page, Integer slot, ItemStack icon, String name) {
        PageIcons.put(slot, new MenuItem(icon, PageIcons.size(), (Player player) -> {
            openPage(page);;
        }));
        addPage(page, name);
    }

    public void addPage(Integer page, String name) {
        pages.put(page, new ShopMenu(inventorySize, name, this.player));
        syncPageIcons();
    }

    private void syncPageIcons(){
        for (Integer i : PageIcons.keySet()) {
            MenuItem menuItem = PageIcons.get(i);
            for (ShopMenu shopMenu : pages.values()) {
                shopMenu.addItem(menuItem, i);
            }
        }
    }

    public void addPage(Integer page, ItemStack icon, String name) {
        this.addPage(page, page, icon, name);
    }

    public void clickOnItem(Integer slot, Player player, ItemStack item) {
        pages.get(openPage).clickOnItem(slot, player, item);
    }

    public void openPage(Integer page) {
        if(openPage.equals(page)) return;
        openPage = page;
        MenuManager.OpenMenu(this, this.player);
    }
}
