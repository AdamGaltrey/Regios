package couk.Adamki11s.Regios.Mutable;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Commands.AdministrationCommands;
import couk.Adamki11s.Regios.CustomEvents.RegionDeleteEvent;
import couk.Adamki11s.Regios.CustomExceptions.RegionExistanceException;
import couk.Adamki11s.Regios.Data.LoaderCore;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.Regions.RegionLocation;

public class MutableModification {

	private String convertLocation(Location l) {
		return l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ();
	}

	public void editExpandUp(Region r, int value) {
		Location smaller = new Location(r.getL1().getWorld(), Math.min(r.getL1().getX(), r.getL2().getX()), Math.min(r.getL1().getY(), r.getL2().getY()), Math.min(r.getL1()
				.getZ(), r.getL2().getZ()));
		Location bigger = new Location(r.getL1().getWorld(), Math.max(r.getL1().getX(), r.getL2().getX()), Math.max(r.getL1().getY(), r.getL2().getY()), Math.max(r.getL1()
				.getZ(), r.getL2().getZ()));
		bigger.add(0, value, 0);
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Points.Point1");
		all.remove("Region.Essentials.Points.Point2");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(smaller));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(bigger));
		r.setL1(new RegionLocation(smaller.getWorld(), smaller.getBlockX(), smaller.getBlockY(), smaller.getBlockZ()));
		r.setL2(new RegionLocation(bigger.getWorld(), bigger.getBlockX(), bigger.getBlockY(), bigger.getBlockZ()));
		c.save();
	}

	public void editExpandDown(Region r, int value) {
		Location smaller = new Location(r.getL1().getWorld(), Math.min(r.getL1().getX(), r.getL2().getX()), Math.min(r.getL1().getY(), r.getL2().getY()), Math.min(r.getL1()
				.getZ(), r.getL2().getZ()));
		Location bigger = new Location(r.getL1().getWorld(), Math.max(r.getL1().getX(), r.getL2().getX()), Math.max(r.getL1().getY(), r.getL2().getY()), Math.max(r.getL1()
				.getZ(), r.getL2().getZ()));
		smaller.subtract(0, value, 0);
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Points.Point1");
		all.remove("Region.Essentials.Points.Point2");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(smaller));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(bigger));
		r.setL1(new RegionLocation(smaller.getWorld(), smaller.getBlockX(), smaller.getBlockY(), smaller.getBlockZ()));
		r.setL2(new RegionLocation(bigger.getWorld(), bigger.getBlockX(), bigger.getBlockY(), bigger.getBlockZ()));
		c.save();
	}

	public void editShrinkDown(Region r, int value) {
		Location smaller = new Location(r.getL1().getWorld(), Math.min(r.getL1().getX(), r.getL2().getX()), Math.min(r.getL1().getY(), r.getL2().getY()), Math.min(r.getL1()
				.getZ(), r.getL2().getZ()));
		Location bigger = new Location(r.getL1().getWorld(), Math.max(r.getL1().getX(), r.getL2().getX()), Math.max(r.getL1().getY(), r.getL2().getY()), Math.max(r.getL1()
				.getZ(), r.getL2().getZ()));
		bigger.subtract(0, value, 0);
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Points.Point1");
		all.remove("Region.Essentials.Points.Point2");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(smaller));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(bigger));
		r.setL1(new RegionLocation(smaller.getWorld(), smaller.getBlockX(), smaller.getBlockY(), smaller.getBlockZ()));
		r.setL2(new RegionLocation(bigger.getWorld(), bigger.getBlockX(), bigger.getBlockY(), bigger.getBlockZ()));
		c.save();
	}

	public void editShrinkUp(Region r, int value) {
		Location smaller = new Location(r.getL1().getWorld(), Math.min(r.getL1().getX(), r.getL2().getX()), Math.min(r.getL1().getY(), r.getL2().getY()), Math.min(r.getL1()
				.getZ(), r.getL2().getZ()));
		Location bigger = new Location(r.getL1().getWorld(), Math.max(r.getL1().getX(), r.getL2().getX()), Math.max(r.getL1().getY(), r.getL2().getY()), Math.max(r.getL1()
				.getZ(), r.getL2().getZ()));
		smaller.add(0, value, 0);
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Points.Point1");
		all.remove("Region.Essentials.Points.Point2");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(smaller));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(bigger));
		r.setL1(new RegionLocation(smaller.getWorld(), smaller.getBlockX(), smaller.getBlockY(), smaller.getBlockZ()));
		r.setL2(new RegionLocation(bigger.getWorld(), bigger.getBlockX(), bigger.getBlockY(), bigger.getBlockZ()));
		c.save();
	}

	public void editExpandMax(Region r) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Points.Point1");
		all.remove("Region.Essentials.Points.Point2");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		r.getL1().setY(0);
		r.getL2().setY(128);
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(r.getL1().toBukkitLocation()));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(r.getL2().toBukkitLocation()));
		c.save();
	}

	public void editModifyPoints(Region r, Location l1, Location l2) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Points.Point1");
		all.remove("Region.Essentials.Points.Point2");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(l1));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(l2));
		r.setL1(l1.getWorld(), l1.getX(), l1.getY(), l1.getY());
		r.setL1(l2.getWorld(), l2.getX(), l2.getY(), l2.getY());
		c.save();
	}

	public void editExpandOut(Region r, int expand) {
		Location smaller = new Location(r.getL1().getWorld(), Math.min(r.getL1().getX(), r.getL2().getX()), Math.min(r.getL1().getY(), r.getL2().getY()), Math.min(r.getL1()
				.getZ(), r.getL2().getZ()));
		Location bigger = new Location(r.getL1().getWorld(), Math.max(r.getL1().getX(), r.getL2().getX()), Math.max(r.getL1().getY(), r.getL2().getY()), Math.max(r.getL1()
				.getZ(), r.getL2().getZ()));
		smaller.subtract(expand, 0, expand);
		bigger.add(expand, 0, expand);
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Points.Point1");
		all.remove("Region.Essentials.Points.Point2");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(smaller));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(bigger));
		r.setL1(new RegionLocation(smaller.getWorld(), smaller.getBlockX(), smaller.getBlockY(), smaller.getBlockZ()));
		r.setL2(new RegionLocation(bigger.getWorld(), bigger.getBlockX(), bigger.getBlockY(), bigger.getBlockZ()));
		c.save();
	}

	public void editShrinkIn(Region r, int shrink) {
		Location smaller = new Location(r.getL1().getWorld(), Math.min(r.getL1().getX(), r.getL2().getX()), Math.min(r.getL1().getY(), r.getL2().getY()), Math.min(r.getL1()
				.getZ(), r.getL2().getZ()));
		Location bigger = new Location(r.getL1().getWorld(), Math.max(r.getL1().getX(), r.getL2().getX()), Math.max(r.getL1().getY(), r.getL2().getY()), Math.max(r.getL1()
				.getZ(), r.getL2().getZ()));
		smaller.add(shrink, 0, shrink);
		bigger.subtract(shrink, 0, shrink);
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Points.Point1");
		all.remove("Region.Essentials.Points.Point2");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(smaller));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(bigger));
		r.setL1(new RegionLocation(smaller.getWorld(), smaller.getBlockX(), smaller.getBlockY(), smaller.getBlockZ()));
		r.setL2(new RegionLocation(bigger.getWorld(), bigger.getBlockX(), bigger.getBlockY(), bigger.getBlockZ()));
		c.save();
	}

	public void editRename(Region r, String new_name, Player p) {

		if (GlobalRegionManager.getRegion(new_name) != null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + new_name + ChatColor.RED + " already exists!");
			return;
		}

		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Name");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Name", new_name);
		c.save();
		
		
		
		c = r.getConfigFile();
		c.load();
		Map<String, Object> construct = c.getAll();
		
		File conf = r.getRawConfigFile();
		conf.delete();
		File newConf = new File(r.getDirectory() + File.separator + new_name + ".rz");
		
		try {
			newConf.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		c = new Configuration(newConf);
		for (Entry<String, Object> cont : construct.entrySet()) {
			c.setProperty(cont.getKey(), cont.getValue());
		}
		c.save();
		
		r.getDirectory().renameTo(new File("plugins" + File.separator + "Regios" + File.separator + "Database" + File.separator + new_name));
		
		new AdministrationCommands().reloadRegions(p);
	}
	
	public static void renameRegion(Region r, String new_name) throws RegionExistanceException {

		if (GlobalRegionManager.getRegion(new_name) != null) {
			throw new RegionExistanceException(new_name);
		}

		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Name");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Name", new_name);
		c.save();
		
		
		
		c = r.getConfigFile();
		c.load();
		Map<String, Object> construct = c.getAll();
		
		File conf = r.getRawConfigFile();
		conf.delete();
		File newConf = new File(r.getDirectory() + File.separator + new_name + ".rz");
		
		try {
			newConf.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		c = new Configuration(newConf);
		for (Entry<String, Object> cont : construct.entrySet()) {
			c.setProperty(cont.getKey(), cont.getValue());
		}
		c.save();
		
		r.getDirectory().renameTo(new File("plugins" + File.separator + "Regios" + File.separator + "Database" + File.separator + new_name));
		
		new AdministrationCommands().reloadRegions(null);
	}

	final static LoaderCore lc = new LoaderCore();

	public static void editDeleteRegion(Region r, boolean reload, Player p) {
		File f = r.getLogFile().getParentFile().getParentFile();
		deleteDir(f);
		GlobalRegionManager.deleteRegionFromCache(r);
		RegionDeleteEvent event = new RegionDeleteEvent("RegionDeleteEvent");
		event.setProperties(p, r);
		Bukkit.getServer().getPluginManager().callEvent(event);
	}
	
	public static void deleteRegion(String name) throws RegionExistanceException{
		Region r = GlobalRegionManager.getRegion(name);
		if(r == null){
			throw new RegionExistanceException(name);
		}
		File f = r.getLogFile().getParentFile().getParentFile();
		deleteDir(f);
		GlobalRegionManager.deleteRegionFromCache(r);
		RegionDeleteEvent event = new RegionDeleteEvent("RegionDeleteEvent");
		event.setProperties(null, r);
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}

}
