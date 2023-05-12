package xyz.knightmaresreign.events.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.projectile.Projectile;
import xyz.knightmaresreign.projectile.data.DamageProjectileData;
import xyz.knightmaresreign.projectile.data.ProjectileData;
import xyz.knightmaresreign.sound.SoundData;

public class PlayerUseProjectileWeapon extends CustomEvent {

	@EventHandler
	public void rightClick(PlayerInteractEvent event) {
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
		if (item.getType().equals(Material.NETHERITE_SWORD)) {
			Location location = event.getPlayer().getLocation().clone();
			ProjectileData data = new DamageProjectileData().setDamage(25.0)
					.setCollideParticle(Particle.EXPLOSION_HUGE)
					.setTrailParticle(Particle.WAX_ON).setMaxDistance(50)
					.setSoundData(new SoundData().setSound(Sound.ENTITY_GENERIC_EXPLODE).setPitch(1).setVolume(0.5f));
			new Projectile(event.getPlayer(), location.getDirection(), location.add(0, 1.6, 0), 1, data);
		}
	}
}
