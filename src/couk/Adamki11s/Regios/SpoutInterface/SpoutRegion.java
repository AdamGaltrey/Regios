package couk.Adamki11s.Regios.SpoutInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutWeather;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.sound.SoundManager;
import couk.Adamki11s.Regios.Main.Regios;
import couk.Adamki11s.Regios.Regions.Region;

public class SpoutRegion {

	public static HashMap<Player, ArrayList<UUID>> widgetBindings = new HashMap<Player, ArrayList<UUID>>();
	public static HashMap<Player, String> lastSong = new HashMap<Player, String>();
	public static HashMap<Player, SpoutWeather> oldWeather = new HashMap<Player, SpoutWeather>();

	public static void forceTexturePack(Player p, Region r){
		SpoutPlayer sp = (SpoutPlayer)p;
		sp.setTexturePack(r.getSpoutTexturePack());
	}
	
	public static void resetTexturePack(Player p){
		SpoutPlayer sp = (SpoutPlayer)p;
		sp.resetTexturePack();
	}

	public static void sendWelcomeMessage(Player p, Region r) {
		SpoutPlayer sp = (SpoutPlayer) p;
		String msg = r.colourFormat(r.liveFormat(r.getSpoutWelcomeMessage(), p));
		if (msg.length() > 26) {
			msg = msg.substring(0, 23);
			msg += "...";
		}
		sp.sendNotification(r.getName(), msg, r.getSpoutWelcomeMaterial());
	}

	public static void sendLeaveMessage(Player p, Region r) {
		SpoutPlayer sp = (SpoutPlayer) p;
		String msg = r.colourFormat(r.liveFormat(r.getSpoutLeaveMessage(), p));
		if (msg.length() > 26) {
			msg = msg.substring(0, 23);
			msg += "...";
		}
		sp.sendNotification(r.getName(), msg, r.getSpoutLeaveMaterial());
	}

	public static void attachLabel(String text, double x, double y, Player p) {
		GenericLabel label = new GenericLabel(text);
		SpoutPlayer sp = (SpoutPlayer) p;
		sp.getMainScreen().attachWidget(Regios.regios, label);

		if (widgetBindings.containsKey(p)) {
			widgetBindings.get(p).add(label.getId());
		} else {
			ArrayList<UUID> push = new ArrayList<UUID>();
			push.add(label.getId());
			widgetBindings.put(p, push);
		}
	}

	public static void removeLabel(UUID uuid, Player p) {
		SpoutPlayer sp = (SpoutPlayer) p;
		sp.getMainScreen().removeWidget(sp.getMainScreen().getWidget(uuid));
	}

	public static void wipeLabels(Player p) {
		if (widgetBindings.containsKey(p)) {
			for (UUID uuid : widgetBindings.get(p)) {
				((SpoutPlayer) p).getMainScreen().removeWidget(((SpoutPlayer) p).getMainScreen().getWidget(uuid));
			}
		}
	}

	public static void playToPlayerMusicFromUrl(Player p, Region r) {
		int length = r.getMusicUrls().length;
		String shuffled = "";
		if (lastSong.containsKey(p)) {
			shuffled = lastSong.get(p);
		}
		if (length == 1) {
			shuffled = r.getMusicUrls()[0];
		} else {
			if (lastSong.containsKey(p)) {
				while (lastSong.get(p).equalsIgnoreCase(shuffled)) {
					int rnd = new Random().nextInt(length) + 0;
					shuffled = r.getMusicUrls()[rnd];
				}
			} else {
				shuffled = r.getMusicUrls()[new Random().nextInt(length - 1)];
			}
		}
		lastSong.put(p, shuffled);
		SoundManager sm = SpoutManager.getSoundManager();
		sm.playCustomMusic(Regios.regios, (SpoutPlayer) p, shuffled, true);
	}

	public static void stopMusicPlaying(Player p, Region r) {
		if (r != null) {
			current = r;
		}
		currentPlayer = p;
		cancelMusicTask();
	}

	static Region current;
	static Player currentPlayer;

	private static void cancelMusicTask() {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Regios.regios, new Runnable() {

			public void run() {
				SpoutManager.getSoundManager().stopMusic((SpoutPlayer) currentPlayer, false, 2500); // 2.5s
																									// Fadeout.
			}

		}, 20L);
	}

}
