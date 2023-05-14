package xyz.knightmaresreign.entities.spawning;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;

import xyz.knightmaresreign.entities.Entities;

public class SpawnNode {
	private Location location;
	private int radius = 10;
	private List<Entities> spawnable;
	private long frequency;
	
	public SpawnNode(Location location, int radius, long frequency, Entities ...spawnable) {
		this.location = location;
		this.radius = radius;
		this.frequency = frequency;
		this.spawnable = Arrays.asList(spawnable);
	}

	public Location getLocation() {
		return location;
	}

	public int getRadius() {
		return radius;
	}

	public List<Entities> getSpawnable() {
		return spawnable;
	}

	public long getFrequency() {
		return frequency;
	}

	public SpawnNode setLocation(Location location) {
		this.location = location;
		return this;
	}

	public SpawnNode setRadius(int radius) {
		this.radius = radius;
		return this;
	}

	public SpawnNode setSpawnable(List<Entities> spawnable) {
		this.spawnable = spawnable;
		return this;
	}

	public SpawnNode setFrequency(long frequency) {
		this.frequency = frequency;
		return this;
	}
	
	
}
