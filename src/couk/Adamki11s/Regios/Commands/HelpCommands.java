package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HelpCommands {

	static Plugin plugin;
	
	public String[] generalDataText = { ("/r create <region>"), ("/r delete <region>"),
			("/r rename <oldname> <newname>"), ("/r info <region>"), ("/r list"), ("/r list-for-sale"),
			("/r delete <region>"), ("/r reload"), ("/r reload-regions"), ("/r reload-config"),
			("/r cancel"), ("/r set-owner <region> <owner>") };

	public String[] modeText = { ("/r protection-mode <region> <bl/wl>"), ("/r prevent-entry-mode <region> <bl/wl>"),
			("/r prevent-exit-mode <region> <bl/wl>"), ("/r item-mode <region> <bl/wl>") };

	public String[] protectionText = { ("/r send-auth <password>"), ("/r un/protect-all <region>"), ("/r un/protect-bb <region>"),
			("/r un/protect-bp <region>"),
			("/r prevent-entry <region>"), ("/r allow-entry <region>"), ("/r prevent-exit <region>"),
			("/r allow-exit <region>"), ("/r prevent-interaction <region> <T/F>"), ("/r doors-locked <region> <T/F>"),
			("/r chests-locked <region> <T/F>"), ("/r set-password <region> <password>"), ("/r use-password <region> <T/F>"),
			("/r fire-protection <region> <T/F>"), ("/r player-cap <region> <cap>"), ("/r block-form <region> <T/F>") };

	public String[] messagesText = { ("/r set-welcome <region> <message>"), ("/r show-welcome <region> <T/F>"),
			("/r set-leave <region> <message>"), ("/r show-leave <region> <T/F>"), ("/r set-prevent-exit <region> <message>"),
			("/r show-prevent-exit <region> <T/F>"), ("/r set-prevent-entry <region> <message>"),
			("/r show-prevent-entry <region> <T/F>"), ("/r set-protection <region> <message>"),
			("/r show-protection <region> <T/F>"), ("/r show-pvp <region> <T/F>") };

	public String[] funText = { ("/r setwarp"), ("/r warp-to <region>"), ("/r reset-warp <region>"),
			("/r lsps <region> <rate>"), ("/r pvp <region> <T/F>"), ("/r health-regen <region> <rate>"),
			("/r health-enabled <region> <T/F>"), ("/r vel-warp <region> <rate>"), ("/r set-price <region> <price>"),
			("/r for-sale <region> <T/F>"),("/r list-for-sale"), ("/r buy <region>") };

	public String[] otherText = { ("/r mob-spawns <region> <T/F>"), ("/r monster-spawns <region> <T/F>"), ("/r check"),
			("/r add-cmd-set <region> <cmd>"), ("/r rem-cmd-set <region> <cmd>"), ("/r list-cmd-set <region> <cmd>"),
			("/r reset-cmd-set <region>"), ("/r use-cmd-set <region> <T/F>") };

	public String[] inventText = { ("/r perm-wipe-entry <region> <T/F>"), ("/r perm-wipe-exit <region> <T/F>"),
			("/r cache-wipe-entry <region> <T/F>"), ("/r cache-wipe-exit <region> <T/F>") };

	public String[] exceptionText = { ("/r add-ex <region> <player>"), ("/r rem-ex <region> <player>"),
			("/r list-player-ex <region>"), ("/r erase-player-ex <region>"), ("/r add-item-ex <region> <itemid>"),
			("/r rem-item-ex <region> <itemid>"), ("/r list-item-ex <region>"), ("/r erase-item-ex <region>"),
			("/r add-node-ex <region> <node>"), ("/r rem-node-ex <region> <node>"), ("/r list-node-ex <region>"),
			("/r erase-node-ex <region>"), ("/r add-sub-ex <region> <node>"), ("/r rem-sub-ex <region> <node>"),
			("/r list-sub-ex <region>"), ("/r reset-sub-ex <region>") };

	public String[] modifyText = { ("/r expand-up <region> <value>"), ("/r expand-down <region> <value>"),
			("/r expand-out <region> <value>"), ("/r expand-max <region>"), ("/r shrink-up <region> <value>"),
			("/r shrink-down <region> <value>"), ("/r shrink-in <region> <value>"), ("/r expand-up <region> <value>"),
			("/r modify <region>"), ("/r modify confirm"), ("/r inherit <toinherit> <inheritfrom>") };

	public String[] spoutText = { ("/r set-spout-welcome <region> <message>"), ("/r set-spout-leave <region> <message>"),
			("/r set-wlecome-id <region> <itemid>"), ("/r set-leave-id <region> <itemid>"),
			("/r spout-texture-url <region> <url>"), ("/r use-texture-url <region> <T/F>"),
			("/r use-music-url <region> <T/F>"), ("/r add-music-url <region> <url>"), ("/r rem-music-url <region> <url>"),
			("/r reset-music-url <region>") };

	public String[] permissionsText = { ("/r perm-cache-add <region> <node>"), ("/r perm-cache-rem <region> <node>"),
			("/r perm-cache-list <region>"), ("/r perm-cache-reset <region>"), ("/r perm-add-add <region> <node>"),
			("/r perm-add-rem <region> <node>"), ("/r perm-add-list <region>"), ("/r perm-add-reset <region>"),
			("/r perm-rem-add <region> <node>"), ("/r perm-rem-rem <region> <node>"), ("/r perm-rem-list <region>"),
			("/r perm-rem-reset <region>") };

	public String[] dataText = { ("/r save-region <region> <name>"), ("/r load-region <region> <name>"),
			("/r list-backups <region>"), ("/r backup-database <region> <name>"), ("/r version"),
			("/r check"), ("/r info <region>"), ("/r patch") };

	public void getStandardHelp(Player p, String[] args) {
		String pre = ChatColor.GREEN + "[Regios] ";
		if (args.length == 1) {
			p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] For more help use the commands below.");
			p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help general");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help protection");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help fun");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help data");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help messages");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help sh.inventory");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help sh.modes");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help sh.modify");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help exceptions");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help spout");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help permissions");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help sh.other");
			p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
			return;
		} else if (args.length == 2) {
			p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
			if (args[1].equalsIgnoreCase("general")) {
				p.sendMessage(ChatColor.DARK_RED + "[General]");
				for (String gl : this.generalDataText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[General]");
				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("protection")) {
				p.sendMessage(ChatColor.DARK_RED + "[Protection]");
				for (String gl : this.protectionText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Protection]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("fun")) {
				p.sendMessage(ChatColor.DARK_RED + "[Fun]");
				for (String gl : this.funText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Fun]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("data")) {
				p.sendMessage(ChatColor.DARK_RED + "[Data]");
				for (String gl : this.dataText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Data]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("messages")) {
				p.sendMessage(ChatColor.DARK_RED + "[Messages]");
				for (String gl : this.messagesText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Messages]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("this.inventory")) {
				p.sendMessage(ChatColor.DARK_RED + "[this.inventory]");
				for (String gl : this.inventText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[this.inventory]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("this.modes")) {
				p.sendMessage(ChatColor.DARK_RED + "[Modes]");
				for (String gl : this.modeText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Modes]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("this.modify")) {
				p.sendMessage(ChatColor.DARK_RED + "[Modify]");
				for (String gl : this.modifyText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Modify]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("exceptions")) {
				p.sendMessage(ChatColor.DARK_RED + "[Exceptions]");
				for (String gl : this.exceptionText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Exceptions]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("spout")) {
				p.sendMessage(ChatColor.DARK_RED + "[Spout]");
				for (String gl : this.spoutText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Spout]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("permissions")) {
				p.sendMessage(ChatColor.DARK_RED + "[Permissions]");
				for (String gl : this.permissionsText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Permissions]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			} else if (args[1].equalsIgnoreCase("this.other")) {
				p.sendMessage(ChatColor.DARK_RED + "[Other]");
				for (String gl : this.otherText) {
					p.sendMessage(pre + gl);
				}
				p.sendMessage(ChatColor.DARK_RED + "[Other]");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
				return;
			}
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help general");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help protection");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help fun");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help data");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help messages");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help inventory");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help modes");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help modify");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help exceptions");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help spout");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help permissions");
			p.sendMessage(ChatColor.GREEN + "[Regios] /r help other");
			p.sendMessage(ChatColor.LIGHT_PURPLE + "[Regios] -----------------------------------------");
		}
	}

	

	

}
