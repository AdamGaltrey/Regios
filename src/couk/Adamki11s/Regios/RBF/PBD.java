package couk.Adamki11s.Regios.RBF;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class PBD {
	
	private Location l;
	private int id;
	private byte data;
	
	public PBD(Block b){ //Needed to create an object which can't be modifed at run time by Bukkit.
		this.l = b.getLocation();
		this.id = b.getTypeId();
		this.data = b.getData();
	}
	
	public Location getL() {
		return l;
	}
	public int getId() {
		return id;
	}
	public byte getData() {
		return data;
	}
	
	

}
