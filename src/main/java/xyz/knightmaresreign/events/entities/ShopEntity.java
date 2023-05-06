package xyz.knightmaresreign.events.entities;

import it.unimi.dsi.fastutil.Hash;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.menus.MenuManager;
import xyz.knightmaresreign.menus.shop.Shop;
import xyz.knightmaresreign.menus.shop.TestShopMenu;

import java.util.HashMap;
import java.util.UUID;

public class ShopEntity extends CustomEvent {

    private HashMap<Entity, Runnable> shopEntities = new HashMap<>();
    private static Player player;

    public ShopEntity() {
        shopEntities.put(Bukkit.getEntity(UUID.fromString("fc534ab5-ab5b-40ba-9c16-0888b5842289")), () -> {
            MenuManager.OpenMenu(new TestShopMenu(player), player);
        });
    }

    @EventHandler
    public void onShopOpen(PlayerInteractEntityEvent event) {
        player = event.getPlayer();
        if(shopEntities.containsKey(event.getRightClicked())) {
            shopEntities.get(event.getRightClicked()).run();
        }
    }


}
