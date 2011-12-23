package couk.Adamki11s.Regios.Versions;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.util.config.Configuration;

import couk.Adamki11s.Regios.Data.ConfigurationData;

public class VersionPatcher {

	private static final File root = new File("plugins" + File.separator + "Regios"), config_root = new File(root + File.separator + "Configuration");

	static final File patch4057F = new File(root + File.separator + "Versions" + File.separator + "Version Tracker" + File.separator + "4.0.57.rv");
	static final File patch4063F = new File(root + File.separator + "Versions" + File.separator + "Version Tracker" + File.separator + "4.0.63.rv");
	static final File patch4071F = new File(root + File.separator + "Versions" + File.separator + "Version Tracker" + File.separator + "4.0.71.rv");

	public static void runPatch(String version) throws IOException {
		if (version.equalsIgnoreCase("4.0.80")) {
			if (!patch4057F.exists()) {
				patch4057(version);
				patch4057F.createNewFile();
			}
			if (!patch4063F.exists()) {
				patch4063(version);
				patch4063F.createNewFile();
			}
			if (!patch4071F.exists()) {
				patch4071(version);
				patch4071F.createNewFile();
			}
		}
	}

	static final PrintStream outstream = System.out;

	private static void patch4057(String v) {
		outstream.println("[Regios][Patch] Patching files for version : " + v);
		outstream.println("[Regios][Patch] Modifying general configuration file...");
		File generalconfig = new File(config_root + File.separator + "GeneralSettings.config");
		Configuration c = new Configuration(generalconfig);
		c.load();
		String value = (String) c.getString("Regios.Economy");
		int oldID = c.getInt("Region.Tools.Setting.ID", 271);
		c = new Configuration(generalconfig);
		c.setProperty("Region.LogsEnabled", true);
		c.setProperty("Region.Tools.Setting.ID", oldID);
		c.setProperty("Region.Economy", value);
		c.save();
		ConfigurationData.logs = true;
		outstream.println("[Regios][Patch] Region.LogsEnabled property added.");
		outstream.println("[Regios][Patch] Region.Economy property modified from Regios.Economy.");
		outstream.println("[Regios][Patch] Patch completed!");
	}

	private static void patch4063(String v) {
		outstream.println("[Regios][Patch] Patching files for version : " + v);
		outstream.println("[Regios][Patch] Modifying 'DefaultRegion' configuration file...");
		File f = new File("plugins" + File.separator + "Regios" + File.separator + "Configuration" + File.separator + "DefaultRegion.config");
		Configuration c = new Configuration(f);
		c.load();
		Map<String, Object> map = c.getAll();
		for (Entry<String, Object> ent : map.entrySet()) {
			c.setProperty(ent.getKey(), ent.getValue());
		}
		c.setProperty("DefaultSettings.Economy.ForSale", false);
		c.setProperty("DefaultSettings.Economy.SalePrice", 0);
		c.save();
		outstream.println("[Regios][Patch] Economy.ForSale property added.");
		outstream.println("[Regios][Patch] Economy.SalePrice property added.");
		outstream.println("[Regios][Patch] Patch completed!");
	}

	private static void patch4071(String v) {
		outstream.println("[Regios][Patch] Patching files for version : " + v);
		outstream.println("[Regios][Patch] Modifying existing region files...");
		File fff = new File("plugins" + File.separator + "Regios" + File.separator + "Database");
		for (File tmp : fff.listFiles()) {
			if (tmp.isDirectory()) {
				File f = new File(tmp + File.separator + tmp.getName() + ".rz");
				Configuration c = new Configuration(f);
				c.load();
				Map<String, Object> map = c.getAll();
				for (Entry<String, Object> ent : map.entrySet()) {
					c.setProperty(ent.getKey(), ent.getValue());
				}
				c.setProperty("Region.Spout.Welcome.Enabled",map.get("Region.Messages.ShowWelcomeMessage"));
				c.setProperty("Region.Spout.Leave.Enabled", map.get("Region.Messages.ShowLeaveMessage"));
				c.save();
			}
		}
		outstream.println("[Regios][Patch] Spout welcome/leave popup toggle property added!");
		outstream.println("[Regios][Patch] Patch completed!");
	}

}
