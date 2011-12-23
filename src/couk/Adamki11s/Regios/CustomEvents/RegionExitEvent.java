package couk.Adamki11s.Regios.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import couk.Adamki11s.Regios.Regions.Region;

@SuppressWarnings("serial")
public class RegionExitEvent extends Event {
	
	private Region region;
	private Player player;

    public RegionExitEvent(String name) {
        super(name);
    }
    
    public Region getRegion(){
    	return this.region;
    }
    
    public Player getPlayer(){
    	return this.player;
    }

    public void setProperties(Player player, Region region) {
    	this.player = player;
        this.region = region;
    }

}
