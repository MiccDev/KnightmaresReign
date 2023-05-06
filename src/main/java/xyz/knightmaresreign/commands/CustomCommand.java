package xyz.knightmaresreign.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.world.entity.Entity;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.CustomEntity;
import xyz.knightmaresreign.items.CustomItem;

public abstract class CustomCommand implements CommandExecutor, TabCompleter {

	public static List<UUID> cooldowns = new ArrayList<UUID>();
	
	public static void init() {
		KnightmaresReign plugin = KnightmaresReign.getInstance();
		
		createCommand("getbonk", new CommandRunnable() {
			@Override
			public boolean run(CommandSender sender, Command command, String label, String[] args) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(plugin.toComponent("&4Only players can use this command."));
					return true;
				}
				
				Player player = (Player) sender;
				
				if(!player.hasPermission("knightmaresreign.admin.bonkers")) {
					player.sendMessage(plugin.toComponent("&4You do not have permission to use this command."));
					return true;
				}
				
				player.getInventory().addItem(CustomItem.SENOR_BONKERS.getItem());
				return false;
			}
		});

		createCommand("getbook", new CommandRunnable() {
			@Override
			public boolean run(CommandSender sender, Command command, String label, String[] args) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(plugin.toComponent("&4Only players can use this command."));
					return true;
				}

				Player player = (Player) sender;

				if(!player.hasPermission("knightmaresreign.admin.bonkers")) {
					player.sendMessage(plugin.toComponent("&4You do not have permission to use this command."));
					return true;
				}
				player.getInventory().addItem(CustomItem.DATA_BOOK.getItem());
				Entity entity = CustomEntity.TEST_BOI.getEntity(player.getLocation());
				if(entity != null) {
					((CraftWorld) player.getLocation().getWorld()).getHandle().b(entity);
				}
				return false;
			}
		});
	}
	
	public static void createCommand(String name, CommandRunnable commandRunner, TabListRunnable tablistRunner) {
		class CCommand extends CustomCommand {

			public CCommand() {
				super(name);
			}

			@Override
			public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command,
					@NotNull String label, @NotNull String[] args) {
				return commandRunner.run(sender, command, label, args);
			}

			@Override
			public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
					@NotNull Command command, @NotNull String label, @NotNull String[] args) {
				if(tablistRunner == null) return Arrays.asList();
				return tablistRunner.run(sender, command, label, args);
			}
			
		}
		
		new CCommand();
	}
	
	public static void createCommand(String name, CommandRunnable commandRunner) {
		createCommand(name, commandRunner, null);
	}
	
	private final KnightmaresReign plugin;
	private String name;
	
	public CustomCommand(String name) {
		this.plugin = KnightmaresReign.getInstance();
		this.name = name;
		plugin.getCommand(name).setExecutor(this);
		plugin.getCommand(name).setTabCompleter(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KnightmaresReign getPlugin() {
		return plugin;
	}
	
}
