package couk.Adamki11s.Regios.Mutable;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Data.LoaderCore;
import couk.Adamki11s.Regios.Data.Saveable;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;
import couk.Adamki11s.Regios.Regions.Region;

public class MutableAdministration extends Saveable {

	final LoaderCore lc = new LoaderCore();

	public void setOwner(Region r, String owner) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Essentials.Owner");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Essentials.Owner", owner);
		r.setOwner(owner);
		c.save();
	}

	public void reloadRegions() {
		GlobalRegionManager.purgeWorldSettings();
		lc.silentReload();
	}

	public void reloadConfig() {
		GlobalRegionManager.purgeWorldSettings();
		lc.loadConfiguration();
	}

	public void reloadAll() {
		GlobalRegionManager.purgeWorldSettings();
		lc.silentReload();
		lc.loadConfiguration();
	}

	public String listRegions() {
		StringBuilder sb = new StringBuilder();
		int build = 0;
		for (File f : new File("plugins" + File.separator + "Regios" + File.separator + "Database").listFiles()) {
			build++;
			sb.append(ChatColor.WHITE).append(f.getName().trim()).append(ChatColor.BLUE).append(", ");
		}
		if (build == 0) {
			return ChatColor.RED + "[Regios] No Regions Found!";
		} else {
			return sb.toString();
		}
	}

	public void inherit(Region tin, Region inf) {
		tin.set_protection(inf.is_protection());
		tin.setBlockForm(inf.isBlockForm());
		tin.setChestsLocked(inf.isChestsLocked());
		tin.setCommandSet(inf.getCommandSet());
		tin.setCustomSoundUrl(inf.getCustomSoundUrl());
		tin.setDoorsLocked(inf.isDoorsLocked());
		tin.setExceptions(inf.getExceptions());
		tin.setFireProtection(inf.isFireProtection());
		tin.setForceCommand(inf.isForceCommand());
		tin.setForSale(inf.isForSale());
		tin.setHealthEnabled(inf.isHealthEnabled());
		tin.setHealthRegen(inf.getHealthRegen());
		tin.setItemMode(inf.getItemMode());
		tin.setItems(inf.getItems());
		tin.setLeaveMessage(inf.getLeaveMessage());
		tin.setLSPS(inf.getLSPS());
		tin.setMobSpawns(inf.isMobSpawns());
		tin.setMonsterSpawns(inf.isMonsterSpawns());
		tin.setNodes(inf.getNodes());
		tin.setPassword(inf.getPassword());
		tin.setPasswordEnabled(inf.isPasswordEnabled());
		tin.setPermanentNodesCacheAdd(inf.getPermanentNodesCacheAdd());
		tin.setPermanentNodesCacheRemove(inf.getPermanentNodesCacheRemove());
		tin.setPermWipeOnEnter(inf.isPermWipeOnEnter());
		tin.setPermWipeOnExit(inf.isPermWipeOnExit());
		tin.setPlayCustomSoundUrl(inf.isPlayCustomSoundUrl());
		tin.setPlayerCap(inf.getPlayerCap());
		tin.setPreventEntry(inf.isPreventEntry());
		tin.setPreventEntryMessage(inf.getPreventEntryMessage());
		tin.setPreventEntryMode(inf.getPreventEntryMode());
		tin.setPreventExit(inf.isPreventExit());
		tin.setPreventExitMessage(inf.getPreventExitMessage());
		tin.setPreventExitMode(inf.getPreventExitMode());
		tin.setPreventInteraction(inf.isPreventInteraction());
		tin.setProtectionMessage(inf.getProtectionMessage());
		tin.setProtectionMode(inf.getProtectionMode());
		tin.setPvp(inf.isPvp());
		tin.setSalePrice(inf.getSalePrice());
		tin.setShowLeaveMessage(inf.isShowLeaveMessage());
		tin.setShowPreventEntryMessage(inf.isShowPreventEntryMessage());
		tin.setShowPreventExitMessage(inf.isShowPreventExitMessage());
		tin.setShowProtectionMessage(inf.isShowProtectionMessage());
		tin.setShowPvpWarning(inf.isShowPvpWarning());
		tin.setShowWelcomeMessage(inf.isShowWelcomeMessage());
		tin.setSpoutEntryMaterial(inf.getSpoutEntryMaterial());
		tin.setSpoutEntryMessage(inf.getSpoutEntryMessage());
		tin.setSpoutExitMaterial(inf.getSpoutExitMaterial());
		tin.setSpoutExitMessage(inf.getSpoutExitMessage());
		tin.setSpoutTexturePack(inf.getSpoutTexturePack());
		tin.setSubOwners(inf.getSubOwners());
		tin.setTemporaryNodesCacheAdd(inf.getTemporaryNodesCacheAdd());
		tin.setUseSpoutTexturePack(inf.isUseSpoutTexturePack());
		tin.setVelocityWarp(inf.getVelocityWarp());
		tin.setWarp(inf.getWarp());
		tin.setWelcomeMessage(inf.getWelcomeMessage());
		tin.setWipeAndCacheOnEnter(inf.isWipeAndCacheOnEnter());
		tin.setWipeAndCacheOnExit(inf.isWipeAndCacheOnExit());
		tin.setWorld(inf.getWorld());
		try {
			super.updateInheritedRegion(tin, tin.getL1(), tin.getL2());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
