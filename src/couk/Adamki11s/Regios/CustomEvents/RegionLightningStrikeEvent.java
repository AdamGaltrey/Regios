package couk.Adamki11s.Regios.CustomEvents;

import org.bukkit.Location;
import org.bukkit.event.Event;

import couk.Adamki11s.Regios.Regions.Region;

@SuppressWarnings("serial")
public class RegionLightningStrikeEvent extends Event {
	
	private Region region;
	private Location location;

    public RegionLightningStrikeEvent(String name) {
        super(name);
    }
    
    public Region getRegion(){
    	return this.region;
    }
    
    public Location getLocation(){
    	return this.location;
    }

    public void setProperties(Location location, Region region) {
    	this.location = location;
        this.region = region;
    }

}
