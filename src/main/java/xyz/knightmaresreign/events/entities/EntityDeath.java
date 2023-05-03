package xyz.knightmaresreign.events.entities;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class EntityDeath extends CustomEvent {

	public EntityDeath() {
		super();
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		if(event.getEntity() instanceof Player) {
			onPlayerDeath(event, (Player) event.getEntity());
		}
	}
	
	public void onPlayerDeath(EntityDeathEvent event, Player player) {
		OnlinePlayerData.addPlayer(player);
	}
	
}
