package xyz.knightmaresreign.entities.spawn;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalAvoidTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.animal.EntityCow;
import net.minecraft.world.entity.player.EntityHuman;
import xyz.knightmaresreign.entities.Entities;

public class Cow extends EntityCow {

	@SuppressWarnings("unchecked")
	public Cow(Location location) {
		super((EntityTypes<? extends EntityCow>) Entities.COW.getType(), ((CraftWorld) location.getWorld()).getHandle());
		
		this.e(location.getX(), location.getY(), location.getZ());
		
		// Pathfinding
		this.bN.a(0, new PathfinderGoalAvoidTarget<EntityHuman>(this, EntityHuman.class, 1.75f, 1.2, 2));
		this.bN.a(1, new PathfinderGoalRandomLookaround(this));
		this.bN.a(2, new PathfinderGoalRandomStrollLand(this, 2));
	}
	
}
