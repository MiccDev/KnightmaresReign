package xyz.knightmaresreign.entities.classes;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySlime;
import xyz.knightmaresreign.entities.Entities;

public class TestBoi extends EntitySlime {

	@SuppressWarnings("unchecked")
	public TestBoi(Location location) {
		super((EntityTypes<? extends EntitySlime>) Entities.TEST_BOI.getType(), ((CraftWorld) location.getWorld()).getHandle());
		
		this.e(location.getX(), location.getY(), location.getZ());
	}
	
}
