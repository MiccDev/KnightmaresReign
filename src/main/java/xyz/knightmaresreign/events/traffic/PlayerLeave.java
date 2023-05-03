package xyz.knightmaresreign.events.traffic;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import xyz.knightmaresreign.events.CustomEvent;

public class PlayerLeave extends CustomEvent {

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.quitMessage(getPlugin().toComponent("&7Good-bye " + player.getName() + "! Thank you for playing."));
	}
	
}
