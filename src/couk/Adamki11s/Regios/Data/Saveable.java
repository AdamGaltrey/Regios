package couk.Adamki11s.Regios.Data;

import java.io.File;
import java.io.IOException;

import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.Regions.RegionLocation;

public class Saveable {
	
	private final File root = new File("plugins" + File.separator + "Regios"),
	 db_root = new File(root + File.separator + "Database");
	
	public synchronized void saveRegion(Region r, RegionLocation rl1, RegionLocation rl2){
		
		File region_root = new File(db_root + File.separator + r.getName());
		File region_core = new File(region_root + File.separator + r.getName() + ".rz");
		
		region_root.mkdir();
		
		try {
			region_core.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Saving Region '" + r.getName() + "' @ " + region_core.getAbsoluteFile().toString());
		
		Configuration c = new Configuration(region_core);
		
		c.setProperty("Region.Messages.WelcomeMessage", r.getWelcomeMessage().toString());
		c.setProperty("Region.Messages.LeaveMessage", r.getLeaveMessage().toString());
		c.setProperty("Region.Messages.ProtectionMessage", r.getProtectionMessage().toString());
		c.setProperty("Region.Messages.PreventEntryMessage", r.getPreventEntryMessage().toString());
		c.setProperty("Region.Messages.PreventExitMessage", r.getPreventExitMessage().toString());
		c.setProperty("Region.Messages.ShowPvpWarning", Boolean.valueOf(r.isShowPvpWarning()));
		
		c.setProperty("Region.Messages.ShowWelcomeMessage", Boolean.valueOf(r.isShowWelcomeMessage()));
		c.setProperty("Region.Messages.ShowLeaveMessage", Boolean.valueOf(r.isShowLeaveMessage()));
		c.setProperty("Region.Messages.ShowProtectionMessage", Boolean.valueOf(r.isShowProtectionMessage()));
		c.setProperty("Region.Messages.ShowPreventEntryMessage", Boolean.valueOf(r.isShowPreventEntryMessage()));
		c.setProperty("Region.Messages.ShowPreventExitMessage", Boolean.valueOf(r.isShowPreventExitMessage()));
		
		c.setProperty("Region.Modes.ItemControlMode", r.getItemMode().toString());
		c.setProperty("Region.Modes.ProtectionMode", r.getProtectionMode().toString());
		c.setProperty("Region.Modes.PreventEntryMode", r.getPreventEntryMode().toString());
		c.setProperty("Region.Modes.PreventExitMode", r.getPreventExitMode().toString());
		
		c.setProperty("Region.Inventory.PermWipeOnEnter", r.isPermWipeOnEnter());
		c.setProperty("Region.Inventory.PermWipeOnExit", r.isPermWipeOnExit());
		c.setProperty("Region.Inventory.WipeAndCacheOnEnter", r.isWipeAndCacheOnEnter());
		c.setProperty("Region.Inventory.WipeAndCacheOnExit", r.isWipeAndCacheOnExit());
		
		c.setProperty("Region.Command.ForceCommand", r.isForceCommand());
		c.setProperty("Region.Command.CommandSet", "");
		
		c.setProperty("Region.Permissions.TemporaryCache.AddNodes", "");
		c.setProperty("Region.Permissions.PermanentCache.AddNodes", "");
		c.setProperty("Region.Permissions.PermanentCache.RemoveNodes", "");
		
		c.setProperty("Region.General.Protected.General", Boolean.valueOf(r.is_protection()));
		c.setProperty("Region.General.Protected.BlockBreak", Boolean.valueOf(r.is_protectionBreak()));
		c.setProperty("Region.General.Protected.BlockPlace", Boolean.valueOf(r.is_protectionPlace()));
		c.setProperty("Region.General.FireProtection", Boolean.valueOf(r.isFireProtection()));
		c.setProperty("Region.General.PreventEntry", Boolean.valueOf(r.isPreventEntry()));
		c.setProperty("Region.General.PreventExit", Boolean.valueOf(r.isPreventExit()));
		c.setProperty("Region.General.PreventInteraction", Boolean.valueOf(r.isPreventInteraction()));
		c.setProperty("Region.General.DoorsLocked", Boolean.valueOf(r.isDoorsLocked()));
		c.setProperty("Region.General.ChestsLocked", Boolean.valueOf(r.isChestsLocked()));
		c.setProperty("Region.General.Password.Enabled", Boolean.valueOf(r.isPasswordEnabled()));
		
		if(r.getPassword().length() > 3){
			c.setProperty("Region.General.Password.Password", r.getExCrypt().computeSHA2_384BitHash(r.getPassword().toString()));
		} else {
			c.setProperty("Region.General.Password.Password", "");
		}
		
		c.setProperty("Region.Other.MobSpawns", Boolean.valueOf(r.isMobSpawns()));
		c.setProperty("Region.Other.MonsterSpawns", Boolean.valueOf(r.isMonsterSpawns()));
		c.setProperty("Region.Other.PvP", Boolean.valueOf(r.isPvp()));
		c.setProperty("Region.Other.HealthEnabled", Boolean.valueOf(r.isHealthEnabled()));
		c.setProperty("Region.Other.HealthRegen", r.getHealthRegen());
		c.setProperty("Region.Other.LSPS", r.getLSPS());
		c.setProperty("Region.Other.VelocityWarp", r.getVelocityWarp());
		
		c.setProperty("Region.Essentials.Owner", r.getOwner().toString());
		c.setProperty("Region.Essentials.SubOwners", "");
		c.setProperty("Region.Essentials.Name", r.getName().toString());
		c.setProperty("Region.Essentials.World", r.getWorld().toString());
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(rl1));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(rl2));
		
