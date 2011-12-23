package couk.Adamki11s.Regios.Economy;

import java.util.UUID;

import org.bukkit.Location;

import couk.Adamki11s.Regios.Regions.Region;

public class RegionSign {
	
	Region r = null;
	Location l = null;
	UUID uuid = null;
	
	public RegionSign(Region region, Location loc, UUID uuid){
		this.r = region;
		this.l = loc;
		this.uuid = uuid;
	}
	
	public String getRegionName(){
		return this.r.getName();
	}
	
	public UUID getUUID(){
		return this.uuid;
	}
	
	public Region getRegion(){
		return this.r;
	}
	
	public Location getLocation(){
		return this.l;
	}

}
