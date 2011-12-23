package couk.Adamki11s.Regios.SpoutInterface;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import couk.Adamki11s.Extras.Regions.ExtrasRegions;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.Regions.SubRegionManager;

public class SpoutInventoryListener extends org.getspout.spoutapi.event.inventory.InventoryListener {
	
	private static final ExtrasRegions extReg = new ExtrasRegions();
	private static final SubRegionManager srm = new SubRegionManager();
	
	public boolean areChunksEqual(Chunk c1, Chunk c2){
		return (c1.getX() == c2.getX() && c1.getZ() == c2.getZ());
	}
	
	@Override
	public void onInventoryOpen(org.getspout.spoutapi.event.inventory.InventoryOpenEvent evt){
		Player p = evt.getPlayer();
		World w = p.getWorld();
		Location l = evt.getLocation();	
		
		if(l == null){ return; }
		
		Block b = w.getBlockAt(l);
		Chunk c = w.getChunkAt(l);
		Region r;
		
		if(b.getTypeId() == 54 || b.getType() == Material.CHEST || b.getTypeId() == 95){
			
			ArrayList<Region> regionSet = new ArrayList<Region>();
			
			for(Region region : GlobalRegionManager.getRegions()){
				for(Chunk chunk : region.getChunkGrid().getChunks()){
					if(chunk.getWorld() == w){
						if(areChunksEqual(chunk, c)){
							if(!regionSet.contains(region)){
								regionSet.add(region);
							}
						}
					}
				}
			}
			
			if(regionSet.isEmpty()){
				return;
			}
			
			ArrayList<Region> currentRegionSet = new ArrayList<Region>();
			
			for(Region reg : regionSet){
				if(extReg.isInsideCuboid(l, reg.getL1().toBukkitLocation(), reg.getL2().toBukkitLocation())){
					currentRegionSet.add(reg);
				}
			}
			
			if(currentRegionSet.isEmpty()){ //If player is in chunk range but not inside region then cancel the check.
				return;
			}
			
			if(currentRegionSet.size() > 1){
				r = srm.getCurrentRegion(p, currentRegionSet);
			} else {
				r = currentRegionSet.get(0);
			}
			
			if(r.areChestsLocked()){
				if(!r.canBuild(p)){
					p.sendMessage(ChatColor.RED + "[Regios] Chests are locked for this region!");
					evt.setCancelled(true);
				} else {
					return;
				}
			}
			
		}
	}

}
