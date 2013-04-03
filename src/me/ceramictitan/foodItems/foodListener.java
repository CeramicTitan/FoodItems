package me.ceramictitan.foodItems;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

public class foodListener implements Listener{
    public FoodItems plugin;

    public foodListener(FoodItems plugin){
	this.plugin=plugin;
    }

    @EventHandler
    public void onPlayerEat(PlayerInteractEvent event){
	if(event.getAction() == Action.RIGHT_CLICK_AIR){
	    if(event.getPlayer().hasPermission("fi.eat")){
		for(String name: plugin.config.getKeys(false)){
		    if(event.getPlayer().getItemInHand().getTypeId() == plugin.config.getInt(name+".id")){
			for(String effects : plugin.config.getStringList(name+".effects")){
			    event.getPlayer().addPotionEffect(PotionEffectType.getByName(effects).createEffect(plugin.getConfig().getInt("potionEffects.duration"), plugin.getConfig().getInt("potionEffects.potency")));
			    event.getPlayer().sendMessage("Pass!");
			}
		    }
		}
	    }
	}
    }
}
