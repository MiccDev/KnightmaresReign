package xyz.knightmaresreign.entities.spawning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.regions.Region;
import xyz.knightmaresreign.utils.EntityUtils;

public class SpawnManager {

	private static HashMap<Region, List<SpawnNode>> spawnNodes = new HashMap<Region, List<SpawnNode>>();
	
	public static void addSpawnNodes(Region region, SpawnNode ...node) {
		if(!spawnNodes.containsKey(region))
			spawnNodes.put(region, new ArrayList<SpawnNode>());
		spawnNodes.get(region).addAll(Arrays.asList(node));
	}
	
	public static void addSpawnNode(Region region, SpawnNode node) {
		if(!spawnNodes.containsKey(region))
			spawnNodes.put(region, new ArrayList<SpawnNode>());
		spawnNodes.get(region).add(node);
	}
	
	public static List<SpawnNode> getSpawnNodes(Region region) {
		if(!spawnNodes.containsKey(region))
			spawnNodes.put(region, new ArrayList<SpawnNode>());
		return spawnNodes.get(region);
	}
	
	public static HashMap<Region, List<SpawnNode>> getAllSpawnNodes() {
		return spawnNodes;
	}
	
	public static Map.Entry<Region, Location> getClosestRegion(Entity entity) {
		HashMap<Region, List<SpawnNode>> nodes = SpawnManager.getAllSpawnNodes();
		Location entityLocation = entity.getLocation();
		
		Map.Entry<Region, Location> closest = nodes.entrySet().stream().map((entry) -> {
			List<Location> nodeLocations = entry.getValue().stream().map((node) -> node.getLocation()).toList();
			Location closestLocation = EntityUtils.getClosestLocationTo(entity, nodeLocations);
			return Map.entry(entry.getKey(), closestLocation);
		}).sorted((a, b) -> (int) a.getValue().distanceSquared(entityLocation) - (int) b.getValue().distanceSquared(entityLocation)).findFirst().orElse(null);
		
		return closest;
	}
	
	private KnightmaresReign plugin;
	private BukkitRunnable tick;
	
	public SpawnManager() {
		plugin = KnightmaresReign.getInstance();
		tick = new BukkitRunnable() {
			int ticks = 0;
			
			public void run() {
				tick(ticks);
				ticks++;
			}
		};
		
		tick.runTaskTimer(plugin, 0, 1);
	}
	
	private void tick(int ticks) {
//		for(Player player : Bukkit.getOnlinePlayers()) {
//			Location playerLocation = player.getLocation();
//			
//			
//			
//			player.sendMessage(plugin.toComponent(closest.getKey() + "&#7b93ba" + closest.getValue().distance(player.getLocation())));
//		}
	}
	
}