		c.setProperty("Region.Spout.Welcome.Enabled", r.isSpoutWelcomeEnabled());
		c.setProperty("Region.Spout.Leave.Enabled", r.isSpoutWelcomeEnabled());
		c.setProperty("Region.Spout.Welcome.Message", r.getSpoutEntryMessage());
		c.setProperty("Region.Spout.Welcome.IconID", r.getSpoutEntryMaterial().getId());	
		c.setProperty("Region.Spout.Leave.Message", r.getSpoutExitMessage());
		c.setProperty("Region.Spout.Leave.IconID", r.getSpoutExitMaterial().getId());
		c.setProperty("Region.Spout.Sound.PlayCustomMusic", r.isPlayCustomSoundUrl());
		c.setProperty("Region.Spout.Sound.CustomMusicURL", r.getCustomSoundUrl());
		c.setProperty("Region.Spout.Texture.UseTexture", r.isUseSpoutTexturePack());
		c.setProperty("Region.Spout.Texture.TexturePackURL", r.getCustomSoundUrl());
		
		c.setProperty("Region.Economy.ForSale", r.isForSale());
		c.setProperty("Region.Economy.Price", r.getSalePrice());
		
		c.setProperty("Region.Teleportation.Warp.Location", r.getWorld() + ",0,0,0");
		
		c.setProperty("Region.Block.BlockForm.Enabled", r.isBlockForm());
		c.setProperty("Region.General.PlayerCap.Cap", r.getPlayerCap());
		
		c.save();
		
		new File(region_root + File.separator + "Exceptions").mkdir();
		new File(region_root + File.separator + "Exceptions" + File.separator + "Players").mkdir();
		new File(region_root + File.separator + "Exceptions" + File.separator + "Nodes").mkdir();
		new File(region_root + File.separator + "Items").mkdir();
		new File(region_root + File.separator + "Backups").mkdir();
		new File(region_root + File.separator + "Logs").mkdir();
		try {
			new File(region_root + File.separator + "Logs" + File.separator + r.getName() + ".log").createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			new File(region_root + File.separator + "Exceptions" + File.separator + "Players" + File.separator + r.getOwner() + ".excep").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Region '" + r.getName() + "' saved successfully!");
	}
	
