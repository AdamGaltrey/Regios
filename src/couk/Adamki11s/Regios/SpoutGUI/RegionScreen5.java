package couk.Adamki11s.Regios.SpoutGUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.Container;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.TextField;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;

import couk.Adamki11s.Regios.Main.Regios;
import couk.Adamki11s.Regios.Mutable.MutableMisc;
import couk.Adamki11s.Regios.Mutable.MutablePermissions;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.SpoutGUI.RegionScreenManager.RGB;

public class RegionScreen5 {
	
	//need to add cmd checking to this.

	public static enum PermToggle {
		CACHE, PERM_ADD, PERM_REMOVE, SET;
	}

	public static HashMap<SpoutPlayer, PermToggle> toggle = new HashMap<SpoutPlayer, PermToggle>();
	public static HashMap<SpoutPlayer, Integer> currentPage = new HashMap<SpoutPlayer, Integer>();

	static final MutablePermissions perms = new MutablePermissions();
	static final MutableMisc misc = new MutableMisc();

	public static int getExceptionPages(int exceptions) {
		if (exceptions <= 5) {
			return 1;
		} else {
			if ((exceptions % 5) != 0) {
				return (exceptions / 5) + 1;
			} else {
				return (exceptions / 5);
			}
		}
	}

	public static void switchToggle(SpoutPlayer sp, PermToggle tog, ScreenHolder sh, Region r, Button butt, boolean silent) {
		((GenericButton) sh.page5Widgets[0]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page5Widgets[1]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page5Widgets[2]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page5Widgets[11]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page5Widgets[0]).setDirty(true);
		((GenericButton) sh.page5Widgets[1]).setDirty(true);
		((GenericButton) sh.page5Widgets[2]).setDirty(true);
		((GenericButton) sh.page5Widgets[11]).setDirty(true);
		butt.setTextColor(RGB.GREEN.getColour());
		butt.setDirty(true);
		toggle.put(sp, tog);
		if (!silent) {
			sp.sendNotification("Permission Type Changed", ChatColor.GREEN + tog.toString(), Material.CACTUS);
		}
		updateButtons(sh, sp, tog);
		updateExceptionPages(sp, currentPage.get(sp), sh, r);
	}

	public static void nextPage(SpoutPlayer sp, Region r, ScreenHolder sh) {
		switch (toggle.get(sp)) {
		case CACHE:
			if (currentPage.get(sp) >= getExceptionPages(r.getTemporaryNodesCacheAdd().length)) {
				sp.sendNotification(ChatColor.RED + "Error!", "No next page!", Material.FIRE);
				return;
			}
			break;
		case PERM_ADD:
			if (currentPage.get(sp) >= getExceptionPages(r.getPermanentNodesCacheAdd().length)) {
				sp.sendNotification(ChatColor.RED + "Error!", "No next page!", Material.FIRE);
				return;
			}
			break;
		case PERM_REMOVE:
			if (currentPage.get(sp) >= getExceptionPages(r.getPermanentNodesCacheRemove().length)) {
				sp.sendNotification(ChatColor.RED + "Error!", "No next page!", Material.FIRE);
				return;
			}
			break;
		case SET:
			if (currentPage.get(sp) >= getExceptionPages(r.getCommandSet().length)) {
				sp.sendNotification(ChatColor.RED + "Error!", "No next page!", Material.FIRE);
				return;
			}
			break;
		}

		currentPage.put(sp, currentPage.get(sp) + 1);
		updateExceptionPages(sp, currentPage.get(sp), sh, r);
	}

	public static void prevPage(SpoutPlayer sp, Region r, ScreenHolder sh) {
		if (currentPage.get(sp) == 1) {
			sp.sendNotification(ChatColor.RED + "Error!", "No previous page!", Material.FIRE);
		} else {
			currentPage.put(sp, currentPage.get(sp) - 1);
			updateExceptionPages(sp, currentPage.get(sp), sh, r);
		}
	}

