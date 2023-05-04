package xyz.knightmaresreign.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private final ItemStack itemStack;
    private final Integer slot;
    private final Runnable clickcallback;

    public MenuItem(ItemStack itemStack, Integer slot, @Nullable Runnable clickcallback) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.clickcallback = clickcallback;
    }

    public void Click() {
        clickcallback.run();
    }

    public ItemStack getItemStack() {return itemStack;}
    public Integer getSlot() {return slot;}
    public Runnable getClickcallback() {return clickcallback;}




    public static ItemStack makeItemStack(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));
        List<Component> lorelst = new ArrayList<>();
        for (String loreln : lore) {
            lorelst.add(Component.text(loreln));
        }
        meta.lore(lorelst);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

        item.setItemMeta(meta);

        return item;
    }
}
