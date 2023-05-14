package xyz.knightmaresreign.entities.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import xyz.knightmaresreign.regions.Region;

public class SpawnData extends EntityData {
	private List<Material> preferredBlocks = new ArrayList<Material>();
	private Region region;
	
	public SpawnData(Region region) {
		this(region, new ArrayList<Material>());
	}
	
	public SpawnData(Region region, List<Material> preferredBlocks) {
		this.region = region;
		this.preferredBlocks = preferredBlocks;
	}
	
	public List<Material> getPreferredBlocks() {
		return preferredBlocks;
	}
	
	public void setPreferredBlocks(List<Material> preferredBlocks) {
		this.preferredBlocks = preferredBlocks;
	}
	
	public Region getRegion() {
		return region;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
}
