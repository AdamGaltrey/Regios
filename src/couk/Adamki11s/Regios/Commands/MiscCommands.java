package couk.Adamki11s.Regios.Commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import couk.Adamki11s.Extras.Regions.ExtrasRegions;
import couk.Adamki11s.Regios.Data.ConfigurationData;
import couk.Adamki11s.Regios.Mutable.MutableEconomy;
import couk.Adamki11s.Regios.Mutable.MutableMisc;
import couk.Adamki11s.Regios.Mutable.MutableModification;
import couk.Adamki11s.Regios.Mutable.MutableProtection;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.Regions.SubRegionManager;

public class MiscCommands extends PermissionsCore {

	MutableMisc mutable = new MutableMisc();
	
	private boolean areChunksEqual(Chunk c1, Chunk c2) {
		return (c1.getX() == c2.getX() && c1.getZ() == c2.getZ());
	}
	
	private final ExtrasRegions extReg = new ExtrasRegions();
	private final SubRegionManager srm = new SubRegionManager();
	private final MutableModification mods = new MutableModification();
	private final MutableEconomy eco = new MutableEconomy();
	private final MutableProtection prot = new MutableProtection();
	
	public void createAllotment(Player p, String region, Region r){
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			mods.editExpandMax(r);
			System.out.println(ConfigurationData.salePrice);
			eco.editSalePrice(r, ConfigurationData.salePrice);
			eco.editForSale(r, true);
			prot.editProtect(r);
			prot.editProtectBB(r);
			prot.editProtectBP(r);
			p.sendMessage(ChatColor.GREEN + "[Regios] Region expanded to max, protected and for sale!");
		}
	}
	
	public Region returnClosestRegion(ArrayList<Region> regs, Player p){
		Location origin = p.getLocation();
		double distance = 999999999;
		Region binding = null;
		for(Region r : regs){
			double x_mid, z_mid;
			x_mid = (double)(Math.max(r.getL1().getX(), r.getL2().getX()) - Math.min(r.getL1().getX(), r.getL2().getX()));
			z_mid = (double)(Math.max(r.getL1().getZ(), r.getL2().getZ()) - Math.min(r.getL1().getZ(), r.getL2().getZ()));
			double hypot_x, hypot_z;
			hypot_x = origin.getX()- x_mid;
			hypot_z = origin.getZ() - z_mid;
			double direct_dist = Math.hypot(hypot_x, hypot_z);
			if(direct_dist < distance){
				binding = r;
				distance = direct_dist;
			}
		}
		return binding;
	}
	
	public void checkRegion(Player p){
		Location l = p.getLocation();
		World w = p.getWorld();
		Chunk c = w.getChunkAt(l);
		Region r;

		ArrayList<Region> regionSet = new ArrayList<Region>();

		for (Region region : GlobalRegionManager.getRegions()) {
			for (Chunk chunk : region.getChunkGrid().getChunks()) {
				if (chunk.getWorld() == w) {
					if (areChunksEqual(chunk, c)) {
						if (!regionSet.contains(region)) {
							regionSet.add(region);
						}
					}
				}
			}
		}

		if (regionSet.isEmpty()) {
			p.sendMessage(ChatColor.GREEN + "[Regios][Check] " + ChatColor.RED + "You are not within chunk range of any regions!");
			return;
		}

		ArrayList<Region> currentRegionSet = new ArrayList<Region>();

		for (Region reg : regionSet) {
			if (extReg.isInsideCuboid(l, reg.getL1().toBukkitLocation(), reg.getL2().toBukkitLocation())) {
				currentRegionSet.add(reg);
			}
		}

		if (currentRegionSet.isEmpty()) { // If player is in chunk range but not
											// inside region then cancel the
											// check.
			r = returnClosestRegion(regionSet, p);
			p.sendMessage(ChatColor.GREEN + "[Regios][Check] You are not in a region.");
			p.sendMessage(ChatColor.GREEN + "[Regios][Check] The closest region is : " + ChatColor.BLUE + r.getName());
			p.sendMessage(ChatColor.GREEN + "[Regios][Check] " + ChatColor.LIGHT_PURPLE + "Coords[1] : X : " + r.getL1().getX() + ", Y : " + r.getL1().getX() + ", Z : " + r.getL1().getZ());
			p.sendMessage(ChatColor.GREEN + "[Regios][Check] " + ChatColor.LIGHT_PURPLE + "Coords[2] : X : " + r.getL2().getX() + ", Y : " + r.getL2().getX() + ", Z : " + r.getL2().getZ());
			return;
		}

		if (currentRegionSet.size() > 1) {
			r = srm.getCurrentRegion(p, currentRegionSet);
		} else {
			r = currentRegionSet.get(0);
		}
		
		p.sendMessage(ChatColor.GREEN + "[Regios][Check] You are in region " + ChatColor.BLUE + r.getName());
		return;
	}

	public void addToCommandSet(Region r, String region, String[] message, Player p) {
		String msg = "";
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyBasic(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			String builder = "";
			for(int index = 2; index < message.length; index++){
				builder += message[index] + " ";
			}
			msg = builder;
			if(mutable.checkCommandSet(r, msg)){
				p.sendMessage(ChatColor.RED + "[Regios] The Command " + ChatColor.BLUE + msg + ChatColor.RED + " already exists!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Command " + ChatColor.BLUE + msg + ChatColor.GREEN + " added to region set " + ChatColor.BLUE + region);
		}
		mutable.editAddToForceCommandSet(r, msg);
	}

	public void removeFromCommandSet(Region r, String region, String[] message, Player p) {
		String msg = "";
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			String builder = "";
			for(int index = 2; index < message.length; index++){
				builder += message[index] + " ";
			}
			msg = builder;
			if(!mutable.checkCommandSet(r, msg)){
				p.sendMessage(ChatColor.RED + "[Regios] The command " + ChatColor.BLUE + msg + ChatColor.RED + " did not match any in the set!");
				return;
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Command " + ChatColor.BLUE + msg + ChatColor.GREEN + " removed from region set " + ChatColor.BLUE + region);
			}
		}
		mutable.editRemoveFromForceCommandSet(r, msg);
	}

	public void resetCommandSet(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Command Set reset for region " + ChatColor.BLUE + region);
		}
		mutable.editResetForceCommandSet(r);
	}

	public void listCommandSet(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			String regionSet = mutable.listCommandSet(r);
			p.sendMessage(ChatColor.GREEN + "Force Command Set List : " + ChatColor.BLUE + region);
			p.sendMessage(regionSet);
		}
	}

	public void setForceCommand(Region r, String region, String input, Player p) {
		boolean val;
		try {
			val = Boolean.parseBoolean(input);
		} catch (Exception bfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be boolean!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (val) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Force Commands enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Force Commands disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editSetForceCommand(r, val);
	}

}
