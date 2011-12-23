package couk.Adamki11s.Regios.Mutable;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Extras.Cryptography.ExtrasCryptography;
import couk.Adamki11s.Regios.Regions.Region;

public class MutableProtectionMisc {
	
	ExtrasCryptography exCrypt = new ExtrasCryptography();
	
	public void editInteraction(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.PreventInteraction");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.PreventInteraction", val);
		r.setPreventInteraction(val);
		c.save();
	}
	
	public void editChestsLocked(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.ChestsLocked");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.ChestsLocked", val);
		r.setChestsLocked(val);
		c.save();
	}
	
	public void editBlockForm(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Block.BlockForm.Enabled");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Block.BlockForm.Enabled", val);
		r.setBlockForm(val);
		c.save();
	}
	
	public void editPlayerCap(Region r, int val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.PlayerCap.Cap");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.PlayerCap.Cap", val);
		r.setPlayerCap(val);
		c.save();
	}

	public void editDoorsLocked(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.DoorsLocked");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.DoorsLocked", val);
		r.setDoorsLocked(val);
		c.save();
	}
	
	public void editSetPassword(Region r, String val){
		Configuration c = r.getConfigFile();
		c.load();
		val = exCrypt.computeSHA2_384BitHash(val);
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.Password.Password");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.Password.Password", val);
		r.setPassword(val);
		c.save();
	}
	
	public void editPasswordEnabled(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.Password.Enabled");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.Password.Enabled", val);
		r.setPasswordEnabled(val);
		c.save();
	}
	
	public void editFireProtection(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.General.FireProtection");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.General.FireProtection", val);
		r.setFireProtection(val);
		c.save();
	}
	
	
}
