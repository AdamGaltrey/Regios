package couk.Adamki11s.Regios.API;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class RegionDataSet {

	private Plugin plugin;
	private String name, owner, world;
	private Location l1, l2;

	public RegionDataSet(Plugin plugin, String name, String owner, String world, Location l1, Location l2) {
		this.plugin = plugin;
		this.name = name;
		this.owner = owner;
		this.world = world;
		this.l1 = l1;
		this.l2 = l2;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public Location getL1() {
		return l1;
	}

	public void setL1(Location l1) {
		this.l1 = l1;
	}

	public Location getL2() {
		return l2;
	}

	public void setL2(Location l2) {
		this.l2 = l2;
	}

}
