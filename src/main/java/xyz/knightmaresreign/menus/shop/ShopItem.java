package xyz.knightmaresreign.menus.shop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.menus.MenuItem;

public class ShopItem extends MenuItem {

    public Integer cost;

    public ShopItem(ItemStack itemStack, Integer slot, @Nullable Runnable clickcallback, Integer cost) {
        super(itemStack, slot, clickcallback);
        this.cost = cost;
    }

    public void Click(Player player) {
        KnightmaresReign.getInstance().currencyManager.removeCurrency(player, cost);
        super.Click(player);
    }

}
