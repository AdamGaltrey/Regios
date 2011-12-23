package couk.Adamki11s.Regios.API;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Commands.CreationCommands;
import couk.Adamki11s.Regios.CustomExceptions.FileExistanceException;
import couk.Adamki11s.Regios.CustomExceptions.InvalidDataSetException;
import couk.Adamki11s.Regios.CustomExceptions.InvalidNBTFormat;
import couk.Adamki11s.Regios.CustomExceptions.RegionExistanceException;
import couk.Adamki11s.Regios.Mutable.MutableModification;
import couk.Adamki11s.Regios.RBF.RBF_Core;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;
import couk.Adamki11s.Regios.Regions.GlobalWorldSetting;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.SpoutInterface.SpoutInterface;

public class RegiosAPI {
	
	private final SaveData sd = new SaveData();

	/**
	 * Get a region by it's name.
	 * 
	 * @param region
	 *            The Region name.
	 * @return Returns the Region object with the corresponding name. Will
	 *         return null if no region with that name exists.
	 */
	public Region getRegion(String region) {
		return GlobalRegionManager.getRegion(region);
	}

	/**
	 * Get the region a player is in.
	 * 
	 * @param p Player.
	 * @return Returns the region that the specified player is in. Will return
	 *         null if the player is not in a region.
	 */
	public Region getRegion(Player p) {
		return GlobalRegionManager.getRegion(p);
	}

	/**
	 * Get a list of all the Regions.
	 * 
	 * @return List of Regions.
	 */
	public ArrayList<Region> getRegions() {
		return GlobalRegionManager.getRegions();
	}

	/**
	 * Return the world setting for the specified world.
	 * 
	 * @param w
	 *            World
	 * @return World Setting.
	 */
	public GlobalWorldSetting getWorldSetting(World w) {
		return GlobalRegionManager.getGlobalWorldSetting(w);
	}

	/**
	 * Get a list of the World Settings.
	 * 
	 * @return World Settings
	 */
	public ArrayList<GlobalWorldSetting> getWorldSettings() {
		return GlobalRegionManager.getWorldSettings();
	}

	/**
	 * Check whether a player is in a region.
	 * 
	 * @param p
	 *            Player.
	 * @return Whether the player is in a region or not.
	 */
	public boolean isInRegion(Player p) {
		return (GlobalRegionManager.getRegion(p) == null ? false : true);
	}

	/**
	 * Check if the player is using Spoutcraft.
	 * 
	 * @param p
	 *            Player
	 * @return Whether the player is running the Spoutcraft launcher.
	 */
	public boolean isSpoutEnabled(Player p) {
		return SpoutInterface.doesPlayerHaveSpout(p);
	}

	/**
	 * Check if Spout is installed on the server.
	 * 
	 * @return Whether Regios is running with Spout support.
	 */
	public boolean isSpoutEnabled() {
		return SpoutInterface.global_spoutEnabled;
	}

	/**
	 * Create a Region
	 * 
	 * @param rds
	 *            Region Data Set.
	 * @throws InvalidDataSetException
	 */
	public void createRegion(RegionDataSet rds) throws InvalidDataSetException {
		CreationCommands.createRegion(rds);
	}

	/**
	 * Rename a Region
	 * 
	 * @param toRename
	 *            The Region to rename.
	 * @param new_name
	 *            New Name.
	 * @throws RegionExistanceException
	 */
	public void renameRegion(Region toRename, String new_name) throws RegionExistanceException {
		MutableModification.renameRegion(toRename, new_name);
	}

	/**
	 * Delete a Region.
	 * 
	 * @param name
	 *            Name of the Region.
	 * @throws RegionExistanceException
	 */
	public void deleteRegion(String name) throws RegionExistanceException {
		MutableModification.deleteRegion(name);
	}

	/**
	 * Create a backup of a Region with the specified Name.
	 * 
	 * @param r
	 *            Region to create a backup of.
	 * @param backupName
	 *            Backup name.
	 */
	public void backupRegion(Region r, String backupName) {
		RBF_Core.rbf_save.startSave(r, null, null, backupName, null, false);
	}

	/**
	 * Restore a Region to its previous state when backed up.
	 * 
	 * @param r
	 *            Region.
	 * @param backupName
	 *            Backup Name.
	 * @throws RegionExistanceException
	 * @throws FileExistanceException
	 * @throws InvalidNBTFormatException
	 */
	public boolean loadBackup(Region r, String backupName) throws RegionExistanceException, FileExistanceException, InvalidNBTFormat {
		try {
			RBF_Core.rbf_load.loadRegion(r, backupName, null);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Create a blueprint file.
	 * 
	 * @param name
	 *            Name of the file.
	 * @param l1
	 *            Point 1.
	 * @param l2
	 *            Point 2.
	 */
	public void saveBlueprint(String name, Location l1, Location l2) {
		CreationCommands.createBlueprint(name, l1, l2);
	}

	/**
	 * Loads a blueprint.
	 * 
	 * @param name
	 *            The name of the blueprint.
	 * @param pasteLocation
	 *            The location to begin the paste.
	 */
	public boolean loadBlueprint(String name, Location pasteLocation) {
		File f = new File("plugins" + File.separator + "Regios" + File.separator + "Blueprints" + File.separator + name + ".blp");
		if (!f.exists()) {
			System.out.println("[Regios] A Blueprint file with the name " + name + " does not exist!");
			return false;
		}
		try {
			RBF_Core.rbf_load_share.loadSharedRegion(name, null, pasteLocation);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public SaveData getSaveData(){
		return this.sd;
	}
	
	
}
