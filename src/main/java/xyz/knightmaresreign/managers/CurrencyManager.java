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

public class CurrencyManager {

	private HashMap<UUID, Integer> currency = new HashMap<UUID, Integer>();
	
	public KnightmaresReign plugin;
	
	public CurrencyManager() {
		plugin = KnightmaresReign.getInstance();
	}
	
	public void saveCurrencyFile() throws FileNotFoundException, IOException {
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			File file = new File(plugin.getDataFolder() + "/currency.dat");
			ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
			
			UUID uuid = p.getUniqueId();
			
			if(currency.get(uuid) != null) {
				currency.put(uuid, currency.get(uuid));
			}
			
			try {
				output.writeObject(currency);
				output.flush();
				output.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadCurrencyFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(plugin.getDataFolder() + "/currency.dat");
		
		if(file != null) {
			ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
			Object readObject = input.readObject();
			input.close();
			
			if(!(readObject instanceof HashMap))
				throw new IOException("Data is not hashmap");
			
			currency = (HashMap<UUID, Integer>) readObject;
			for(UUID key : currency.keySet()) {
				currency.put(key, currency.get(key));
			}
		}
	}
	
	public void addCurrency(OfflinePlayer p, int amount) {
		UUID uuid = p.getUniqueId();
		if(currency.get(uuid) == null) currency.put(uuid, 0);
		currency.put(uuid, currency.get(uuid) + amount);
	}
	
	public void removeCurrency(OfflinePlayer p, int amount) {
		UUID uuid = p.getUniqueId();
		if(currency.get(uuid) == null) currency.put(uuid, 0);
		currency.put(uuid, currency.get(uuid) - amount);
	}
	
	public void setCurrency(OfflinePlayer p, int amount) {
		UUID uuid = p.getUniqueId();
		currency.put(uuid, amount);
	}
	
	public int getCurrency(OfflinePlayer p) {
		UUID uuid = p.getUniqueId();
		if(currency.get(uuid) == null) currency.put(uuid, 0);
		return currency.get(uuid);
	}
	
}
