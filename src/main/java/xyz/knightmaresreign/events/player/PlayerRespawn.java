package xyz.knightmaresreign.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class PlayerRespawn extends CustomEvent {

	public PlayerRespawn() {
		super();
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		OnlinePlayerData.addPlayer(event.getPlayer());	
	}

}
