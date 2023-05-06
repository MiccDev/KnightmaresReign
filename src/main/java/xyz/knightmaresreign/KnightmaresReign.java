package xyz.knightmaresreign;

import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minecraft.network.chat.IChatBaseComponent;
import xyz.knightmaresreign.commands.CustomCommand;
import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.events.traffic.PlayerJoin;
import xyz.knightmaresreign.managers.CurrencyManager;
import xyz.knightmaresreign.managers.PlayerData;
import xyz.knightmaresreign.managers.PlayerManager;
import xyz.knightmaresreign.scoreboard.ScoreboardUtils;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class KnightmaresReign extends JavaPlugin {

	private static KnightmaresReign instance;

	public static void setInstance(KnightmaresReign instance) {
		KnightmaresReign.instance = instance;
	}

	public static KnightmaresReign getInstance() {
		return KnightmaresReign.instance;
	}

	public static final String KNIGHTMARES_REIGN = "knightmares_reign";
	
	public String name = "Knightmare's Reign";
	public String title = "&8[&4&lKnightmare's Reign&8]";
	public String version = "Beta 0.0.1";

	public CurrencyManager currencyManager;
	public PlayerManager playerManager;

	public PlainTextComponentSerializer plainSerializer = PlainTextComponentSerializer.plainText();

	@Override
	public void onEnable() {
		getLogger().info("Enabling with version: " + version);
		setInstance(this);

		saveDefaultConfig();
		currencyManager = new CurrencyManager();
		try {
			currencyManager.loadCurrencyFile();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		playerManager = new PlayerManager();
		try {
			playerManager.loadPlayerDataFile();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		CustomCommand.init();
		CustomEvent.init();

		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerJoin.createScoreboard(p);
		}

		new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					ScoreboardUtils scoreboard = ScoreboardUtils.scoreboards.get(p.getUniqueId());

					String rank = PlaceholderAPI.setPlaceholders(p, "%luckperms_prefix%");

					scoreboard.setSuffix("rank", rank);
					scoreboard.setSuffix("players",
							"&6" + Bukkit.getOnlinePlayers().size() + "&7/&6" + Bukkit.getMaxPlayers());
					scoreboard.setSuffix("coins", "&6" + currencyManager.getCurrency(p));

					p.sendPlayerListFooter(toComponent("&7------------------\n" + "&7Players: &6"
							+ Bukkit.getOnlinePlayers().size() + "&7/&6" + Bukkit.getMaxPlayers()));

					PlayerData data = playerManager.getPlayerData(p);
					OnlinePlayerData plrData = OnlinePlayerData.getPlayer(p);
					if (plrData == null)
						plrData = OnlinePlayerData.addPlayer(p);

					p.sendActionBar(toComponent("&cHealth ♥: " + (int) plrData.getHealth() + "/" + (int) data.health
							+ "   &9Mana: " + (int) plrData.getMana() + "/" + (int) data.mana));
					p.setFoodLevel(20);
					p.setSaturation(20);
				}
			}
		}.runTaskTimer(this, 0L, 5L);
	}

	@Override
	public void onDisable() {
		getLogger().info("Disabling version.");

		try {
			currencyManager.saveCurrencyFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			playerManager.savePlayerDataFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isInRegion(Location source, Location bound1, Location bound2) {
		return source.getX() >= Math.min(bound1.getX(), bound2.getX())
				&& source.getY() >= Math.min(bound1.getY(), bound2.getY())
				&& source.getZ() >= Math.min(bound1.getZ(), bound2.getZ())
				&& source.getX() <= Math.max(bound1.getX(), bound2.getX())
				&& source.getY() <= Math.max(bound1.getY(), bound2.getY())
				&& source.getZ() <= Math.max(bound1.getZ(), bound2.getZ());
	}

	public int getRandom(int from, int to) {
		Random random = new Random();
		if (from < to)
			return from + random.nextInt(Math.abs(to - from));
		return from - random.nextInt(Math.abs(to - from));
	}

	public String toColour(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	public Component toComponent(String text) {
		return Component.text(toColour(text));
	}
	
	public net.minecraft.network.chat.IChatBaseComponent toMcComponent(String text) {
		return IChatBaseComponent.ChatSerializer.a(toColour(text));
	}

}
