package me.ceramictitan.foodItems;

import java.io.File;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
	if (args.length == 0){
	    plugin.printHelp(sender);
	    return true;	
	}

	if(args.length == 2 && sender.hasPermission("fi.delete")){
	    if(!sender.hasPermission("fi.delete")){
		sender.sendMessage(ChatColor.RED+ "You do not have permission for this!");
		return true;
	    }
	    if(args[0].equalsIgnoreCase("delete") && sender.hasPermission("fi.delete")){
		String name = String.valueOf(args[1]);
		if(plugin.config.contains(name)){
		    try{
			plugin.config.set(name, null);
			plugin.config.save(new File(plugin.getDataFolder()+ File.separator, "items.yml"));
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.GOLD+name+ChatColor.RED+" has been deleted!");
			return true;
		    }catch(Exception e){e.printStackTrace();}



		}else if(!plugin.config.contains(name)){
		    sender.sendMessage(ChatColor.GOLD+ name+ChatColor.RED+" hasn't been registered!");
		    return true;
		}
	    }
	}else if(args.length < 2 && sender.hasPermission("fi.delete")){
	    sender.sendMessage(ChatColor.RED+ "Not enough arguments");
	    plugin.printHelp(sender);
	    return true;
	}
	if(args.length >= 3 && sender.hasPermission("fi.add")){
	    if(!sender.hasPermission("fi.add")){
		sender.sendMessage(ChatColor.RED+ "You do not have permission for this!");
		return true;


	    }
	    if(args[0].equalsIgnoreCase("add")){
		String name = String.valueOf(args[1]);
		if(plugin.config.contains(name)){
		    sender.sendMessage(ChatColor.DARK_RED+name+ChatColor.RED+ " is already being used!");
		    return true;
		}  



		try { 
		    Integer id = Integer.parseInt(String.valueOf(args[2])); 
		    if(plugin.config.contains(String.valueOf(id))){
			sender.sendMessage(ChatColor.RED+"Item Id: "+ChatColor.DARK_RED+String.valueOf(id)+ChatColor.RED+"(Material:" +Material.getMaterial(id).toString().toLowerCase().replaceAll("_", "")+")"+ChatColor.RED+ " is already being used!");
			return true;
		    }



		    String[] effects = Arrays.copyOfRange(args, 3, args.length);
		    plugin.config.set(name+".id", id);
		    plugin.config.set(name+".effects", Arrays.asList(effects));
		    plugin.config.save(new File(plugin.getDataFolder()+ File.separator, "items.yml"));
		    sender.sendMessage(ChatColor.GREEN +"You successfully registered: "+ChatColor.GOLD + name +ChatColor.GREEN+" with the following id: "+ChatColor.GOLD + id+"("+Material.getMaterial(id).toString().replace("_", "").toUpperCase()+")");
		}catch(Exception e){
		    e.printStackTrace();

		}
	    }
	    plugin.reloadConfig();
	}else if(args.length < 3 && sender.hasPermission("fi.add")){
	    sender.sendMessage(ChatColor.RED+ "Not enough arguments");
	    plugin.printHelp(sender);
	    return true;
	}
	return false;
    }
}

