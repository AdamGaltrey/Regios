package couk.Adamki11s.Regios.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;

import com.iCo6.iConomy;

import couk.Adamki11s.Regios.Economy.EconomyCore;

public class RegiosServerListener extends ServerListener {
	
	@Override
    public void onPluginDisable(PluginDisableEvent event) {
        if (EconomyCore.iconomy != null) {
            if (event.getPlugin().getDescription().getName().equals("iConomy")) {
            	EconomyCore.iconomy = null;
                System.out.println("[Regios] Un-hooked from iConomy.");
            }
        }
    }

    @Override
    public void onPluginEnable(PluginEnableEvent event) {
        if (EconomyCore.iconomy == null) {
            Plugin iConomy = Bukkit.getServer().getPluginManager().getPlugin("iConomy");

            if (iConomy != null) {
                if (iConomy.isEnabled() && iConomy.getClass().getName().equals("com.iConomy.iConomy")) {
                	EconomyCore.iconomy = (iConomy)iConomy;
                    System.out.println("[Regios] Hooked into iConomy successfully!");
                    EconomyCore.economySupport = true;
                }
            } else {
            	System.out.println("[Regios] iConomy not found on server!");
            }
        }
    }

}
