package xyz.knightmaresreign.events;

import org.bukkit.event.Listener;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.events.entities.EntityDamage;
import xyz.knightmaresreign.events.entities.EntityDamageEntity;
import xyz.knightmaresreign.events.entities.EntityDeath;
import xyz.knightmaresreign.events.entities.ShopEntity;
import xyz.knightmaresreign.events.gui.MenuListener;
import xyz.knightmaresreign.events.player.PlayerRespawn;
import xyz.knightmaresreign.events.traffic.PlayerJoin;
import xyz.knightmaresreign.events.traffic.PlayerLeave;

public class CustomEvent implements Listener {

	public static void init() {
		new PlayerJoin();
		new PlayerLeave();
		new PlayerRespawn();
		
		new EntityDamage();
		new EntityDeath();
		new EntityDamageEntity();

		new MenuListener();
		new ShopEntity();
	}
	
	private KnightmaresReign plugin;
	
	public CustomEvent() {
		this.plugin = KnightmaresReign.getInstance();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public KnightmaresReign getPlugin() {
		return plugin;
	}
	
}
