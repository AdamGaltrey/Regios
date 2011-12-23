package couk.Adamki11s.Regios.Versions;

import java.io.File;
import java.io.IOException;

import couk.Adamki11s.Regios.Main.Regios;

public class VersionTracker {

	static final File vtCore = new File("plugins" + File.separator + "Regios" + File.separator + "Versions" + File.separator + "Version Tracker");

	public static void createCurrentTracker() {
		try {
			VersionPatcher.runPatch(Regios.version);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
