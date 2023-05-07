package xyz.knightmaresreign.entities.classes;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.monster.Slime;
import xyz.knightmaresreign.entities.Entities;

public class TestBoi extends Slime {

	@SuppressWarnings("unchecked")
	public TestBoi(Location location) {
		super((EntityType<? extends Slime>) Entities.TEST_BOI.getType(), ((CraftWorld) location.getWorld()).getHandle());
		
		this.setPos(location.getX(), location.getY(), location.getZ());
		
		this.goalSelector.addGoal(0, new RandomLookAroundGoal(this));
	}
	
}
