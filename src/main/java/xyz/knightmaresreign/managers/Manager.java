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

public class Manager<T> {
	private String fileName;
	
	public HashMap<UUID, T> data = new HashMap<UUID, T>();
	public KnightmaresReign plugin;
	
	private T newInstance;
	
	public Manager(String fileName, T newInstance) {
		this.fileName = fileName;
		this.newInstance = newInstance;
		plugin = KnightmaresReign.getInstance();
	}
	
	public void save() throws FileNotFoundException, IOException {
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			File file = new File(plugin.getDataFolder() + "/" + fileName + ".dat");
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
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(plugin.getDataFolder() + "/" + fileName + ".dat");
		
		if(file != null) {
			ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
			Object readObject = input.readObject();
			input.close();
			
			if(!(readObject instanceof HashMap))
				throw new IOException("Data is not hashmap");
			
			data = (HashMap<UUID, T>) readObject;
			for(UUID key : data.keySet()) {
				data.put(key, data.get(key));
			}
		}
	}
	
	public void set(OfflinePlayer p, T d) {
		UUID uuid = p.getUniqueId();
		if(data.get(uuid) == null) data.put(uuid, newInstance);
		data.put(uuid, d);
	}
	
	public T get(OfflinePlayer p) {
		UUID uuid = p.getUniqueId();
		if(data.get(uuid) == null) data.put(uuid, newInstance);
		return data.get(uuid);
	}
}
