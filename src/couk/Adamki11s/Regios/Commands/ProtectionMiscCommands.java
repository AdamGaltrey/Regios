package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Mutable.MutableProtectionMisc;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class ProtectionMiscCommands extends PermissionsCore {

	MutableProtectionMisc mutable = new MutableProtectionMisc();
	
	public void setPlayerCap(Region r, String region, String input, Player p) {
		int val;
		try {
			val = Integer.parseInt(input);
		} catch (Exception bfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be an integer!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " player cap set to : " + ChatColor.BLUE + val);
		}
		mutable.editPlayerCap(r, val);
	}

	public void setInteraction(Region r, String region, String input, Player p) {
		boolean val;
		try {
			val = Boolean.parseBoolean(input);
		} catch (Exception bfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be boolean!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (val) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Interaction Protection enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Interaction Protection disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editInteraction(r, val);
	}
	
	public void setBlockForm(Region r, String region, String input, Player p) {
		boolean val;
		try {
			val = Boolean.parseBoolean(input);
		} catch (Exception bfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be boolean!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyBasic(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (val) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Block Forming enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Block Forming disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editBlockForm(r, val);
	}

	public void setChestsLocked(Region r, String region, String input, Player p) {
		boolean val;
		try {
			val = Boolean.parseBoolean(input);
		} catch (Exception bfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be boolean!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (val) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Chest Locking enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Chest Locking disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editChestsLocked(r, val);
	}

	public void setDoorsLocked(Region r, String region, String input, Player p) {
		boolean val;
		try {
			val = Boolean.parseBoolean(input);
		} catch (Exception bfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be boolean!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (val) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Door Locking enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Door Locking disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editDoorsLocked(r, val);
	}

	public void setPasswordEnabled(Region r, String region, String input, Player p) {
		boolean val;
		try {
			val = Boolean.parseBoolean(input);
		} catch (Exception bfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be boolean!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (val) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Password Protection enabled for region " + ChatColor.BLUE + region);
				p.sendMessage(ChatColor.BLUE + "[Regios] Be sure to enable prevent-entry or prevent-exit for this to have an effect.");
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Password Protection disabled for region " + ChatColor.BLUE + region);
				p.sendMessage(ChatColor.BLUE + "[Regios] Be sure to enable prevent-entry or prevent-exit for this to have an effect.");
			}
		}
		mutable.editPasswordEnabled(r, val);
	}

	public void setFireProtection(Region r, String region, String input, Player p) {
		boolean val;
		try {
			val = Boolean.parseBoolean(input);
		} catch (Exception bfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be boolean!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			if (val) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Fire Protection enabled for region " + ChatColor.BLUE + region);
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Fire Protection disabled for region " + ChatColor.BLUE + region);
			}
		}
		mutable.editFireProtection(r, val);
	}

	public void setPassword(Region r, String region, String input, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if(!super.canModifyMain(r, p)){
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Password for region " + ChatColor.BLUE + region + ChatColor.GREEN + " set to : " + ChatColor.BLUE + input);
		}
		mutable.editSetPassword(r, input);
	}

}
