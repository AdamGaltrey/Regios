package couk.Adamki11s.Regios.Data;

import org.bukkit.Material;

public class ConfigurationData {

	public static String defaultWelcomeMessage, defaultLeaveMessage, defaultProtectionMessage, defaultPreventEntryMessage, defaultPreventExitMessage, password,
			defaultAuthenticationMessage, defaultAuthenticationSuccessMessage, defaulSpoutEntryMessage, defaulSpoutExitMessage;

	public static String[] defaultCustomMusicUrl, commandSet, temporaryNodesCacheAdd, permanentNodesCacheAdd, permanentNodesCacheRemove;

	public static Material defaultSpoutWelcomeMaterial, defaultSpoutLeaveMaterial, defaultSelectionTool;

	public static boolean regionProtected, regionProtectedBreak, regionPlaceProtected, regionPreventEntry, regionPreventExit, mobSpawns, monsterSpawns, healthEnabled, pvp, doorsLocked, chestsLocked, preventInteraction,
			showPvpWarning, checkForUpdates, downloadUpdatesAuto, cacheOldVersions, forceReload, passwordEnabled, showWelcomeMessage, showLeaveMessage, showProtectionMessage,
			showPreventEntryMessage, showPreventExitMessage, fireProtection, playCustomMusic, permWipeOnEnter, permWipeOnExit,
			wipeAndCacheOnEnter, wipeAndCacheOnExit, forceCommand, blockForm, forSale, logs;

	public static int LSPS, healthRegen, velocityWarp, playerCap, salePrice;

	public static MODE protectionMode, preventEntryMode, preventExitMode, itemMode;

	public ConfigurationData(String a, String b, String c, String d, String e, String pass, boolean f, boolean g, boolean h, boolean i, boolean j, boolean k, boolean m, boolean n,
			boolean o, boolean v, boolean passEnabled, int p, int q, int r, MODE s, MODE t, MODE u, MODE item, boolean w, boolean x, boolean y, boolean z, boolean exit,
			String dam, String dasm, Material welcome, Material leave, boolean welcomeMsg, boolean leaveMsg, boolean protectMsg, boolean preventEntryMsg, boolean preventExitMsg,
			boolean fireProt, String[] music, boolean playmusic, boolean permWipeOnEnter1, boolean permWipeOnExit1,
			boolean wipeAndCacheOnEnter1, boolean wipeAndCacheOnExit1, boolean forceCommand1, String[] commandSet1, String[] tempAddCache, String[] permAddCache,
			String[] permRemoveCache, boolean form, int cap, boolean placeprotect, boolean breakProtect, boolean fs, int sp) {
		defaultWelcomeMessage = a;
		defaultLeaveMessage = b;
		defaultProtectionMessage = c;
		defaultPreventEntryMessage = d;
		defaultPreventExitMessage = e;
		regionProtected = f;
		regionPreventEntry = g;
		mobSpawns = h;
		monsterSpawns = i;
		healthEnabled = j;
		pvp = k;
		doorsLocked = m;
		chestsLocked = n;
		preventInteraction = o;
		LSPS = p;
		healthRegen = q;
		velocityWarp = r;
		protectionMode = s;
		preventEntryMode = t;
		preventExitMode = u;
		showPvpWarning = v;
		checkForUpdates = w;
		downloadUpdatesAuto = x;
		cacheOldVersions = y;
		forceReload = z;
		passwordEnabled = passEnabled;
		password = pass;
		itemMode = item;
		regionPreventExit = exit;
		defaultAuthenticationMessage = dam;
		defaultAuthenticationSuccessMessage = dasm;
		defaultSpoutWelcomeMaterial = welcome;
		defaultSpoutLeaveMaterial = leave;
		showWelcomeMessage = welcomeMsg;
		showLeaveMessage = leaveMsg;
		showProtectionMessage = protectMsg;
		showPreventEntryMessage = preventEntryMsg;
		showPreventExitMessage = preventExitMsg;
		fireProtection = fireProt;
		defaultCustomMusicUrl = music;
		playCustomMusic = playmusic;
		permWipeOnEnter = permWipeOnEnter1;
		permWipeOnExit = permWipeOnExit1;
		wipeAndCacheOnEnter = wipeAndCacheOnEnter1;
		wipeAndCacheOnExit = wipeAndCacheOnExit1;
		forceCommand = forceCommand1;
		commandSet = commandSet1;
		temporaryNodesCacheAdd = tempAddCache;
		permanentNodesCacheAdd = permAddCache;
		permanentNodesCacheRemove = permRemoveCache;
		blockForm = form;
		playerCap = cap;
		regionPlaceProtected = placeprotect;
		regionProtectedBreak = breakProtect;
		forSale = fs;
		salePrice = sp;
	}

}
