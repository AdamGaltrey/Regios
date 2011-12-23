package couk.Adamki11s.Regios.Scheduler;

import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import couk.Adamki11s.Regios.CustomEvents.RegionLightningStrikeEvent;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.Regions.RegionLocation;

public class LightningRunner {

	public static HashMap<Region, Integer> strikes = new HashMap<Region, Integer>();
	public static HashMap<Region, Integer> strikeCounter = new HashMap<Region, Integer>();

	public static void addRegion(Region r) {
		strikes.put(r, r.getLSPS());
		strikeCounter.put(r, 0);
	}

	public static void removeRegion(Region r) {
		if (strikes.containsKey(r)) {
			strikes.remove(r);
		}
		if (strikeCounter.containsKey(r)) {
			strikeCounter.remove(r);
		}
	}

	public static boolean doesStikesContain(Region r) {
		return strikes.containsKey(r);
	}

	public static void executeStrikes() {
		for (Entry<Region, Integer> entry : strikes.entrySet()) {
			Region r = entry.getKey();
			int count = strikeCounter.get(r);
			if (count >= strikes.get(r)) {
				fireStrike(r);
			} else {
				incrementCounter(r);
			}
		}
	}

	private static void fireStrike(Region r) {
		resetCounter(r);
		RegionLocation rl1 = r.getL1(), rl2 = r.getL2();
		strike(rl1.getX(), rl2.getX(), rl1.getZ(), rl2.getZ(), rl1.getY(), rl2.getY(), rl1.getWorld(), r);
	}

	private static void strike(double x1, double x2, double z1, double z2, double y1, double y2, World world, Region r) {
		double xdiff, zdiff, xStrike, yStrike, zStrike;
		boolean x1bigger = false, z1bigger = false;

		if (x1 > x2) {
			xdiff = x1 - x2;
			x1bigger = true;
		} else {
			xdiff = x2 - x1;
			x1bigger = false;
		}
		if (z1 > z2) {
			zdiff = z1 - z2;
			z1bigger = true;
		} else {
			zdiff = z2 - z1;
			z1bigger = false;
		}
		if (y1 > y2) {
			yStrike = y2;
		} else {
			yStrike = y1;
		}

		Random gen = new Random();
		int randXStrike = gen.nextInt((int) ((xdiff) + 1));
		int randZStrike = gen.nextInt((int) ((zdiff) + 1));

		if (x1bigger) {
			xStrike = (x2 + randXStrike);
		} else {
			xStrike = (x1 + randXStrike);
		}
		if (z1bigger) {
			zStrike = (z2 + randZStrike);
		} else {
			zStrike = (z1 + randZStrike);
		}

		Location STRIKE = new Location(world, xStrike, yStrike, zStrike, 0, 0);

		world.strikeLightning(STRIKE);

		RegionLightningStrikeEvent event = new RegionLightningStrikeEvent("RegionLightningStrikeEvent");
		event.setProperties(new Location(world, xStrike, yStrike, zStrike, 0, 0), r);
		Bukkit.getServer().getPluginManager().callEvent(event);

	}

	private static void incrementCounter(Region r) {
		if (strikeCounter.containsKey(r)) {
			strikeCounter.put(r, strikeCounter.get(r) + 1);
		} else {
			strikeCounter.put(r, 0);
		}
	}

	private static void resetCounter(Region r) {
		strikeCounter.put(r, 0);
	}

}
