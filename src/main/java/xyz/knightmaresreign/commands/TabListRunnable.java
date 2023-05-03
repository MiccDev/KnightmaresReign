package xyz.knightmaresreign.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface TabListRunnable {
	public List<String> run(CommandSender sender, Command command, String label, String[] args);
}
