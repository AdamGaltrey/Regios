package couk.Adamki11s.Regios.Regions;

import org.bukkit.Location;
import org.bukkit.World;

public class RegionLocation {
	
	private double x, y, z;
	private World w;
	
	public RegionLocation(World w, double x, double y, double z){
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public double getZ(){
		return this.z;
	}
	
	public World getWorld(){
		return this.w;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setZ(double z){
		this.z = z;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void add(double x, double y, double z){
		this.x += x;
		this.y += y;
		this.z += z;
	}
	
	public void subtract(double x, double y, double z){
		this.x -= x;
		this.y -= y;
		this.z -= z;
	}
	
	public Location toBukkitLocation(){
		return new Location(w, x, y, z);
	}
	
	@Override
	public boolean equals(Object rl){
		RegionLocation l = (RegionLocation)rl;
		return l.getX() == this.getX() && l.getY() == this.getY() && l.getZ() == this.getZ() && this.getWorld().getName().equals(l.getWorld().getName());
	}

}
