package couk.Adamki11s.Regios.CustomEvents;

import org.bukkit.event.Event;

import couk.Adamki11s.Regios.Regions.Region;

@SuppressWarnings("serial")
public class RegionLoadEvent extends Event {
	
	private Region region;

    public RegionLoadEvent(String name) {
        super(name);
    }
    
    public Region getRegion(){
    	return this.region;
    }

    public void setProperties(Region region) {
        this.region = region;
    }

}
