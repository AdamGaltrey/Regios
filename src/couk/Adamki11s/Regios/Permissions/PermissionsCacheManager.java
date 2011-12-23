package couk.Adamki11s.Regios.Permissions;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Regions.Region;

public class PermissionsCacheManager {

	public static HashMap<Player, ArrayList<String>> temporaryCache = new HashMap<Player, ArrayList<String>>();

	public static void cacheNodes(Player p, Region r) {
		ArrayList<String> nodeCache = new ArrayList<String>();
		for (String node : r.getTempCacheNodes()) {
			nodeCache.add(node.trim());
			PermissionsCore.addUserPermission(p, node.trim());
		}
		temporaryCache.put(p, nodeCache);
	}

	public static void unCacheNodes(Player p, Region r) {
		if (temporaryCache.containsKey(p)) {
			ArrayList<String> cache = temporaryCache.get(p);
			if (!cache.isEmpty()) {
				for (String node : cache) {
					PermissionsCore.removeUserPermission(p, node.trim());
				}
			}
			temporaryCache.remove(p);
		}
	}

	public static void permAddNodes(Player p, Region r) {
		for (String node : r.getPermAddNodes()) {
			PermissionsCore.addUserPermission(p, node.trim());
		}
	}

	public static void permRemoveNodes(Player p, Region r) {
		for (String node : r.getPermRemoveNodes()) {
			if (PermissionsCore.permissionHandler.has(p, node.trim())) {
				PermissionsCore.removeUserPermission(p, node.trim());
			}
		}
	}

}
