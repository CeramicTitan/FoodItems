package me.ceramictitan.foodItems;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FoodItems extends JavaPlugin {

	FileConfiguration config;

	@Override
	public void onEnable(){
		addConfig();
		File g = new File(getDataFolder(), "items.yml");
		if (!g.exists())
			g.mkdirs();
		if (!g.exists())
			try {
				g.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "items.yml"));
		getCommand("fiCommand").setExecutor(new fiCommand(this));
		for(String titan : this.getTitan()){
			getServer().getConsoleSender().sendMessage(titan);
		}
	}
	@Override
	public void onDisable(){

	}

	public void addConfig(){
		getConfig().addDefault(path, value);
		getConfig().addDefault(path, value);

	}
	@Override
	public FileConfiguration getConfig() {
		return this.config;
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
		String[] help = new String[2];
		help[0] = ChatColor.AQUA +"--------"+ ChatColor.DARK_GRAY + "FoodItems Help" + ChatColor.AQUA +"--------";
		help[1] = ChatColor.GREEN+ "Add: "+ ChatColor.DARK_GRAY+"/fi add [name] [item-id] [effect 1] [effect 2]....";
		help[2] = ChatColor.DARK_RED+ "Delete: "+ ChatColor.DARK_GRAY+ "/fi delte [name]";
		return help;
	}
	public void printHelp(CommandSender sender){
		for(String helpLines : this.getHelp()){
			sender.sendMessage(helpLines);
		}
	}


}
