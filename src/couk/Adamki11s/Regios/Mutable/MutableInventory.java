package couk.Adamki11s.Regios.Mutable;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Regions.Region;

public class MutableInventory {
	
	public void editPermWipeOnEnter(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Inventory.PermWipeOnEnter");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Inventory.PermWipeOnEnter", val);
		r.setPermWipeOnEnter(val);
		c.save();
	}
	
	public void editPermWipeOnExit(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Inventory.PermWipeOnExit");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Inventory.PermWipeOnExit", val);
		r.setPermWipeOnExit(val);
		c.save();
	}
	
	public void editWipeAndCacheOnEnter(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Inventory.WipeAndCacheOnEnter");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Inventory.WipeAndCacheOnEnter", val);
		r.setWipeAndCacheOnEnter(val);
		c.save();
	}
	
	public void editWipeAndCacheOnExit(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Inventory.WipeAndCacheOnExit");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Inventory.WipeAndCacheOnExit", val);
		r.setWipeAndCacheOnExit(val);
		c.save();
	}

}
