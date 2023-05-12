package xyz.knightmaresreign.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class PlayerEquipArmour extends CustomEvent {

	@EventHandler
	public void onEquipArmour(PlayerArmorChangeEvent event) {
		Player player = event.getPlayer();
		OnlinePlayerData data = OnlinePlayerData.getPlayer(player);
		if(data == null)
			data = OnlinePlayerData.addPlayer(player);
		data.setDefense(OnlinePlayerData.getDefense(player));
	}
	
}
