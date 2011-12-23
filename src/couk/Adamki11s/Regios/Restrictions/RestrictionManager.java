package couk.Adamki11s.Regios.Restrictions;

import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Permissions.PermissionsCore;

public class RestrictionManager {

	public static RestrictionParameters getRestriction(Player p) {
		int c = 0, w = 0, h = 0, l = 0;

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.size10")) {
			w = 10;
			h = 10;
			l = 10;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.size20")) {
			w = 20;
			h = 20;
			l = 20;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.size30")) {
			w = 30;
			h = 30;
			l = 30;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.size40")) {
			w = 40;
			h = 40;
			l = 40;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.size50")) {
			w = 50;
			h = 50;
			l = 50;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.size75")) {
			w = 75;
			h = 75;
			l = 75;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.size100")) {
			w = 100;
			h = 100;
			l = 100;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.size125")) {
			w = 125;
			h = 125;
			l = 125;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.size150")) {
			w = 150;
			h = 150;
			l = 150;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.1")) {
			c = 1;
			w = 1;
			h = 1;
			l = 1;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.2")) {
			c = 2;
			w = 2;
			h = 2;
			l = 2;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.3")) {
			c = 3;
			w = 3;
			h = 3;
			l = 3;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.5")) {
			c = 5;
			w = 5;
			h = 5;
			l = 5;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.10")) {
			c = 10;
			w = 10;
			h = 10;
			l = 10;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.20")) {
			c = 20;
			w = 20;
			h = 20;
			l = 20;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.30")) {
			c = 30;
			w = 30;
			h = 30;
			l = 30;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.40")) {
			c = 40;
			w = 40;
			h = 40;
			l = 40;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.50")) {
			c = 50;
			w = 50;
			h = 50;
			l = 50;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.100")) {
			c = 100;
			w = 100;
			h = 100;
			l = 100;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.250")) {
			c = 250;
			w = 250;
			h = 250;
			l = 250;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.500")) {
			c = 500;
			w = 500;
			h = 500;
			l = 500;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.1000")) {
			c = 1000;
			w = 1000;
			h = 1000;
			l = 1000;
		}

		if (PermissionsCore.doesHaveNode(p, "regios.restrictions.none")) {
			c = 999999999;
			w = 999999999;
			h = 999999999;
			l = 999999999;
		}
		
		if (p.isOp()) {
			c = 999999999;
			w = 999999999;
			h = 999999999;
			l = 999999999;
		}

		return new RestrictionParameters(c, w, h, l);
	}

}