	public synchronized void updateInheritedRegion(Region r, RegionLocation rl1, RegionLocation rl2) throws IOException{
		
		this.deleteRegion(r.getName());
		
		File region_root = new File(db_root + File.separator + r.getName());
		File region_core = new File(region_root + File.separator + r.getName() + ".rz");
		
		region_root.mkdir();
		
		try {
			region_core.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Saving Region '" + r.getName() + "' @ " + region_core.getAbsoluteFile().toString());
		
		Configuration c = new Configuration(region_core);
		
		c.setProperty("Region.Messages.WelcomeMessage", r.getWelcomeMessage().toString());
		c.setProperty("Region.Messages.LeaveMessage", r.getLeaveMessage().toString());
		c.setProperty("Region.Messages.ProtectionMessage", r.getProtectionMessage().toString());
		c.setProperty("Region.Messages.PreventEntryMessage", r.getPreventEntryMessage().toString());
		c.setProperty("Region.Messages.PreventExitMessage", r.getPreventExitMessage().toString());
		c.setProperty("Region.Messages.ShowPvpWarning", Boolean.valueOf(r.isShowPvpWarning()));
		
		c.setProperty("Region.Messages.ShowWelcomeMessage", Boolean.valueOf(r.isShowWelcomeMessage()));
		c.setProperty("Region.Messages.ShowLeaveMessage", Boolean.valueOf(r.isShowLeaveMessage()));
		c.setProperty("Region.Messages.ShowProtectionMessage", Boolean.valueOf(r.isShowProtectionMessage()));
		c.setProperty("Region.Messages.ShowPreventEntryMessage", Boolean.valueOf(r.isShowPreventEntryMessage()));
		c.setProperty("Region.Messages.ShowPreventExitMessage", Boolean.valueOf(r.isShowPreventExitMessage()));
		
		c.setProperty("Region.Modes.ItemControlMode", r.getItemMode().toString());
		c.setProperty("Region.Modes.ProtectionMode", r.getProtectionMode().toString());
		c.setProperty("Region.Modes.PreventEntryMode", r.getPreventEntryMode().toString());
		c.setProperty("Region.Modes.PreventExitMode", r.getPreventExitMode().toString());
		
		c.setProperty("Region.Inventory.PermWipeOnEnter", r.isPermWipeOnEnter());
		c.setProperty("Region.Inventory.PermWipeOnExit", r.isPermWipeOnExit());
		c.setProperty("Region.Inventory.WipeAndCacheOnEnter", r.isWipeAndCacheOnEnter());
		c.setProperty("Region.Inventory.WipeAndCacheOnExit", r.isWipeAndCacheOnExit());
		
		c.setProperty("Region.Command.ForceCommand", r.isForceCommand());
		StringBuilder sb = new StringBuilder();
		for(String s : r.getCommandSet()){
			sb.append(s).append(",");
		}
		c.setProperty("Region.Command.CommandSet", sb.toString());
		sb.replace(0, sb.length(), "");
		
		for(String s : r.getTempCacheNodes()){
			sb.append(s).append(",");
		}
		
		c.setProperty("Region.Permissions.TemporaryCache.AddNodes", sb.toString());
		sb.replace(0, sb.length(), "");
		
		for(String s : r.getPermanentNodesCacheAdd()){
			sb.append(s).append(",");
		}
		
		c.setProperty("Region.Permissions.PermanentCache.AddNodes", sb.toString());
		sb.replace(0, sb.length(), "");
		
		for(String s : r.getPermanentNodesCacheRemove()){
			sb.append(s).append(",");
		}
		
		c.setProperty("Region.Permissions.PermanentCache.RemoveNodes", sb.toString());
		sb.replace(0, sb.length(), "");
		
		c.setProperty("Region.General.Protected", Boolean.valueOf(r.is_protection()));
		c.setProperty("Region.General.FireProtection", Boolean.valueOf(r.isFireProtection()));
		c.setProperty("Region.General.PreventEntry", Boolean.valueOf(r.isPreventEntry()));
		c.setProperty("Region.General.PreventExit", Boolean.valueOf(r.isPreventExit()));
		c.setProperty("Region.General.PreventInteraction", Boolean.valueOf(r.isPreventInteraction()));
		c.setProperty("Region.General.DoorsLocked", Boolean.valueOf(r.isDoorsLocked()));
		c.setProperty("Region.General.ChestsLocked", Boolean.valueOf(r.isChestsLocked()));
		c.setProperty("Region.General.Password.Enabled", Boolean.valueOf(r.isPasswordEnabled()));
		
		if(r.getPassword().length() > 3){
			c.setProperty("Region.General.Password.Password", r.getExCrypt().computeSHA2_384BitHash(r.getPassword().toString()));
		} else {
			c.setProperty("Region.General.Password.Password", "");
		}
		
		c.setProperty("Region.Other.MobSpawns", Boolean.valueOf(r.isMobSpawns()));
		c.setProperty("Region.Other.MonsterSpawns", Boolean.valueOf(r.isMonsterSpawns()));
		c.setProperty("Region.Other.PvP", Boolean.valueOf(r.isPvp()));
		c.setProperty("Region.Other.HealthEnabled", Boolean.valueOf(r.isHealthEnabled()));
		c.setProperty("Region.Other.HealthRegen", r.getHealthRegen());
		c.setProperty("Region.Other.LSPS", r.getLSPS());
		c.setProperty("Region.Other.VelocityWarp", r.getVelocityWarp());
		
		c.setProperty("Region.Essentials.Owner", r.getOwner().toString());
		
		for(String s : r.getSubOwners()){
			sb.append(s).append(",");
		}
		
		c.setProperty("Region.Essentials.SubOwners", sb.toString());
		sb.replace(0, sb.length(), "");
		
		c.setProperty("Region.Essentials.Name", r.getName().toString());
		c.setProperty("Region.Essentials.World", r.getWorld().toString());
		c.setProperty("Region.Essentials.Points.Point1", convertLocation(rl1));
		c.setProperty("Region.Essentials.Points.Point2", convertLocation(rl2));
		
		c.setProperty("Region.Spout.Welcome.Message", r.getSpoutEntryMessage());
		c.setProperty("Region.Spout.Welcome.IconID", r.getSpoutEntryMaterial().getId());	
		c.setProperty("Region.Spout.Leave.Message", r.getSpoutExitMessage());
		c.setProperty("Region.Spout.Leave.IconID", r.getSpoutExitMaterial().getId());
		c.setProperty("Region.Spout.Sound.PlayCustomMusic", r.isPlayCustomSoundUrl());
		c.setProperty("Region.Spout.Sound.CustomMusicURL", r.getCustomSoundUrl());
		c.setProperty("Region.Spout.Texture.UseTexture", r.isUseSpoutTexturePack());
		c.setProperty("Region.Spout.Texture.TexturePackURL", r.getCustomSoundUrl());
		
		c.setProperty("Region.Economy.ForSale", r.isForSale());
		c.setProperty("Region.Economy.Price", r.getSalePrice());
		
		c.setProperty("Region.Spout.Welcome.Enabled", r.isSpoutWelcomeEnabled());
		c.setProperty("Region.Spout.Leave.Enabled", r.isSpoutWelcomeEnabled());
		
		c.setProperty("Region.Teleportation.Warp.Location", r.getWarp().getWorld() + "," + r.getWarp().getBlockX() + "," + r.getWarp().getBlockY() + "," + r.getWarp().getBlockZ());
		
		c.setProperty("Region.Block.BlockForm.Enabled", r.isBlockForm());
		c.setProperty("Region.General.PlayerCap.Cap", r.getPlayerCap());
		
		c.save();
		
		new File(region_root + File.separator + "Exceptions").mkdir();
		new File(region_root + File.separator + "Exceptions" + File.separator + "Players").mkdir();
		new File(region_root + File.separator + "Exceptions" + File.separator + "Nodes").mkdir();
		new File(region_root + File.separator + "Items").mkdir();
		new File(region_root + File.separator + "Backups").mkdir();
		new File(region_root + File.separator + "Logs").mkdir();
		
		for(String ex : r.getExceptions()){
			new File(region_root + File.separator + "Exceptions" + File.separator + "Players" + File.separator + ex + ".excep").createNewFile();
		}
		
		for(int ex : r.getItems()){
			new File(region_root + File.separator + "Items" + File.separator + ex + ".excep").createNewFile();
		}
		
		for(String ex : r.getNodes()){
			new File(region_root + File.separator + "Exceptions" + File.separator + "Nodes" + File.separator + ex + ".excep").createNewFile();
		}
		
		try {
			new File(region_root + File.separator + "Logs" + File.separator + r.getName() + ".log").createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			new File(region_root + File.separator + "Exceptions" + File.separator + "Players" + File.separator + r.getOwner() + ".excep").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Region '" + r.getName() + "' saved successfully!");
	}
	
	public String convertLocation(RegionLocation l){
		return l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ();
	}
	
	public void deleteRegion(String s){
		if(doesRegionExist(s)){
			File f = new File(db_root + File.separator + s);
			f.delete();
		}
	}
	
	public boolean doesRegionExist(String s){
		File f = new File(db_root + File.separator + s);
		if(f.exists()){
			return true;
		} else {
			return false;
		}
	}

}
