package couk.Adamki11s.Regios.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import couk.Adamki11s.Regios.Regions.Region;

@SuppressWarnings("serial")
public class RegionRestoreEvent extends Event {
	
	private Region region;
	private Player player;
	private String backupname;

    public RegionRestoreEvent(String name) {
        super(name);
    }
    
    public Region getRegion(){
    	return this.region;
    }
    
    public Player getPlayer(){
    	return this.player;
    }
    
    public String getBackupName(){
    	return this.backupname;
    }

    public void setProperties(Region region, String backupname, Player p) {
    	this.player = p;
        this.region = region;
        this.backupname = backupname;
    }


}
