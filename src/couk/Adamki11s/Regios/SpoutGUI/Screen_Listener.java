package couk.Adamki11s.Regios.SpoutGUI;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.gui.TextField;
import org.getspout.spoutapi.player.SpoutPlayer;
import couk.Adamki11s.Regios.Commands.SpoutHelp;
import couk.Adamki11s.Regios.Data.MODE;
import couk.Adamki11s.Regios.Mutable.MutableEconomy;
import couk.Adamki11s.Regios.Mutable.MutableFun;
import couk.Adamki11s.Regios.Mutable.MutableInventory;
import couk.Adamki11s.Regios.Mutable.MutableMessages;
import couk.Adamki11s.Regios.Mutable.MutableMisc;
import couk.Adamki11s.Regios.Mutable.MutableMobs;
import couk.Adamki11s.Regios.Mutable.MutableModes;
import couk.Adamki11s.Regios.Mutable.MutableProtection;
import couk.Adamki11s.Regios.Mutable.MutableProtectionMisc;
import couk.Adamki11s.Regios.Mutable.MutableSpout;
import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.SpoutGUI.RegionScreen4.ExToggle;
import couk.Adamki11s.Regios.SpoutGUI.RegionScreen5.PermToggle;

public class Screen_Listener extends ScreenListener {

	public static HashMap<SpoutPlayer, GenericLabel[]> oldWidgets = new HashMap<SpoutPlayer, GenericLabel[]>();

	public void onButtonClick(ButtonClickEvent evt) {
		if (evt.getScreenType() == ScreenType.CUSTOM_SCREEN) {
			ScreenHolder sh = ScreenHolder.getScreenHolder(evt.getPlayer());
			helpListener(evt, sh);
			if (RegionScreenManager.page.containsKey(evt.getPlayer())) {
				if (RegionScreenManager.page.get(evt.getPlayer()) == 1) {
					regionScreen1Listener(evt, sh);
				} else if (RegionScreenManager.page.get(evt.getPlayer()) == 2) {
					regionScreen2Listener(evt, sh);
				} else if (RegionScreenManager.page.get(evt.getPlayer()) == 3) {
					regionScreen3Listener(evt, sh);
				} else if (RegionScreenManager.page.get(evt.getPlayer()) == 4) {
					regionScreen4Listener(evt, sh);
				} else if (RegionScreenManager.page.get(evt.getPlayer()) == 5) {
					regionScreen5Listener(evt, sh);
				} else if (RegionScreenManager.page.get(evt.getPlayer()) == 6) {
					regionScreen6Listener(evt, sh);
				} else if (RegionScreenManager.page.get(evt.getPlayer()) == 7) {
					regionScreen7Listener(evt, sh);
				}
			}
			regionControlListener(evt, sh);
		}
	}

