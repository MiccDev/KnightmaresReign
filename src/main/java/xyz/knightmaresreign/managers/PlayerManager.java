package xyz.knightmaresreign.managers;

import java.util.UUID;

import org.bukkit.OfflinePlayer;

public class PlayerManager extends Manager<PlayerData> {
	public PlayerManager() {
		super("player", new PlayerData());
	}
	
	public void addCurrency(OfflinePlayer p, int amount) {
		UUID uuid = p.getUniqueId();
		if(data.get(uuid) == null) data.put(uuid, new PlayerData());
		data.get(uuid).coins += amount;
	}
	
	public void removeCurrency(OfflinePlayer p, int amount) {
		UUID uuid = p.getUniqueId();
		if(data.get(uuid) == null) data.put(uuid, new PlayerData());
		data.get(uuid).coins -= amount;
	}
	
	public void setCurrency(OfflinePlayer p, int amount) {
		UUID uuid = p.getUniqueId();
		data.get(uuid).coins = amount;
	}
	
	public int getCurrency(OfflinePlayer p) {
		UUID uuid = p.getUniqueId();
		if(data.get(uuid) == null) data.put(uuid, new PlayerData());
		return data.get(uuid).coins;
	}

	public boolean hasCurrency(OfflinePlayer p, int amount) {
		return getCurrency(p) >= amount;
	}
	
	public void interactedWith(OfflinePlayer p, String name) {
		UUID uuid = p.getUniqueId();
		if(data.get(uuid) == null) data.put(uuid, new PlayerData());
		PlayerData d = data.get(uuid);
		d.npcInteractions.put(name, d.npcInteractions.get(name) + 1);
	}
}
