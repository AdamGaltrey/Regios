package couk.Adamki11s.Regios.Scheduler;

import org.bukkit.Bukkit;
import couk.Adamki11s.Regios.Main.Regios;

public class MainRunner {
	
	public static int taskid = 0;
	
	public static final void startMainRunner(){
		if(taskid == 0){
			mainRunner();
		}
	}
	
	public static final void stopMainRunner(){
		Bukkit.getServer().getScheduler().cancelTask(taskid);
	}
	
	private static final void mainRunner(){
		taskid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Regios.regios, new Runnable() {	

			public void run() {
				HealthRegeneration.loopRegenerators();
				LightningRunner.executeStrikes();
				LogRunner.pollLogMessages();
			}

		}, 20L, 20L); //Run every second 
	}

}
