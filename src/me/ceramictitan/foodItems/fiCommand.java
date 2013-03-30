package me.ceramictitan.foodItems;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class fiCommand implements CommandExecutor {

	public FoodItems plugin; 

	public fiCommand(FoodItems plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if(cmd.getName().equals("fi")) {
			if (args.length == 0){
				plugin.printHelp(sender);
				return true;	
			}
			if(args.length == 1){
				sender.sendMessage(ChatColor.RED+"Not enough arugments!");
				plugin.printHelp(sender);
			}
			// Assuming /fi add <name> <item> <effects>...
			int x = 0;
			int item = 0;
			String name = "";
			String action = "";
			String effect = "";
			for (String arg : args) {
				x++;
				if (x == 1) action = arg;
				if (x == 2) name = arg;
				if (x == 3) item = Integer.parseInt(arg);
				if (x == 4) effect = arg;
				if (x > 4) effect = effect + "," + arg;
			}
			String[] effects = effect.split(",");
			for (String eff : effects) {
				if (eff.equalsIgnoreCase("confusion")) doSomething();
				if (eff.equalsIgnoreCase("jump")) doSomethingElse();
			}
			return true;
		}
		return false;
	}

	// http://stackoverflow.com/a/2275030
	public boolean contains(String haystack, String needle) {
		haystack = haystack == null ? "" : haystack;
		needle = needle == null ? "" : needle;
		return haystack.toLowerCase().contains(needle.toLowerCase());
	}
	
}
