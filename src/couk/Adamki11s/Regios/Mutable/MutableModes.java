package couk.Adamki11s.Regios.Mutable;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Data.MODE;
import couk.Adamki11s.Regios.Regions.Region;

public class MutableModes {
	
	public void editProtectionMode(Region r, MODE mode){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Modes.ProtectionMode");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Modes.ProtectionMode", mode.toString());
		r.setProtectionMode(mode);
		c.save();
	}
	
	public void editPreventEntryMode(Region r, MODE mode){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Modes.PreventEntryMode");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Modes.PreventEntryMode", mode.toString());
		r.setPreventEntryMode(mode);
		c.save();
	}
	
	public void editPreventExitMode(Region r, MODE mode){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Modes.PreventExitMode");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Modes.PreventExitMode", mode.toString());
		r.setPreventExitMode(mode);
		c.save();
	}
	
	public void editItemControlMode(Region r, MODE mode){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Modes.ItemControlMode");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Modes.ItemControlMode", mode.toString());
		r.setItemMode(mode);
		c.save();
	}

}