	public static void eraseExceptions(PermToggle toggle, SpoutPlayer sp, Region r) {
		switch (toggle) {
		case CACHE:
			perms.editResetTempAddCache(r);
			sp.sendNotification("Cache", ChatColor.GREEN + "Permissions erased", Material.PAPER);
			break;
		case PERM_ADD:
			perms.editResetPermAddCache(r);
			sp.sendNotification("Perm-Add", ChatColor.GREEN + "Permissions erased", Material.PAPER);
			break;
		case PERM_REMOVE:
			perms.editResetPermRemoveCache(r);
			sp.sendNotification("Perm-Remove", ChatColor.GREEN + "Permissions erased", Material.PAPER);
			break;
		case SET:
			misc.editResetForceCommandSet(r);
			sp.sendNotification("Command Set", ChatColor.GREEN + "Commands erased", Material.PAPER);
			break;
		}
		updateExceptionPages(sp, currentPage.get(sp), ScreenHolder.getScreenHolder(sp), r);
	}

	public static void addException(PermToggle toggle, SpoutPlayer sp, Region r, String ex, TextField tf) {
		switch (toggle) {
		case CACHE:
			if (perms.checkTempCache(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Permission Exists!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				perms.editAddToTempAddCache(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Temp Cache", "Permission added", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case PERM_ADD:
			if (perms.checkPermAdd(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Permission exists!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				perms.editAddToPermAddCache(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Perm Add", "Permission added", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case PERM_REMOVE:
			if (perms.checkPermRemove(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Permission exists!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				perms.editAddToPermRemoveCache(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Perm Remove", "Permission added", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case SET:
			if (misc.checkCommandSet(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Command already exists!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				misc.editAddToForceCommandSet(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Command Set", "Command added", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		}
		updateExceptionPages(sp, currentPage.get(sp), ScreenHolder.getScreenHolder(sp), r);
	}

	public static void removeException(PermToggle toggle, SpoutPlayer sp, Region r, String ex, TextField tf) {
		switch (toggle) {
		case CACHE:
			if (!perms.checkTempCache(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Permission doesn't exist", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				perms.editRemoveFromTempAddCache(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Temp Cache", "Permission removed", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case PERM_ADD:
			if (!perms.checkPermAdd(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Permission doesn't exist", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				perms.editRemoveFromPermAddCache(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Perm Add", "Permission removed", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case PERM_REMOVE:
			if (!perms.checkPermRemove(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Permission doesn't exist", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				perms.editRemoveFromPermRemoveCache(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Perm Remove", "Permission removed", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case SET:
			if (!misc.checkCommandSet(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Command doesn't exist", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				misc.editRemoveFromForceCommandSet(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Commands Set", "Command removed", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		}
		updateExceptionPages(sp, currentPage.get(sp), ScreenHolder.getScreenHolder(sp), r);
	}
	
	public static void updateButtons(ScreenHolder sh, SpoutPlayer sp, PermToggle tog){
		if(tog == PermToggle.CACHE || tog == PermToggle.PERM_ADD || tog == PermToggle.PERM_REMOVE){
			((GenericButton) sh.page5Widgets[4]).setText("Add Permission");
			((GenericButton) sh.page5Widgets[4]).setDirty(true);
			((GenericButton) sh.page5Widgets[5]).setText("Remove Permission");
			((GenericButton) sh.page5Widgets[5]).setDirty(true);
			((GenericButton) sh.page5Widgets[6]).setText("Erase Permissions");
			((GenericButton) sh.page5Widgets[6]).setDirty(true);
		} else {
			((GenericButton) sh.page5Widgets[4]).setText("Add Command");
			((GenericButton) sh.page5Widgets[4]).setDirty(true);
			((GenericButton) sh.page5Widgets[5]).setText("Remove Command");
			((GenericButton) sh.page5Widgets[5]).setDirty(true);
			((GenericButton) sh.page5Widgets[6]).setText("Erase Commands");
			((GenericButton) sh.page5Widgets[6]).setDirty(true);
		}
	}

	public static void updateExceptionPages(SpoutPlayer sp, int page, ScreenHolder sh, Region r) {
		for (Widget w : ((GenericContainer) sh.page5Widgets[10]).getChildren()) {
			((GenericContainer) sh.page5Widgets[10]).removeChild(w);
		}
		
		ArrayList<String> sortedTempNodes = new ArrayList<String>();
		ArrayList<String> sortedAddNodes = new ArrayList<String>();
		ArrayList<String> sortedRemNodes = new ArrayList<String>();
		ArrayList<String> sortedCmdSet = new ArrayList<String>();
		
		if(r.getTemporaryNodesCacheAdd() != null){
		for(String s : r.getTemporaryNodesCacheAdd()){
			sortedTempNodes.add(s);
		}
		}
		if(r.getPermAddNodes() != null){
		for(String s : r.getPermAddNodes()){
			sortedAddNodes.add(s);
		}
		}
		if(r.getPermanentNodesCacheRemove() != null){
		for(String s : r.getPermanentNodesCacheRemove()){
			sortedRemNodes.add(s);
		}
		}
		if(r.getCommandSet() != null){
		for(String s : r.getCommandSet()){
			sortedCmdSet.add(s);
		}
		}
		
		Collections.sort(sortedTempNodes);
		Collections.sort(sortedAddNodes);
		Collections.sort(sortedRemNodes);
		Collections.sort(sortedCmdSet);
		
		for (int exc = ((page * 5) - 5); exc < ((page * 5)); exc++) {

			switch (toggle.get(sp)) {
			case CACHE:
				((GenericLabel) sh.page5Widgets[7]).setText("Page " + currentPage.get(sp) + " / " + getExceptionPages(r.getTempCacheNodes().length));
				((GenericLabel) sh.page5Widgets[7]).setDirty(true);
				((GenericContainer) sh.page5Widgets[10]).setDirty(true);
				if (exc < (sortedTempNodes.size()) && sortedTempNodes.get(exc).length() >= 2) {
					GenericLabel ex = new GenericLabel((sortedTempNodes.get(exc)));
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page5Widgets[10]).addChild(ex);
				} else {
					GenericLabel ex = new GenericLabel("-");
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page5Widgets[10]).addChild(ex);
				}
				break;
			case PERM_ADD:
				((GenericLabel) sh.page5Widgets[7]).setText("Page " + currentPage.get(sp) + " / " + getExceptionPages(sortedAddNodes.size()));
				((GenericLabel) sh.page5Widgets[7]).setDirty(true);
				((GenericContainer) sh.page5Widgets[10]).setDirty(true);
				if (exc < (sortedAddNodes.size()) && sortedAddNodes.get(exc).length() >= 2) {
					GenericLabel ex = new GenericLabel(sortedAddNodes.get(exc));
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page5Widgets[10]).addChild(ex);
				} else {
					GenericLabel ex = new GenericLabel("-");
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page5Widgets[10]).addChild(ex);
				}
				break;
			case PERM_REMOVE:
				((GenericLabel) sh.page5Widgets[7]).setText("Page " + currentPage.get(sp) + " / " + getExceptionPages(r.getPermanentNodesCacheRemove().length));
				((GenericLabel) sh.page5Widgets[7]).setDirty(true);
				((GenericContainer) sh.page5Widgets[10]).setDirty(true);
				if ((exc < sortedRemNodes.size()) && sortedRemNodes.get(exc).length() >= 2) {
					GenericLabel ex = new GenericLabel(sortedRemNodes.get(exc));
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page5Widgets[10]).addChild(ex);
				} else {
					GenericLabel ex = new GenericLabel("-");
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page5Widgets[10]).addChild(ex);
				}
				break;
			case SET:
				((GenericLabel) sh.page5Widgets[7]).setText("Page " + currentPage.get(sp) + " / " + getExceptionPages(r.getCommandSet().length));
				((GenericLabel) sh.page5Widgets[7]).setDirty(true);
				((GenericContainer) sh.page5Widgets[10]).setDirty(true);
				if ((exc < sortedCmdSet.size()) && sortedCmdSet.get(exc).length() >= 2) {
					GenericLabel ex = new GenericLabel(sortedCmdSet.get(exc));
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page5Widgets[10]).addChild(ex);
				} else {
					GenericLabel ex = new GenericLabel("-");
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page5Widgets[10]).addChild(ex);
				}
				break;
			}

		}
	}

	public static void loadScreen(SpoutPlayer sp, Region r, Object[] oldWidgets, ScreenHolder sh) {

		Collections.sort(r.getExceptions());

		if (oldWidgets != null) {
			for (Object w : oldWidgets) {
				((Widget) w).setDirty(true);
				((Widget) w).shiftYPos(1000);// work around for overlap layer //
												// stack bug
			}
		}

		for (Widget w : sh.page5Widgets) {
			w.setPriority(RenderPriority.Lowest);
		}

		currentPage.put(sp, 1);
		toggle.put(sp, PermToggle.CACHE);

		((GenericButton) sh.page5Widgets[0]).setText("Cache");
		((GenericButton) sh.page5Widgets[0]).setHeight(20);
		((GenericButton) sh.page5Widgets[0]).setWidth(80);
		((GenericButton) sh.page5Widgets[0]).setX(45);
		((GenericButton) sh.page5Widgets[0]).setY(55);
		((GenericButton) sh.page5Widgets[0]).setHoverColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page5Widgets[0]).setTextColor(RGB.GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[0])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[0].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[0].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[0]);
		}

		((GenericButton) sh.page5Widgets[1]).setText("Perm Add");
		((GenericButton) sh.page5Widgets[1]).setHeight(20);
		((GenericButton) sh.page5Widgets[1]).setWidth(80);
		((GenericButton) sh.page5Widgets[1]).setX(130);
		((GenericButton) sh.page5Widgets[1]).setY(55);
		((GenericButton) sh.page5Widgets[1]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[1])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[1].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[1].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[1]);
		}

		((GenericButton) sh.page5Widgets[2]).setText("Perm Remove");
		((GenericButton) sh.page5Widgets[2]).setHeight(20);
		((GenericButton) sh.page5Widgets[2]).setWidth(80);
		((GenericButton) sh.page5Widgets[2]).setX(215);
		((GenericButton) sh.page5Widgets[2]).setY(55);
		((GenericButton) sh.page5Widgets[2]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[2])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[2].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[2].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[2]);
		}
		
		((GenericButton) sh.page5Widgets[11]).setText("Command Set");
		((GenericButton) sh.page5Widgets[11]).setHeight(20);
		((GenericButton) sh.page5Widgets[11]).setWidth(80);
		((GenericButton) sh.page5Widgets[11]).setX(300);
		((GenericButton) sh.page5Widgets[11]).setY(55);
		((GenericButton) sh.page5Widgets[11]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[11])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[11].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[11].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[11]);
		}
		
		switchToggle(sp, PermToggle.CACHE, sh, r, ((GenericButton) sh.page5Widgets[0]), true);

		((GenericTextField) sh.page5Widgets[3]).setText("");
		((GenericTextField) sh.page5Widgets[3]).setHeight(20);
		((GenericTextField) sh.page5Widgets[3]).setWidth(160);
		((GenericTextField) sh.page5Widgets[3]).setX(10);
		((GenericTextField) sh.page5Widgets[3]).setY(95);
		((GenericTextField) sh.page5Widgets[3]).setMaximumCharacters(25);
		((GenericTextField) sh.page5Widgets[3]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page5Widgets[3]).setBorderColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[3])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[3].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[3].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[3]);
		}

		((GenericButton) sh.page5Widgets[4]).setText("Add Permission");
		((GenericButton) sh.page5Widgets[4]).setHeight(20);
		((GenericButton) sh.page5Widgets[4]).setWidth(160);
		((GenericButton) sh.page5Widgets[4]).setX(10);
		((GenericButton) sh.page5Widgets[4]).setY(125);
		((GenericButton) sh.page5Widgets[4]).setHoverColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page5Widgets[4]).setTextColor(RGB.GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[4])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[4].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[4].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[4]);
		}

