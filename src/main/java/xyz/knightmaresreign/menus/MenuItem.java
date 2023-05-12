package xyz.knightmaresreign.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import xyz.knightmaresreign.utils.PlayerRunnable;

public class MenuItem {
    private final ItemStack itemStack;
    private final Integer slot;
    private final PlayerRunnable clickcallback;

    public MenuItem(ItemStack itemStack, Integer slot, @Nullable PlayerRunnable clickcallback) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.clickcallback = clickcallback;
    }

    public void Click(Player player) {
        clickcallback.run(player);
    }

    public ItemStack getItemStack() {return itemStack;}
    public Integer getSlot() {return slot;}
    public PlayerRunnable getClickcallback() {return clickcallback;}




    public static ItemStack makeItemStack(Material material, @Nullable String name, String[] lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (!Objects.isNull(name)) {
            meta.displayName(Component.text(formatString(name)));
        }
        List<Component> lorelst = new ArrayList<>();
        for (String loreln : lore) {
            lorelst.add(Component.text(formatString(loreln)));
        }
        meta.lore(lorelst);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_UNBREAKABLE);

        item.setItemMeta(meta);

        return item;
    }

    private static String formatString(String string) {
        string = string.replace("&", "§");
        return string;
    }
}
