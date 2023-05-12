package xyz.knightmaresreign.menus.shop;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.menus.MenuItem;
import xyz.knightmaresreign.utils.PlayerRunnable;

public class ShopItem extends MenuItem {

    public Integer cost;

    public ShopItem(ItemStack itemStack, Integer slot, @Nullable PlayerRunnable clickcallback, Integer cost) {
        super(itemStack, slot, clickcallback);
        ItemMeta meta = itemStack.getItemMeta();
        List<Component> lore = meta.lore();
        lore.add(Component.text("Cost: " + String.valueOf(cost)));
        meta.lore(lore);
        itemStack.setItemMeta(meta);

        this.cost = cost;
    }

    public void Click(Player player) {
        if(!KnightmaresReign.getInstance().playerManager.hasCurrency(player, cost)) return;
        KnightmaresReign.getInstance().playerManager.removeCurrency(player, cost);
        super.Click(player);
    }

}
