package xyz.knightmaresreign;

import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minecraft.network.chat.IChatBaseComponent;
import xyz.knightmaresreign.commands.CustomCommand;
import xyz.knightmaresreign.entities.Entities;
import xyz.knightmaresreign.entities.npc.NPCManager;
import xyz.knightmaresreign.entities.spawning.SpawnManager;
import xyz.knightmaresreign.entities.spawning.SpawnNode;
import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.events.traffic.PlayerJoin;
import xyz.knightmaresreign.managers.PlayerData;
import xyz.knightmaresreign.managers.PlayerManager;
import xyz.knightmaresreign.regions.Region;
import xyz.knightmaresreign.scoreboard.ScoreboardUtils;
import xyz.knightmaresreign.stats.OnlinePlayerData;
import xyz.knightmaresreign.utils.ColourUtils;

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

	public PlayerManager playerManager;
	public NPCManager npcManager;
	public SpawnManager spawnManager;

	private ProtocolManager protocolManager;

	public PlainTextComponentSerializer plainSerializer = PlainTextComponentSerializer.plainText();

	@Override
	public void onEnable() {
		getLogger().info("Enabling with version: " + version);
		setInstance(this);

		protocolManager = ProtocolLibrary.getProtocolManager();

		saveDefaultConfig();

		playerManager = new PlayerManager();
		try {
			playerManager.load();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		npcManager = new NPCManager();
		npcManager.spawnAllNPCs();

		World world = Bukkit.getWorld("openworld");

		spawnManager = new SpawnManager();
		SpawnManager.addSpawnNodes(Region.SPAWN, new SpawnNode(new Location(world, 66, 53, 37), 50, 10, Entities.COW));
		SpawnManager.addSpawnNodes(Region.FOREST, new SpawnNode(new Location(world, 0, 42, -60), 50, 10, Entities.COW),
				new SpawnNode(new Location(world, -23, 43, -103), 50, 10, Entities.BEE, Entities.COW),
				new SpawnNode(new Location(world, 106, 57, -117), 50, 10, Entities.COW));

		CustomCommand.init();
		CustomEvent.init(protocolManager);

		for (Player p : Bukkit.getOnlinePlayers()) {
			OnlinePlayerData plrData = OnlinePlayerData.getPlayer(p);
			if (plrData == null)
				plrData = OnlinePlayerData.addPlayer(p);

			plrData.setDefense(OnlinePlayerData.getDefense(p));
			PlayerJoin.createScoreboard(p);
			npcManager.showAllNPCs(p);
		}

		new BukkitRunnable() {
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					ScoreboardUtils scoreboard = ScoreboardUtils.scoreboards.get(p.getUniqueId());

					String rank = PlaceholderAPI.setPlaceholders(p, "%luckperms_prefix%");

					PlayerData data = playerManager.get(p);
					OnlinePlayerData plrData = OnlinePlayerData.getPlayer(p);
					if (plrData == null)
						plrData = OnlinePlayerData.addPlayer(p);

					scoreboard.setSuffix("rank", rank);
					scoreboard.setSuffix("players",
							"&6" + Bukkit.getOnlinePlayers().size() + "&7/&6" + Bukkit.getMaxPlayers());
					scoreboard.setSuffix("region", "&6" + SpawnManager.getClosestRegion(p).getKey().getDisplayName());
					scoreboard.setSuffix("coins", "&6" + data.coins);

					p.sendPlayerListFooter(toComponent("&7------------------\n" + "&7Players: &6"
							+ Bukkit.getOnlinePlayers().size() + "&7/&6" + Bukkit.getMaxPlayers()));

					p.sendActionBar(toComponent("&cHealth: " + (int) plrData.getHealth() + "/" + (int) data.health
							+ "   &7Defense: " + (int) plrData.getDefense() + "   &9Mana: " + (int) plrData.getMana()
							+ "/" + (int) data.mana));
					p.setFoodLevel(20);
					p.setSaturation(20);

					double xpToNextLevel = data.getXpToNextLevel();
					p.setExp((float) (data.xp / xpToNextLevel));
					p.setLevel(data.level);
				}
			}
		}.runTaskTimer(this, 0L, 5L);
	}

	@Override
	public void onDisable() {
		getLogger().info("Disabling version.");

		if (npcManager != null) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				npcManager.hideAllNPCs(p);
			}
		}

		try {
			playerManager.save();
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
		return ColourUtils.translateColourCodes(text);
	}

	public Component toComponent(String text) {
		return Component.text(toColour(text));
	}

	public IChatBaseComponent toMcComponent(String text) {
		return IChatBaseComponent.a(toColour(text));
	}

}
