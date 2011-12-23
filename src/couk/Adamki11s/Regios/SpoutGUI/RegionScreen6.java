package couk.Adamki11s.Regios.SpoutGUI;

import org.bukkit.ChatColor;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;

import couk.Adamki11s.Regios.Main.Regios;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.SpoutGUI.RegionScreenManager.RGB;

public class RegionScreen6 {

	public static void loadScreen(SpoutPlayer sp, Region r, Object[] oldWidgets, ScreenHolder sh) {
		if (oldWidgets != null) {
			for (Object w : oldWidgets) {
				((Widget) w).setVisible(false);
				((Widget) w).setDirty(true);
			}
		}

		for (Widget w : sh.page6Widgets) {
			w.setPriority(RenderPriority.Lowest);
		}

		((GenericTextField) sh.page6Widgets[0]).setX(15);
		((GenericTextField) sh.page6Widgets[0]).setY(55);
		((GenericTextField) sh.page6Widgets[0]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page6Widgets[0]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page6Widgets[0]).setWidth(400);
		((GenericTextField) sh.page6Widgets[0]).setHeight(15);
		((GenericTextField) sh.page6Widgets[0]).setMaximumCharacters(26);
		((GenericTextField) sh.page6Widgets[0]).setText(r.getSpoutWelcomeMessage());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[0])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[0].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[0].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[0]);
		}

		((GenericTextField) sh.page6Widgets[1]).setX(15);
		((GenericTextField) sh.page6Widgets[1]).setY(90);
		((GenericTextField) sh.page6Widgets[1]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page6Widgets[1]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page6Widgets[1]).setWidth(400);
		((GenericTextField) sh.page6Widgets[1]).setHeight(15);
		((GenericTextField) sh.page6Widgets[1]).setMaximumCharacters(26);
		((GenericTextField) sh.page6Widgets[1]).setText(r.getSpoutLeaveMessage());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[1])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[1].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[1].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[1]);
		}

		((GenericButton) sh.page6Widgets[2]).setText("Reset");
		((GenericButton) sh.page6Widgets[2]).setWidth(40);
		((GenericButton) sh.page6Widgets[2]).setHeight(15);
		((GenericButton) sh.page6Widgets[2]).setX(376);
		((GenericButton) sh.page6Widgets[2]).setY(37);
		((GenericButton) sh.page6Widgets[2]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[2]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[2])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[2].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[2].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[2]);
		}

		((GenericButton) sh.page6Widgets[3]).setText("Reset");
		((GenericButton) sh.page6Widgets[3]).setWidth(40);
		((GenericButton) sh.page6Widgets[3]).setHeight(15);
		((GenericButton) sh.page6Widgets[3]).setX(376);
		((GenericButton) sh.page6Widgets[3]).setY(72);
		((GenericButton) sh.page6Widgets[3]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[3]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[3])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[3].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[3].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[3]);
		}

		((GenericButton) sh.page6Widgets[5]).setText("Clear");
		((GenericButton) sh.page6Widgets[5]).setWidth(40);
		((GenericButton) sh.page6Widgets[5]).setHeight(15);
		((GenericButton) sh.page6Widgets[5]).setX(333);
		((GenericButton) sh.page6Widgets[5]).setY(72);
		((GenericButton) sh.page6Widgets[5]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[5]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[5])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[5].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[5].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[5]);
		}
		
		((GenericButton) sh.page6Widgets[22]).setText("Enabled");
		((GenericButton) sh.page6Widgets[22]).setWidth(50);
		((GenericButton) sh.page6Widgets[22]).setHeight(15);
		((GenericButton) sh.page6Widgets[22]).setX(280);
		((GenericButton) sh.page6Widgets[22]).setY(72);
		((GenericButton) sh.page6Widgets[22]).setTextColor(RegionScreenManager.getColourToken(r.isSpoutLeaveEnabled()));
		((GenericButton) sh.page6Widgets[22]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[22])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[22].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[22].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[22]);
		}


		((GenericButton) sh.page6Widgets[4]).setText("Clear");
		((GenericButton) sh.page6Widgets[4]).setWidth(40);
		((GenericButton) sh.page6Widgets[4]).setHeight(15);
		((GenericButton) sh.page6Widgets[4]).setX(333);
		((GenericButton) sh.page6Widgets[4]).setY(37);
		((GenericButton) sh.page6Widgets[4]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[4]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[4])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[4].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[4].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[4]);
		}
		
		((GenericButton) sh.page6Widgets[23]).setText("Enabled");
		((GenericButton) sh.page6Widgets[23]).setWidth(50);
		((GenericButton) sh.page6Widgets[23]).setHeight(15);
		((GenericButton) sh.page6Widgets[23]).setX(280);
		((GenericButton) sh.page6Widgets[23]).setY(37);
		((GenericButton) sh.page6Widgets[23]).setTextColor(RegionScreenManager.getColourToken(r.isSpoutWelcomeEnabled()));
		((GenericButton) sh.page6Widgets[23]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[23])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[23].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[23].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[23]);
		}

		((GenericButton) sh.page6Widgets[6]).setText("Update");
		((GenericButton) sh.page6Widgets[6]).setWidth(83);
		((GenericButton) sh.page6Widgets[6]).setHeight(20);
		((GenericButton) sh.page6Widgets[6]).setX(333);
		((GenericButton) sh.page6Widgets[6]).setY(110);
		((GenericButton) sh.page6Widgets[6]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[6]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[6])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[6].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[6].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[6]);
		}

		((GenericTextField) sh.page6Widgets[7]).setX(15);
		((GenericTextField) sh.page6Widgets[7]).setY(120);
		((GenericTextField) sh.page6Widgets[7]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page6Widgets[7]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page6Widgets[7]).setWidth(50);
		((GenericTextField) sh.page6Widgets[7]).setHeight(15);
		((GenericTextField) sh.page6Widgets[7]).setMaximumCharacters(4);
		((GenericTextField) sh.page6Widgets[7]).setText(Integer.toString(r.getSpoutWelcomeMaterial().getId()));

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[7])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[7].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[7].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[7]);
		}

		((GenericLabel) sh.page6Widgets[18]).setText("Spout Welcome Icon ID");
		((GenericLabel) sh.page6Widgets[18]).setX(15);
		((GenericLabel) sh.page6Widgets[18]).setY(109);
		((GenericLabel) sh.page6Widgets[18]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page6Widgets[18]).setTooltip(ChatColor.YELLOW + "  Block Icon shown upon entering a region.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[18])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[18].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[18].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[18]);
		}

		((GenericButton) sh.page6Widgets[9]).setText("Update");
		((GenericButton) sh.page6Widgets[9]).setWidth(55);
		((GenericButton) sh.page6Widgets[9]).setHeight(15);
		((GenericButton) sh.page6Widgets[9]).setX(70);
		((GenericButton) sh.page6Widgets[9]).setY(120);
		((GenericButton) sh.page6Widgets[9]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[9]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[9])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[9].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[9].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[9]);
		}

		((GenericTextField) sh.page6Widgets[8]).setX(15);
		((GenericTextField) sh.page6Widgets[8]).setY(150);
		((GenericTextField) sh.page6Widgets[8]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page6Widgets[8]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page6Widgets[8]).setWidth(50);
		((GenericTextField) sh.page6Widgets[8]).setHeight(15);
		((GenericTextField) sh.page6Widgets[8]).setMaximumCharacters(4);
		((GenericTextField) sh.page6Widgets[8]).setText(Integer.toString(r.getSpoutLeaveMaterial().getId()));

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[8])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[8].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[8].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[8]);
		}

		((GenericLabel) sh.page6Widgets[19]).setText("Spout Leave Icon ID");
		((GenericLabel) sh.page6Widgets[19]).setX(15);
		((GenericLabel) sh.page6Widgets[19]).setY(139);
		((GenericLabel) sh.page6Widgets[19]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page6Widgets[19]).setTooltip(ChatColor.YELLOW + "  Block Icon shown upon leaving a region.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[19])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[19].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[19].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[19]);
		}

		((GenericButton) sh.page6Widgets[10]).setText("Update");
		((GenericButton) sh.page6Widgets[10]).setWidth(55);
		((GenericButton) sh.page6Widgets[10]).setHeight(15);
		((GenericButton) sh.page6Widgets[10]).setX(70);
		((GenericButton) sh.page6Widgets[10]).setY(150);
		((GenericButton) sh.page6Widgets[10]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[10]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[10])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[10].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[10].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[10]);
		}

		((GenericTextField) sh.page6Widgets[11]).setX(15);
		((GenericTextField) sh.page6Widgets[11]).setY(180);
		((GenericTextField) sh.page6Widgets[11]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page6Widgets[11]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page6Widgets[11]).setWidth(400);
		((GenericTextField) sh.page6Widgets[11]).setHeight(15);
		((GenericTextField) sh.page6Widgets[11]).setMaximumCharacters(70);
		((GenericTextField) sh.page6Widgets[11]).setText(r.getSpoutTexturePack());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[11])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[11].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[11].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[11]);
		}

		((GenericLabel) sh.page6Widgets[20]).setText("Spout Texture Pack URL");
		((GenericLabel) sh.page6Widgets[20]).setX(15);
		((GenericLabel) sh.page6Widgets[20]).setY(169);
		((GenericLabel) sh.page6Widgets[20]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page6Widgets[20]).setTooltip(ChatColor.YELLOW + "  Force a texture pack upon entering a region.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[20])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[20].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[20].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[20]);
		}

		((GenericButton) sh.page6Widgets[12]).setText("Reset");
		((GenericButton) sh.page6Widgets[12]).setWidth(40);
		((GenericButton) sh.page6Widgets[12]).setHeight(15);
		((GenericButton) sh.page6Widgets[12]).setX(376);
		((GenericButton) sh.page6Widgets[12]).setY(162);
		((GenericButton) sh.page6Widgets[12]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[12]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[12])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[12].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[12].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[12]);
		}

		((GenericButton) sh.page6Widgets[13]).setText("Clear");
		((GenericButton) sh.page6Widgets[13]).setWidth(40);
		((GenericButton) sh.page6Widgets[13]).setHeight(15);
		((GenericButton) sh.page6Widgets[13]).setX(326);
		((GenericButton) sh.page6Widgets[13]).setY(162);
		((GenericButton) sh.page6Widgets[13]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[13]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[13])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[13].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[13].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[13]);
		}

		((GenericButton) sh.page6Widgets[14]).setText("Paste");
		((GenericButton) sh.page6Widgets[14]).setWidth(40);
		((GenericButton) sh.page6Widgets[14]).setHeight(15);
		((GenericButton) sh.page6Widgets[14]).setX(276);
		((GenericButton) sh.page6Widgets[14]).setY(162);
		((GenericButton) sh.page6Widgets[14]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[14]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[14])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[14].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[14].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[14]);
		}
		
		((GenericButton) sh.page6Widgets[21]).setText("Update");
		((GenericButton) sh.page6Widgets[21]).setWidth(40);
		((GenericButton) sh.page6Widgets[21]).setHeight(15);
		((GenericButton) sh.page6Widgets[21]).setX(226);
		((GenericButton) sh.page6Widgets[21]).setY(162);
		((GenericButton) sh.page6Widgets[21]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page6Widgets[21]).setHoverColor(RGB.SPRING_GREEN.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[21])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[21].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[21].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[21]);
		}

		((GenericButton) sh.page6Widgets[15]).setText("Use Textures");
		((GenericButton) sh.page6Widgets[15]).setWidth(80);
		((GenericButton) sh.page6Widgets[15]).setHeight(15);
		((GenericButton) sh.page6Widgets[15]).setX(336);
		((GenericButton) sh.page6Widgets[15]).setY(200);
		((GenericButton) sh.page6Widgets[15]).setTextColor(RegionScreenManager.getColourToken(r.isUseSpoutTexturePack()));
		((GenericButton) sh.page6Widgets[15]).setHoverColor(RGB.YELLOW.getColour());

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[15])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[15].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[15].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[15]);
		}

		((GenericLabel) sh.page6Widgets[16]).setText("Spout Welcome Message");
		((GenericLabel) sh.page6Widgets[16]).setX(15);
		((GenericLabel) sh.page6Widgets[16]).setY(44);
		((GenericLabel) sh.page6Widgets[16]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page6Widgets[16]).setTooltip(ChatColor.YELLOW + "  Popup Message shown upon entering a region.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[16])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[16].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[16].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[16]);
		}

		((GenericLabel) sh.page6Widgets[17]).setText("Spout Leave Message");
		((GenericLabel) sh.page6Widgets[17]).setX(15);
		((GenericLabel) sh.page6Widgets[17]).setY(79);
		((GenericLabel) sh.page6Widgets[17]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page6Widgets[17]).setTooltip(ChatColor.YELLOW + "  Popup Message shown upon entering a region.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page6Widgets[17])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[17].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page6Widgets[17].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page6Widgets[17]);
		}

	}

}
