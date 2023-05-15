package xyz.knightmaresreign.entities.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

public class SpawnData extends EntityData {
	private List<Material> preferredBlocks = new ArrayList<Material>();
	
	public SpawnData(Material ...preferredBlocks) {
		this.preferredBlocks = Arrays.asList(preferredBlocks);
	}
	
	public List<Material> getPreferredBlocks() {
		return preferredBlocks;
	}
	
	public void setPreferredBlocks(List<Material> preferredBlocks) {
		this.preferredBlocks = preferredBlocks;
	}
	
}
