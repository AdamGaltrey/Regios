package couk.Adamki11s.Regios.SpoutGUI;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.InGameHUD;
import org.getspout.spoutapi.gui.PopupScreen;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

import couk.Adamki11s.Regios.Data.MODE;
import couk.Adamki11s.Regios.Main.Regios;
import couk.Adamki11s.Regios.Regions.Region;

public class RegionScreenManager {

	public static HashMap<SpoutPlayer, GenericPopup> popup = new HashMap<SpoutPlayer, GenericPopup>();
	public static HashMap<SpoutPlayer, Integer> page = new HashMap<SpoutPlayer, Integer>();
	public static HashMap<SpoutPlayer, Region> editing = new HashMap<SpoutPlayer, Region>();

	public static HashMap<SpoutPlayer, GenericButton> escButton = new HashMap<SpoutPlayer, GenericButton>();
	public static HashMap<SpoutPlayer, GenericButton> pageForward = new HashMap<SpoutPlayer, GenericButton>();
	public static HashMap<SpoutPlayer, GenericButton> pageBackwards = new HashMap<SpoutPlayer, GenericButton>();
	public static HashMap<SpoutPlayer, GenericLabel> pageTracker = new HashMap<SpoutPlayer, GenericLabel>();

	public static Plugin plugin;

	private static final int pages = 7;

	public static void drawPanelFramework(SpoutPlayer sp, Region r, ScreenHolder sh) {

		plugin = Regios.regios;

		editing.put(sp, r);

		if (popup.containsKey(sp)) {
			((GenericPopup) popup.get(sp)).removeWidgets(plugin);
		}

		popup.put((SpoutPlayer) sp, new GenericPopup());

		page.put(sp, 1);

		drawPanelBase(sp, sh);

		RegionScreen1.loadScreen(sp, r, null, sh);
	}

	private static void drawPanelBase(SpoutPlayer sp, ScreenHolder sh) {
		InGameHUD hud = sp.getMainScreen();

		GenericTexture texture = new GenericTexture("http://dl.dropbox.com/u/27260323/Regios/GUI/Editor%20GUI%20Texture.png");

		texture.setAnchor(WidgetAnchor.SCALE);
		texture.setWidth(hud.getWidth());
		texture.setHeight(hud.getHeight());
		texture.setPriority(RenderPriority.Highest);

		((GenericPopup) popup.get(sp)).attachWidget(plugin, texture);

		GenericButton tmp1 = new GenericButton("Close");
		tmp1.setColor(RGB.RED.getColour());
		tmp1.setHoverColor(RGB.FIREBRICK.getColour());
		tmp1.setX(4);
		tmp1.setY(6);
		tmp1.setWidth(60);
		tmp1.setHeight(20);
		tmp1.setTooltip(ChatColor.RED + "  Close the Editor");

		sh.escButton = tmp1;

		escButton.put(sp, tmp1);

		((GenericPopup) popup.get(sp)).attachWidget(plugin, escButton.get(sp));

		GenericLabel tracker = new GenericLabel("Page : " + page.get(sp) + " / " + pages);
		tracker.setX((hud.getWidth() / 2) - 37);
		tracker.setY(hud.getHeight() - 15);
		tracker.setWidth(60);
		tracker.setTextColor(RGB.YELLOW.getColour());

		pageTracker.put(sp, tracker);

		sh.pageTracker = tracker;

		((GenericPopup) popup.get(sp)).attachWidget(plugin, pageTracker.get(sp));

		GenericButton tm2 = new GenericButton(">");
		tm2.setWidth(35);
		tm2.setHeight(20);
		tm2.setX(390);
		tm2.setY(hud.getHeight() - 24);
		tm2.setColor(RGB.RED.getColour());
		tm2.setHoverColor(RGB.GREEN.getColour());

		pageForward.put(sp, tm2);

		sh.pageForward = tm2;

		((GenericPopup) popup.get(sp)).attachWidget(plugin, pageForward.get(sp));

		GenericButton back = new GenericButton("<");
		back.setWidth(35);
		back.setHeight(20);
		back.setX(2);
		back.setY(hud.getHeight() - 22);
		back.setColor(RGB.RED.getColour());
		back.setHoverColor(RGB.GREEN.getColour());

		pageBackwards.put(sp, back);

		sh.pageBackwards = back;

		((GenericPopup) popup.get(sp)).attachWidget(plugin, pageBackwards.get(sp));

		hud.attachPopupScreen((PopupScreen) popup.get(sp));

	}

