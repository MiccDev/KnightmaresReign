package xyz.knightmaresreign.events.player;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.projectile.Projectile;

public class PlayerUseProjectileWeapon extends CustomEvent {

    @EventHandler
    public void rightClick(PlayerInteractEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if(item.getType().equals(Material.NETHERITE_SWORD)) {
            new Projectile(event.getPlayer().getLocation().add(0, 1.6, 0), event.getPlayer().getLocation().getDirection(), 1, 5, 50, 100.0, Particle.WAX_ON, Particle.EXPLOSION_HUGE);
        }
    }
}
