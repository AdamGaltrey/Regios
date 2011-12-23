package couk.Adamki11s.Regios.Regions;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class SubRegionManager {
	
	public Region getCurrentRegion(Player p, ArrayList<Region> regions){
		int hba = 999999999,
		tdba = 999999999;
		Region current = null;
		for(Region r : regions){
			int horizblockarea = getHorizontalBlockArea(r),
			tdBlockArea = get3DBlockArea(r);
			if(horizblockarea < hba){
				hba = horizblockarea;
				current = r;
				tdba = tdBlockArea;
			} else if(hba == horizblockarea){
				if(tdBlockArea < tdba){
					hba = horizblockarea;
					current = r;
					tdba = tdBlockArea;
				}
			}
		}
		return current;
	}
	
	private int get3DBlockArea(Region r){
		int xVAL, zVAL, yVAL;
		
		if(r.getL1().toBukkitLocation().getBlockX() > r.getL2().toBukkitLocation().getBlockX()){
			xVAL = r.getL1().toBukkitLocation().getBlockX() - r.getL2().toBukkitLocation().getBlockX();
		} else {
			xVAL = r.getL2().toBukkitLocation().getBlockX() - r.getL1().toBukkitLocation().getBlockX();
		}
		
		if(r.getL1().toBukkitLocation().getBlockZ() > r.getL2().toBukkitLocation().getBlockZ()){
			zVAL = r.getL1().toBukkitLocation().getBlockZ() - r.getL2().toBukkitLocation().getBlockZ();
		} else {
			zVAL = r.getL2().toBukkitLocation().getBlockZ() - r.getL1().toBukkitLocation().getBlockZ();
		}
		
		if(r.getL1().toBukkitLocation().getBlockY() > r.getL2().toBukkitLocation().getBlockY()){
			yVAL = r.getL1().toBukkitLocation().getBlockY() - r.getL2().toBukkitLocation().getBlockY();
		} else {
			yVAL = r.getL2().toBukkitLocation().getBlockY() - r.getL1().toBukkitLocation().getBlockY();
		}
		
		if(xVAL == 0)
			xVAL = 1;
		if(yVAL == 0)
			yVAL = 1;
		if(zVAL == 0)
			zVAL = 1;
		
		return xVAL * yVAL * zVAL;
	}
	
	private int getHorizontalBlockArea(Region r){
		int xVAL, zVAL;
		
		if(r.getL1().toBukkitLocation().getBlockX() > r.getL2().toBukkitLocation().getBlockX()){
			xVAL = r.getL1().toBukkitLocation().getBlockX() - r.getL2().toBukkitLocation().getBlockX();
		} else {
			xVAL = r.getL2().toBukkitLocation().getBlockX() - r.getL1().toBukkitLocation().getBlockX();
		}
		
		if(r.getL1().toBukkitLocation().getBlockZ() > r.getL2().toBukkitLocation().getBlockZ()){
			zVAL = r.getL1().toBukkitLocation().getBlockZ() - r.getL2().toBukkitLocation().getBlockZ();
		} else {
			zVAL = r.getL2().toBukkitLocation().getBlockZ() - r.getL1().toBukkitLocation().getBlockZ();
		}
		
		if(xVAL == 0)
			xVAL = 1;
		if(zVAL == 0)
			zVAL = 1;
		
		return xVAL * zVAL;
	}

}
