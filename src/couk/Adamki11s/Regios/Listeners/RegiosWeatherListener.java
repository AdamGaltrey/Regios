package couk.Adamki11s.Regios.Listeners;

import org.bukkit.World;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherListener;
import couk.Adamki11s.Regios.Regions.GlobalRegionManager;

public class RegiosWeatherListener extends WeatherListener {
	
	public void onLightningStrike(LightningStrikeEvent evt){
		World w = evt.getWorld();
		if(GlobalRegionManager.getGlobalWorldSetting(w) == null){
			return;
		}
		if(!GlobalRegionManager.getGlobalWorldSetting(w).lightning_enabled){
			evt.setCancelled(true);
		}
	}

}
