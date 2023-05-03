package xyz.knightmaresreign.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import xyz.knightmaresreign.KnightmaresReign;

public class PlayerManager {

	private HashMap<UUID, PlayerData> data = new HashMap<UUID, PlayerData>();
	
	public KnightmaresReign plugin;
	
	public PlayerManager() {
		plugin = KnightmaresReign.getInstance();
	}
	
	public void savePlayerDataFile() throws FileNotFoundException, IOException {
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			File file = new File(plugin.getDataFolder() + "/player-data.dat");
			ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
			
			UUID uuid = p.getUniqueId();
			
			if(data.get(uuid) != null) {
				data.put(uuid, data.get(uuid));
			}
			
			try {
				output.writeObject(data);
				output.flush();
				output.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadPlayerDataFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(plugin.getDataFolder() + "/player-data.dat");
		
		if(file != null) {
			ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
			Object readObject = input.readObject();
			input.close();
			
			if(!(readObject instanceof HashMap))
				throw new IOException("Data is not hashmap");
			
			data = (HashMap<UUID, PlayerData>) readObject;
			for(UUID key : data.keySet()) {
				data.put(key, data.get(key));
			}
		}
	}
	
	public void setPlayerData(OfflinePlayer p, PlayerData d) {
		UUID uuid = p.getUniqueId();
		if(data.get(uuid) == null) data.put(uuid, new PlayerData());
		data.put(uuid, d);
	}
	
	public PlayerData getPlayerData(OfflinePlayer p) {
		UUID uuid = p.getUniqueId();
		if(data.get(uuid) == null) data.put(uuid, new PlayerData());
		return data.get(uuid);
	}
	
}
