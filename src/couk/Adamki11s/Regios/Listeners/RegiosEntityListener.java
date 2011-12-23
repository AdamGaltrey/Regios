package couk.Adamki11s.Regios.Listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.painting.PaintingBreakByEntityEvent;
import org.bukkit.event.painting.PaintingBreakEvent;
import org.bukkit.event.painting.PaintingPlaceEvent;

import couk.Adamki11s.Extras.Regions.ExtrasRegions;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;
import couk.Adamki11s.Regios.Regions.GlobalWorldSetting;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.Regions.SubRegionManager;
import couk.Adamki11s.Regios.Scheduler.HealthRegeneration;
import couk.Adamki11s.Regios.Scheduler.LogRunner;

public class RegiosEntityListener extends EntityListener {

	private static final ExtrasRegions extReg = new ExtrasRegions();
	private static final SubRegionManager srm = new SubRegionManager();

	public void onCreatureSpawn(CreatureSpawnEvent evt) {

		Location l = evt.getEntity().getLocation();
		World w = l.getWorld();
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
			if (GlobalRegionManager.getGlobalWorldSetting(w) != null) {
				if (!GlobalRegionManager.getGlobalWorldSetting(w).canCreatureSpawn(evt.getCreatureType())) {
					evt.setCancelled(true);
				}
				return;
			}
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
			if (GlobalRegionManager.getGlobalWorldSetting(w) != null) {
				if (!GlobalRegionManager.getGlobalWorldSetting(w).canCreatureSpawn(evt.getCreatureType())) {
					evt.setCancelled(true);
				}
			}
			return;
		}

		if (currentRegionSet.size() > 1) {
			r = srm.getCurrentRegion(null, currentRegionSet);
		} else {
			r = currentRegionSet.get(0);
		}

		if (!r.canMobsSpawn()) {
			CreatureType ce = evt.getCreatureType();
			if (ce == CreatureType.CHICKEN || ce == CreatureType.COW || ce == CreatureType.PIG || ce == CreatureType.SHEEP || ce == CreatureType.SQUID) {
				LogRunner.addLogMessage(r, LogRunner.getPrefix(r) + (" Mob '" + ce.getName() + "' tried to spawn but was prevented."));
				evt.setCancelled(true);
				return;
			}
		}

