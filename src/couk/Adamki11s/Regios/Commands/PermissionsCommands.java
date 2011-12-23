package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Mutable.MutablePermissions;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class PermissionsCommands extends PermissionsCore {

	MutablePermissions mutable = new MutablePermissions();

	public void addToTempCache(Region r, String region, String message, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(mutable.checkTempCache(r, message)){
				p.sendMessage(ChatColor.RED + "[Regios] The Node " + ChatColor.BLUE + message + ChatColor.RED + " already exists!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Node " + ChatColor.BLUE + message + ChatColor.GREEN + " added to region cache " + ChatColor.BLUE + region);
		}
		mutable.editAddToTempAddCache(r, message);
	}

	public void removeFromTempCache(Region r, String region, String message, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(!mutable.checkTempCache(r, message)){
				p.sendMessage(ChatColor.RED + "[Regios] The node " + ChatColor.BLUE + message + ChatColor.RED + " did not match any in the cache!");
				return;
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Node " + ChatColor.BLUE + message + ChatColor.GREEN + " removed from region cache " + ChatColor.BLUE + region);
			}
		}
		mutable.editRemoveFromTempAddCache(r, message);
	}

	public void resetTempAddCache(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Node cache reset for region " + ChatColor.BLUE + region);
		}
		mutable.editResetTempAddCache(r);
	}

	public void addToPermAddCache(Region r, String region, String message, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(mutable.checkPermAdd(r, message)){
				p.sendMessage(ChatColor.RED + "[Regios] The Node " + ChatColor.BLUE + message + ChatColor.RED + " already exists!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Node " + ChatColor.BLUE + message + ChatColor.GREEN + " added to region cache " + ChatColor.BLUE + region);
		}
		mutable.editAddToPermAddCache(r, message);
	}

	public void removeFromPermAddCache(Region r, String region, String message, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(!mutable.checkPermAdd(r, message)){
				p.sendMessage(ChatColor.RED + "[Regios] The node " + ChatColor.BLUE + message + ChatColor.RED + " did not match any in the cache!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Node " + ChatColor.BLUE + message + ChatColor.GREEN + " removed from region cache " + ChatColor.BLUE + region);
		}
		mutable.editRemoveFromPermAddCache(r, message);
	}

	public void resetPermAddCache(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Node cache reset for region " + ChatColor.BLUE + region);
		}
		mutable.editResetPermAddCache(r);
	}

	public void addToPermRemCache(Region r, String region, String message, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(mutable.checkPermRemove(r, message)){
				p.sendMessage(ChatColor.RED + "[Regios] The Node " + ChatColor.BLUE + message + ChatColor.RED + " already exists!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Node " + ChatColor.BLUE + message + ChatColor.GREEN + " added to region cache " + ChatColor.BLUE + region);
		}
		mutable.editAddToPermRemoveCache(r, message);
	}

	public void removeFromPermRemCache(Region r, String region, String message, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if(!mutable.checkPermRemove(r, message)){
				p.sendMessage(ChatColor.RED + "[Regios] The node " + ChatColor.BLUE + message + ChatColor.RED + " did not match any in the cache!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Node " + ChatColor.BLUE + message + ChatColor.GREEN + " removed from region cache " + ChatColor.BLUE + region);
		}
		mutable.editRemoveFromPermRemoveCache(r, message);
	}

	public void resetPermRemCache(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Node cache reset for region " + ChatColor.BLUE + region);
		}
		mutable.editResetPermRemoveCache(r);
	}
	
	public void listTempAddCache(Region r, String region, Player p){
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Temp Node cache for region " + ChatColor.BLUE + region);
			p.sendMessage(mutable.listTempAddCache(r));
		}
	}
	
	public void listPermAdd(Region r, String region, Player p){
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Perm Node adds for region " + ChatColor.BLUE + region);
			p.sendMessage(mutable.listPermAddCache(r));
		}
	}
	
	public void listPermRemCache(Region r, String region, Player p){
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Perm Node removals for region " + ChatColor.BLUE + region);
			p.sendMessage(mutable.listPermRemCache(r));
		}
	}

}
