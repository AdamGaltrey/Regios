package couk.Adamki11s.Regios.Mutable;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Regions.Region;

public class MutableMessages {
	
	public void editWelcomeMessage(Region r, String message){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.WelcomeMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.WelcomeMessage", message);
		r.setWelcomeMessage(message);
		c.save();
	}
	
	public void editLeaveMessage(Region r, String message){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.LeaveMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.LeaveMessage", message);
		r.setLeaveMessage(message);
		c.save();
	}
	
	public void editPreventEntryMessage(Region r, String message){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.PreventEntryMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.PreventEntryMessage", message);
		r.setPreventEntryMessage(message);
		c.save();
	}
	
	public void editPreventExitMessage(Region r, String message){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.PreventExitMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.PreventExitMessage", message);
		r.setPreventExitMessage(message);
		c.save();
	}
	
	public void editProtectionMessage(Region r, String message){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.ProtectionMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.ProtectionMessage", message);
		r.setProtectionMessage(message);
		c.save();
	}
	
	public void editShowWelcomeMessage(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.ShowWelcomeMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.ShowWelcomeMessage", val);
		r.setShowWelcomeMessage(val);
		c.save();
	}
	
	public void editShowLeaveMessage(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.ShowLeaveMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.ShowLeaveMessage", val);
		r.setShowLeaveMessage(val);
		c.save();
	}
	
	public void editShowProtectionMessage(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.ShowProtectionMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.ShowProtectionMessage", val);
		r.setShowProtectionMessage(val);
		c.save();
	}
	
	public void editShowPreventEntryMessage(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.ShowPreventEntryMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.ShowPreventEntryMessage", val);
		r.setShowPreventEntryMessage(val);
		c.save();
	}
	
	public void editShowPreventExitMessage(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.ShowPreventExitMessage");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.ShowPreventExitMessage", val);
		r.setShowPreventExitMessage(val);
		c.save();
	}
	
	public void editShowPvpWarningMessage(Region r, boolean val){
		Configuration c = r.getConfigFile();
		c.load();
		Map<String, Object> all = c.getAll();
		all.remove("Region.Messages.ShowPvpWarning");
		for(Entry<String, Object> entry : all.entrySet()){
			c.setProperty(entry.getKey(), entry.getValue());
		}
		c.setProperty("Region.Messages.ShowPvpWarning", val);
		r.setShowPvpWarning(val);
		c.save();
	}

}
