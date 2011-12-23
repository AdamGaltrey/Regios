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

public class RegionScreen3 {
	
	public static void loadScreen(SpoutPlayer sp, Region r, Object[] oldWidgets, ScreenHolder sh) {
		if (oldWidgets != null) {
			for (Object w : oldWidgets) {
				//if (w instanceof Widget) {
					//((Widget) w).setVisible(false);
					((Widget) w).setDirty(true);
					((Widget) w).shiftYPos(1000);//work around for overlap layer stack bug
					//((GenericPopup) RegionScreenManager.popup.get(sp)).removeWidget((Widget)w);
				//}
			}
		}
		
		for(Widget w : sh.page1Widgets){
			w.setPriority(RenderPriority.Lowest);
		}

		((GenericLabel) sh.page3Widgets[0]).setX(15);
		((GenericLabel) sh.page3Widgets[0]).setY(55);
		((GenericLabel) sh.page3Widgets[0]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page3Widgets[0]).setTooltip(ChatColor.YELLOW + "  The delay in seconds between each lightning strike.");		

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[0])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[0].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[0].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[0]);
		}
		
		((GenericTextField) sh.page3Widgets[1]).setText(Integer.toString(r.getLSPS()));
		((GenericTextField) sh.page3Widgets[1]).setX(15);
		((GenericTextField) sh.page3Widgets[1]).setY(65);
		((GenericTextField) sh.page3Widgets[1]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page3Widgets[1]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page3Widgets[1]).setWidth(70);
		((GenericTextField) sh.page3Widgets[1]).setHeight(15);
		((GenericTextField) sh.page3Widgets[1]).setMaximumCharacters(11);
		
		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[1])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[1].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[1].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[1]);
		}
		
		((GenericButton) sh.page3Widgets[2]).setText("Update");
		((GenericButton) sh.page3Widgets[2]).setX(90);
		((GenericButton) sh.page3Widgets[2]).setY(65);
		((GenericButton) sh.page3Widgets[2]).setWidth(50);
		((GenericButton) sh.page3Widgets[2]).setHeight(15);
		((GenericButton) sh.page3Widgets[2]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page3Widgets[2]).setHoverColor(RGB.SPRING_GREEN.getColour());	

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[2])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[2].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[2].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[2]);
		}
		
		((GenericLabel) sh.page3Widgets[3]).setX(15);
		((GenericLabel) sh.page3Widgets[3]).setY(85);
		((GenericLabel) sh.page3Widgets[3]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page3Widgets[3]).setTooltip(ChatColor.YELLOW + "  How many half-hearts will be healed per second in the region.");		

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[3])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[3].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[3].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[3]);
		}
		
		((GenericTextField) sh.page3Widgets[4]).setText(Integer.toString(r.getHealthRegen()));
		((GenericTextField) sh.page3Widgets[4]).setX(15);
		((GenericTextField) sh.page3Widgets[4]).setY(95);
		((GenericTextField) sh.page3Widgets[4]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page3Widgets[4]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page3Widgets[4]).setWidth(70);
		((GenericTextField) sh.page3Widgets[4]).setHeight(15);
		((GenericTextField) sh.page3Widgets[4]).setMaximumCharacters(11);
		
		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[4])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[4].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[4].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[4]);
		}
		
		((GenericButton) sh.page3Widgets[5]).setText("Update");
		((GenericButton) sh.page3Widgets[5]).setX(90);
		((GenericButton) sh.page3Widgets[5]).setY(95);
		((GenericButton) sh.page3Widgets[5]).setWidth(50);
		((GenericButton) sh.page3Widgets[5]).setHeight(15);
		((GenericButton) sh.page3Widgets[5]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page3Widgets[5]).setHoverColor(RGB.SPRING_GREEN.getColour());		

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[5])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[5].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[5].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[5]);
		}
		
		((GenericLabel) sh.page3Widgets[6]).setX(15);
		((GenericLabel) sh.page3Widgets[6]).setY(115);
		((GenericLabel) sh.page3Widgets[6]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page3Widgets[6]).setTooltip(ChatColor.YELLOW + "  The factor your velocity will be warped by when inside a region.");		

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[6])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[6].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[6].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[6]);
		}
		
		((GenericTextField) sh.page3Widgets[7]).setText(Double.toString(r.getVelocityWarp()));
		((GenericTextField) sh.page3Widgets[7]).setX(15);
		((GenericTextField) sh.page3Widgets[7]).setY(125);
		((GenericTextField) sh.page3Widgets[7]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page3Widgets[7]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page3Widgets[7]).setWidth(70);
		((GenericTextField) sh.page3Widgets[7]).setHeight(15);
		((GenericTextField) sh.page3Widgets[7]).setMaximumCharacters(11);
		
		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[7])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[7].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[7].getId()).setDirty(true);
			((GenericTextField)((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[7].getId())).setEnabled(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[7]);
		}
		
		((GenericButton) sh.page3Widgets[8]).setText("Update");
		((GenericButton) sh.page3Widgets[8]).setX(90);
		((GenericButton) sh.page3Widgets[8]).setY(125);
		((GenericButton) sh.page3Widgets[8]).setWidth(50);
		((GenericButton) sh.page3Widgets[8]).setHeight(15);
		((GenericButton) sh.page3Widgets[8]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page3Widgets[8]).setHoverColor(RGB.SPRING_GREEN.getColour());		

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[8])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[8].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[8].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[8]);
		}
		
		((GenericButton) sh.page3Widgets[9]).setText("For Sale");
		((GenericButton) sh.page3Widgets[9]).setX(14);
		((GenericButton) sh.page3Widgets[9]).setY(145);
		((GenericButton) sh.page3Widgets[9]).setWidth(72);
		((GenericButton) sh.page3Widgets[9]).setHeight(20);
		((GenericButton)sh.page3Widgets[9]).setTextColor(RegionScreenManager.getColourToken(r.isForSale()));
		((GenericButton)sh.page3Widgets[9]).setTooltip(RegionScreenManager.getStatus(r.isForSale()));
		((GenericButton)sh.page3Widgets[9]).setDirty(true);

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[9])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[9].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[9].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[9]);
		}
		
		((GenericLabel) sh.page3Widgets[10]).setX(15);
		((GenericLabel) sh.page3Widgets[10]).setY(170);
		((GenericLabel) sh.page3Widgets[10]).setTextColor(RGB.YELLOW.getColour());
		((GenericLabel) sh.page3Widgets[10]).setTooltip(ChatColor.YELLOW + "  The price the region will be sold for");		

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[10])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[10].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[10].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[10]);
		}
		
		((GenericTextField) sh.page3Widgets[11]).setText(Integer.toString(r.getSalePrice()));
		((GenericTextField) sh.page3Widgets[11]).setX(15);
		((GenericTextField) sh.page3Widgets[11]).setY(180);
		((GenericTextField) sh.page3Widgets[11]).setFieldColor(RGB.BLACK.getColour());
		((GenericTextField) sh.page3Widgets[11]).setBorderColor(RGB.SPRING_GREEN.getColour());
		((GenericTextField) sh.page3Widgets[11]).setWidth(70);
		((GenericTextField) sh.page3Widgets[11]).setHeight(15);
		((GenericTextField) sh.page3Widgets[11]).setMaximumCharacters(11);
		
		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[11])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[11].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[11].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[11]);
		}
		
		((GenericButton) sh.page3Widgets[12]).setText("Update");
		((GenericButton) sh.page3Widgets[12]).setX(90);
		((GenericButton) sh.page3Widgets[12]).setY(180);
		((GenericButton) sh.page3Widgets[12]).setWidth(50);
		((GenericButton) sh.page3Widgets[12]).setHeight(15);
		((GenericButton) sh.page3Widgets[12]).setTextColor(RGB.WHITE.getColour());
		((GenericButton) sh.page3Widgets[12]).setHoverColor(RGB.SPRING_GREEN.getColour());		

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[12])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[12].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[12].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[12]);
		}
		
		((GenericButton) sh.page3Widgets[13]).setText("Wipe & Cache On Enter");
		((GenericButton) sh.page3Widgets[13]).setX(150);
		((GenericButton) sh.page3Widgets[13]).setY(65);
		((GenericButton) sh.page3Widgets[13]).setWidth(150);
		((GenericButton) sh.page3Widgets[13]).setHeight(20);
		((GenericButton) sh.page3Widgets[13]).setTextColor(RegionScreenManager.getColourToken(r.isWipeAndCacheOnEnter()));
		((GenericButton) sh.page3Widgets[13]).setHoverColor(RGB.YELLOW.getColour());	
		((GenericButton) sh.page3Widgets[13]).setTooltip(ChatColor.YELLOW + "  Wipes inventory on entrance. Restores on exit.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[13])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[13].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[13].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[13]);
		}
		
		((GenericButton) sh.page3Widgets[14]).setText("Wipe & Cache On Exit");
		((GenericButton) sh.page3Widgets[14]).setX(150);
		((GenericButton) sh.page3Widgets[14]).setY(90);
		((GenericButton) sh.page3Widgets[14]).setWidth(150);
		((GenericButton) sh.page3Widgets[14]).setHeight(20);
		((GenericButton) sh.page3Widgets[14]).setTextColor(RegionScreenManager.getColourToken(r.isWipeAndCacheOnExit()));
		((GenericButton) sh.page3Widgets[14]).setHoverColor(RGB.YELLOW.getColour());	
		((GenericButton) sh.page3Widgets[14]).setTooltip(ChatColor.YELLOW + "  Wipes inventory on exit. Restores on entrance.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[14])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[14].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[14].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[14]);
		}
		
		((GenericButton) sh.page3Widgets[15]).setText("Perm Wipe On Enter");
		((GenericButton) sh.page3Widgets[15]).setX(150);
		((GenericButton) sh.page3Widgets[15]).setY(115);
		((GenericButton) sh.page3Widgets[15]).setWidth(150);
		((GenericButton) sh.page3Widgets[15]).setHeight(20);
		((GenericButton) sh.page3Widgets[15]).setTextColor(RegionScreenManager.getColourToken(r.isPermWipeOnEnter()));
		((GenericButton) sh.page3Widgets[15]).setHoverColor(RGB.YELLOW.getColour());	
		((GenericButton) sh.page3Widgets[15]).setTooltip(ChatColor.YELLOW + "  Wipes inventory on entrance.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[15])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[15].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[15].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[15]);
		}
		
		((GenericButton) sh.page3Widgets[16]).setText("Perm Wipe On Exit");
		((GenericButton) sh.page3Widgets[16]).setX(150);
		((GenericButton) sh.page3Widgets[16]).setY(140);
		((GenericButton) sh.page3Widgets[16]).setWidth(150);
		((GenericButton) sh.page3Widgets[16]).setHeight(20);
		((GenericButton) sh.page3Widgets[16]).setTextColor(RegionScreenManager.getColourToken(r.isPermWipeOnExit()));
		((GenericButton) sh.page3Widgets[16]).setHoverColor(RGB.YELLOW.getColour());	
		((GenericButton) sh.page3Widgets[16]).setTooltip(ChatColor.YELLOW + "  Wipes inventory on exit.");

		if (((GenericPopup) RegionScreenManager.popup.get(sp)).containsWidget(sh.page3Widgets[16])) {
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[16].getId()).setVisible(true);
			((GenericPopup) RegionScreenManager.popup.get(sp)).getWidget(sh.page3Widgets[16].getId()).setDirty(true);
		} else {
			((GenericPopup) RegionScreenManager.popup.get(sp)).attachWidget(Regios.regios, sh.page3Widgets[16]);
		}
	}

}
