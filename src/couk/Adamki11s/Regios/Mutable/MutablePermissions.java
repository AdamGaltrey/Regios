package couk.Adamki11s.Regios.Mutable;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Regions.Region;

public class MutablePermissions {

	public void editAddToTempAddCache(Region r, String message) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		String current = (String) all.get("Region.Permissions.TemporaryCache.AddNodes");
		all.remove("Region.Permissions.TemporaryCache.AddNodes");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Permissions.TemporaryCache.AddNodes", current.trim() + message.trim() + ",");
		r.setTemporaryNodesCacheAdd((current.trim() + "," + message.trim()).split(","));
		c.save();
	}

	public void editRemoveFromTempAddCache(Region r, String message) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		String current = (String) all.get("Region.Permissions.TemporaryCache.AddNodes");
		current = current.replaceAll(" ", "");
		current = current.replaceAll(message + ",", "");
		current = current.replaceAll(",,", ",");
		all.remove("Region.Permissions.TemporaryCache.AddNodes");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Permissions.TemporaryCache.AddNodes", current.trim());
		r.setTemporaryNodesCacheAdd((current.trim()).split(","));
		c.save();
	}

	public void editResetTempAddCache(Region r) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Permissions.TemporaryCache.AddNodes");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Permissions.TemporaryCache.AddNodes", "");
		r.setTemporaryNodesCacheAdd(("").split(","));
		c.save();
	}

	public boolean checkTempCache(Region r, String match) {
		for (String s : r.getTempCacheNodes()) {
			if (s.trim().equalsIgnoreCase(match.trim())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkPermAdd(Region r, String match) {
		for (String s : r.getPermanentNodesCacheAdd()) {
			if (s.trim().equalsIgnoreCase(match.trim())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkPermRemove(Region r, String match) {
		for (String s : r.getPermanentNodesCacheRemove()) {
			if (s.trim().equalsIgnoreCase(match.trim())) {
				return true;
			}
		}
		return false;
	}


	public void editAddToPermAddCache(Region r, String message) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		String current = (String) all.get("Region.Permissions.PermanentCache.AddNodes");
		all.remove("Region.Permissions.PermanentCache.AddNodes");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Permissions.PermanentCache.AddNodes", current.trim() + message.trim() + ",");
		r.setPermanentNodesCacheAdd((current.trim() + "," + message.trim()).split(","));
		c.save();
	}

	public void editRemoveFromPermAddCache(Region r, String message) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		String current = (String) all.get("Region.Permissions.PermanentCache.AddNodes");
		current = current.replaceAll(" ", "");
		current = current.replaceAll(message + ",", "");
		current = current.replaceAll(",,", ",");
		all.remove("Region.Permissions.PermanentCache.AddNodes");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Permissions.PermanentCache.AddNodes", current.trim());
		r.setPermanentNodesCacheAdd((current.trim()).split(","));
		c.save();
	}

	public void editResetPermAddCache(Region r) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Permissions.PermanentCache.AddNodes");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Permissions.PermanentCache.AddNodes", "");
		r.setPermanentNodesCacheAdd(("").split(","));
		c.save();
	}

	public void editAddToPermRemoveCache(Region r, String message) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		String current = (String) all.get("Region.Permissions.PermanentCache.RemoveNodes");
		all.remove("Region.Permissions.PermanentCache.RemoveNodes");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Permissions.PermanentCache.RemoveNodes", current.trim() + message.trim() + ",");
		r.setPermanentNodesCacheRemove((current.trim() + "," + message.trim()).split(","));
		c.save();
	}

	public void editRemoveFromPermRemoveCache(Region r, String message) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		String current = (String) all.get("Region.Permissions.PermanentCache.RemoveNodes");
		current = current.replaceAll(" ", "");
		current = current.replaceAll(message + ",", "");
		current = current.replaceAll(",,", ",");
		all.remove("Region.Permissions.PermanentCache.RemoveNodes");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Permissions.PermanentCache.RemoveNodes", current.trim());
		r.setPermanentNodesCacheRemove((current.trim()).split(","));
	}

	public void editResetPermRemoveCache(Region r) {
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Permissions.PermanentCache.RemoveNodes");
		for (Entry<String, Object> entry : all.entrySet()) {
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Permissions.PermanentCache.RemoveNodes", "");
		r.setPermanentNodesCacheRemove(("").split(","));
		c.save();
	}

	public String listTempAddCache(Region r) {
		Configuration c = r.getConfigFile();
		c.load();
		String s = c.getString("Region.Permissions.TemporaryCache.AddNodes", "");
		return s;
	}

	public String listPermAddCache(Region r) {
		Configuration c = r.getConfigFile();
		c.load();
		String s = c.getString("Region.Permissions.PermanentCache.AddNodes", "");
		return s;
	}

	public String listPermRemCache(Region r) {
		Configuration c = r.getConfigFile();
		c.load();
		String s = c.getString("Region.Permissions.PermanentCache.RemoveNodes", "");
		return s;
	}

}
