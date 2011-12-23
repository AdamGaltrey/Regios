package couk.Adamki11s.Regios.Mutable;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Regions.Region;

public class MutableMisc {
	
	public void editAddToForceCommandSet(Region r, String message){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		String current = (String)all.get("Region.Command.CommandSet");
		all.remove("Region.Command.CommandSet");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Command.CommandSet", current.trim() + message.trim() + ",");
		r.setCommandSet((current.trim() + "," + message.trim()).split(","));
		c.save();
	}
	
	public boolean checkCommandSet(Region r, String addition){
		for (String s : r.getCommandSet()) {
			if (s.equalsIgnoreCase(addition)) {
				return true;
			}
		}
		return false;
	}
	
	public void editRemoveFromForceCommandSet(Region r, String message){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		String current = (String)all.get("Region.Command.CommandSet");
		current = current.replaceAll(" ", "");
		current = current.replaceAll(message + ",", "");
		current = current.replaceAll(",,", ",");
		all.remove("Region.Command.CommandSet");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Command.CommandSet", current.trim());
		r.setCommandSet((current.trim()).split(","));
		c.save();
	}
	
	public void editSetForceCommand(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Command.ForceCommand");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Command.ForceCommand", val);
		r.setForceCommand(val);
		c.save();
	}
	
	public void editResetForceCommandSet(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Command.CommandSet");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Command.CommandSet", "");
		r.setCommandSet(("").split(","));
		c.save();
	}
	
	public String listCommandSet(Region r) {
		StringBuilder sb = new StringBuilder();
		for (String s : r.getCommandSet()) {
			sb.append(ChatColor.WHITE + s).append(ChatColor.BLUE + ", ");
		}
		return sb.toString();
	}

}