		if (!r.canMonstersSpawn()) {
			CreatureType ce = evt.getCreatureType();
			if (ce != CreatureType.CHICKEN && ce != CreatureType.COW && ce != CreatureType.PIG && ce != CreatureType.SHEEP && ce != CreatureType.SQUID) {
				LogRunner.addLogMessage(r, LogRunner.getPrefix(r) + (" Monster '" + ce.getName() + "' tried to spawn but was prevented."));
				evt.setCancelled(true);
				return;
			}
		}
	}

	public void onEntityDeath(EntityDeathEvent evt) {
		Entity e = evt.getEntity();
		if (e instanceof Player) {
			Player p = (Player) e;
			if (HealthRegeneration.isRegenerator(p)) {
				HealthRegeneration.removeRegenerator(p);
			}
		}
	}

	public void onPaintingBreak(PaintingBreakEvent evt) {

		Player cause;

		if (!(evt instanceof PaintingBreakByEntityEvent)) {
			return;
		}

		PaintingBreakByEntityEvent event = (PaintingBreakByEntityEvent) evt;
		if (!(event.getRemover() instanceof Player)) {
			return;
		}

		cause = (Player) event.getRemover();

		Location l = evt.getPainting().getLocation();
		World w = l.getWorld();
		Chunk c = w.getChunkAt(l);

		GlobalWorldSetting gws = GlobalRegionManager.getGlobalWorldSetting(w);

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
			if (gws != null) {
				if (gws.invert_protection) {
					evt.setCancelled(true);
					return;
				}
			}
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
			if (gws != null) {
				if (gws.invert_protection) {
					evt.setCancelled(true);
					return;
				}
			}
			return;
		}

		if (currentRegionSet.size() > 1) {
			r = srm.getCurrentRegion(null, currentRegionSet);
		} else {
			r = currentRegionSet.get(0);
		}

		if (r.is_protectionBreak()) {
			if (!r.canBuild(cause)) {
				LogRunner.addLogMessage(r, LogRunner.getPrefix(r) + (" Painting break was prevented."));
				r.sendBuildMessage(cause);
				evt.setCancelled(true);
				return;
			}
		}

	}

	public void onPaintingPlace(PaintingPlaceEvent evt) {

		Player cause = evt.getPlayer();

		Location l = evt.getPainting().getLocation();
		World w = l.getWorld();
		Chunk c = w.getChunkAt(l);

		GlobalWorldSetting gws = GlobalRegionManager.getGlobalWorldSetting(w);

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
			if (gws != null) {
				if (gws.invert_protection) {
					evt.setCancelled(true);
					return;
				}
			}
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
			if (gws != null) {
				if (gws.invert_protection) {
					evt.setCancelled(true);
					return;
				}
			}
			return;
		}

		if (currentRegionSet.size() > 1) {
			r = srm.getCurrentRegion(null, currentRegionSet);
		} else {
			r = currentRegionSet.get(0);
		}

		if (r.is_protectionPlace()) {
			if (!r.canBuild(cause)) {
				LogRunner.addLogMessage(r, LogRunner.getPrefix(r) + (" Painting place was prevented."));
				r.sendBuildMessage(cause);
				evt.setCancelled(true);
				return;
			}
		}

	}


	
	public void onExplosionPrime(ExplosionPrimeEvent evt) {

		Location l = evt.getEntity().getLocation();
		World w = l.getWorld();
		Chunk c = w.getChunkAt(l);

		if (evt.getEntity() instanceof Creeper) {
			if (GlobalRegionManager.getGlobalWorldSetting(w) != null) {
				if (!GlobalRegionManager.getGlobalWorldSetting(w).creeperExplodes) {
					evt.setCancelled(true);
					evt.setRadius(0);
					return;
				}
			}
		}

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
			return;
		}

		ArrayList<Region> currentRegionSet = new ArrayList<Region>();

		for (Region reg : regionSet) {
			Location rl1 = reg.getL1().toBukkitLocation(), rl2 = reg.getL2().toBukkitLocation();
			if (rl1.getX() > rl2.getX()) {
				rl2.subtract(6, 0, 0);
				rl1.add(6, 0, 0);
			} else {
				rl2.add(6, 0, 0);
				rl1.subtract(6, 0, 0);
			}
			if (rl1.getZ() > rl2.getZ()) {
				rl2.subtract(0, 0, 6);
				rl1.add(0, 0, 6);
			} else {
				rl2.add(0, 0, 6);
				rl1.subtract(0, 0, 6);
			}
			if (rl1.getY() > rl2.getY()) {
				rl2.subtract(0, 10, 0);
				rl1.add(0, 10, 0);
			} else {
				rl2.add(0, 10, 0);
				rl1.subtract(0, 10, 0);
			}
			if (extReg.isInsideCuboid(l, rl1, rl2)) {
				currentRegionSet.add(reg);
			}
		}

		if (currentRegionSet.isEmpty()) { // If player is in chunk range but not
											// inside region then cancel the
											// check.
			return;
		} else {
			for (Region r : currentRegionSet) {
				if (r.is_protection()) {
					LogRunner.addLogMessage(r, LogRunner.getPrefix(r) + (" Entity explosion was prevented."));
					evt.setCancelled(true);
					evt.setRadius(0);
					return;
				}
			}
		}

	}

	public void onEntityDamage(EntityDamageEvent evt) {

		if (!(evt.getEntity() instanceof Player)) {
			return;
		}

		Location l = evt.getEntity().getLocation();
		World w = l.getWorld();
		Chunk c = w.getChunkAt(l);
		Player p = (Player) evt.getEntity();

		GlobalWorldSetting gws = GlobalRegionManager.getGlobalWorldSetting(w);

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
			return;
		}

		ArrayList<Region> currentRegionSet = new ArrayList<Region>();

		for (Region reg : regionSet) {
			Location rl1 = reg.getL1().toBukkitLocation(), rl2 = reg.getL2().toBukkitLocation();
			if (extReg.isInsideCuboid(l, rl1, rl2)) {
				currentRegionSet.add(reg);
			}
		}

		if (currentRegionSet.isEmpty()) { // If player is in chunk range but not
											// inside region then cancel the
											// check.
			if (gws != null) {
				if (!gws.invert_pvp && gws.overridingPvp) {
					evt.setCancelled(true);
					return;
				}
			}
			return;
		}

		if (currentRegionSet.size() > 1) {
			r = srm.getCurrentRegion(p, currentRegionSet);
		} else {
			r = currentRegionSet.get(0);
		}

		if (!r.isHealthEnabled()) {
			evt.setCancelled(true);
			evt.setDamage(0);
			return;
		}

		if (!r.isPvpEnabled()) {
			if (evt instanceof EntityDamageByEntityEvent) {
				EntityDamageByEntityEvent edevt = (EntityDamageByEntityEvent) evt;
				if (edevt.getDamager() instanceof Player && edevt.getEntity() instanceof Player) {
					Player damager = (Player) edevt.getDamager();
					LogRunner.addLogMessage(r, LogRunner.getPrefix(r)
							+ (" Player '" + damager.getName() + "' tried to attack '" + ((Player) evt.getEntity()).getName() + " but was prevented."));
					damager.sendMessage(ChatColor.RED + "[Regios] You cannot fight within regions in this world!");
					evt.setCancelled(true);
					evt.setDamage(0);
					return;
				}
			}
		}

	}

	public boolean areChunksEqual(Chunk c1, Chunk c2) {
		return (c1.getX() == c2.getX() && c1.getZ() == c2.getZ());
	}

}
