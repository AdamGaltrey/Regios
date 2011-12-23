package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Economy.Economy;
import couk.Adamki11s.Regios.Economy.EconomyCore;
import couk.Adamki11s.Regios.Mutable.MutableEconomy;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class EconomyCommands extends PermissionsCore {

	MutableEconomy mutable = new MutableEconomy();

	public void setForSale(Region r, String region, String input, Player p) {
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
			if (!super.canModifyMain(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to sell this region!");
				return;
			}
			if (val) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " is now for sale!");
			} else {
				p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + region + ChatColor.GREEN + " is no longer for sale!");
			}
		}
		mutable.editForSale(r, val);
	}

	public void listRegionsForSale(Player p) {
		p.sendMessage(mutable.listRegionsForSale());
	}

	public void buyRegion(Region r, String region, Player p) {
		if (!EconomyCore.economySupport) {
			p.sendMessage(ChatColor.RED + "[Regios] Economy support is not enabled!");
			return;
		} else {
			if (r == null) {
				p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
				return;
			}
			if (!PermissionsCore.doesHaveNode(p, "regios.fun.buy")) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to buy regions!");
				return;
			}
			int price = r.getSalePrice();
			if (EconomyCore.getEconomy() == Economy.ICONOMY) {
				if (!EconomyCore.getiConomyManager().canAffordRegion(p, price)) {
					p.sendMessage(ChatColor.RED + "[Regios] You cannot afford this region!");
					return;
				} else {
					EconomyCore.getiConomyManager().buyRegion(r, p.getName(), r.getOwner(), price);
					p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + r.getName() + ChatColor.GREEN + " purchased for " + ChatColor.GOLD + price
							+ ChatColor.GREEN + "!");
					return;
				}
			} else if (EconomyCore.getEconomy() == Economy.BOSECONOMY) {
				if (!EconomyCore.getBoseEconomyManager().canAffordRegion(p.getName(), price)) {
					p.sendMessage(ChatColor.RED + "[Regios] You cannot afford this region!");
					return;
				} else {
					EconomyCore.getBoseEconomyManager().buyRegion(r, p.getName(), r.getOwner(), price);
					p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + r.getName() + ChatColor.GREEN + " purchased for " + ChatColor.GOLD + price
							+ ChatColor.GREEN + "!");
					return;
				}
			}
		}
	}

	public void setSalePrice(Region r, String region, String input, Player p) {
		int val;
		try {
			val = Integer.parseInt(input);
		} catch (Exception bfe) {
			p.sendMessage(ChatColor.RED + "[Regios] The value for the 2nd paramteter must be an integer!");
			return;
		}
		if (val < 0) {
			p.sendMessage(ChatColor.RED + "[Regios] The sale price must be a positive value!");
			return;
		}
		if (r == null) {
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!");
			return;
		} else {
			if (!super.canModifyBasic(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] Sale price for region " + ChatColor.BLUE + region + ChatColor.GREEN + " set to " + ChatColor.BLUE + val);
			mutable.editSalePrice(r, val);
		}
	}

}
