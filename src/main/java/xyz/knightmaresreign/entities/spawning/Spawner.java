package xyz.knightmaresreign.entities.spawning;

import java.util.ArrayList;
import java.util.List;

public class Spawner {

	private List<SpawnNode> nodes;
	
	public Spawner() {
		nodes = new ArrayList<SpawnNode>();
	}
	
	public void addNode(SpawnNode node) {
		nodes.add(node);
	}
	
}
