package couk.Adamki11s.Regios.RBF;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Permissions.PermissionsCore;
import couk.Adamki11s.jnbt.ByteArrayTag;
import couk.Adamki11s.jnbt.CompoundTag;
import couk.Adamki11s.jnbt.IntTag;
import couk.Adamki11s.jnbt.NBTInputStream;
import couk.Adamki11s.jnbt.Tag;

public class RBF_Load_Share extends PermissionsCore {

	public static HashMap<Player, ArrayList<PBD>> undoCache = new HashMap<Player, ArrayList<PBD>>();

	private Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
		Tag tag = items.get(key);
		return tag;
	}

	public void undoLoad(Player p) {
		if (!undoCache.containsKey(p)) {
			p.sendMessage(ChatColor.RED + "[Regios] Nothing to undo!");
			return;
		} else {
			ArrayList<PBD> bb = new ArrayList<PBD>();
			bb = undoCache.get(p);
			for (PBD b : bb) {
				Block block = p.getWorld().getBlockAt(b.getL());
				block.setTypeId(b.getId());
			}
			bb.clear();
			undoCache.remove(p);
			p.sendMessage(ChatColor.GREEN + "[Regios] Undo successful!");
		}
	}

	public void loadSharedRegion(String sharename, Player p, Location l) throws IOException {

		if (undoCache.containsKey(p)) {
			undoCache.remove(p);
		}

		ArrayList<PBD> blockss = new ArrayList<PBD>();
		undoCache.put(p, blockss);

		File f = new File("plugins" + File.separator + "Regios" + File.separator + "Blueprints" + File.separator + sharename + ".blp");

		if (!f.exists()) {
			p.sendMessage(ChatColor.RED + "[Regios] A blueprint file with the name " + ChatColor.BLUE + sharename + ChatColor.RED + " does not exist!");
			return;
		}

		p.sendMessage(ChatColor.GREEN + "[Regios] Restoring region from " + sharename + ".blp file...");

		World w = p.getWorld();

		FileInputStream fis = new FileInputStream(f);
		NBTInputStream nbt = new NBTInputStream(new GZIPInputStream(fis));

		CompoundTag backuptag = (CompoundTag) nbt.readTag();
		Map<String, Tag> tagCollection = backuptag.getValue();

		if (!backuptag.getName().equals("BLP")) {
			p.sendMessage(ChatColor.RED + "[Regios] Blueprint file in unexpected format! Tag does not match 'BLP'.");
		}

		int StartX = l.getBlockX();
		int StartY = l.getBlockY();
		int StartZ = l.getBlockZ();

		int width = (Integer) getChildTag(tagCollection, "XSize", IntTag.class).getValue();
		int height = (Integer) getChildTag(tagCollection, "YSize", IntTag.class).getValue();
		int length = (Integer) getChildTag(tagCollection, "ZSize", IntTag.class).getValue();

		byte[] blocks = (byte[]) getChildTag(tagCollection, "BlockID", ByteArrayTag.class).getValue();
		byte[] blockData = (byte[]) getChildTag(tagCollection, "Data", ByteArrayTag.class).getValue();

		int index = 0;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					Block b = w.getBlockAt(StartX + x, StartY + y, StartZ + z);
					blockss.add(new PBD(b));
				}
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					Block b = w.getBlockAt(StartX + x, StartY + y, StartZ + z);
					b.setTypeId((int) blocks[index]);
					b.setData(blockData[index]);
					index++;
				}
			}
		}

		undoCache.put(p, blockss);

		fis.close();
		nbt.close();

		p.sendMessage(ChatColor.GREEN + "[Regios] Blueprint " + ChatColor.BLUE + sharename + ChatColor.GREEN + " loaded successfully from .blp file!");
	}

}
