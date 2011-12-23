package couk.Adamki11s.Regios.Main;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.player.SpoutPlayer;

import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.nijikokun.bukkit.Permissions.Permissions;

import couk.Adamki11s.Regios.Commands.CommandCore;
import couk.Adamki11s.Regios.Data.CreationCore;
import couk.Adamki11s.Regios.Economy.Economy;
import couk.Adamki11s.Regios.Economy.EconomyCore;
import couk.Adamki11s.Regios.Listeners.RegiosBlockListener;
import couk.Adamki11s.Regios.Listeners.RegiosEntityListener;
import couk.Adamki11s.Regios.Listeners.RegiosPlayerListener;
import couk.Adamki11s.Regios.Listeners.RegiosServerListener;
import couk.Adamki11s.Regios.Listeners.RegiosWeatherListener;
import couk.Adamki11s.Regios.Net.PingManager;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;
import couk.Adamki11s.Regios.Regions.GlobalWorldSetting;
import couk.Adamki11s.Regios.Scheduler.MainRunner;
import couk.Adamki11s.Regios.SpoutGUI.CacheHandler;
import couk.Adamki11s.Regios.SpoutGUI.Screen_Listener;
import couk.Adamki11s.Regios.SpoutInterface.SpoutCraftListener;
import couk.Adamki11s.Regios.SpoutInterface.SpoutInterface;
import couk.Adamki11s.Regios.SpoutInterface.SpoutInventoryListener;
import couk.Adamki11s.Regios.Versions.VersionTracker;

public class Regios extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft.Regios");
	public static String prefix = "[Regios]", version;

	public final RegiosBlockListener blockListener = new RegiosBlockListener();
	public final RegiosPlayerListener playerListener = new RegiosPlayerListener();
	public final RegiosEntityListener entityListener = new RegiosEntityListener();
	public final RegiosWeatherListener weatherListener = new RegiosWeatherListener();
	public final RegiosServerListener serverListener = new RegiosServerListener();

	public static Plugin regios;

	@Override
	public void onDisable() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (SpoutInterface.doesPlayerHaveSpout(p)) {
				((SpoutPlayer) p).getMainScreen().closePopup();
			}
		}
		log.info(prefix + " Shutting down scheduler task...");
		MainRunner.stopMainRunner();
		log.info(prefix + " Scheduler task stopped successfully!");
		log.info(prefix + " Regios version " + version + " disabled successfully!");
	}

	@Override
	public void onEnable() {
		PingManager.enabled();
		
		version = this.getDescription().getVersion();
		PluginManager pm = this.getServer().getPluginManager();

		Plugin p = pm.getPlugin("Spout");
		if(p == null){
			log.info("[Regios] Spout not detected. No Spout support.");
		} else {
			SpoutInterface.global_spoutEnabled = true;
			log.info("[Regios] Spout detected! Spout support enabled!");
		}
		

		try {
			new CreationCore().setup();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		regios = this;
		
		pm.registerEvent(Type.PLAYER_MOVE, playerListener, Priority.Highest, this);
		pm.registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Highest, this);
		pm.registerEvent(Type.PLAYER_QUIT, playerListener, Priority.Highest, this);
		pm.registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Highest, this);
		pm.registerEvent(Type.PLAYER_BUCKET_EMPTY, playerListener, Priority.Highest, this);
		pm.registerEvent(Type.PLAYER_BUCKET_FILL, playerListener, Priority.Highest, this);
		pm.registerEvent(Type.BLOCK_PLACE, blockListener, Priority.Highest, this);
		pm.registerEvent(Type.BLOCK_BREAK, blockListener, Priority.Highest, this);
		pm.registerEvent(Type.BLOCK_IGNITE, blockListener, Priority.Highest, this);
		pm.registerEvent(Type.BLOCK_FORM, blockListener, Priority.Highest, this);
		pm.registerEvent(Type.SIGN_CHANGE, blockListener, Priority.Highest, this);
		pm.registerEvent(Type.EXPLOSION_PRIME, entityListener, Priority.Highest, this);
		pm.registerEvent(Type.ENTITY_DAMAGE, entityListener, Priority.Highest, this);
		pm.registerEvent(Type.CREATURE_SPAWN, entityListener, Priority.Highest, this);
		pm.registerEvent(Type.PAINTING_BREAK, entityListener, Priority.Highest, this);
		pm.registerEvent(Type.PAINTING_PLACE, entityListener, Priority.Highest, this);
		pm.registerEvent(Type.ENTITY_DEATH, entityListener, Priority.Highest, this);
		pm.registerEvent(Type.LIGHTNING_STRIKE, weatherListener, Priority.Highest, this);
		
		if(EconomyCore.economy == Economy.ICONOMY){
			pm.registerEvent(Type.PLUGIN_ENABLE, serverListener, Priority.Monitor, this);
	        pm.registerEvent(Type.PLUGIN_DISABLE, serverListener, Priority.Monitor, this);
		} else if(EconomyCore.economy == Economy.BOSECONOMY){
			EconomyCore.boseConomySetup();
		}

		setupPermissions();

		getCommand("regios").setExecutor(new CommandCore());
		
		for(World w : Bukkit.getServer().getWorlds()){
			 GlobalWorldSetting gws = GlobalRegionManager.getGlobalWorldSetting(w);
			 if(gws.overridingPvp && !w.getPVP()){
				 w.setPVP(true);
				 log.info("[Regios] PvP Setting for world : " + w.getName() + ", overridden!");
			 }
		}

		if (SpoutInterface.global_spoutEnabled) {
			CacheHandler.cacheObjects();
			pm.registerEvent(Type.CUSTOM_EVENT, new SpoutCraftListener(), Priority.Highest, this);
			pm.registerEvent(Type.CUSTOM_EVENT, new SpoutInventoryListener(), Priority.Highest, this);
			pm.registerEvent(Type.CUSTOM_EVENT, new Screen_Listener(), Priority.Highest, this);
		}
		
		log.info(prefix + " Starting scheduler task...");
		MainRunner.startMainRunner();
		log.info(prefix + " Scheduler task initiated!");
		
		VersionTracker.createCurrentTracker();
		
		log.info(prefix + " Regios version " + version + " enabled successfully!");
		log.info(prefix + " Regios Developed by [Adamki11s].");
		
	}

	private void setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
		if (PermissionsCore.permissionHandler == null) {
			if (permissionsPlugin != null) {
				PermissionsCore.permissionHandler = ((Permissions) permissionsPlugin).getHandler();
				PermissionsCore.hasPermissions = true;
				log.info("[Regios] Permissions support enabled!");
			} else {
				log.info("[Regios] Permissions not detected, checking PEX...");
				if (Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
					PermissionsCore.pex = PermissionsEx.getPermissionManager();
					PermissionsCore.hasPEX = true;
					log.info("[Regios] Permissions (PEX) support enabled!");
				} else {
					log.info("[Regios] PEX not detected, defaulting to OP.");
				}

			}
		}
	}

}
