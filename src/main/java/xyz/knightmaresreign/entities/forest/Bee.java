package xyz.knightmaresreign.entities.forest;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.EntityBee;
import xyz.knightmaresreign.entities.Entities;

public class Bee extends EntityBee {

	@SuppressWarnings("unchecked")
	public Bee(Location location) {
		super((EntityTypes<? extends EntityBee>) Entities.BEE.getType(), ((CraftWorld) location.getWorld()).getHandle());
		
		this.e(location.getX(), location.getY(), location.getY());
	}

}