		((GenericButton) sh.page5Widgets[5]).setText("Remove Permission");
		((GenericButton) sh.page5Widgets[5]).setHeight(20);
		((GenericButton) sh.page5Widgets[5]).setWidth(160);
		((GenericButton) sh.page5Widgets[5]).setX(10);
		((GenericButton) sh.page5Widgets[5]).setY(155);
		((GenericButton) sh.page5Widgets[5]).setHoverColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page5Widgets[5]).setTextColor(RGB.RED.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[5])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[5].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[5].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[5]);
		}

		((GenericButton) sh.page5Widgets[6]).setText("Erase Permissions");
		((GenericButton) sh.page5Widgets[6]).setHeight(20);
		((GenericButton) sh.page5Widgets[6]).setWidth(160);
		((GenericButton) sh.page5Widgets[6]).setX(10);
		((GenericButton) sh.page5Widgets[6]).setY(185);
		((GenericButton) sh.page5Widgets[6]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[6])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[6].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[6].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[6]);
		}

		((GenericLabel) sh.page5Widgets[7]).setX(215);
		((GenericLabel) sh.page5Widgets[7]).setY(85);
		((GenericLabel) sh.page5Widgets[7]).setWidth(50);
		((GenericLabel) sh.page5Widgets[7]).setHeight(20);
		((GenericLabel) sh.page5Widgets[7]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page5Widgets[7]).setText("Page 1 / " + getExceptionPages(r.getTempCacheNodes().length));
		((GenericLabel) sh.page5Widgets[7]).setTooltip(ChatColor.YELLOW + "  Toggle between permissions");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[7])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[7].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[7].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[7]);
		}

		((GenericButton) sh.page5Widgets[8]).setX(215);
		((GenericButton) sh.page5Widgets[8]).setY(95);
		((GenericButton) sh.page5Widgets[8]).setWidth(35);
		((GenericButton) sh.page5Widgets[8]).setHeight(20);
		((GenericButton) sh.page5Widgets[8]).setTextColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page5Widgets[8]).setText("<");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[8])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[8].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[8].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[8]);
		}

		((GenericButton) sh.page5Widgets[9]).setX(345);
		((GenericButton) sh.page5Widgets[9]).setY(95);
		((GenericButton) sh.page5Widgets[9]).setWidth(35);
		((GenericButton) sh.page5Widgets[9]).setHeight(20);
		((GenericButton) sh.page5Widgets[9]).setTextColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page5Widgets[9]).setText(">");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[9])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[9].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[9].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[9]);
		}

		((GenericContainer) sh.page5Widgets[10]).setX(215);
		((GenericContainer) sh.page5Widgets[10]).setY(120);
		((GenericContainer) sh.page5Widgets[10]).setWidth(100);
		((GenericContainer) sh.page5Widgets[10]).setHeight(85);

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[10])) {
			for (Widget w : ((Container) ((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[10].getId())).getChildren()) {
				((Container) ((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[10].getId())).removeChild(w);
			}
		}

		for (int index = 0; index <= 4; index++) {
			if ((index < (r.getTemporaryNodesCacheAdd()).length) && r.getTemporaryNodesCacheAdd()[index].length() > 2) {
				GenericLabel ex = new GenericLabel((r.getTemporaryNodesCacheAdd())[index]);
				ex.setTextColor(RGB.YELLOW.getColour());
				((GenericContainer) sh.page5Widgets[10]).addChild(ex);
			} else {
				GenericLabel ex = new GenericLabel("-");
				ex.setTextColor(RGB.YELLOW.getColour());
				((GenericContainer) sh.page5Widgets[10]).addChild(ex);
			}
		}

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page5Widgets[10])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[10].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page5Widgets[10].getId()).setDirty(true);

		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page5Widgets[10]);
		}
		
		updateExceptionPages(sp, 1, sh, r);

	}

}
