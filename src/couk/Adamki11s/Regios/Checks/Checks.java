package couk.Adamki11s.Regios.Checks;

import org.bukkit.entity.Player;

public interface Checks {
	
	public void sendWelcomeMessage(Player p);
	
	public void sendLeaveMessage(Player p);
	
	public boolean canBuild(Player p);
	
	public boolean canEnter(Player p);
	
	public boolean canExit(Player p);
	
	public boolean canModify(Player p);
	
	public boolean isProtected();
	
	public boolean isPreventingEntry();
	
	public boolean isPreventingExit();

}
