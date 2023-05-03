package xyz.knightmaresreign.events.entities;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class EntityDamage extends CustomEvent {

	public EntityDamage() {
		super();
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			onPlayerDamage(event, (Player) event.getEntity());
		}
	}
	
	public void onPlayerDamage(EntityDamageEvent event, Player player) {
		OnlinePlayerData plrData = OnlinePlayerData.getPlayer(player);
		if(plrData == null) 
			plrData = OnlinePlayerData.addPlayer(player);
		
		double damage = event.getDamage();
		if(player.getFallDistance() > 3) {
			damage = 3 * Math.round(player.getFallDistance()) - 9;
		}
		
		event.setDamage(0);
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		plrData.damage(damage);
	}
	
}
