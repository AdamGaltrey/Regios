package couk.Adamki11s.Regios.Permissions;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import ru.tehkode.permissions.PermissionManager;

import com.nijiko.permissions.PermissionHandler;

import couk.Adamki11s.Regios.Regions.Region;

public class PermissionsCore {

	public static PermissionHandler permissionHandler = null;
	public static PermissionManager pex;
	public static boolean hasPermissions = false, iConomyEnabled = false, hasPEX = false;

	public static boolean doesHaveNode(Player p, String node) {
		if (p.isOp()) {
			return true;
		}

		if (hasPermissions) {
			return permissionHandler.has(p, node);
		} else {
			if (hasPEX) {
				return pex.has(p, node);
			} else {
				return p.hasPermission(node);
			}
		}
	}

	public static void sendInvalidPerms(Player p) {
		p.sendMessage(ChatColor.RED + "[Regios] You do not have permissions to do this!");
	}

	public static void sendInvalidPermsPopup(SpoutPlayer p) {
		p.sendNotification("Permissions", ChatColor.RED + "You cannot do this!", Material.FIRE);
	}

	public static boolean canModifyBasic(Region r, Player p) {
		if (doesHaveNode(p, ("regios.override." + r.getName())) || doesHaveNode(p, "regios.override.all")) {
			return true;
		}
		if (canModifyMain(r, p)) {
			return true;
		}
		if (r.getOwner().equalsIgnoreCase(p.getName())) {
			return true;
		}
		for (String s : r.getSubOwners()) {
			if (s.equalsIgnoreCase(p.getName())) {
				return true;
			}
		}
		if (p.isOp()) {
			return true;
		}
		return false;
	}

	public static boolean canModifyMain(Region r, Player p) {
		if (doesHaveNode(p, ("regios.override." + r.getName())) || doesHaveNode(p, "regios.override.all")) {
			return true;
		}
		if (r.getOwner().equalsIgnoreCase(p.getName())) {
			return true;
		}
		if (p.isOp()) {
			return true;
		}
		return false;
	}

	public static void addUserPermission(Player p, String node) {
		if (hasPermissions) {
			permissionHandler.addUserPermission(p.getWorld().getName(), p.getName(), node);
			permissionHandler.save(p.getName());
			try {
				permissionHandler.load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void removeUserPermission(Player p, String node) {
		if (hasPermissions) {
			permissionHandler.removeUserPermission(p.getWorld().getName(), p.getName(), node);
			permissionHandler.save(p.getName());
			try {
				permissionHandler.load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
