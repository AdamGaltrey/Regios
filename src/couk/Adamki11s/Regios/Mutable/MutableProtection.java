package couk.Adamki11s.Regios.Mutable;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Regions.Region;

public class MutableProtection {
	
	public void editProtectBP(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.Protected.BlockPlace");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.Protected.BlockPlace", true);
		r.set_protectionPlace(true);
		c.save();
	}
	
	public void editUnProtectBP(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.Protected.BlockPlace");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.Protected.BlockPlace", false);
		r.set_protectionPlace(false);
		c.save();
	}
	
	public void editProtectBB(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.Protected.BlockBreak");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.Protected.BlockBreak", true);
		r.set_protectionBreak(true);
		c.save();
	}
	
	public void editUnProtectBB(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.Protected.BlockBreak");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.Protected.BlockBreak", false);
		r.set_protectionBreak(false);
		c.save();
	}
	
	public void editProtect(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.Protected.General");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.Protected.General", true);
		r.set_protection(true);
		c.save();
	}
	
	public void editUnprotect(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.Protected.General");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.Protected.General", false);
		r.set_protection(false);
		c.save();
	}
	
	public void editPreventEntry(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.PreventEntry");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.PreventEntry", true);
		r.setPreventEntry(true);
		c.save();
	}
	
	public void editAllowEntry(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.PreventEntry");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.PreventEntry", false);
		r.setPreventEntry(false);
		c.save();
	}
	
	public void editPreventExit(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.PreventExit");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.PreventExit", true);
		r.setPreventExit(true);
		c.save();
	}
	
	public void editAllowExit(Region r){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.PreventExit");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.PreventExit", false);
		r.setPreventExit(false);
		c.save();
	}

}
