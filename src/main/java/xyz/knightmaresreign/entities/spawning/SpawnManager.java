package xyz.knightmaresreign.entities.spawning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.CustomEntity;
import xyz.knightmaresreign.entities.data.SpawnData;
import xyz.knightmaresreign.regions.Region;
import xyz.knightmaresreign.utils.EntityUtils;
import xyz.knightmaresreign.utils.MathUtils;

public class SpawnManager {

	private static HashMap<Region, List<SpawnNode>> spawnNodes = new HashMap<Region, List<SpawnNode>>();

	public static void addSpawnNodes(Region region, SpawnNode... node) {
		if (!spawnNodes.containsKey(region))
			spawnNodes.put(region, new ArrayList<SpawnNode>());
		spawnNodes.get(region).addAll(Arrays.asList(node));
	}

	public static void addSpawnNode(Region region, SpawnNode node) {
		if (!spawnNodes.containsKey(region))
			spawnNodes.put(region, new ArrayList<SpawnNode>());
		spawnNodes.get(region).add(node);
	}

	public static List<SpawnNode> getSpawnNodes(Region region) {
		if (!spawnNodes.containsKey(region))
			spawnNodes.put(region, new ArrayList<SpawnNode>());
		return spawnNodes.get(region);
	}

	public static HashMap<Region, List<SpawnNode>> getAllSpawnNodes() {
		return spawnNodes;
	}

	public static Map.Entry<Region, Map.Entry<List<SpawnNode>, Location>> getClosestRegion(Entity entity) {
		HashMap<Region, List<SpawnNode>> nodes = SpawnManager.getAllSpawnNodes();
		Location entityLocation = entity.getLocation();

		Map.Entry<Region, Map.Entry<List<SpawnNode>, Location>> closest = nodes.entrySet().stream().map((entry) -> {
			List<SpawnNode> spawnNodes = entry.getValue();
			List<Location> nodeLocations = spawnNodes.stream().map((node) -> node.getLocation()).toList();
			Location closestLocation = EntityUtils.getClosestLocationTo(entity, nodeLocations);
			return Map.entry(entry.getKey(), Map.entry(spawnNodes, closestLocation));
		}).sorted((a, b) -> (int) a.getValue().getValue().distanceSquared(entityLocation)
				- (int) b.getValue().getValue().distanceSquared(entityLocation)).findFirst().orElse(null);

		return closest;
	}

	private KnightmaresReign plugin;
	private BukkitRunnable tick;

	private HashMap<UUID, Long> lastSpawnInRegion;
	
	public SpawnManager() {
		lastSpawnInRegion = new HashMap<UUID, Long>();
		
		plugin = KnightmaresReign.getInstance();
		tick = new BukkitRunnable() {
			long ticks = 0;

			public void run() {
				tick(ticks);
				ticks++;
			}
		};

		tick.runTaskTimer(plugin, 0, 1);
	}

	private void tick(long ticks) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			Map.Entry<Region, Map.Entry<List<SpawnNode>, Location>> regionEntry = getClosestRegion(player);
			List<SpawnNode> nodes = regionEntry.getValue().getKey();
			Location closestLocation = regionEntry.getValue().getValue();
			SpawnNode closestNode = nodes.stream().filter((node) -> node.getLocation().equals(closestLocation))
					.findFirst().orElse(null);
			
			UUID uuid = player.getUniqueId();
			long lastTicks = lastSpawnInRegion.containsKey(uuid) ? lastSpawnInRegion.get(uuid) : 0;
			if(ticks > lastTicks + closestNode.getFrequency()) {
				spawnEntity(ticks, player, closestLocation, closestNode);
			}
		}
	}

	private void spawnEntity(long ticks, Player player, Location location, SpawnNode node) {
		Location playerLocation = player.getLocation();

		Vector randomPointInSphere = MathUtils.randomPointInSphere(25);
		Location randomPointInSphereFromPlayer = playerLocation.add(randomPointInSphere).toHighestLocation();

		CustomEntity entity = node.getSpawnable().get(MathUtils.getRandomInt(0, node.getSpawnable().size() - 1));
		SpawnData data = entity.getDataOfType(SpawnData.class);
		if(data == null) return;
		
		if(data.getPreferredBlocks().contains(randomPointInSphereFromPlayer.getBlock().getType())) {
			((CraftWorld) playerLocation.getWorld()).getHandle().addFreshEntity(
					entity.getEntity(randomPointInSphereFromPlayer.add(0, 1, 0)), CreatureSpawnEvent.SpawnReason.NATURAL);
			lastSpawnInRegion.put(player.getUniqueId(), ticks);
		}
	}

}
