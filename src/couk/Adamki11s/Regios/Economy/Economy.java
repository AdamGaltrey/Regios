package couk.Adamki11s.Regios.Economy;

import java.util.logging.Logger;

public enum Economy {

	BOSECONOMY, ICONOMY, NONE;

	public static Economy toEconomy(String s) {
		if (s.equalsIgnoreCase("BOSECONOMY")) {
			return BOSECONOMY;
		} else if (s.equalsIgnoreCase("ICONOMY")) {
			return ICONOMY;
		} else if (s.equalsIgnoreCase("NONE")) {
			return NONE;
		} else {
			Logger.getLogger("Minecraft.Regios").severe(("[Regios] The Economy '" + s + "' is not one of the supported presets!"));
			return null;
		}
	}

}