	public static void nextPage(SpoutPlayer sp, ScreenHolder sh) {
		int pageNum = page.get(sp);
		if (pageNum == pages) {
			sp.sendNotification(ChatColor.DARK_RED + "Error!", "No next page.", Material.FIRE);
			return;
		}
		page.put(sp, pageNum + 1);
		pageTracker.get(sp).setText("Page : " + page.get(sp) + " / " + pages);
		pageTracker.get(sp).setDirty(true);
		switch (page.get(sp)) {
		case 2:
			RegionScreen2.loadScreen(sp, editing.get(sp), sh.page1Widgets, sh);
			break;
		case 3:
			RegionScreen3.loadScreen(sp, editing.get(sp), sh.page2Widgets, sh);
			break;
		case 4:
			RegionScreen4.loadScreen(sp, editing.get(sp), sh.page3Widgets, sh);
			break;
		case 5:
			RegionScreen5.loadScreen(sp, editing.get(sp), sh.page4Widgets, sh);
			break;
		case 6:
			RegionScreen6.loadScreen(sp, editing.get(sp), sh.page5Widgets, sh);
			break;
		case 7:
			RegionScreen7.loadScreen(sp, editing.get(sp), sh.page6Widgets, sh);
			break;
		}
	}

	public static void previousPage(SpoutPlayer sp, ScreenHolder sh) {
		int pageNum = page.get(sp);
		if (pageNum == 1) {
			sp.sendNotification(ChatColor.DARK_RED + "Error!", "No previous page.", Material.FIRE);
			return;
		}
		page.put(sp, pageNum - 1);
		pageTracker.get(sp).setText("Page : " + page.get(sp) + " / " + pages);
		pageTracker.get(sp).setDirty(true);
		switch (page.get(sp)) {
		case 1:
			RegionScreen1.loadScreen(sp, editing.get(sp), sh.page2Widgets, sh);
			break;
		case 2:
			RegionScreen2.loadScreen(sp, editing.get(sp), sh.page3Widgets, sh);
			break;
		case 3:
			RegionScreen3.loadScreen(sp, editing.get(sp), sh.page4Widgets, sh);
			break;
		case 4:
			RegionScreen4.loadScreen(sp, editing.get(sp), sh.page5Widgets, sh);
			break;
		case 5:
			RegionScreen5.loadScreen(sp, editing.get(sp), sh.page6Widgets, sh);
			break;
		case 6:
			RegionScreen6.loadScreen(sp, editing.get(sp), sh.page7Widgets, sh);
			break;
		}
	}

	public static String getStatus(boolean b) {
		if (b) {
			return "  True";
		} else {
			return "  False";
		}
	}

	public static String getStatus(MODE m) {
		if (m == MODE.Whitelist) {
			return "  Whitelist";
		} else {
			return "  Blacklist";
		}
	}

	public static Color getColourToken(boolean b) {
		if (b) {
			return RGB.GREEN.getColour();
		} else {
			return RGB.FIREBRICK.getColour();
		}
	}

	public static Color getColourToken(MODE m) {
		if (m == MODE.Whitelist) {
			return RGB.WHITE.getColour();
		} else {
			return RGB.DARK_GREY.getColour();
		}
	}

	public static enum RGB {
		SPRING_GREEN(new Color((float) 0, (float) 0.803, (float) 0.4)), GREEN(new Color(0 / 255, 255 / 255, 0 / 255)), RED(new Color(255 / 255, 0 / 255, 0 / 255)), MIDNIGHT_BLUE(
				new Color((float) 0.098, (float) 0.098, (float) 0.439)), YELLOW(new Color((float) 255 / 255, (float) 255 / 255, (float) 0 / 255)), WHITE(new Color(255 / 255,
				255 / 255, 255 / 255)), FIREBRICK((new Color((float) 0.698, (float) 0.13, (float) 0.13))), BLACK(new Color(0 / 255, 0 / 255, 0 / 255)), DARK_GREY(new Color(
				128 / 255, 128 / 255, 128 / 255));

		RGB(Color c) {
			this.colour = c;
		}

		public Color getColour() {
			return this.colour;
		}

		private final Color colour;

		@Override
		public String toString() {
			return super.toString().substring(0, 1).toUpperCase() + super.toString().toLowerCase().substring(1, super.toString().length());
		}
	}

}
