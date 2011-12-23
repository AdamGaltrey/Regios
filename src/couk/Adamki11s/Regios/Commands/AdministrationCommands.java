package couk.Adamki11s.Regios.Commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Mutable.MutableAdministration;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class AdministrationCommands extends PermissionsCore {
	
	MutableAdministration mutable = new MutableAdministration();
	
	public void listRegions(Player p){
		p.sendMessage(mutable.listRegions());
	}
	
	public void reloadRegions(Player p){
		mutable.reloadRegions();
		p.sendMessage(ChatColor.GREEN + "[Regios] All Regions reloaded.");
	}
	
	public void reloadConfig(Player p){
		mutable.reloadConfig();
		p.sendMessage(ChatColor.GREEN + "[Regios] All Configurations reloaded.");
	}
	
	public void reloadAll(Player p){
		mutable.reloadAll();
		p.sendMessage(ChatColor.GREEN + "[Regios] Complete reload completed.");
	}
	
	public void listRegionBackups(Region r, String region, Player p){
		if(r == null){
			p.sendMessage(ChatColor.RED + "[Regios] The region to inherit : " + ChatColor.BLUE + region + ChatColor.RED + " does not exist!");
			return;
		}
		File f = r.getBackupsDirectory();
		if(f.listFiles().length < 1){
			p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " has no backups!");
		} else {
			StringBuilder sb = new StringBuilder();
			for(File backup : f.listFiles()){
				sb.append(ChatColor.WHITE).append(backup.getName().substring(0, backup.getName().lastIndexOf("."))).append(ChatColor.BLUE).append(", ");
			}
			p.sendMessage(sb.toString());
			return;
		}
	}
	
	public void setOwner(Region r, String name, String owner, Player p){
		if(r == null){
			p.sendMessage(ChatColor.RED + "[Regios] The region to inherit : " + ChatColor.BLUE + name + ChatColor.RED + " does not exist!");
			return;
		}
		if(!super.canModifyMain(r, p)){
			p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
			return;
		}
		mutable.setOwner(r, owner);
		p.sendMessage(ChatColor.GREEN + "[Regios] Owner for region " + ChatColor.BLUE + name + ChatColor.GREEN + " changed to " + ChatColor.BLUE + owner);
	}
	
	public void inherit(Region tin, Region inf, String tinName, String infName, Player p){
		if(tin == null){
			p.sendMessage(ChatColor.RED + "[Regios] The region to inherit : " + ChatColor.BLUE + tinName + ChatColor.RED + " does not exist!");
			return;
		}
		if(inf == null){
			p.sendMessage(ChatColor.RED + "[Regios] The region to inherit from : " + ChatColor.BLUE + infName + ChatColor.RED + " does not exist!");
			return;
		}
		if(!super.canModifyMain(tin, p)){
			p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
			return;
		}
		mutable.inherit(tin, inf);
		p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + tinName + ChatColor.GREEN + " inherited properties from region " + ChatColor.BLUE + infName);
		return;
	}

}
