package couk.Adamki11s.Regios.Commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import couk.Adamki11s.Regios.API.RegionDataSet;
import couk.Adamki11s.Regios.CustomEvents.RegionCreateEvent;
import couk.Adamki11s.Regios.CustomExceptions.InvalidDataSetException;
import couk.Adamki11s.Regios.CustomExceptions.RegionNameExistsException;
import couk.Adamki11s.Regios.CustomExceptions.RegionPointsNotSetException;
import couk.Adamki11s.Regios.Data.ConfigurationData;
import couk.Adamki11s.Regios.Listeners.RegiosPlayerListener;
import couk.Adamki11s.Regios.Net.PingManager;
import couk.Adamki11s.Regios.RBF.RBF_Core;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.Restrictions.RestrictionManager;
import couk.Adamki11s.Regios.Restrictions.RestrictionParameters;

public class CreationCommands {

	public static HashMap<Player, Location> point1 = new HashMap<Player, Location>();
	public static HashMap<Player, Location> point2 = new HashMap<Player, Location>();
	public static HashMap<Player, Boolean> setting = new HashMap<Player, Boolean>();

	public static HashMap<Player, Boolean> modding = new HashMap<Player, Boolean>();
	public static HashMap<Player, Location> mod1 = new HashMap<Player, Location>();
	public static HashMap<Player, Location> mod2 = new HashMap<Player, Location>();

	public static HashMap<Player, Region> modRegion = new HashMap<Player, Region>();

	public static char[] invalidModifiers = { '!', '\'', '£', '$', '%', '^', '&', '*', '¬', '`', '/', '?', '<', '>', '|', '\\' };

	static GlobalRegionManager grm = new GlobalRegionManager();

	public boolean isSetting(Player p) {
		return (setting.containsKey(p) ? setting.get(p) : false);
	}

	public boolean isModding(Player p) {
		return (modding.containsKey(p) ? modding.get(p) : false);
	}

	public void giveTool(Player p) {
		if (isSetting(p)) {
			if (!p.getInventory().contains(new ItemStack(ConfigurationData.defaultSelectionTool, 1))) {
				ItemStack is = new ItemStack(ConfigurationData.defaultSelectionTool, 1);
				p.getInventory().addItem(is);

				if (p.getItemInHand() == new ItemStack(Material.AIR, 0)) {
					p.setItemInHand(is);
				}

			}
			p.sendMessage(ChatColor.RED + "[Regios] You are already setting a region!");
			return;
		} else {
			setting.put(p, true);
			modding.put(p, false);
		}
		if (!p.getInventory().contains(new ItemStack(ConfigurationData.defaultSelectionTool, 1))) {
			ItemStack is = new ItemStack(ConfigurationData.defaultSelectionTool, 1);

			p.getInventory().addItem(is);

			if (p.getItemInHand() == new ItemStack(Material.AIR, 0)) {
				p.setItemInHand(is);
			}

		}
		p.sendMessage(ChatColor.GREEN + "[Regios] Left and right click to select points.");
	}
	
