package me.ceramictitan.foodItems;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class FoodItems extends JavaPlugin {

    FileConfiguration config;
    public List<String> active_effects = new ArrayList<String>();
    @Override
    public void onEnable(){
	addConfig();
	File f = new File(getDataFolder() + File.separator);
	File g = new File(f, "items.yml");
	if(!f.exists()){
	    f.mkdirs();
	}
	if (!g.exists())
	    try {
		g.createNewFile();
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	config = YamlConfiguration.loadConfiguration(new File(getDataFolder() + File.separator, "items.yml"));
	getCommand("fiCommand").setExecutor(new fiCommand(this));
	getServer().getPluginManager().registerEvents(new foodListener(this), this);
	for(String titan : this.getTitan()){
	    getServer().getConsoleSender().sendMessage(titan);
	}
    }
    @Override
    public void onDisable(){

    }

    public void addConfig(){
	getConfig().addDefault("potionEffects.duration", 10);
	getConfig().addDefault("potionEffects.potency", 1);
	getConfig().options().copyDefaults(true);
	saveConfig();
    }
    public String[] getTitan(){
	String[] titan = new String[8];
	titan[0] = " ********** ** **********     **     ****     **";
	titan[1] = "/////**/// /**/////**///     ****   /**/**   /**";
	titan[2] = "    /**    /**    /**       **//**  /**//**  /**";
	titan[3] = "    /**    /**    /**      **  //** /** //** /**";
	titan[4] = "    /**    /**    /**     **********/**  //**/**";
	titan[5] = "    /**    /**    /**    /**//////**/**   //****";
	titan[6] = "    /**    /**    /**    /**     /**/**    //***";
	titan[7] = "    //     //     //     //      // //      /// ";
	return titan;
    }
    public String[] getHelp(){
	String[] help = new String[3];
	help[0] = ChatColor.AQUA +"--------"+ ChatColor.DARK_GRAY + "FoodItems Help" + ChatColor.AQUA +"--------";
	help[1] = ChatColor.GREEN+ "Add: "+ ChatColor.DARK_GRAY+"/fi add [name] [item-id] [effect 1] [effect 2]....";
	help[2] = ChatColor.DARK_RED+ "Delete: "+ ChatColor.DARK_GRAY+ "/fi delete [name]";
	return help;
    }
    public void printHelp(CommandSender sender){
	for(String helpLines : this.getHelp()){
	    sender.sendMessage(helpLines);
	}
    }

}
