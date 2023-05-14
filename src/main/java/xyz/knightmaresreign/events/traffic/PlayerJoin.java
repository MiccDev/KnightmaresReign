package xyz.knightmaresreign.events.traffic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.scoreboard.ScoreboardUtils;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class PlayerJoin extends CustomEvent {

	public PlayerJoin() {
		super();
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();	
	
		event.joinMessage(getPlugin().toComponent("&aWelcome " + player.getName() + " to the server!"));
		
		createTablist(player);
		createScoreboard(player);
		
		OnlinePlayerData.addPlayer(player);
		
		getPlugin().npcManager.showAllNPCs(player);
	}
	
	private void createTablist(Player player) {
		KnightmaresReign plugin = KnightmaresReign.getInstance();
		player.sendPlayerListHeader(plugin.toComponent(plugin.title + "\n&7------------------"));
		player.sendPlayerListFooter(plugin.toComponent("&7------------------\n" + "&7Players: &6" + Bukkit.getOnlinePlayers().size() + "&7/&6" + Bukkit.getMaxPlayers()));
	}
	
	public static void createScoreboard(Player player) {
		KnightmaresReign plugin = KnightmaresReign.getInstance();
		ScoreboardUtils scoreboard = new ScoreboardUtils("knightmaresreign", plugin.title);
		
		scoreboard.createScore("rank", "  &e| &7Rank: ");
		scoreboard.createScore("players", "  &e| &7Players: ");
		scoreboard.createScore("coins", "  &e| &7Coins: ");
		scoreboard.createScore("region", "  &e| &7Region: &6");
		scoreboard.createScore("  ", "  &e&oplay.knightmaresreign.xyz");
		
		scoreboard.finalizeScoreboard(player);
	}
	
}