	public static void createRegion(RegionDataSet rds) throws InvalidDataSetException{
		String errors = "";
		if (rds.getPlugin() == null) {
			errors += "Plugin, ";
		}
		if (rds.getName() == null) {
			errors += "Region name, ";
		}
		if (rds.getName() == null) {
			errors += "Owner, ";
		}
		if (rds.getWorld() == null || Bukkit.getServer().getWorld(rds.getWorld()) == null) {
			errors += "World Name, ";
		}
		if (rds.getL1() == null) {
			errors += "Location 1, ";
		}
		if (rds.getL2() == null) {
			errors += "Location 2, ";
		}
		if (errors.length() >= 5) {
			throw new InvalidDataSetException(errors);
		}
		Region r = new Region(rds.getOwner(), rds.getName(), rds.getL1(), rds.getL2(), Bukkit.getServer().getWorld(rds.getWorld()), null, true);
		PingManager.created();
		RegionCreateEvent event = new RegionCreateEvent("RegionCreateEvent");
		event.setProperties(null, r);
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	public void createRegion(Player p, String name) throws RegionNameExistsException, RegionPointsNotSetException {
		if (GlobalRegionManager.doesExist(name)) {
			p.sendMessage(ChatColor.RED + "[Regios] A region with name : " + ChatColor.BLUE + name + ChatColor.RED + " already exists!");
			throw new RegionNameExistsException(name);
		}
		if (!point1.containsKey(p) || !point2.containsKey(p)) {
			p.sendMessage(ChatColor.RED + "[Regios] You must set 2 points!");
			throw new RegionPointsNotSetException(name);
		}
		StringBuilder invalidName = new StringBuilder();
		boolean integrity = true;
		for (char ch : name.toCharArray()) {
			boolean valid = true;
			for (char inv : invalidModifiers) {
				if (ch == inv) {
					valid = false;
					integrity = false;
				}
			}
			if (!valid) {
				invalidName.append(ChatColor.RED).append(ch);
			} else {
				invalidName.append(ChatColor.GREEN).append(ch);
			}
		}

		if (!integrity) {
			p.sendMessage(ChatColor.RED + "[Regios] Name contained  invalid characters : " + invalidName.toString());
			return;
		}

		RestrictionParameters params = RestrictionManager.getRestriction(p);
		double width = Math.max(point1.get(p).getX(), point2.get(p).getX()) - Math.min(point1.get(p).getX(), point2.get(p).getX()), height = Math.max(point1.get(p).getY(),
				point2.get(p).getY()) - Math.min(point1.get(p).getY(), point2.get(p).getY()), length = Math.max(point1.get(p).getZ(), point2.get(p).getZ())
				- Math.min(point1.get(p).getZ(), point2.get(p).getZ());
		int rCount = GlobalRegionManager.getOwnedRegions(p.getName());
		
		if(width > params.getRegionWidthLimit()){
			p.sendMessage(ChatColor.RED + "[Regios] You cannot create a region of this width!");
			p.sendMessage(ChatColor.RED + "[Regios] Maximum width : " + ChatColor.BLUE + params.getRegionWidthLimit() + ChatColor.RED + ", your width : " + ChatColor.BLUE + width);
			return;
		}
		
		if(height > params.getRegionHeightLimit()){
			p.sendMessage(ChatColor.RED + "[Regios] You cannot create a region of this height!");
			p.sendMessage(ChatColor.RED + "[Regios] Maximum height : " + ChatColor.BLUE + params.getRegionHeightLimit() + ChatColor.RED + ", your height : " + ChatColor.BLUE + height);
			return;
		}
		
		if(length > params.getRegionLengthLimit()){
			p.sendMessage(ChatColor.RED + "[Regios] You cannot create a region of this length!");
			p.sendMessage(ChatColor.RED + "[Regios] Maximum length : " + ChatColor.BLUE + params.getRegionLengthLimit() + ChatColor.RED + ", your length : " + ChatColor.BLUE + length);
			return;
		}
		
		if(rCount > params.getRegionLimit()){
			p.sendMessage(ChatColor.RED + "[Regios] You cannot create more than " + ChatColor.YELLOW + params.getRegionLimit() + ChatColor.RED + " regions!");
			return;
		}

		Region r = new Region(p.getName(), name, point1.get(p), point2.get(p), p.getWorld(), null, true);
		p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + name + ChatColor.GREEN + " created successfully!");
		clearPoints(p);
		modding.put(p, false);
		setting.put(p, false);
		PingManager.created();
		RegionCreateEvent event = new RegionCreateEvent("RegionCreateEvent");
		event.setProperties(p, r);
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	public void createBlueprint(Player p, String name) {
		if (!point1.containsKey(p) || !point2.containsKey(p)) {
			p.sendMessage(ChatColor.RED + "[Regios] You must set 2 points!");
			return;
		}
		StringBuilder invalidName = new StringBuilder();
		boolean integrity = true;
		for (char ch : name.toCharArray()) {
			boolean valid = true;
			for (char inv : invalidModifiers) {
				if (ch == inv) {
					valid = false;
					integrity = false;
				}
			}
			if (!valid) {
				invalidName.append(ChatColor.RED).append(ch);
			} else {
				invalidName.append(ChatColor.GREEN).append(ch);
			}
		}

		if (!integrity) {
			p.sendMessage(ChatColor.RED + "[Regios] Name contained  invalid characters : " + invalidName.toString());
			return;
		}

		RBF_Core.rbf_save.startSave(null, point1.get(p), point2.get(p), name, p, true);
		clearPoints(p);
		modding.put(p, false);
		setting.put(p, false);
	}
	
	public static void createBlueprint(String name, Location l1, Location l2) {
		if (l1 == null || l2 == null) {
			return;
		}
		StringBuilder invalidName = new StringBuilder();
		boolean integrity = true;
		for (char ch : name.toCharArray()) {
			boolean valid = true;
			for (char inv : invalidModifiers) {
				if (ch == inv) {
					valid = false;
					integrity = false;
				}
			}
			if (!valid) {
				invalidName.append(ChatColor.RED).append(ch);
			} else {
				invalidName.append(ChatColor.GREEN).append(ch);
			}
		}

		if (!integrity) {
			return;
		}

		RBF_Core.rbf_save.startSave(null, l1, l2, name, null, true);
	}

	public boolean arePointsSet(Player p) {
		return point1.containsKey(p) && point2.containsKey(p);
	}

	public boolean areModPointsSet(Player p) {
		return mod1.containsKey(p) && mod2.containsKey(p);
	}

	public void expandMaxSelection(Player p) {
		if (point1.containsKey(p) && point2.containsKey(p)) {
			point1.put(p, (new Location(p.getWorld(), point1.get(p).getX(), 0, point1.get(p).getZ())));
			point2.put(p, (new Location(p.getWorld(), point2.get(p).getX(), 128, point2.get(p).getZ())));
			p.sendMessage(ChatColor.GREEN + "[Regios] Selection expanded from bedrock to sky.");
			return;
		} else if (mod1.containsKey(p) && mod2.containsKey(p)) {
			mod1.put(p, (new Location(p.getWorld(), mod1.get(p).getX(), 0, mod1.get(p).getZ())));
			mod2.put(p, (new Location(p.getWorld(), mod2.get(p).getX(), 128, mod2.get(p).getZ())));
			p.sendMessage(ChatColor.GREEN + "[Regios] Selection expanded from bedrock to sky.");
			return;
		} else {
			p.sendMessage(ChatColor.RED + "[Regios] You must set 2 points!");
			return;
		}
	}

	public void setFirst(Player p, Location l) {
		if (p.getItemInHand().getType() == ConfigurationData.defaultSelectionTool) {
			point1.put(p, l);
			p.sendMessage(ChatColor.GREEN + "[Regios]" + ChatColor.BLUE + "[1] " + ChatColor.LIGHT_PURPLE
					+ String.format("X : %d, Y : %d, Z : %d", l.getBlockX(), l.getBlockY(), l.getBlockZ()));
		}
	}

	public void setSecond(Player p, Location l) {
		if (p.getItemInHand().getType() == ConfigurationData.defaultSelectionTool) {
			point2.put(p, l);
			p.sendMessage(ChatColor.GREEN + "[Regios]" + ChatColor.BLUE + "[2] " + ChatColor.LIGHT_PURPLE
					+ String.format("X : %d, Y : %d, Z : %d", l.getBlockX(), l.getBlockY(), l.getBlockZ()));
		}
	}

	public void setFirstMod(Player p, Location l) {
		if (p.getItemInHand().getType() == ConfigurationData.defaultSelectionTool) {
			mod1.put(p, l);
			p.sendMessage(ChatColor.GREEN + "[Regios]" + ChatColor.BLUE + "[1] " + ChatColor.LIGHT_PURPLE
					+ String.format("X : %d, Y : %d, Z : %d", l.getBlockX(), l.getBlockY(), l.getBlockZ()));
		}
	}

	public void setSecondMod(Player p, Location l) {
		if (p.getItemInHand().getType() == ConfigurationData.defaultSelectionTool) {
			mod2.put(p, l);
			p.sendMessage(ChatColor.GREEN + "[Regios]" + ChatColor.BLUE + "[2] " + ChatColor.LIGHT_PURPLE
					+ String.format("X : %d, Y : %d, Z : %d", l.getBlockX(), l.getBlockY(), l.getBlockZ()));
		}
	}

	public static void clearAll(Player p) {
		clearPoints(p);
		if (mod1.containsKey(p)) {
			mod1.remove(p);
		}
		if (mod2.containsKey(p)) {
			mod2.remove(p);
		}
		if (setting.containsKey(p)) {
			setting.remove(p);
		}
		if (modding.containsKey(p)) {
			modding.remove(p);
		}
		if (RegiosPlayerListener.loadingTerrain.containsKey(p)) {
			RegiosPlayerListener.loadingTerrain.remove(p);
		}
		p.sendMessage(ChatColor.RED + "[Regios] Region setting cancelled.");
	}

	public static void clearPoints(Player p) {
		if (point1.containsKey(p)) {
			point1.remove(p);
		}
		if (point2.containsKey(p)) {
			point2.remove(p);
		}
	}

}