	private void regionControlListener(ButtonClickEvent evt, ScreenHolder sh) {

		if (!RegionScreenManager.popup.containsKey(evt.getPlayer())) {
			return;
		}

		SpoutPlayer sp = evt.getPlayer();

		UUID esc = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.escButton.getId()).getId();
		UUID next = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.pageForward.getId()).getId();
		UUID prev = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.pageBackwards.getId()).getId();

		UUID buttonID = evt.getButton().getId();

		if (buttonID == esc) {
			sp.getMainScreen().closePopup();
			return;
		}

		if (buttonID == next) {
			RegionScreenManager.nextPage(sp, sh);
			return;
		}

		if (buttonID == prev) {
			RegionScreenManager.previousPage(sp, sh);
			return;
		}
	}

	private void helpListener(ButtonClickEvent evt, ScreenHolder sh) {

		if (!SpoutHelp.helps.containsKey(evt.getPlayer())) {
			return;
		}

		UUID esc = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.escButton.getId()).getId();
		UUID general = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.generalData.getId()).getId();
		UUID protection = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.protection.getId()).getId();
		UUID data = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.data.getId()).getId();
		UUID exceptions = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.exceptions.getId()).getId();
		UUID fun = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.fun.getId()).getId();
		UUID invent = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.inventory.getId()).getId();
		UUID message = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.messages.getId()).getId();
		UUID modes = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.modes.getId()).getId();
		UUID modify = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.modify.getId()).getId();
		UUID other = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.other.getId()).getId();
		UUID perms = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.perms.getId()).getId();
		UUID spout = ((GenericPopup) SpoutHelp.helps.get(evt.getPlayer())).getWidget(sh.spout.getId()).getId();

		SpoutPlayer sp = evt.getPlayer();

		UUID buttonID = evt.getButton().getId();

		if (buttonID == esc) {
			evt.getPlayer().getMainScreen().closePopup();
		}

		if (buttonID == general) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.generalDataText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.generalDataText, null, sh);
			}
			oldWidgets.put(sp, sh.generalDataText);
		}

		if (buttonID == protection) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.protectionText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.protectionText, null, sh);
			}
			oldWidgets.put(sp, sh.protectionText);
		}

		if (buttonID == data) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.dataText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.dataText, null, sh);
			}
			oldWidgets.put(sp, sh.dataText);
		}

		if (buttonID == exceptions) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.exceptionText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.exceptionText, null, sh);
			}
			oldWidgets.put(sp, sh.exceptionText);
		}

		if (buttonID == fun) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.funText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.funText, null, sh);
			}
			oldWidgets.put(sp, sh.funText);
		}

		if (buttonID == invent) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.inventText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.inventText, null, sh);
			}
			oldWidgets.put(sp, sh.inventText);
		}

		if (buttonID == message) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.messagesText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.messagesText, null, sh);
			}
			oldWidgets.put(sp, sh.messagesText);
		}

		if (buttonID == modes) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.modeText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.modeText, null, sh);
			}
			oldWidgets.put(sp, sh.modeText);
		}

		if (buttonID == modify) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.modifyText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.modifyText, null, sh);
			}
			oldWidgets.put(sp, sh.modifyText);
		}

		if (buttonID == other) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.otherText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.otherText, null, sh);
			}
			oldWidgets.put(sp, sh.otherText);
		}

		if (buttonID == perms) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.permissionsText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.permissionsText, null, sh);
			}
			oldWidgets.put(sp, sh.permissionsText);
		}

		if (buttonID == spout) {
			if (oldWidgets.containsKey(sp)) {
				SpoutHelp.pinLabels(sp, sh.spoutText, oldWidgets.get(sp), sh);
			} else {
				SpoutHelp.pinLabels(sp, sh.spoutText, null, sh);
			}
			oldWidgets.put(sp, sh.spoutText);
		}
	}

	static final MutableProtection protection = new MutableProtection();
	static final MutableProtectionMisc miscProtection = new MutableProtectionMisc();
	static final MutableMessages msg = new MutableMessages();
	static final MutableEconomy eco = new MutableEconomy();
	static final MutableFun fun = new MutableFun();
	static final MutableMobs mob = new MutableMobs();
	static final MutableMisc misc = new MutableMisc();
	static final MutableModes modes = new MutableModes();

	private void regionScreen1Listener(ButtonClickEvent evt, ScreenHolder sh) {

		if (!RegionScreenManager.popup.containsKey(evt.getPlayer())) {
			return;
		}

		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		UUID protect = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[0].getId()).getId();
		UUID protectplace = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[1].getId()).getId();
		UUID prevententry = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[2].getId()).getId();
		UUID preventexit = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[3].getId()).getId();
		UUID preventinteraction = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[4].getId()).getId();
		UUID doorslocked = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[5].getId()).getId();
		UUID chestslocked = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[6].getId()).getId();
		UUID fireprotection = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[7].getId()).getId();
		UUID blockform = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[8].getId()).getId();
		UUID mobspawn = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[9].getId()).getId();
		UUID monsterspawn = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[10].getId()).getId();
		UUID showwelcome = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[11].getId()).getId();
		UUID showleave = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[12].getId()).getId();
		UUID showprevententry = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[13].getId()).getId();
		UUID showpreventexit = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[14].getId()).getId();
		UUID showprotection = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[15].getId()).getId();
		UUID showpvp = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[16].getId()).getId();
		UUID pvp = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[17].getId()).getId();
		UUID healthenabled = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[18].getId()).getId();
		UUID protectmode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[19].getId()).getId();
		UUID prevententrymode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[20].getId()).getId();
		UUID preventexitmode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[21].getId()).getId();
		UUID itemmode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[22].getId()).getId();
		UUID forceCmd = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[23].getId()).getId();
		UUID generalProtection = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[24].getId()).getId();

		SpoutPlayer sp = evt.getPlayer();

		UUID buttonID = evt.getButton().getId();

		if (buttonID == generalProtection) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.is_protection()) {
				protection.editUnprotect(r);
				sp.sendNotification("General Protection", ChatColor.RED + "Protection Disabled", Material.DIAMOND_BLOCK);
			} else {
				protection.editProtect(r);
				sp.sendNotification("General Protection", ChatColor.GREEN + "Protection Enabled", Material.DIAMOND_BLOCK);
			}
			((GenericButton) (sh.page1Widgets[24])).setTooltip(RegionScreenManager.getStatus(r.is_protection()));
			((GenericButton) (sh.page1Widgets[24])).setTextColor(RegionScreenManager.getColourToken(r.is_protection()));
			((GenericButton) (sh.page1Widgets[24])).setDirty(true);
			return;
		}

		if (buttonID == protect) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.is_protectionBreak()) {
				protection.editUnProtectBB(r);
				sp.sendNotification("Protection - Block Break", ChatColor.RED + "Protection Disabled", Material.DIAMOND_BLOCK);
			} else {
				protection.editProtectBB(r);
				sp.sendNotification("Protection - Block Break", ChatColor.GREEN + "Protection Enabled", Material.DIAMOND_BLOCK);
			}
			((GenericButton) (sh.page1Widgets[0])).setTooltip(RegionScreenManager.getStatus(r.is_protectionBreak()));
			((GenericButton) (sh.page1Widgets[0])).setTextColor(RegionScreenManager.getColourToken(r.is_protectionBreak()));
			((GenericButton) (sh.page1Widgets[0])).setDirty(true);
			return;
		}

		if (buttonID == protectplace) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.is_protectionPlace()) {
				protection.editUnProtectBP(r);
				sp.sendNotification("Protection - Block Place", ChatColor.RED + "Protection Disabled", Material.DIAMOND_BLOCK);
			} else {
				protection.editProtectBP(r);
				sp.sendNotification("Protection - Block Place", ChatColor.GREEN + "Protection Enabled", Material.DIAMOND_BLOCK);
			}
			((GenericButton) (sh.page1Widgets[1])).setTooltip(RegionScreenManager.getStatus(r.is_protectionPlace()));
			((GenericButton) (sh.page1Widgets[1])).setTextColor(RegionScreenManager.getColourToken(r.is_protectionPlace()));
			((GenericButton) (sh.page1Widgets[1])).setDirty(true);
			return;
		}

		if (buttonID == prevententry) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isPreventEntry()) {
				protection.editAllowEntry(r);
				sp.sendNotification("Prevent Entry", ChatColor.RED + "Prevent Entry Disabled", Material.CHAINMAIL_CHESTPLATE);
			} else {

				protection.editPreventEntry(r);
				sp.sendNotification("Prevent Entry", ChatColor.GREEN + "Prevent Entry Enabled", Material.CHAINMAIL_CHESTPLATE);
			}
			((GenericButton) (sh.page1Widgets[2])).setTooltip(RegionScreenManager.getStatus(r.isPreventEntry()));
			((GenericButton) (sh.page1Widgets[2])).setTextColor(RegionScreenManager.getColourToken(r.isPreventEntry()));
			((GenericButton) (sh.page1Widgets[2])).setDirty(true);
			return;
		}

		if (buttonID == preventexit) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isPreventExit()) {
				protection.editAllowExit(r);
				sp.sendNotification("Prevent Exit", ChatColor.RED + "Prevent Exit Disabled", Material.CHAINMAIL_CHESTPLATE);
			} else {

				protection.editPreventExit(r);
				sp.sendNotification("Prevent Exit", ChatColor.GREEN + "Prevent Exit Enabled", Material.CHAINMAIL_CHESTPLATE);
			}
			((GenericButton) (sh.page1Widgets[3])).setTooltip(RegionScreenManager.getStatus(r.isPreventExit()));
			((GenericButton) (sh.page1Widgets[3])).setTextColor(RegionScreenManager.getColourToken(r.isPreventExit()));
			((GenericButton) (sh.page1Widgets[3])).setDirty(true);
			return;
		}

		if (buttonID == preventinteraction) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isPreventingInteraction()) {
				miscProtection.editInteraction(r, false);
				sp.sendNotification("Prevent Interaction", ChatColor.RED + "Interaction Disabled", Material.SHEARS);
			} else {
				miscProtection.editInteraction(r, true);
				sp.sendNotification("Prevent Interaction", ChatColor.GREEN + "Interaction Enabled", Material.SHEARS);
			}
			((GenericButton) (sh.page1Widgets[4])).setTooltip(RegionScreenManager.getStatus(r.isPreventingInteraction()));
			((GenericButton) (sh.page1Widgets[4])).setTextColor(RegionScreenManager.getColourToken(r.isPreventingInteraction()));
			((GenericButton) (sh.page1Widgets[4])).setDirty(true);
			return;
		}

		if (buttonID == doorslocked) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.areDoorsLocked()) {
				miscProtection.editDoorsLocked(r, false);
				sp.sendNotification("Doors Locked", ChatColor.RED + "Door Locking Disabled", Material.IRON_DOOR);
			} else {
				miscProtection.editDoorsLocked(r, true);
				sp.sendNotification("Doors Locked", ChatColor.GREEN + "Door Locking Enabled", Material.IRON_DOOR);
			}
			((GenericButton) (sh.page1Widgets[5])).setTooltip(RegionScreenManager.getStatus(r.areDoorsLocked()));
			((GenericButton) (sh.page1Widgets[5])).setTextColor(RegionScreenManager.getColourToken(r.areDoorsLocked()));
			((GenericButton) (sh.page1Widgets[5])).setDirty(true);
			return;
		}

		if (buttonID == chestslocked) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isChestsLocked()) {
				miscProtection.editChestsLocked(r, false);
				sp.sendNotification("Chests Locked", ChatColor.RED + "Chest Locking Disabled", Material.CHEST);
			} else {
				miscProtection.editChestsLocked(r, true);
				sp.sendNotification("Chests Locked", ChatColor.GREEN + "Chest Locking Enabled", Material.CHEST);
			}
			((GenericButton) (sh.page1Widgets[6])).setTooltip(RegionScreenManager.getStatus(r.isChestsLocked()));
			((GenericButton) (sh.page1Widgets[6])).setTextColor(RegionScreenManager.getColourToken(r.isChestsLocked()));
			((GenericButton) (sh.page1Widgets[6])).setDirty(true);
			return;
		}

		if (buttonID == fireprotection) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isFireProtection()) {
				miscProtection.editFireProtection(r, false);
				sp.sendNotification("Fire Protection", ChatColor.RED + "Fire Protection Disabled", Material.FIRE);
			} else {
				miscProtection.editFireProtection(r, true);
				sp.sendNotification("Fire Protection", ChatColor.GREEN + "Fire Protection Enabled", Material.FIRE);
			}
			((GenericButton) (sh.page1Widgets[7])).setTooltip(RegionScreenManager.getStatus(r.isFireProtection()));
			((GenericButton) (sh.page1Widgets[7])).setTextColor(RegionScreenManager.getColourToken(r.isFireProtection()));
			((GenericButton) (sh.page1Widgets[7])).setDirty(true);
			return;
		}

		if (buttonID == blockform) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isBlockForm()) {
				miscProtection.editBlockForm(r, false);
				sp.sendNotification("Block Form", ChatColor.RED + "Block Form Disabled", Material.SNOW);
			} else {
				miscProtection.editBlockForm(r, true);
				sp.sendNotification("Block Form", ChatColor.GREEN + "Block Form Enabled", Material.SNOW);
			}
			((GenericButton) (sh.page1Widgets[8])).setTooltip(RegionScreenManager.getStatus(r.isBlockForm()));
			((GenericButton) (sh.page1Widgets[8])).setTextColor(RegionScreenManager.getColourToken(r.isBlockForm()));
			((GenericButton) (sh.page1Widgets[8])).setDirty(true);
			return;
		}

		if (buttonID == mobspawn) {
			if (r.isMobSpawns()) {
				if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
					PermissionsCore.sendInvalidPermsPopup(sp);
				}
				mob.editMobSpawn(r, false);
				sp.sendNotification("Mob Spawns", ChatColor.RED + "Mob Spawns Disabled", Material.RAW_FISH);
			} else {
				mob.editMobSpawn(r, true);
				sp.sendNotification("Mob Spawns", ChatColor.GREEN + "Mob Spawns Enabled", Material.RAW_FISH);
			}
			((GenericButton) (sh.page1Widgets[9])).setTooltip(RegionScreenManager.getStatus(r.isMobSpawns()));
			((GenericButton) (sh.page1Widgets[9])).setTextColor(RegionScreenManager.getColourToken(r.isMobSpawns()));
			((GenericButton) (sh.page1Widgets[9])).setDirty(true);
			return;
		}

		if (buttonID == monsterspawn) {
			if (r.isMonsterSpawns()) {
				if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
					PermissionsCore.sendInvalidPermsPopup(sp);
				}
				mob.editMonsterSpawn(r, false);
				sp.sendNotification("Monster Spawns", ChatColor.RED + "Monster Spawns Disabled", Material.RAW_FISH);
			} else {
				mob.editMonsterSpawn(r, true);
				sp.sendNotification("Monster Spawns", ChatColor.GREEN + "Monster Spawns Enabled", Material.RAW_FISH);
			}
			((GenericButton) (sh.page1Widgets[10])).setTooltip(RegionScreenManager.getStatus(r.isMonsterSpawns()));
			((GenericButton) (sh.page1Widgets[10])).setTextColor(RegionScreenManager.getColourToken(r.isMonsterSpawns()));
			((GenericButton) (sh.page1Widgets[10])).setDirty(true);
			return;
		}

		if (buttonID == showwelcome) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isShowWelcomeMessage()) {
				msg.editShowWelcomeMessage(r, false);
				sp.sendNotification("Welcome Message", ChatColor.RED + "Welcome Msg Disabled", Material.BOOK);
			} else {
				msg.editShowWelcomeMessage(r, true);
				sp.sendNotification("Welcome Message", ChatColor.GREEN + "Welcome Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[11])).setTooltip(RegionScreenManager.getStatus(r.isShowWelcomeMessage()));
			((GenericButton) (sh.page1Widgets[11])).setTextColor(RegionScreenManager.getColourToken(r.isShowWelcomeMessage()));
			((GenericButton) (sh.page1Widgets[11])).setDirty(true);
			return;
		}

		if (buttonID == showleave) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isShowLeaveMessage()) {
				msg.editShowLeaveMessage(r, false);
				sp.sendNotification("Leave Message", ChatColor.RED + "Leave Msg Disabled", Material.BOOK);
			} else {
				msg.editShowLeaveMessage(r, true);
				sp.sendNotification("Leave Message", ChatColor.GREEN + "Leave Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[12])).setTooltip(RegionScreenManager.getStatus(r.isShowLeaveMessage()));
			((GenericButton) (sh.page1Widgets[12])).setTextColor(RegionScreenManager.getColourToken(r.isShowLeaveMessage()));
			((GenericButton) (sh.page1Widgets[12])).setDirty(true);
			return;
		}

		if (buttonID == showprevententry) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isShowPreventEntryMessage()) {
				msg.editShowPreventEntryMessage(r, false);
				sp.sendNotification("Prev Entry Message", ChatColor.RED + "Prev Entry Msg Disabled", Material.BOOK);
			} else {
				msg.editShowPreventEntryMessage(r, true);
				sp.sendNotification("Prev Entry Message", ChatColor.GREEN + "Prev Entry Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[13])).setTooltip(RegionScreenManager.getStatus(r.isShowPreventEntryMessage()));
			((GenericButton) (sh.page1Widgets[13])).setTextColor(RegionScreenManager.getColourToken(r.isShowPreventEntryMessage()));
			((GenericButton) (sh.page1Widgets[13])).setDirty(true);
			return;
		}

		if (buttonID == showpreventexit) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isShowPreventExitMessage()) {
				msg.editShowPreventExitMessage(r, false);
				sp.sendNotification("Prev Exit Message", ChatColor.RED + "Prev Exit Msg Disabled", Material.BOOK);
			} else {
				msg.editShowPreventExitMessage(r, true);
				sp.sendNotification("Prev Exit Message", ChatColor.GREEN + "Prev Exit Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[14])).setTooltip(RegionScreenManager.getStatus(r.isShowPreventExitMessage()));
			((GenericButton) (sh.page1Widgets[14])).setTextColor(RegionScreenManager.getColourToken(r.isShowPreventExitMessage()));
			((GenericButton) (sh.page1Widgets[14])).setDirty(true);
			return;
		}

		if (buttonID == showprotection) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isShowProtectionMessage()) {
				msg.editShowProtectionMessage(r, false);
				sp.sendNotification("Protection Message", ChatColor.RED + "Protection Msg Disabled", Material.BOOK);
			} else {
				msg.editShowProtectionMessage(r, true);
				sp.sendNotification("Protection Message", ChatColor.GREEN + "Protection Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[15])).setTooltip(RegionScreenManager.getStatus(r.isShowProtectionMessage()));
			((GenericButton) (sh.page1Widgets[15])).setTextColor(RegionScreenManager.getColourToken(r.isShowProtectionMessage()));
			((GenericButton) (sh.page1Widgets[15])).setDirty(true);
			return;
		}

		if (buttonID == showpvp) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isShowPvpWarning()) {
				msg.editShowPvpWarningMessage(r, false);
				sp.sendNotification("PvP Message", ChatColor.RED + "PvP Msg Disabled", Material.BOOK);
			} else {
				msg.editShowPvpWarningMessage(r, true);
				sp.sendNotification("PvP Message", ChatColor.GREEN + "PvP Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[16])).setTooltip(RegionScreenManager.getStatus(r.isShowPvpWarning()));
			((GenericButton) (sh.page1Widgets[16])).setTextColor(RegionScreenManager.getColourToken(r.isShowPvpWarning()));
			((GenericButton) (sh.page1Widgets[16])).setDirty(true);
			return;
		}

		if (buttonID == pvp) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isPvp()) {
				fun.editPvPEnabled(r, false);
				sp.sendNotification("PvP", ChatColor.RED + "PvP Disabled", Material.DIAMOND_SWORD);
			} else {
				fun.editPvPEnabled(r, true);
				sp.sendNotification("PvP", ChatColor.GREEN + "PvP Enabled", Material.DIAMOND_SWORD);
			}
			((GenericButton) (sh.page1Widgets[17])).setTooltip(RegionScreenManager.getStatus(r.isPvp()));
			((GenericButton) (sh.page1Widgets[17])).setTextColor(RegionScreenManager.getColourToken(r.isPvp()));
			((GenericButton) (sh.page1Widgets[17])).setDirty(true);
			return;
		}

		if (buttonID == healthenabled) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isHealthEnabled()) {
				fun.editHealthEnabled(r, false);
				sp.sendNotification("Health", ChatColor.RED + "Health Disabled", Material.GOLDEN_APPLE);
			} else {
				fun.editHealthEnabled(r, true);
				sp.sendNotification("Health", ChatColor.GREEN + "Health Enabled", Material.GOLDEN_APPLE);
			}
			((GenericButton) (sh.page1Widgets[18])).setTooltip(RegionScreenManager.getStatus(r.isHealthEnabled()));
			((GenericButton) (sh.page1Widgets[18])).setTextColor(RegionScreenManager.getColourToken(r.isHealthEnabled()));
			((GenericButton) (sh.page1Widgets[18])).setDirty(true);
			return;
		}

		if (buttonID == protectmode) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.getProtectionMode() == MODE.Whitelist) {
				modes.editProtectionMode(r, MODE.Blacklist);
				sp.sendNotification("Protection Mode", ChatColor.BLACK + "Mode : Blacklist", Material.OBSIDIAN);
			} else if (r.getProtectionMode() == MODE.Blacklist) {
				modes.editProtectionMode(r, MODE.Whitelist);
				sp.sendNotification("Protection Mode", "Mode : Whitelist", Material.OBSIDIAN);
			}
			((GenericButton) (sh.page1Widgets[19])).setTooltip(RegionScreenManager.getStatus(r.getProtectionMode()));
			((GenericButton) (sh.page1Widgets[19])).setTextColor(RegionScreenManager.getColourToken(r.getProtectionMode()));
			((GenericButton) (sh.page1Widgets[19])).setDirty(true);
			return;
		}

		if (buttonID == prevententrymode) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.getPreventEntryMode() == MODE.Whitelist) {
				modes.editPreventEntryMode(r, MODE.Blacklist);
				sp.sendNotification("Prevent Entry Mode", ChatColor.BLACK + "Mode : Blacklist", Material.OBSIDIAN);
			} else if (r.getPreventEntryMode() == MODE.Blacklist) {
				modes.editPreventEntryMode(r, MODE.Whitelist);
				sp.sendNotification("Prevent Entry Mode", "Mode : Whitelist", Material.OBSIDIAN);
			}
			((GenericButton) (sh.page1Widgets[20])).setTooltip(RegionScreenManager.getStatus(r.getPreventEntryMode()));
			((GenericButton) (sh.page1Widgets[20])).setTextColor(RegionScreenManager.getColourToken(r.getPreventEntryMode()));
			((GenericButton) (sh.page1Widgets[20])).setDirty(true);
			return;
		}

		if (buttonID == preventexitmode) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.getPreventExitMode() == MODE.Whitelist) {
				modes.editPreventExitMode(r, MODE.Blacklist);
				sp.sendNotification("Prevent Exit Mode", ChatColor.BLACK + "Mode : Blacklist", Material.OBSIDIAN);
			} else if (r.getPreventExitMode() == MODE.Blacklist) {
				modes.editPreventExitMode(r, MODE.Whitelist);
				sp.sendNotification("Prevent Exit Mode", "Mode : Whitelist", Material.OBSIDIAN);
			}
			((GenericButton) (sh.page1Widgets[21])).setTooltip(RegionScreenManager.getStatus(r.getPreventExitMode()));
			((GenericButton) (sh.page1Widgets[21])).setTextColor(RegionScreenManager.getColourToken(r.getPreventExitMode()));
			((GenericButton) (sh.page1Widgets[21])).setDirty(true);
			return;
		}

		if (buttonID == itemmode) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.getItemMode() == MODE.Whitelist) {
				modes.editItemControlMode(r, MODE.Blacklist);
				sp.sendNotification("Item Control Mode", ChatColor.BLACK + "Mode : Blacklist", Material.OBSIDIAN);
			} else if (r.getItemMode() == MODE.Blacklist) {
				modes.editItemControlMode(r, MODE.Whitelist);
				sp.sendNotification("Item Control Mode", "Mode : Whitelist", Material.OBSIDIAN);
			}
			((GenericButton) (sh.page1Widgets[22])).setTooltip(RegionScreenManager.getStatus(r.getItemMode()));
			((GenericButton) (sh.page1Widgets[22])).setTextColor(RegionScreenManager.getColourToken(r.getItemMode()));
			((GenericButton) (sh.page1Widgets[22])).setDirty(true);
			return;
		}

		if (buttonID == forceCmd) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isForcingCommand()) {
				misc.editSetForceCommand(r, false);
				sp.sendNotification("Force Command", ChatColor.RED + "Force Command Disabled", Material.GOLDEN_APPLE);
			} else {
				misc.editSetForceCommand(r, true);
				sp.sendNotification("Force Command", ChatColor.GREEN + "Force Command Enabled", Material.GOLDEN_APPLE);
			}
			((GenericButton) (sh.page1Widgets[23])).setTooltip(RegionScreenManager.getStatus(r.isForcingCommand()));
			((GenericButton) (sh.page1Widgets[23])).setTextColor(RegionScreenManager.getColourToken(r.isForcingCommand()));
			((GenericButton) (sh.page1Widgets[23])).setDirty(true);
			return;
		}

	}

	private void regionScreen2Listener(ButtonClickEvent evt, ScreenHolder sh) {

		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		SpoutPlayer sp = evt.getPlayer();

		TextField welcometxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[0].getId());
		TextField leavetxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[1].getId());
		TextField prevententrytxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[2].getId());
		TextField preventexittxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[3].getId());
		TextField protecttxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[4].getId());

		// 5 - 9 are labels
		UUID update = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[10].getId()).getId();

		UUID resetwelcome = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[11].getId()).getId();
		UUID resetleave = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[12].getId()).getId();
		UUID resetprevententry = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[13].getId()).getId();
		UUID resetpreventexit = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[14].getId()).getId();
		UUID resetprotection = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[15].getId()).getId();

		UUID cw = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[16].getId()).getId();
		UUID rl = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[17].getId()).getId();
		UUID rpent = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[18].getId()).getId();
		UUID rpex = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[19].getId()).getId();
		UUID rprot = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[20].getId()).getId();

		UUID button = evt.getButton().getId();

		if (button == update) {

			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			msg.editWelcomeMessage(r, welcometxt.getText());
			msg.editLeaveMessage(r, leavetxt.getText());
			msg.editPreventEntryMessage(r, prevententrytxt.getText());
			msg.editPreventExitMessage(r, preventexittxt.getText());
			msg.editProtectionMessage(r, protecttxt.getText());
			sp.sendNotification("Region Messages", ChatColor.GREEN + "All Messages Updated!", Material.PAPER);
			return;
		}

		if (button == cw) {
			welcometxt.setText("");
			welcometxt.setDirty(true);
			sp.sendNotification("Welcome Message", ChatColor.GREEN + "Welcome Msg Wiped", Material.PAPER);
			return;
		}

		if (button == rl) {
			leavetxt.setText("");
			leavetxt.setDirty(true);
			sp.sendNotification("Leave Message", ChatColor.GREEN + "Leave Msg Wiped", Material.PAPER);
			return;
		}

		if (button == rpent) {
			prevententrytxt.setText("");
			prevententrytxt.setDirty(true);
			sp.sendNotification("Prevent Entry Message", ChatColor.GREEN + "Prevent Entry Msg Wiped", Material.PAPER);
			return;
		}

		if (button == rpex) {
			preventexittxt.setText("");
			preventexittxt.setDirty(true);
			sp.sendNotification("Prevent Exit Message", ChatColor.GREEN + "Prevent Exit Msg Wiped", Material.PAPER);
			return;
		}

		if (button == rprot) {
			protecttxt.setText("");
			protecttxt.setDirty(true);
			sp.sendNotification("Protection Message", ChatColor.GREEN + "Protection Msg Wiped", Material.PAPER);
			return;
		}

		if (button == resetwelcome) {
			welcometxt.setText(r.getWelcomeMessage());
			welcometxt.setDirty(true);
			sp.sendNotification("Welcome Message", ChatColor.GREEN + "Welcome Msg Reset", Material.PAPER);
			return;
		}

		if (button == resetleave) {
			leavetxt.setText(r.getLeaveMessage());
			leavetxt.setDirty(true);
			sp.sendNotification("Leave Message", ChatColor.GREEN + "Leave Msg Reset", Material.PAPER);
			return;
		}

		if (button == resetprevententry) {
			prevententrytxt.setText(r.getPreventEntryMessage());
			prevententrytxt.setDirty(true);
			sp.sendNotification("Prevent Entry Message", ChatColor.GREEN + "Prevent Entry Msg Reset", Material.PAPER);
			return;
		}

		if (button == resetpreventexit) {
			preventexittxt.setText(r.getPreventExitMessage());
			preventexittxt.setDirty(true);
			sp.sendNotification("Prevent Exit Message", ChatColor.GREEN + "Prevent Exit Msg Reset", Material.PAPER);
			return;
		}

		if (button == resetprotection) {
			protecttxt.setText(r.getProtectionMessage());
			protecttxt.setDirty(true);
			sp.sendNotification("Protection Message", ChatColor.GREEN + "Protection Msg Reset", Material.PAPER);
			return;
		}
	}

	final MutableInventory invent = new MutableInventory();

	private void regionScreen3Listener(ButtonClickEvent evt, ScreenHolder sh) {
		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		SpoutPlayer sp = evt.getPlayer();

		TextField lspstxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[1].getId());
		TextField healthregentxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[4].getId());
		TextField velwarptxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[7].getId());
		TextField pricetxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[11].getId());

		UUID togglesale = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[9].getId()).getId();
		UUID confirmlsps = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[2].getId()).getId();
		UUID confirmhealthregen = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[5].getId()).getId();
		UUID confirmvelwarp = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[8].getId()).getId();
		UUID confirmprice = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[12].getId()).getId();

		UUID cwEnter = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[13].getId()).getId();
		UUID cwExit = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[14].getId()).getId();
		UUID pwEnter = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[15].getId()).getId();
		UUID pwExit = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[16].getId()).getId();

		UUID button = evt.getButton().getId();

		if (button == cwEnter) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isWipeAndCacheOnEnter()) {
				invent.editWipeAndCacheOnEnter(r, false);
				sp.sendNotification("Inventory", ChatColor.RED + "Disabled", Material.BUCKET);
			} else {
				invent.editWipeAndCacheOnEnter(r, true);
				sp.sendNotification("Inventory", ChatColor.GREEN + "Enabled", Material.BUCKET);
			}
			((GenericButton) (sh.page3Widgets[13])).setTextColor(RegionScreenManager.getColourToken(r.isWipeAndCacheOnEnter()));
			((GenericButton) (sh.page3Widgets[13])).setDirty(true);
			return;
		}

		if (button == cwExit) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isWipeAndCacheOnExit()) {
				invent.editWipeAndCacheOnExit(r, false);
				sp.sendNotification("Inventory", ChatColor.RED + "Disabled", Material.BUCKET);
			} else {
				invent.editWipeAndCacheOnExit(r, true);
				sp.sendNotification("Inventory", ChatColor.GREEN + "Enabled", Material.BUCKET);
			}
			((GenericButton) (sh.page3Widgets[14])).setTextColor(RegionScreenManager.getColourToken(r.isWipeAndCacheOnExit()));
			((GenericButton) (sh.page3Widgets[14])).setDirty(true);
			return;
		}

		if (button == pwEnter) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isPermWipeOnEnter()) {
				invent.editPermWipeOnEnter(r, false);
				sp.sendNotification("Inventory", ChatColor.RED + "Disabled", Material.BUCKET);
			} else {
				invent.editPermWipeOnEnter(r, true);
				sp.sendNotification("Inventory", ChatColor.GREEN + "Enabled", Material.BUCKET);
			}
			((GenericButton) (sh.page3Widgets[15])).setTextColor(RegionScreenManager.getColourToken(r.isPermWipeOnEnter()));
			((GenericButton) (sh.page3Widgets[15])).setDirty(true);
			return;
		}

		if (button == pwExit) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isPermWipeOnExit()) {
				invent.editPermWipeOnExit(r, false);
				sp.sendNotification("Inventory", ChatColor.RED + "Disabled", Material.BUCKET);
			} else {
				invent.editPermWipeOnExit(r, true);
				sp.sendNotification("Inventory", ChatColor.GREEN + "Enabled", Material.BUCKET);
			}
			((GenericButton) (sh.page3Widgets[16])).setTextColor(RegionScreenManager.getColourToken(r.isPermWipeOnExit()));
			((GenericButton) (sh.page3Widgets[16])).setDirty(true);
			return;
		}

		if (button == togglesale) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isForSale()) {
				eco.editForSale(r, false);
				sp.sendNotification("Sale", ChatColor.RED + "Not for sale", Material.SIGN);
			} else {
				eco.editForSale(r, true);
				sp.sendNotification("Sale", ChatColor.GREEN + "For sale", Material.SIGN);
			}
			((GenericButton) (sh.page3Widgets[9])).setTooltip(RegionScreenManager.getStatus(r.isForSale()));
			((GenericButton) (sh.page3Widgets[9])).setTextColor(RegionScreenManager.getColourToken(r.isForSale()));
			((GenericButton) (sh.page3Widgets[9])).setDirty(true);
			return;
		}

		if (button == confirmlsps) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			String lsps = lspstxt.getText();
			int val;
			try {
				val = Integer.parseInt(lsps);
			} catch (NumberFormatException ex) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be an int!", Material.FIRE);
				return;
			}
			if (val < 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be > 0!", Material.FIRE);
				return;
			}
			fun.editLSPS(r, val);
			sp.sendNotification("LSPS", ChatColor.GREEN + "Set to : " + val, Material.FIRE);
		}

		if (button == confirmhealthregen) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			String hr = healthregentxt.getText();
			int val;
			try {
				val = Integer.parseInt(hr);
			} catch (NumberFormatException ex) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be an int!", Material.FIRE);
				return;
			}
			if (val < 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be > 0!", Material.FIRE);
				return;
			}
			fun.editHealthRegen(r, val);
			sp.sendNotification("Health Regen", ChatColor.GREEN + "Set to : " + val, Material.PORK);
		}

		if (button == confirmvelwarp) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			String vel = velwarptxt.getText();
			double val;
			try {
				val = Double.parseDouble(vel);
			} catch (NumberFormatException ex) {
				sp.sendNotification(ChatColor.RED + "Error!", "Value must be a double!", Material.FIRE);
				return;
			}
			if (val < 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be > 0!", Material.FIRE);
				return;
			}
			fun.editVelocityWarp(r, val);
			sp.sendNotification("Velocity Warp", ChatColor.GREEN + "Set to : " + val, Material.CAKE);
		}

		if (button == confirmprice) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			String price = pricetxt.getText();
			int val;
			try {
				val = Integer.parseInt(price);
			} catch (NumberFormatException ex) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be an int!", Material.FIRE);
				return;
			}
			if (val < 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be > 0!", Material.FIRE);
				return;
			}
			eco.editSalePrice(r, val);
			sp.sendNotification("Region Price", ChatColor.GREEN + "Set to : " + val, Material.SIGN);
		}
	}

	private void regionScreen4Listener(ButtonClickEvent evt, ScreenHolder sh) {

		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		SpoutPlayer sp = evt.getPlayer();

		TextField excepField = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[5].getId());

		UUID togglePlayer = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[1].getId()).getId();
		UUID toggleNode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[2].getId()).getId();
		UUID toggleSubOwner = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[3].getId()).getId();
		UUID toggleItems = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[4].getId()).getId();

		UUID addEx = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[6].getId()).getId();
		UUID remEx = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[7].getId()).getId();
		UUID eraseEx = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[8].getId()).getId();
		UUID back = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[10].getId()).getId();
		UUID forward = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[11].getId()).getId();

		UUID button = evt.getButton().getId();

		if (button == addEx) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (excepField.getText().length() < 1) {
				sp.sendNotification(ChatColor.RED + "Error!", "No Text Entered!", Material.FIRE);
				return;
			}
			if (excepField.getText().contains(" ")) {
				sp.sendNotification(ChatColor.RED + "Error!", "No Spaces Allowed!", Material.FIRE);
				excepField.setText("");
				excepField.setDirty(true);
				return;
			}
			RegionScreen4.addException(RegionScreen4.toggle.get(sp), sp, r, excepField.getText().trim(), excepField);
		}

		if (button == remEx) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (excepField.getText().length() < 1) {
				sp.sendNotification(ChatColor.RED + "Error!", "No Text Entered!", Material.FIRE);
				return;
			}
			RegionScreen4.removeException(RegionScreen4.toggle.get(sp), sp, r, excepField.getText(), excepField);
		}

		if (button == eraseEx) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			RegionScreen4.eraseExceptions(RegionScreen4.toggle.get(sp), sp, r);
		}

		if (button == back) {
			RegionScreen4.prevPage(sp, r, sh);
		}

		if (button == forward) {
			RegionScreen4.nextPage(sp, r, sh);
		}

		if (button == togglePlayer) {
			RegionScreen4.switchToggle(sp, ExToggle.PLAYER, sh, r, evt.getButton(), false);
		}

		if (button == toggleNode) {
			RegionScreen4.switchToggle(sp, ExToggle.NODE, sh, r, evt.getButton(), false);
		}

		if (button == toggleSubOwner) {
			RegionScreen4.switchToggle(sp, ExToggle.SUB_OWNER, sh, r, evt.getButton(), false);
		}

		if (button == toggleItems) {
			RegionScreen4.switchToggle(sp, ExToggle.ITEM, sh, r, evt.getButton(), false);
		}
	}

	private void regionScreen5Listener(ButtonClickEvent evt, ScreenHolder sh) {

		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		SpoutPlayer sp = evt.getPlayer();

		TextField excepField = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[3].getId());

		UUID cache = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[0].getId()).getId();
		UUID pa = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[1].getId()).getId();
		UUID pr = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[2].getId()).getId();
		UUID cs = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[11].getId()).getId();

		UUID addEx = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[4].getId()).getId();
		UUID remEx = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[5].getId()).getId();
		UUID eraseEx = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[6].getId()).getId();
		UUID back = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[8].getId()).getId();
		UUID forward = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page5Widgets[9].getId()).getId();

		UUID button = evt.getButton().getId();

		if (button == cache) {
			RegionScreen5.switchToggle(sp, PermToggle.CACHE, sh, r, evt.getButton(), false);
		}

		if (button == pa) {
			RegionScreen5.switchToggle(sp, PermToggle.PERM_ADD, sh, r, evt.getButton(), false);
		}

		if (button == pr) {
			RegionScreen5.switchToggle(sp, PermToggle.PERM_REMOVE, sh, r, evt.getButton(), false);
		}

		if (button == cs) {
			RegionScreen5.switchToggle(sp, PermToggle.SET, sh, r, evt.getButton(), false);
		}

		if (button == addEx) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (excepField.getText().length() < 1) {
				sp.sendNotification(ChatColor.RED + "Error!", "No Text Entered!", Material.FIRE);
				return;
			}
			if (excepField.getText().contains(" ")) {
				sp.sendNotification(ChatColor.RED + "Error!", "No Spaces Allowed!", Material.FIRE);
				excepField.setText("");
				excepField.setDirty(true);
				return;
			}
			RegionScreen5.addException(RegionScreen5.toggle.get(sp), sp, r, excepField.getText().trim(), excepField);
		}

		if (button == remEx) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (excepField.getText().length() < 1) {
				sp.sendNotification(ChatColor.RED + "Error!", "No Text Entered!", Material.FIRE);
				return;
			}
			RegionScreen5.removeException(RegionScreen5.toggle.get(sp), sp, r, excepField.getText(), excepField);
		}

		if (button == eraseEx) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			RegionScreen5.eraseExceptions(RegionScreen5.toggle.get(sp), sp, r);
		}

		if (button == back) {
			RegionScreen5.prevPage(sp, r, sh);
		}

		if (button == forward) {
			RegionScreen5.nextPage(sp, r, sh);
		}

	}

	final MutableSpout spout = new MutableSpout();

	private void regionScreen6Listener(ButtonClickEvent evt, ScreenHolder sh) {
		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		SpoutPlayer sp = evt.getPlayer();

		TextField welcomeMsg = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[0].getId());
		TextField leaveMsg = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[1].getId());
		TextField welcomeID = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[7].getId());
		TextField leaveID = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[8].getId());
		TextField textureURL = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[11].getId());

		UUID button = evt.getButton().getId();

		UUID resetWelcome = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[2].getId()).getId();
		UUID resetLeave = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[3].getId()).getId();
		UUID clearWelcome = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[4].getId()).getId();
		UUID clearLeave = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[5].getId()).getId();
		UUID updateWelcomeID = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[9].getId()).getId();
		UUID updateLeaveID = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[10].getId()).getId();

		UUID resetTexture = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[12].getId()).getId();
		UUID clearTexture = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[13].getId()).getId();
		UUID pasteTexture = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[14].getId()).getId();

		UUID useTextures = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[15].getId()).getId();

		UUID toggleLeave = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[22].getId()).getId();
		UUID toggleWelcome = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[23].getId()).getId();

		UUID updateMessages = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[6].getId()).getId();

		UUID updateTexture = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page6Widgets[21].getId()).getId();

		if (button == toggleLeave) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isSpoutLeaveEnabled()) {
				spout.editLeaveEnabled(r, false);
				sp.sendNotification("Spout - Leave", ChatColor.RED + "Message Disabled", Material.PAPER);
			} else {
				spout.editLeaveEnabled(r, true);
				sp.sendNotification("Spout - Leave", ChatColor.GREEN + "Message Enabled", Material.PAPER);
			}
			((GenericButton) (sh.page6Widgets[22])).setTextColor(RegionScreenManager.getColourToken(r.isSpoutLeaveEnabled()));
			((GenericButton) (sh.page6Widgets[22])).setDirty(true);
			return;
		}

		if (button == toggleWelcome) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isSpoutWelcomeEnabled()) {
				spout.editWelcomeEnabled(r, false);
				sp.sendNotification("Spout - Welcome", ChatColor.RED + "Message Disabled", Material.PAPER);
			} else {
				spout.editWelcomeEnabled(r, true);
				sp.sendNotification("Spout - Welcome", ChatColor.GREEN + "Message Enabled", Material.PAPER);
			}
			((GenericButton) (sh.page6Widgets[23])).setTextColor(RegionScreenManager.getColourToken(r.isSpoutWelcomeEnabled()));
			((GenericButton) (sh.page6Widgets[23])).setDirty(true);
			return;
		}

		if (button == updateTexture) {
			if(textureURL.getText().length() < 1 || textureURL.getText() == null){
				sp.sendNotification(ChatColor.RED + "Error!", "No Text Entered!", Material.FIRE);
				return;
			}
			try {
				URL u = new URL(textureURL.getText());
				HttpURLConnection huc = (HttpURLConnection) u.openConnection();
				HttpURLConnection.setFollowRedirects(false);
				huc.setRequestMethod("HEAD");
				huc.connect();
				if (huc.getResponseCode() != HttpURLConnection.HTTP_OK) {
					sp.sendNotification(ChatColor.RED + "Error!", "URL does not exist!", Material.FIRE);
				} else {
					spout.editTexturePackURL(r, textureURL.getText());
					sp.sendNotification("Spout", "Texture Pack Updated", Material.PAINTING);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (button == resetTexture) {
			textureURL.setText(r.getSpoutTexturePack());
			textureURL.setDirty(true);
			sp.sendNotification("Spout", "Texture Reset", Material.PAINTING);
		}

		if (button == clearTexture) {
			textureURL.setText("");
			textureURL.setDirty(true);
			sp.sendNotification("Spout", "Texture Cleared", Material.PAINTING);
		}

		if (button == pasteTexture) {
			String clipboard = sp.getClipboardText();
			if (clipboard == null) {
				sp.sendNotification(ChatColor.RED + "Clipboard Empty", ChatColor.RED + "Or Innacessible", Material.FIRE);
				return;
			}
			textureURL.setText(clipboard);
			textureURL.setDirty(true);
			sp.sendNotification("Spout", "Clipboard Pasted", Material.PAINTING);
		}

		if (button == useTextures) {
			if (!PermissionsCore.canModifyMain(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			if (r.isUseSpoutTexturePack()) {
				spout.editUseTexturePack(r, false);
				sp.sendNotification("Spout", ChatColor.RED + "Texture Pack Disabled", Material.PAINTING);
			} else {

				spout.editUseTexturePack(r, true);
				sp.sendNotification("Spout", ChatColor.GREEN + "Texture Pack Enabled", Material.PAINTING);
			}
			((GenericButton) (sh.page6Widgets[15])).setTooltip(RegionScreenManager.getStatus(r.isUseSpoutTexturePack()));
			((GenericButton) (sh.page6Widgets[15])).setTextColor(RegionScreenManager.getColourToken(r.isUseSpoutTexturePack()));
			((GenericButton) (sh.page6Widgets[15])).setDirty(true);
			return;
		}

		if (button == resetWelcome) {
			welcomeMsg.setText(r.getSpoutWelcomeMessage());
			welcomeMsg.setDirty(true);
		}

		if (button == resetLeave) {
			leaveMsg.setText(r.getSpoutLeaveMessage());
			leaveMsg.setDirty(true);
		}

		if (button == clearWelcome) {
			welcomeMsg.setText("");
			welcomeMsg.setDirty(true);
		}

		if (button == clearLeave) {
			leaveMsg.setText("");
			leaveMsg.setDirty(true);
		}

		if (button == updateMessages) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			spout.editWelcomeMessage(r, welcomeMsg.getText());
			spout.editLeaveMessage(r, leaveMsg.getText());
			sp.sendNotification("Spout", ChatColor.GREEN + "Messages Updated", Material.PAPER);
		}

		if (button == updateWelcomeID) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			int id;
			try {
				id = Integer.parseInt(welcomeID.getText());
			} catch (NumberFormatException nfex) {
				sp.sendNotification(ChatColor.RED + "Error!", "ID must be an Integer!", Material.FIRE);
				return;
			}
			if (id == 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "ID cannot be 0!", Material.FIRE);
				return;
			}

			Material m = Material.getMaterial(id);
			if (m == null) {
				sp.sendNotification(ChatColor.RED + "Error!", "Invalid Item ID!", Material.FIRE);
				return;
			}
			spout.editWelcomeMaterial(r, id);
			sp.sendNotification("Spout", ChatColor.GREEN + "ID Updated", m);
		}

		if (button == updateLeaveID) {
			if (!PermissionsCore.canModifyBasic(r, (Player) sp)) {
				PermissionsCore.sendInvalidPermsPopup(sp);
			}
			int id;
			try {
				id = Integer.parseInt(leaveID.getText());
			} catch (NumberFormatException nfex) {
				sp.sendNotification(ChatColor.RED + "Error!", "ID must be an Integer!", Material.FIRE);
				return;
			}
			if (id == 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "ID cannot be 0!", Material.FIRE);
				return;
			}
			Material m = Material.getMaterial(id);
			if (m == null) {
				sp.sendNotification(ChatColor.RED + "Error!", "Invalid Item ID!", Material.FIRE);
				return;
			}
			spout.editLeaveMaterial(r, id);
			sp.sendNotification("Spout", ChatColor.GREEN + "ID Updated", m);
		}

	}

	private void regionScreen7Listener(ButtonClickEvent evt, ScreenHolder sh) {

		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		SpoutPlayer sp = evt.getPlayer();

		TextField field = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page7Widgets[0].getId());

		UUID button = evt.getButton().getId();

		UUID prev = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page7Widgets[4].getId()).getId();
		UUID next = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page7Widgets[5].getId()).getId();

		UUID add = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page7Widgets[6].getId()).getId();
		UUID remove = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page7Widgets[7].getId()).getId();
		UUID clear = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page7Widgets[8].getId()).getId();

		if (button == prev) {
			RegionScreen7.prevPage(sp, r, sh);
		}

		if (button == next) {
			RegionScreen7.prevPage(sp, r, sh);
		}

		if (button == add) {
			if(field.getText().length() < 1 || field.getText() == null){
				sp.sendNotification(ChatColor.RED + "Error!", "No Text Entered!", Material.FIRE);
				return;
			}
			try {
				URL u = new URL(field.getText());
				HttpURLConnection huc = (HttpURLConnection) u.openConnection();
				HttpURLConnection.setFollowRedirects(false);
				huc.setRequestMethod("HEAD");
				huc.connect();
				if (huc.getResponseCode() != HttpURLConnection.HTTP_OK) {
					sp.sendNotification(ChatColor.RED + "Error!", "URL does not exist!", Material.FIRE);
				} else {
					RegionScreen7.addURL(sp, r, field.getText(), field);
					sp.sendNotification("Spout", "Music Added", Material.GREEN_RECORD);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (button == remove) {
			if(field.getText().length() < 1 || field.getText() == null){
				sp.sendNotification(ChatColor.RED + "Error!", "No Text Entered!", Material.FIRE);
				return;
			}
			RegionScreen7.removeURL(sp, r, field.getText(), field);
		}

		if (button == clear) {
			RegionScreen7.clearURLS(sp, r);
		}

	}
}
