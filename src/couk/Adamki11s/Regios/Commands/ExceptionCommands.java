package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Mutable.MutableExceptions;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class ExceptionCommands extends PermissionsCore {

	MutableExceptions mutable = new MutableExceptions();

	private boolean checkException(int val) {
		if (Material.getMaterial(val) != null) {
			return true;
		} else {
			return false;
		}
	}

	public void addPlayerException(Region r, String region, String ex, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (!mutable.checkPlayerException(r, ex)) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Exception added : " + ChatColor.BLUE + ex);
				mutable.addPlayerException(r, ex);
			} else {
				p.sendMessage(ChatColor.RED + "[Regios] Player " + ChatColor.BLUE + ex + ChatColor.RED + " is already an exception!");
			}
		}
	}

	public void removePlayerException(Region r, String region, String ex, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (mutable.checkPlayerException(r, ex)) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Exception removed : " + ChatColor.BLUE + ex);
				mutable.removePlayerException(r, ex);
			} else {
				p.sendMessage(ChatColor.RED + "[Regios] Player " + ChatColor.BLUE + ex + ChatColor.RED + " is not an exception!");
			}
		}
	}

	public void addItemException(Region r, String region, String ex, Player p) {
		int val = 0;
		try {
			val = Integer.parseInt(ex);
		} catch (NumberFormatException nfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The item must be an integer!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!checkException(val)){
				p.sendMessage(ChatColor.RED + "[Regios] The item id " + ChatColor.BLUE + val + ChatColor.RED + " is invalid!");
				return;
			}
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (!mutable.checkItemException(r, val)) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Item Exception added : " + ChatColor.BLUE + ex);
				mutable.addItemException(r, val);
			} else {
				p.sendMessage(ChatColor.RED + "[Regios] Item " + ChatColor.BLUE + ex + ChatColor.RED + " is already an exception!");
			}
		}
	}

	public void removeItemException(Region r, String region, String ex, Player p) {
		int val = 0;
		try {
			val = Integer.parseInt(ex);
		} catch (NumberFormatException nfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The item must be an integer!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!checkException(val)){
				p.sendMessage(ChatColor.RED + "[Regios] The item id " + ChatColor.BLUE + val + ChatColor.RED + " is invalid!");
				return;
			}
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (mutable.checkItemException(r, val)) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Item Exception removed : " + ChatColor.BLUE + ex);
				mutable.removeItemException(r, val);
			} else {
				p.sendMessage(ChatColor.RED + "[Regios] Item " + ChatColor.BLUE + ex + ChatColor.RED + " is not an exception!");
			}
		}
	}

	public void addNodeException(Region r, String region, String ex, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (!mutable.checkNodeException(r, ex)) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Exception Node added : " + ChatColor.BLUE + ex);
				mutable.addNodeException(r, ex);
			} else {
				p.sendMessage(ChatColor.RED + "[Regios] Node " + ChatColor.BLUE + ex + ChatColor.RED + " is already an exception!");
			}
		}
	}

	public void removeNodeException(Region r, String region, String ex, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (mutable.checkNodeException(r, ex)) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Exception Node added : " + ChatColor.BLUE + ex);
				mutable.removeNodeException(r, ex);
			} else {
				p.sendMessage(ChatColor.RED + "[Regios] Node " + ChatColor.BLUE + ex + ChatColor.RED + " is already an exception!");
			}
		}
	}

	public void erasePlayerExceptions(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] All player exceptions removed for region : " + ChatColor.BLUE + region);
			mutable.eraseAllPlayerExceptions(r);
			return;
		}
	}

	public void eraseSubOwnerExceptions(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] All Sub Owners removed for region : " + ChatColor.BLUE + region);
			mutable.eraseAllSubOwners(r);
			return;
		}
	}

	public void eraseItemExceptions(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] All item exceptions removed for region : " + ChatColor.BLUE + region);
			mutable.eraseAllItemExceptions(r);
			return;
		}
	}

	public void eraseNodeExceptions(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] All node exceptions removed for region : " + ChatColor.BLUE + region);
			mutable.eraseAllNodeExceptions(r);
			return;
		}
	}

	public void listItemExceptions(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			String regionSet = mutable.listItemExceptions(r);
			p.sendMessage(ChatColor.GREEN + "Regios Item Exception List : " + ChatColor.BLUE + region);
			p.sendMessage(regionSet);
		}
	}

	public void listPlayerExceptions(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			String regionSet = mutable.listPlayerExceptions(r);
			p.sendMessage(ChatColor.GREEN + "Regios Player Exception List : " + ChatColor.BLUE + region);
			p.sendMessage(regionSet);
		}
	}

	public void listSubOwnerExceptions(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			String regionSet = mutable.listSubOwnersExceptions(r);
			p.sendMessage(ChatColor.GREEN + "Regios Sub Owner Exception List : " + ChatColor.BLUE + region);
			p.sendMessage(regionSet);
		}
	}

	public void listNodeExceptions(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			String regionSet = mutable.listNodeExceptions(r);
			p.sendMessage(ChatColor.GREEN + "Regios Node Exception List : " + ChatColor.BLUE + region);
			p.sendMessage(regionSet);
		}
	}

	public void addSubOwner(Region r, String region, String message, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			
			if (mutable.checkSubOwnerException(r, message)) {
				p.sendMessage(ChatColor.RED + "[Regios] The Sub Owner " + ChatColor.BLUE + message + ChatColor.RED + " already exists!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Sub Owner " + ChatColor.BLUE + message + ChatColor.GREEN + " added to region " + ChatColor.BLUE + region);
		}
		mutable.addSubOwner(r, message);
	}

	public void removeSubowner(Region r, String region, String ex, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (mutable.checkSubOwnerException(r, ex)) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Sub Owner removed : " + ChatColor.BLUE + ex);
				mutable.removeSubOwner(r, ex);
			} else {
				p.sendMessage(ChatColor.RED + "[Regios] Sub Owner " + ChatColor.BLUE + ex + ChatColor.RED + " does not exist!");
			}
		}
	}

	public void removeFromPermRemCache(Region r, String region, String message, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			boolean nodeMatch = false;
			for (String s : r.getSubOwners()) {
				if (s.trim().equalsIgnoreCase(message.trim())) {
					nodeMatch = true;
				}
			}
			if (!nodeMatch) {
				p.sendMessage(ChatColor.RED + "[Regios] The Sub Owner " + ChatColor.BLUE + message + ChatColor.RED + " did not match any in the cache!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Sub Owner " + ChatColor.BLUE + message + ChatColor.GREEN + " removed from region cache " + ChatColor.BLUE + region);
		}
		mutable.removeSubOwner(r, message);
	}

	public void resetPermRemCache(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Sub Owners reset for region " + ChatColor.BLUE + region);
		}
		mutable.eraseAllSubOwners(r);
	}

}
