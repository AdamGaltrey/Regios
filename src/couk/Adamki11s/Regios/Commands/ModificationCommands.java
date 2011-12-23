package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import couk.Adamki11s.Regios.Data.ConfigurationData;
import couk.Adamki11s.Regios.Mutable.MutableModification;
import couk.Adamki11s.Regios.Net.PingManager;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;
import couk.Adamki11s.Regios.Regions.Region;

public class ModificationCommands extends PermissionsCore {

	final MutableModification mutable = new MutableModification();
	final CreationCommands creationCommands = new CreationCommands();

	public void setExpandUp(Region r, String region, String input, Player p) {
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
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " expanded up by : " + ChatColor.BLUE + val);
		}
		mutable.editExpandUp(r, val);
	}

	public void setExpandDown(Region r, String region, String input, Player p) {
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
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " expanded down by : " + ChatColor.BLUE + val);
		}
		mutable.editExpandDown(r, val);
	}

	public void setExpandMax(Region r, String region, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " expanded to max!");
		}
		mutable.editExpandMax(r);
	}

	public void setExpandOut(Region r, String region, String input, Player p) {
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
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " expanded outwards by : " + ChatColor.BLUE + val);
		}
		mutable.editExpandOut(r, val);
	}

	public void setShrinkDown(Region r, String region, String input, Player p) {
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
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " shrunk down by : " + ChatColor.BLUE + val);
		}
		mutable.editShrinkDown(r, val);
	}

	public void setShrinkUp(Region r, String region, String input, Player p) {
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
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " shrunk up by : " + ChatColor.BLUE + val);
		}
		mutable.editShrinkUp(r, val);
	}

	public void setShrinkIn(Region r, String region, String input, Player p) {
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
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " shrunk inwards by : " + ChatColor.BLUE + val);
		}
		mutable.editShrinkIn(r, val);
	}

	public void setDelete(Region r, String region, String input, Player p) {
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " deleted successfully.");
		}
		MutableModification.editDeleteRegion(r, true, p);
		PingManager.delete();
	}

	public void setRename(Region r, String region, String input, Player p) {
		if (GlobalRegionManager.getRegion(input) != null) {
			p.sendMessage(ChatColor.RED + "[Regios] A Region with name " + ChatColor.BLUE + input + ChatColor.RED + " already exists!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
		}
		mutable.editRename(r, input, p);
		p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " renamed to : " + ChatColor.BLUE + input);
	}

	public void setModifyPoints(Location l1, Location l2, Player p) {
		if (l1 == null || l2 == null) {
			p.sendMessage(ChatColor.RED + "[Regios] You have not set 2 points!");
			return;
		}
		Region r = CreationCommands.modRegion.get(p);
		if (!super.canModifyMain(r, p)) {
			p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
			return;
		}
		p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + r.getName() + ChatColor.GREEN + ", points modified successfully");
		mutable.editModifyPoints(r, l1, l2);
	}

	public void startModification(Region r, String region, String input, Player p) {
		if (!p.getInventory().contains(new ItemStack(ConfigurationData.defaultSelectionTool, 1))) {
			ItemStack is = new ItemStack(ConfigurationData.defaultSelectionTool, 1);

			p.getInventory().addItem(is);

			if (p.getItemInHand() == new ItemStack(Material.AIR, 0)) {
				p.setItemInHand(is);
			}

		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Modifying points for region " + ChatColor.BLUE + region);
		}
		CreationCommands.modRegion.put(p, r);
		CreationCommands.modding.put(p, true);
		CreationCommands.setting.put(p, false);
	}

}
