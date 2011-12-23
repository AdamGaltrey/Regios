package couk.Adamki11s.Regios.Commands;

import org.bukkit.entity.Player;

public class DebugCommands {
	
	public void printChunk(Player p){
		System.out.println(p.getWorld().getChunkAt(p.getLocation()));
	}
	
	public void printLocation(Player p){
		System.out.println(p.getLocation());
	}

}
