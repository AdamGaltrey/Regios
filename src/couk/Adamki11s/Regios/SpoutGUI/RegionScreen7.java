package couk.Adamki11s.Regios.SpoutGUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
import couk.Adamki11s.Regios.Mutable.MutableSpout;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.SpoutGUI.RegionScreenManager.RGB;

public class RegionScreen7 {

	public static HashMap<SpoutPlayer, Integer> currentPage = new HashMap<SpoutPlayer, Integer>();

	public static final MutableSpout spout = new MutableSpout();

	public static int getPages(int urls) {
		if (urls <= 5) {
			return 1;
		} else {
			if ((urls % 5) != 0) {
				return (urls / 5) + 1;
			} else {
				return (urls / 5);
			}
		}
	}

	public static void nextPage(SpoutPlayer sp, Region r, ScreenHolder sh) {
		if (currentPage.get(sp) >= getPages(r.getMusicUrls().length)) {
			sp.sendNotification(ChatColor.RED + "Error!", "No next page!", Material.FIRE);
			return;
		}

		currentPage.put(sp, currentPage.get(sp) + 1);
		updatePages(sp, currentPage.get(sp), sh, r);
	}

	public static void prevPage(SpoutPlayer sp, Region r, ScreenHolder sh) {
		if (currentPage.get(sp) == 1) {
			sp.sendNotification(ChatColor.RED + "Error!", "No previous page!", Material.FIRE);
		} else {
			currentPage.put(sp, currentPage.get(sp) - 1);
			updatePages(sp, currentPage.get(sp), sh, r);
		}
	}

	public static void clearURLS(SpoutPlayer sp, Region r) {
		spout.editResetMusicList(r);
		sp.sendNotification(ChatColor.GREEN + "Spout Music", "URL'S Erased", Material.GREEN_RECORD);
		updatePages(sp, currentPage.get(sp), ScreenHolder.getScreenHolder(sp), r);
	}

	public static void addURL(SpoutPlayer sp, Region r, String ex, TextField tf) {
		if (spout.checkMusicUrl(ex, r)) {
			sp.sendNotification(ChatColor.RED + "Error!", "URL already exists!", Material.FIRE);
			tf.setText("");
			tf.setDirty(true);
		} else {
			spout.editAddToMusicList(r, ex);
			sp.sendNotification(ChatColor.GREEN + "Spout Music", "URL added", Material.GREEN_RECORD);
			tf.setText("");
			tf.setDirty(true);
		}
		updatePages(sp, currentPage.get(sp), ScreenHolder.getScreenHolder(sp), r);
	}

	public static void removeURL(SpoutPlayer sp, Region r, String ex, TextField tf) {
		if (!spout.checkMusicUrl(ex, r)) {
			sp.sendNotification(ChatColor.RED + "Error!", "URL doesn't exist", Material.FIRE);
			tf.setText("");
			tf.setDirty(true);
		} else {
			spout.editRemoveFromMusicList(r, ex);
			sp.sendNotification(ChatColor.GREEN + "Spout Music", "URL removed", Material.GREEN_RECORD);
			tf.setText("");
			tf.setDirty(true);
		}
		updatePages(sp, currentPage.get(sp), ScreenHolder.getScreenHolder(sp), r);
	}

	public static void updatePages(SpoutPlayer sp, int page, ScreenHolder sh, Region r) {

		for (Widget w : ((GenericContainer) sh.page7Widgets[3]).getChildren()) {
			((GenericContainer) sh.page7Widgets[3]).removeChild(w);
		}

		ArrayList<String> sortedURLS = new ArrayList<String>();

		for (String s : r.getMusicUrls()) {
			sortedURLS.add(s);
		}

		Collections.sort(sortedURLS);

		for (int exc = ((page * 5) - 5); exc < ((page * 5)); exc++) {

			((GenericLabel) sh.page7Widgets[2]).setText("Page " + currentPage.get(sp) + " / " + getPages(r.getMusicUrls().length));
			((GenericLabel) sh.page7Widgets[2]).setDirty(true);
			((GenericContainer) sh.page7Widgets[3]).setDirty(true);
			if ((exc < sortedURLS.size()) && sortedURLS.get(exc).length() >= 2) {
				GenericLabel ex = new GenericLabel(sortedURLS.get(exc));
				ex.setTextColor(RGB.YELLOW.getColour());
				((GenericContainer) sh.page7Widgets[3]).addChild(ex);
			} else {
				GenericLabel ex = new GenericLabel("-");
				ex.setTextColor(RGB.YELLOW.getColour());
				((GenericContainer) sh.page7Widgets[3]).addChild(ex);
			}

		}
	}

