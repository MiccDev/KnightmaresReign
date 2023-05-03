package xyz.knightmaresreign.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import xyz.knightmaresreign.KnightmaresReign;

public class DataManager {

	private KnightmaresReign plugin;
	private FileConfiguration dataConfig = null;
	private File configFile = null;
	
	private String name;

	public DataManager(KnightmaresReign plugin, String name) {
		this.plugin = plugin;
		this.name = name;
		saveDefaultConfig();
	}
	
	public void reloadConfig() {
		if(this.configFile == null) this.configFile = new File(this.plugin.getDataFolder(), this.name + ".yml");
		
		this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
		
		InputStream defaultStream = this.plugin.getResource(this.name + ".yml");
		if(defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.dataConfig.setDefaults(defaultConfig);
		}
	}
	
	public void saveConfig() {
		if(this.dataConfig == null || this.configFile == null) return;
		
		try {
			this.getConfig().save(this.configFile);
		} catch (IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
		}
	}
	
	public void saveDefaultConfig() {
		if(this.configFile == null) this.configFile = new File(this.plugin.getDataFolder(), this.name + ".yml");
		
		if(!this.configFile.exists()) {
			this.plugin.saveResource(this.name + ".yml", false);
		}
	}
	
	public FileConfiguration getConfig() {
		if(dataConfig == null) reloadConfig();
		return dataConfig;
	}
	
}
