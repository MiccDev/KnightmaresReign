package xyz.knightmaresreign.utils;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EntityUtils {

	public static Location getClosestLocationTo(Entity entity, List<Location> locations) {
		Location center = entity.getLocation();
		Location closest = null;
		for(Location loc : locations) {
			if(closest == null) closest = loc;
			else if(loc.distanceSquared(center) < closest.distanceSquared(center)) closest = loc;
		}
		return closest;
	}
	
}
