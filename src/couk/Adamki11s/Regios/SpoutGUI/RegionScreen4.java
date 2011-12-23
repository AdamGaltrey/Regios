package couk.Adamki11s.Regios.SpoutGUI;

import java.util.Collections;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.gui.*;

import couk.Adamki11s.Regios.Main.Regios;
import couk.Adamki11s.Regios.Mutable.MutableExceptions;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.SpoutGUI.RegionScreenManager.RGB;

public class RegionScreen4 {

	public static enum ExToggle {
		PLAYER, NODE, SUB_OWNER, ITEM;
	}

	public static HashMap<SpoutPlayer, ExToggle> toggle = new HashMap<SpoutPlayer, ExToggle>();
	public static HashMap<SpoutPlayer, Integer> currentPage = new HashMap<SpoutPlayer, Integer>();

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

	public static void switchToggle(SpoutPlayer sp, ExToggle tog, ScreenHolder sh, Region r, Button butt, boolean silent) {
		((GenericButton) sh.page4Widgets[1]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page4Widgets[2]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page4Widgets[3]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page4Widgets[4]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page4Widgets[1]).setDirty(true);
		((GenericButton) sh.page4Widgets[2]).setDirty(true);
		((GenericButton) sh.page4Widgets[3]).setDirty(true);
		((GenericButton) sh.page4Widgets[4]).setDirty(true);
		((GenericLabel) sh.page4Widgets[0]).setX(butt.getX() + 15);
		((GenericLabel) sh.page4Widgets[0]).setDirty(true);
		butt.setTextColor(RGB.GREEN.getColour());
		butt.setDirty(true);
		toggle.put(sp, tog);
		if (!silent) {
			sp.sendNotification("Exception Type Changed", ChatColor.GREEN + tog.toString(), Material.CACTUS);
		}
		updateExceptionPages(sp, currentPage.get(sp), sh, r);
	}

