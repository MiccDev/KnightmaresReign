package xyz.knightmaresreign.events.entities;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.menus.MenuManager;
import xyz.knightmaresreign.menus.shop.TestShopMenu;
import xyz.knightmaresreign.utils.PlayerRunnable;

public class ShopEntity extends CustomEvent {
    private HashMap<Entity, PlayerRunnable> shopEntities = new HashMap<>();

    public ShopEntity() {
        shopEntities.put(Bukkit.getEntity(UUID.fromString("fc534ab5-ab5b-40ba-9c16-0888b5842289")), (Player player) -> {
            MenuManager.OpenMenu(new TestShopMenu(player), player);
        });
    }

    @EventHandler
    public void onShopOpen(PlayerInteractEntityEvent event) {
        if(shopEntities.containsKey(event.getRightClicked())) {
            shopEntities.get(event.getRightClicked()).run(event.getPlayer());
            event.setCancelled(true);
        }
    }


}
