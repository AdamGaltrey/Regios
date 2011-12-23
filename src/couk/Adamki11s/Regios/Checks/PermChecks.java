package couk.Adamki11s.Regios.Checks;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Data.MODE;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;

public class PermChecks {

	public boolean canOverride(Player p, Region r) {
		if (PermissionsCore.doesHaveNode(p, ("regios.override." + r.getName())) || PermissionsCore.doesHaveNode(p, "regios.override.all")) {
			return true;
		}
		if (p.hasPermission("regios.override." + r.getName())) {
			return true;
		}
		if (p.hasPermission("regios.override.all")) {
			return true;
		}
		if (p.isOp()) {
			return true;
		} else if (r.getOwner().equalsIgnoreCase(p.getName())) {
			return true;
		} else {
			if (r.getSubOwners() != null && r.getSubOwners().length > 0) {
				for (String subOwner : r.getSubOwners()) {
					if (subOwner.equalsIgnoreCase(p.getName())) {
						return true;
					}
				}
			}
			return false;
		}
	}

	public boolean canBypassItemProtection(Player p, Region r) {
		if (PermissionsCore.doesHaveNode(p, ("regios.bypass." + r.getName())) || PermissionsCore.doesHaveNode(p, "regios.bypass.all")) {
			return true;
		}
		if (p.hasPermission("regios.bypass." + r.getName())) {
			return true;
		}
		if (p.hasPermission("regios.bypass.all")) {
			return true;
		}
		if (canOverride(p, r)) {
			return true;
		} else {
			for (String excep : r.getExceptionNodes()) {
				if (r.getItemMode() == MODE.Whitelist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return true;
					}
				} else if (r.getItemMode() == MODE.Blacklist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return false;
					}
				}
			}
			for (String excep : r.getExceptions()) {
				if (r.getItemMode() == MODE.Whitelist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return true;
					}
				} else if (r.getItemMode() == MODE.Blacklist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return false;
					}
				}
			}
			if (r.getSubOwners() != null && r.getSubOwners().length > 0) {
				for (String excep : r.getSubOwners()) {
					if (r.getItemMode() == MODE.Whitelist) {
						if (excep.equalsIgnoreCase(p.getName())) {
							return true;
						}
					} else if (r.getItemMode() == MODE.Blacklist) {
						if (excep.equalsIgnoreCase(p.getName())) {
							return false;
						}
					}
				}
			}
			if (r.getItemMode() == MODE.Whitelist) {
				return false;
			} else if (r.getItemMode() == MODE.Blacklist) {
				return true;
			}
			return false;
		}
	}

	public boolean canBypassProtection(Player p, Region r) {
		if (PermissionsCore.doesHaveNode(p, ("regios.bypass." + r.getName())) || PermissionsCore.doesHaveNode(p, "regios.bypass.all")) {
			return true;
		}
		if (p.hasPermission("regios.bypass." + r.getName())) {
			return true;
		}
		if (p.hasPermission("regios.bypass.all")) {
			return true;
		}
		if (canOverride(p, r)) {
			return true;
		} else {
			for (String excep : r.getExceptionNodes()) {
				if (r.getProtectionMode() == MODE.Whitelist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return true;
					}
				} else if (r.getProtectionMode() == MODE.Blacklist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return false;
					}
				}
			}
			for (String excep : r.getExceptions()) {
				if (r.getProtectionMode() == MODE.Whitelist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return true;
					}
				} else if (r.getProtectionMode() == MODE.Blacklist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return false;
					}
				}
			}
			if (r.getSubOwners() != null && r.getSubOwners().length > 0) {
				for (String excep : r.getSubOwners()) {
					if (r.getProtectionMode() == MODE.Whitelist) {
						if (excep.equalsIgnoreCase(p.getName())) {
							return true;
						}
					} else if (r.getProtectionMode() == MODE.Blacklist) {
						if (excep.equalsIgnoreCase(p.getName())) {
							return false;
						}
					}
				}
			}
			if (r.getProtectionMode() == MODE.Whitelist) {
				return false;
			} else if (r.getProtectionMode() == MODE.Blacklist) {
				return true;
			}
			return false;
		}
	}

	public boolean canBypassEntryProtection(Player p, Region r) {
		if (PermissionsCore.doesHaveNode(p, ("regios.bypass." + r.getName())) || PermissionsCore.doesHaveNode(p, "regios.bypass.all")) {
			return true;
		}
		if (p.hasPermission("regios.bypass." + r.getName())) {
			return true;
		}
		if (p.hasPermission("regios.bypass.all")) {
			return true;
		}
		if (canOverride(p, r)) {
			return true;
		} else {
			for (String excep : r.getExceptionNodes()) {
				if (r.getPreventEntryMode() == MODE.Whitelist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return true;
					}
				} else if (r.getPreventEntryMode() == MODE.Blacklist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return false;
					}
				}
			}
			for (String excep : r.getExceptions()) {
				if (r.getPreventEntryMode() == MODE.Whitelist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return true;
					}
				} else if (r.getPreventEntryMode() == MODE.Blacklist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return false;
					}
				}
			}
			if (r.getSubOwners() != null && r.getSubOwners().length > 0) {
				for (String excep : r.getSubOwners()) {
					if (r.getPreventEntryMode() == MODE.Whitelist) {
						if (excep.equalsIgnoreCase(p.getName())) {
							return true;
						}
					} else if (r.getPreventEntryMode() == MODE.Blacklist) {
						if (excep.equalsIgnoreCase(p.getName())) {
							return false;
						}
					}
				}
			}
			if (r.getPreventEntryMode() == MODE.Whitelist) {
				return false;
			} else if (r.getPreventEntryMode() == MODE.Blacklist) {
				return true;
			}
			return false;
		}
	}

	public boolean canBypassExitProtection(Player p, Region r) {
		if (PermissionsCore.doesHaveNode(p, ("regios.bypass." + r.getName())) || PermissionsCore.doesHaveNode(p, "regios.bypass.all")) {
			return true;
		}
		if (p.hasPermission("regios.bypass." + r.getName())) {
			return true;
		}
		if (p.hasPermission("regios.bypass.all")) {
			return true;
		}
		if (canOverride(p, r)) {
			return true;
		} else {
			for (String excep : r.getExceptionNodes()) {
				if (r.getPreventExitMode() == MODE.Whitelist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return true;
					}
				} else if (r.getPreventExitMode() == MODE.Blacklist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return false;
					}
				}
			}
			for (String excep : r.getExceptions()) {
				if (r.getPreventExitMode() == MODE.Whitelist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return true;
					}
				} else if (r.getPreventExitMode() == MODE.Blacklist) {
					if (excep.equalsIgnoreCase(p.getName())) {
						return false;
					}
				}
			}
			if (r.getSubOwners() != null && r.getSubOwners().length > 0) {
				for (String excep : r.getSubOwners()) {
					if (r.getPreventExitMode() == MODE.Whitelist) {
						if (excep.equalsIgnoreCase(p.getName())) {
							return true;
						}
					} else if (r.getPreventExitMode() == MODE.Blacklist) {
						if (excep.equalsIgnoreCase(p.getName())) {
							return false;
						}
					}
				}
			}
			if (r.getPreventExitMode() == MODE.Whitelist) {
				return false;
			} else if (r.getPreventExitMode() == MODE.Blacklist) {
				return true;
			}
			return false;
		}
	}

	public boolean canItemBePlaced(Player p, Material m, Region r) {
		if (canBypassItemProtection(p, r)) {
			return true;
		}
		if (isSuper(p, r)) {
			return true;
		}
		if (r.getItemMode() == MODE.Whitelist) {
			return r.getItems().contains(m.getId());
		} else if (r.getItemMode() == MODE.Blacklist) {
			return !r.getItems().contains(m.getId());
		}
		return false;
	}

	public boolean canBuild(Player p, Region r) {
		if (canBypassProtection(p, r)) {
			return true;
		}
		if (isSuper(p, r)) {
			return true;
		}
		if (p.hasPermission("regios.bypass." + r.getName())) {
			return true;
		}
		if (p.hasPermission("regios.bypass.all")) {
			return true;
		}
		for (String excep : r.getExceptions()) {
			if (r.getProtectionMode() == MODE.Whitelist) {
				if (excep.equalsIgnoreCase(p.getName())) {
					return true;
				}
			} else if (r.getProtectionMode() == MODE.Blacklist) {
				if (excep.equalsIgnoreCase(p.getName())) {
					return false;
				}
			}
		}
		if (r.getProtectionMode() == MODE.Whitelist) {
			return false;
		} else if (r.getProtectionMode() == MODE.Blacklist) {
			return true;
		}
		return false;
	}
	
	

	public boolean canEnter(Player p, Region r) {
		if (canBypassEntryProtection(p, r)) {
			return true;
		}
		if (isSuper(p, r)) {
			return true;
		}
		if (p.hasPermission("regios.bypass." + r.getName())) {
			return true;
		}
		if (p.hasPermission("regios.bypass.all")) {
			return true;
		}
		for (String excep : r.getExceptions()) {
			if (r.getPreventEntryMode() == MODE.Whitelist) {
				if (excep.equalsIgnoreCase(p.getName())) {
					return true;
				}
			} else if (r.getPreventEntryMode() == MODE.Blacklist) {
				if (excep.equalsIgnoreCase(p.getName())) {
					return false;
				}
			}
		}
		if (r.getPreventEntryMode() == MODE.Whitelist) {
			return false;
		} else if (r.getPreventEntryMode() == MODE.Blacklist) {
			return true;
		}
		return false;
	}

	public boolean canExit(Player p, Region r) {
		if (canBypassExitProtection(p, r)) {
			return true;
		}
		if (isSuper(p, r)) {
			return true;
		}
		if (p.hasPermission("regios.bypass." + r.getName())) {
			return true;
		}
		if (p.hasPermission("regios.bypass.all")) {
			return true;
		}
		for (String excep : r.getExceptions()) {
			if (r.getPreventExitMode() == MODE.Whitelist) {
				if (excep.equalsIgnoreCase(p.getName())) {
					return true;
				}
			} else if (r.getPreventExitMode() == MODE.Blacklist) {
				if (excep.equalsIgnoreCase(p.getName())) {
					return false;
				}
			}
		}
		if (r.getPreventExitMode() == MODE.Whitelist) {
			return false;
		} else if (r.getPreventExitMode() == MODE.Blacklist) {
			return true;
		}
		return false;
	}

	public boolean isSuper(Player p, Region r) {
		if (p.isOp()) {
			return true;
		}
		if (p.hasPermission("regios.override." + r.getName())) {
			return true;
		}
		if (p.hasPermission("regios.override.all")) {
			return true;
		}
		return r.getOwner().equalsIgnoreCase(p.getName());
	}

}