	public static void nextPage(SpoutPlayer sp, Region r, ScreenHolder sh) {
		switch (toggle.get(sp)) {
		case PLAYER:
			if (currentPage.get(sp) >= getExceptionPages(r.getExceptions().size())) {
				sp.sendNotification(ChatColor.RED + "Error!", "No next page!", Material.FIRE);
				return;
			}
			break;
		case NODE:
			if (currentPage.get(sp) >= getExceptionPages(r.getNodes().size())) {
				sp.sendNotification(ChatColor.RED + "Error!", "No next page!", Material.FIRE);
				return;
			}
			break;
		case SUB_OWNER:
			if (currentPage.get(sp) >= getExceptionPages(r.getSubOwners().length)) {
				sp.sendNotification(ChatColor.RED + "Error!", "No next page!", Material.FIRE);
				return;
			}
			break;
		case ITEM:
			if (currentPage.get(sp) >= getExceptionPages(r.getItems().size())) {
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

	static final MutableExceptions excep = new MutableExceptions();

	private static boolean checkException(int val) {
		if (Material.getMaterial(val) != null) {
			return true;
		} else {
			return false;
		}
	}

	public static void eraseExceptions(ExToggle toggle, SpoutPlayer sp, Region r) {
		switch (toggle) {
		case PLAYER:
			excep.eraseAllPlayerExceptions(r);
			sp.sendNotification("Players", ChatColor.GREEN + "Exceptions erased", Material.PAPER);
		case NODE:
			excep.eraseAllNodeExceptions(r);
			sp.sendNotification("Nodes", ChatColor.GREEN + "Exceptions erased", Material.PAPER);
		case SUB_OWNER:
			excep.eraseAllSubOwners(r);
			sp.sendNotification("Sub Owners", ChatColor.GREEN + "Exceptions erased", Material.PAPER);
		case ITEM:
			excep.eraseAllItemExceptions(r);
			sp.sendNotification("Items", ChatColor.GREEN + "Exceptions erased", Material.PAPER);
		}
		updateExceptionPages(sp, currentPage.get(sp), ScreenHolder.getScreenHolder(sp), r);
	}

	public static void addException(ExToggle toggle, SpoutPlayer sp, Region r, String ex, TextField tf) {
		switch (toggle) {
		case PLAYER:
			if (excep.checkPlayerException(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Player already exception!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				excep.addPlayerException(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Players", "Exception added", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case NODE:
			if (excep.checkNodeException(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Node already exception!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				excep.addNodeException(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Nodes", "Exception added", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case SUB_OWNER:
			if (excep.checkSubOwnerException(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Player already sub-owner!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				excep.addSubOwner(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Sub Owners", "Exception added", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case ITEM:
			int item;
			try {
				item = Integer.parseInt(ex);
			} catch (NumberFormatException nfe) {
				sp.sendNotification(ChatColor.RED + "Error!", "Value must be an integer!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
			if (!checkException(item)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Invalid item ID!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
			if (excep.checkItemException(r, item)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Item already exception!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				excep.addItemException(r, item);
				sp.sendNotification(ChatColor.GREEN + "Items", "Exception added", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		}
		updateExceptionPages(sp, currentPage.get(sp), ScreenHolder.getScreenHolder(sp), r);
	}

	public static void removeException(ExToggle toggle, SpoutPlayer sp, Region r, String ex, TextField tf) {
		switch (toggle) {
		case PLAYER:
			if (!excep.checkPlayerException(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Player not exception!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				excep.removePlayerException(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Players", "Exception removed", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case NODE:
			if (!excep.checkNodeException(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Node not exception!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				excep.removeNodeException(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Nodes", "Exception removed", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case SUB_OWNER:
			if (!excep.checkSubOwnerException(r, ex)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Player not sub-owner!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				excep.removeSubOwner(r, ex);
				sp.sendNotification(ChatColor.GREEN + "Sub Owners", "Exception removed", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		case ITEM:
			int item;
			try {
				item = Integer.parseInt(ex);
			} catch (NumberFormatException nfe) {
				sp.sendNotification(ChatColor.RED + "Error!", "Value must be an integer!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
			if (!checkException(item)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Invalid item ID!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
			if (!excep.checkItemException(r, item)) {
				sp.sendNotification(ChatColor.RED + "Error!", "Item not exception!", Material.FIRE);
				tf.setText("");
				tf.setDirty(true);
				break;
			} else {
				excep.removeItemException(r, item);
				sp.sendNotification(ChatColor.GREEN + "Items", "Exception removed", Material.PAPER);
				tf.setText("");
				tf.setDirty(true);
				break;
			}
		}
		updateExceptionPages(sp, currentPage.get(sp), ScreenHolder.getScreenHolder(sp), r);
	}

	public static void updateExceptionPages(SpoutPlayer sp, int page, ScreenHolder sh, Region r) {
		for (Widget w : ((GenericContainer) sh.page4Widgets[12]).getChildren()) {
			((GenericContainer) sh.page4Widgets[12]).removeChild(w);
		}
		Collections.sort(r.getExceptions());
		Collections.sort(r.getNodes());
		Collections.sort(r.getItems());
		for (int exc = ((page * 5) - 5); exc < ((page * 5)); exc++) {

			switch (toggle.get(sp)) {
			case PLAYER:
				((GenericLabel) sh.page4Widgets[9]).setText("Page " + currentPage.get(sp) + " / " + getExceptionPages(r.getExceptions().size()));
				((GenericLabel) sh.page4Widgets[9]).setDirty(true);
				((GenericContainer) sh.page4Widgets[12]).setDirty(true);
				if (exc < (r.getExceptions()).size()) {
					GenericLabel ex = new GenericLabel((r.getExceptions()).get(exc));
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page4Widgets[12]).addChild(ex);
				} else {
					GenericLabel ex = new GenericLabel("-");
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page4Widgets[12]).addChild(ex);
				}
				break;
			case NODE:
				((GenericLabel) sh.page4Widgets[9]).setText("Page " + currentPage.get(sp) + " / " + getExceptionPages(r.getNodes().size()));
				((GenericLabel) sh.page4Widgets[9]).setDirty(true);
				((GenericContainer) sh.page4Widgets[12]).setDirty(true);
				if (exc < (r.getNodes()).size()) {
					GenericLabel ex = new GenericLabel((r.getNodes()).get(exc));
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page4Widgets[12]).addChild(ex);
				} else {
					GenericLabel ex = new GenericLabel("-");
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page4Widgets[12]).addChild(ex);
				}
				break;
			case SUB_OWNER:
				((GenericLabel) sh.page4Widgets[9]).setText("Page " + currentPage.get(sp) + " / " + getExceptionPages(r.getSubOwners().length));
				((GenericLabel) sh.page4Widgets[9]).setDirty(true);
				((GenericContainer) sh.page4Widgets[12]).setDirty(true);
				if ((exc < r.getSubOwners().length) && r.getSubOwners()[exc].length() >= 3) {
					GenericLabel ex = new GenericLabel(r.getSubOwners()[exc]);
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page4Widgets[12]).addChild(ex);
				} else {
					GenericLabel ex = new GenericLabel("-");
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page4Widgets[12]).addChild(ex);
				}
				break;
			case ITEM:
				((GenericLabel) sh.page4Widgets[9]).setText("Page " + currentPage.get(sp) + " / " + getExceptionPages(r.getItems().size()));
				((GenericLabel) sh.page4Widgets[9]).setDirty(true);
				((GenericContainer) sh.page4Widgets[12]).setDirty(true);
				if (exc < r.getItems().size()) {
					GenericLabel ex = new GenericLabel(Integer.toString(r.getItems().get(exc)));
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page4Widgets[12]).addChild(ex);
				} else {
					GenericLabel ex = new GenericLabel("-");
					ex.setTextColor(RGB.YELLOW.getColour());
					((GenericContainer) sh.page4Widgets[12]).addChild(ex);
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

		for (Widget w : sh.page1Widgets) {
			w.setPriority(RenderPriority.Lowest);
		}

		currentPage.put(sp, 1);
		toggle.put(sp, ExToggle.PLAYER);

		((GenericLabel) sh.page4Widgets[0]).setX(45);
		((GenericLabel) sh.page4Widgets[0]).setY(45);
		((GenericLabel) sh.page4Widgets[0]).setWidth(50);
		((GenericLabel) sh.page4Widgets[0]).setHeight(20);
		((GenericLabel) sh.page4Widgets[0]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page4Widgets[0]).setText("Exceptions");
		((GenericLabel) sh.page4Widgets[0]).setTooltip(ChatColor.YELLOW + "  Toggle between exceptions");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[0])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[0].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[0].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[0]);
		}

		((GenericButton) sh.page4Widgets[1]).setText("Players");
		((GenericButton) sh.page4Widgets[1]).setHeight(20);
		((GenericButton) sh.page4Widgets[1]).setWidth(80);
		((GenericButton) sh.page4Widgets[1]).setX(45);
		((GenericButton) sh.page4Widgets[1]).setY(55);
		((GenericButton) sh.page4Widgets[1]).setHoverColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page4Widgets[1]).setTextColor(RGB.GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[1])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[1].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[1].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[1]);
		}

		((GenericButton) sh.page4Widgets[2]).setText("Nodes");
		((GenericButton) sh.page4Widgets[2]).setHeight(20);
		((GenericButton) sh.page4Widgets[2]).setWidth(80);
		((GenericButton) sh.page4Widgets[2]).setX(130);
		((GenericButton) sh.page4Widgets[2]).setY(55);
		((GenericButton) sh.page4Widgets[2]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[2])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[2].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[2].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[2]);
		}

		((GenericButton) sh.page4Widgets[3]).setText("Sub Owners");
		((GenericButton) sh.page4Widgets[3]).setHeight(20);
		((GenericButton) sh.page4Widgets[3]).setWidth(80);
		((GenericButton) sh.page4Widgets[3]).setX(215);
		((GenericButton) sh.page4Widgets[3]).setY(55);
		((GenericButton) sh.page4Widgets[3]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[3])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[3].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[3].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[3]);
		}

		((GenericButton) sh.page4Widgets[4]).setText("Items");
		((GenericButton) sh.page4Widgets[4]).setHeight(20);
		((GenericButton) sh.page4Widgets[4]).setWidth(80);
		((GenericButton) sh.page4Widgets[4]).setX(300);
		((GenericButton) sh.page4Widgets[4]).setY(55);
		((GenericButton) sh.page4Widgets[4]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[4])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[4].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[4].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[4]);
		}

		switchToggle(sp, ExToggle.PLAYER, sh, r, ((GenericButton) sh.page4Widgets[1]), true);

		((GenericTextField) sh.page4Widgets[5]).setText("");
		((GenericTextField) sh.page4Widgets[5]).setHeight(20);
		((GenericTextField) sh.page4Widgets[5]).setWidth(160);
		((GenericTextField) sh.page4Widgets[5]).setX(10);
		((GenericTextField) sh.page4Widgets[5]).setY(95);
		((GenericTextField) sh.page4Widgets[5]).setMaximumCharacters(25);
		((GenericTextField) sh.page4Widgets[5]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page4Widgets[5]).setBorderColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[5])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[5].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[5].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[5]);
		}

		((GenericButton) sh.page4Widgets[6]).setText("Add Exception");
		((GenericButton) sh.page4Widgets[6]).setHeight(20);
		((GenericButton) sh.page4Widgets[6]).setWidth(160);
		((GenericButton) sh.page4Widgets[6]).setX(10);
		((GenericButton) sh.page4Widgets[6]).setY(125);
		((GenericButton) sh.page4Widgets[6]).setHoverColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page4Widgets[6]).setTextColor(RGB.GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[6])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[6].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[6].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[6]);
		}

		((GenericButton) sh.page4Widgets[7]).setText("Remove Exception");
		((GenericButton) sh.page4Widgets[7]).setHeight(20);
		((GenericButton) sh.page4Widgets[7]).setWidth(160);
		((GenericButton) sh.page4Widgets[7]).setX(10);
		((GenericButton) sh.page4Widgets[7]).setY(155);
		((GenericButton) sh.page4Widgets[7]).setHoverColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page4Widgets[7]).setTextColor(RGB.RED.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[7])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[7].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[7].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[7]);
		}

		((GenericButton) sh.page4Widgets[8]).setText("Erase Exceptions");
		((GenericButton) sh.page4Widgets[8]).setHeight(20);
		((GenericButton) sh.page4Widgets[8]).setWidth(160);
		((GenericButton) sh.page4Widgets[8]).setX(10);
		((GenericButton) sh.page4Widgets[8]).setY(185);
		((GenericButton) sh.page4Widgets[8]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[8])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[8].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[8].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[8]);
		}

		((GenericLabel) sh.page4Widgets[9]).setX(215);
		((GenericLabel) sh.page4Widgets[9]).setY(85);
		((GenericLabel) sh.page4Widgets[9]).setWidth(50);
		((GenericLabel) sh.page4Widgets[9]).setHeight(20);
		((GenericLabel) sh.page4Widgets[9]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page4Widgets[9]).setText("Page 1 / " + getExceptionPages(r.getExceptions().size()));
		((GenericLabel) sh.page4Widgets[9]).setTooltip(ChatColor.YELLOW + "  Toggle between exception types");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[9])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[9].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[9].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[9]);
		}

		((GenericButton) sh.page4Widgets[10]).setX(215);
		((GenericButton) sh.page4Widgets[10]).setY(95);
		((GenericButton) sh.page4Widgets[10]).setWidth(35);
		((GenericButton) sh.page4Widgets[10]).setHeight(20);
		((GenericButton) sh.page4Widgets[10]).setTextColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page4Widgets[10]).setText("<");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[10])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[10].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[10].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[10]);
		}

		((GenericButton) sh.page4Widgets[11]).setX(345);
		((GenericButton) sh.page4Widgets[11]).setY(95);
		((GenericButton) sh.page4Widgets[11]).setWidth(35);
		((GenericButton) sh.page4Widgets[11]).setHeight(20);
		((GenericButton) sh.page4Widgets[11]).setTextColor(RGB.YELLOW.getColour());
		((GenericButton) sh.page4Widgets[11]).setText(">");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[11])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[11].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[11].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[11]);
		}

		((GenericContainer) sh.page4Widgets[12]).setX(215);
		((GenericContainer) sh.page4Widgets[12]).setY(120);
		((GenericContainer) sh.page4Widgets[12]).setWidth(100);
		((GenericContainer) sh.page4Widgets[12]).setHeight(85);

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[12])) {
			for (Widget w : ((Container) ((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[12].getId())).getChildren()) {
				((Container) ((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[12].getId())).removeChild(w);
			}
		}

		for (int index = 0; index <= 4; index++) {
			if (index < (r.getExceptions()).size()) {
				GenericLabel ex = new GenericLabel((r.getExceptions()).get(index));
				ex.setTextColor(RGB.YELLOW.getColour());
				((GenericContainer) sh.page4Widgets[12]).addChild(ex);
			} else {
				GenericLabel ex = new GenericLabel("-");
				ex.setTextColor(RGB.YELLOW.getColour());
				((GenericContainer) sh.page4Widgets[12]).addChild(ex);
			}
		}

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page4Widgets[12])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[12].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page4Widgets[12].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page4Widgets[12]);
		}

		updateExceptionPages(sp, 1, sh, r);

	}

}