	public static void loadScreen(SpoutPlayer sp, Region r, Object[] oldWidgets, ScreenHolder sh) {

		currentPage.put(sp, 1);

		if (oldWidgets != null) {
			for (Object w : oldWidgets) {
				((Widget) w).setDirty(true);
				((Widget) w).shiftYPos(1000);// work around for overlap layer //
												// stack bug
			}
		}

		for (Widget w : sh.page7Widgets) {
			w.setPriority(RenderPriority.Lowest);
		}

		((GenericTextField) sh.page7Widgets[0]).setX(15);
		((GenericTextField) sh.page7Widgets[0]).setY(55);
		((GenericTextField) sh.page7Widgets[0]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page7Widgets[0]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page7Widgets[0]).setWidth(400);
		((GenericTextField) sh.page7Widgets[0]).setHeight(15);
		((GenericTextField) sh.page7Widgets[0]).setMaximumCharacters(26);

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[0])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[0].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[0].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page7Widgets[0]);
		}

		((GenericLabel) sh.page7Widgets[1]).setText("Spout Music");
		((GenericLabel) sh.page7Widgets[1]).setX(15);
		((GenericLabel) sh.page7Widgets[1]).setY(45);
		((GenericLabel) sh.page7Widgets[1]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page7Widgets[1]).setTooltip(ChatColor.YELLOW + "  Music collection played whilst inside a region.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[1])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[1].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[1].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page7Widgets[1]);
		}

		((GenericButton) sh.page7Widgets[6]).setText("Add Music");
		((GenericButton) sh.page7Widgets[6]).setWidth(100);
		((GenericButton) sh.page7Widgets[6]).setHeight(20);
		((GenericButton) sh.page7Widgets[6]).setX(15);
		((GenericButton) sh.page7Widgets[6]).setY(73);
		((GenericButton) sh.page7Widgets[6]).setTextColor(RGB.GREEN.getColour());
		((GenericButton) sh.page7Widgets[6]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[6])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[6].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[6].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page7Widgets[6]);
		}

		((GenericButton) sh.page7Widgets[7]).setText("Remove Music");
		((GenericButton) sh.page7Widgets[7]).setWidth(100);
		((GenericButton) sh.page7Widgets[7]).setHeight(20);
		((GenericButton) sh.page7Widgets[7]).setX(120);
		((GenericButton) sh.page7Widgets[7]).setY(73);
		((GenericButton) sh.page7Widgets[7]).setTextColor(RGB.RED.getColour());
		((GenericButton) sh.page7Widgets[7]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[7])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[7].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[7].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page7Widgets[7]);
		}

		((GenericButton) sh.page7Widgets[8]).setText("Clear Music");
		((GenericButton) sh.page7Widgets[8]).setWidth(100);
		((GenericButton) sh.page7Widgets[8]).setHeight(20);
		((GenericButton) sh.page7Widgets[8]).setX(225);
		((GenericButton) sh.page7Widgets[8]).setY(73);
		((GenericButton) sh.page7Widgets[8]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page7Widgets[8]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[8])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[8].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[8].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page7Widgets[8]);
		}

		((GenericLabel) sh.page7Widgets[2]).setText("Page : ");
		((GenericLabel) sh.page7Widgets[2]).setX(200);
		((GenericLabel) sh.page7Widgets[2]).setY(200);
		((GenericLabel) sh.page7Widgets[2]).setTextColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[2])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[2].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[2].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page7Widgets[2]);
		}
		
		((GenericButton) sh.page7Widgets[4]).setText("<");
		((GenericButton) sh.page7Widgets[4]).setWidth(35);
		((GenericButton) sh.page7Widgets[4]).setHeight(20);
		((GenericButton) sh.page7Widgets[4]).setX(15);
		((GenericButton) sh.page7Widgets[4]).setY(195);
		((GenericButton) sh.page7Widgets[4]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page7Widgets[4]).setHoverColor(RGB.GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[4])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[4].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[4].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page7Widgets[4]);
		}
		
		((GenericButton) sh.page7Widgets[5]).setText(">");
		((GenericButton) sh.page7Widgets[5]).setWidth(35);
		((GenericButton) sh.page7Widgets[5]).setHeight(20);
		((GenericButton) sh.page7Widgets[5]).setX(380);
		((GenericButton) sh.page7Widgets[5]).setY(195);
		((GenericButton) sh.page7Widgets[5]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page7Widgets[5]).setHoverColor(RGB.GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[5])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[5].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[5].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page7Widgets[5]);
		}

		((GenericContainer) sh.page7Widgets[3]).setX(15);
		((GenericContainer) sh.page7Widgets[3]).setY(110);
		((GenericContainer) sh.page7Widgets[3]).setWidth(400);
		((GenericContainer) sh.page7Widgets[3]).setHeight(80);

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[3])) {
			for (Widget w : ((Container) ((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[3].getId())).getChildren()) {
				((Container) ((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[3].getId())).removeChild(w);
			}
		}
		
		for (int index = 0; index <= 4; index++) {
			if ((index < (r.getMusicUrls()).length) && r.getMusicUrls()[index].length() > 2) {
				GenericLabel ex = new GenericLabel((r.getMusicUrls())[index]);
				ex.setTextColor(RGB.YELLOW.getColour());
				((GenericContainer) sh.page7Widgets[3]).addChild(ex);
			} else {
				GenericLabel ex = new GenericLabel("-");
				ex.setTextColor(RGB.YELLOW.getColour());
				((GenericContainer) sh.page7Widgets[3]).addChild(ex);
			}
		}
		
		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page7Widgets[3])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[3].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page7Widgets[3].getId()).setDirty(true);

		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page7Widgets[3]);
		}

		updatePages(sp, 1, sh, r);

	}

}
