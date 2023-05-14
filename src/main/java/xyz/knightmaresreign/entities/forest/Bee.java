package xyz.knightmaresreign.entities.forest;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFloat;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomFly;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityBee;
import net.minecraft.world.entity.player.EntityHuman;
import xyz.knightmaresreign.entities.Entities;

public class Bee extends EntityBee {

	@SuppressWarnings("unchecked")
	public Bee(Location location) {
		super((EntityTypes<? extends EntityBee>) Entities.BEE.getType(), ((CraftWorld) location.getWorld()).getHandle());
		
		this.e(location.getX(), location.getY(), location.getY());
		
		this.bN.a(0, new PathfinderGoalFloat(this));
		this.bN.a(1, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, false));
		this.bN.a(2, new PathfinderGoalRandomFly(this, 2));
		this.bN.a(2, new PathfinderGoalRandomLookaround(this));
	}
	
	public void l() {
		super.l();
		
		// Always has stinger
		this.x(false);
	}

}
