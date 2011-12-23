package couk.Adamki11s.Regios.SpoutGUI;

import java.util.ArrayList;
import java.util.HashMap;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ScreenHolder {

	public static HashMap<SpoutPlayer, ScreenHolder> screenHolder = new HashMap<SpoutPlayer, ScreenHolder>();

	public GenericLabel[] generalDataText = { new GenericLabel("/r create <region>"), new GenericLabel("/r delete <region>"),
			new GenericLabel("/r rename <oldname> <newname>"), new GenericLabel("/r info <region>"), new GenericLabel("/r list"), new GenericLabel("/r list-for-sale"),
			new GenericLabel("/r delete <region>"), new GenericLabel("/r reload"), new GenericLabel("/r reload-regions"), new GenericLabel("/r reload-config"),
			new GenericLabel("/r cancel"), new GenericLabel("/r set-owner <region> <owner>") };

	public GenericLabel[] modeText = { new GenericLabel("/r protection-mode <region> <bl/wl>"), new GenericLabel("/r prevent-entry-mode <region> <bl/wl>"),
			new GenericLabel("/r prevent-exit-mode <region> <bl/wl>"), new GenericLabel("/r item-mode <region> <bl/wl>") };

	public GenericLabel[] protectionText = { new GenericLabel("/r send-auth <password>"), new GenericLabel("/r un/protect-all <region>"), new GenericLabel("/r un/protect-bb <region>"),
			new GenericLabel("/r un/protect-bp <region>"),
			new GenericLabel("/r prevent-entry <region>"), new GenericLabel("/r allow-entry <region>"), new GenericLabel("/r prevent-exit <region>"),
			new GenericLabel("/r allow-exit <region>"), new GenericLabel("/r prevent-interaction <region> <T/F>"), new GenericLabel("/r doors-locked <region> <T/F>"),
			new GenericLabel("/r chests-locked <region> <T/F>"), new GenericLabel("/r set-password <region> <password>"), new GenericLabel("/r use-password <region> <T/F>"),
			new GenericLabel("/r fire-protection <region> <T/F>"), new GenericLabel("/r player-cap <region> <cap>"), new GenericLabel("/r block-form <region> <T/F>") };

	public GenericLabel[] messagesText = { new GenericLabel("/r set-welcome <region> <message>"), new GenericLabel("/r show-welcome <region> <T/F>"),
			new GenericLabel("/r set-leave <region> <message>"), new GenericLabel("/r show-leave <region> <T/F>"), new GenericLabel("/r set-prevent-exit <region> <message>"),
			new GenericLabel("/r show-prevent-exit <region> <T/F>"), new GenericLabel("/r set-prevent-entry <region> <message>"),
			new GenericLabel("/r show-prevent-entry <region> <T/F>"), new GenericLabel("/r set-protection <region> <message>"),
			new GenericLabel("/r show-protection <region> <T/F>"), new GenericLabel("/r show-pvp <region> <T/F>") };

	public GenericLabel[] funText = { new GenericLabel("/r setwarp"), new GenericLabel("/r warp-to <region>"), new GenericLabel("/r reset-warp <region>"),
			new GenericLabel("/r lsps <region> <rate>"), new GenericLabel("/r pvp <region> <T/F>"), new GenericLabel("/r health-regen <region> <rate>"),
			new GenericLabel("/r health-enabled <region> <T/F>"), new GenericLabel("/r vel-warp <region> <rate>"), new GenericLabel("/r set-price <region> <price>"),
			new GenericLabel("/r for-sale <region> <T/F>"),new GenericLabel("/r list-for-sale"), new GenericLabel("/r buy <region>") };

	public GenericLabel[] otherText = { new GenericLabel("/r mob-spawns <region> <T/F>"), new GenericLabel("/r monster-spawns <region> <T/F>"), new GenericLabel("/r check"),
			new GenericLabel("/r add-cmd-set <region> <cmd>"), new GenericLabel("/r rem-cmd-set <region> <cmd>"), new GenericLabel("/r list-cmd-set <region> <cmd>"),
			new GenericLabel("/r reset-cmd-set <region>"), new GenericLabel("/r use-cmd-set <region> <T/F>") };

	public GenericLabel[] inventText = { new GenericLabel("/r perm-wipe-entry <region> <T/F>"), new GenericLabel("/r perm-wipe-exit <region> <T/F>"),
			new GenericLabel("/r cache-wipe-entry <region> <T/F>"), new GenericLabel("/r cache-wipe-exit <region> <T/F>") };

	public GenericLabel[] exceptionText = { new GenericLabel("/r add-ex <region> <player>"), new GenericLabel("/r rem-ex <region> <player>"),
			new GenericLabel("/r list-player-ex <region>"), new GenericLabel("/r erase-player-ex <region>"), new GenericLabel("/r add-item-ex <region> <itemid>"),
			new GenericLabel("/r rem-item-ex <region> <itemid>"), new GenericLabel("/r list-item-ex <region>"), new GenericLabel("/r erase-item-ex <region>"),
			new GenericLabel("/r add-node-ex <region> <node>"), new GenericLabel("/r rem-node-ex <region> <node>"), new GenericLabel("/r list-node-ex <region>"),
			new GenericLabel("/r erase-node-ex <region>"), new GenericLabel("/r add-sub-ex <region> <node>"), new GenericLabel("/r rem-sub-ex <region> <node>"),
			new GenericLabel("/r list-sub-ex <region>"), new GenericLabel("/r reset-sub-ex <region>") };

	public GenericLabel[] modifyText = { new GenericLabel("/r expand-up <region> <value>"), new GenericLabel("/r expand-down <region> <value>"),
			new GenericLabel("/r expand-out <region> <value>"), new GenericLabel("/r expand-max <region>"), new GenericLabel("/r shrink-up <region> <value>"),
			new GenericLabel("/r shrink-down <region> <value>"), new GenericLabel("/r shrink-in <region> <value>"), new GenericLabel("/r expand-up <region> <value>"),
			new GenericLabel("/r modify <region>"), new GenericLabel("/r modify confirm"), new GenericLabel("/r inherit <toinherit> <inheritfrom>") };

	public GenericLabel[] spoutText = { new GenericLabel("/r set-spout-welcome <region> <message>"), new GenericLabel("/r set-spout-leave <region> <message>"),
			new GenericLabel("/r set-wlecome-id <region> <itemid>"), new GenericLabel("/r set-leave-id <region> <itemid>"),
			new GenericLabel("/r spout-texture-url <region> <url>"), new GenericLabel("/r use-texture-url <region> <T/F>"),
			new GenericLabel("/r use-music-url <region> <T/F>"), new GenericLabel("/r add-music-url <region> <url>"), new GenericLabel("/r rem-music-url <region> <url>"),
			new GenericLabel("/r reset-music-url <region>") };

	public GenericLabel[] permissionsText = { new GenericLabel("/r perm-cache-add <region> <node>"), new GenericLabel("/r perm-cache-rem <region> <node>"),
			new GenericLabel("/r perm-cache-list <region>"), new GenericLabel("/r perm-cache-reset <region>"), new GenericLabel("/r perm-add-add <region> <node>"),
			new GenericLabel("/r perm-add-rem <region> <node>"), new GenericLabel("/r perm-add-list <region>"), new GenericLabel("/r perm-add-reset <region>"),
			new GenericLabel("/r perm-rem-add <region> <node>"), new GenericLabel("/r perm-rem-rem <region> <node>"), new GenericLabel("/r perm-rem-list <region>"),
			new GenericLabel("/r perm-rem-reset <region>") };

	public GenericLabel[] dataText = { new GenericLabel("/r save-region <region> <name>"), new GenericLabel("/r load-region <region> <name>"),
			new GenericLabel("/r list-backups <region>"), new GenericLabel("/r backup-database <region> <name>"), new GenericLabel("/r version"),
			new GenericLabel("/r check"), new GenericLabel("/r info <region>"), new GenericLabel("/r patch") };

	public SpoutPlayer sp;

	public Widget[] page1Widgets = { new GenericButton("Block Break Protection"), new GenericButton("Block Place Protection"), new GenericButton("Prevent Entry"),
			new GenericButton("Prevent Exit"), new GenericButton("Prevent Interaction"), new GenericButton("Doors Locked"), new GenericButton("Chests Locked"),
			new GenericButton("Fire Protection"), new GenericButton("Block Form"), new GenericButton("Mobs Spawn"), new GenericButton("Monsters Spawn"),
			new GenericButton("Show Welcome"), new GenericButton("Show Leave"), new GenericButton("Show Prevent Entry"), new GenericButton("Show Prevent Exit"),
			new GenericButton("Show Protection"), new GenericButton("Show Pvp"), new GenericButton("PvP"), new GenericButton("Health Enabled"),
			new GenericButton("Protection Mode"), new GenericButton("Prevent Entry Mode"), new GenericButton("Prevent Exit Mode"), new GenericButton("Item Mode"),
			new GenericButton("Force Commands"), new GenericButton("General Protection") };

	public GenericButton escButton = new GenericButton(), pageForward = new GenericButton(), pageBackwards = new GenericButton();

	public GenericButton generalData, modes, protection, messages, fun, other, inventory, perms, exceptions, modify, spout, data;

	public GenericLabel pageTracker;

	public Widget[] page2Widgets = { new GenericTextField(), new GenericTextField(), new GenericTextField(), new GenericTextField(), new GenericTextField(),
			new GenericLabel("Welcome Message"), new GenericLabel("Leave Message"), new GenericLabel("Prevent Entry Message"), new GenericLabel("Prevent Exit Message"),
			new GenericLabel("Protection Message"), new GenericButton("Update Messages"), new GenericButton("Reset"), new GenericButton("Reset"), new GenericButton("Reset"),
			new GenericButton("Reset"), new GenericButton("Reset"), new GenericButton("Clear"), new GenericButton("Clear"), new GenericButton("Clear"),
			new GenericButton("Clear"), new GenericButton("Clear") };

	public Widget[] page3Widgets = { new GenericLabel("LSPS"), new GenericTextField(), new GenericButton("Update"), new GenericLabel("Health-Regen"), new GenericTextField(),
			new GenericButton("Update"), new GenericLabel("Velocity-Warp"), new GenericTextField(), new GenericButton("Update"), new GenericButton("For Sale"),
			new GenericLabel("Sale Price"), new GenericTextField(), new GenericButton("Update"), new GenericButton("WipeAndCacheEnter"),
			new GenericButton("WipeAndCacheExit"), new GenericButton("PermWipeEnter"), new GenericButton("PermWipeExit") };

	public Widget[] page4Widgets = { new GenericLabel("Exceptions"), new GenericButton("Players"), new GenericButton("Nodes"), new GenericButton("Sub Owners"),
			new GenericButton("Items"), new GenericTextField(), new GenericButton("Add"), new GenericButton("Remove"), new GenericButton("Erase"),
			new GenericLabel("Indicator"), new GenericButton("<"), new GenericButton(">"), new GenericContainer() };

	public Widget[] page5Widgets = { new GenericButton("Cache"), new GenericButton("Perm-Add"), new GenericButton("Perm-Rem"), new GenericTextField(),
			new GenericButton("Add"), new GenericButton("Remove"), new GenericButton("Erase"), new GenericLabel("Indicator"), new GenericButton("<"), new GenericButton(">"),
			new GenericContainer(), new GenericButton("Cmd-Set") };

	public Widget[] page6Widgets = { new GenericTextField(), new GenericTextField(), new GenericButton("Reset"), new GenericButton("Reset"), new GenericButton("Clear"),
			new GenericButton("Clear"), new GenericButton("Update"), new GenericTextField(), new GenericTextField(), new GenericButton("Update"), new GenericButton("Update"),
			new GenericTextField(), new GenericButton("Reset"), new GenericButton("Clear"), new GenericButton("Paste"), new GenericButton("Use Textures"),
			new GenericLabel("Spout Welcome Message"), new GenericLabel("Spout Leave Message"), new GenericLabel("Spout Welcome Icon ID"),
			new GenericLabel("Spout Leave Icon ID"), new GenericLabel("Texture Pack URL"), new GenericButton("Update"), new GenericButton("Enabled"),
			new GenericButton("Enabled") };

	public Widget[] page7Widgets = { new GenericTextField(), new GenericLabel("Music"), new GenericLabel("Page : "), new GenericContainer(), new GenericButton("<"),
			new GenericButton(">"), new GenericButton("Add Music"), new GenericButton("Remove Music"), new GenericButton("Clear Music") };

	public static HashMap<SpoutPlayer, ArrayList<Widget>> page4Exceptions = new HashMap<SpoutPlayer, ArrayList<Widget>>();

	public void addScreenHolder(SpoutPlayer sp, ScreenHolder sh) {
		screenHolder.put(sp, sh);
	}

	public static ScreenHolder getScreenHolder(SpoutPlayer sp) {
		if (screenHolder.containsKey(sp)) {
			return screenHolder.get(sp);
		} else {
			return new ScreenHolder();
		}
	}
}
