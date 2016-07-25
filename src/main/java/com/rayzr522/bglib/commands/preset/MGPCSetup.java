
package com.rayzr522.bglib.commands.preset;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rayzr522.bglib.arena.Arena;
import com.rayzr522.bglib.data.MinigameData;
import com.rayzr522.bglib.messaging.MGMessages;
import com.rayzr522.bglib.plugin.MinigamePlugin;
import com.rayzr522.bglib.utils.MinigameUtils;
import com.rayzr522.bitzapi.BitzPlugin;
import com.rayzr522.bitzapi.client.BitzMessages;
import com.rayzr522.bitzapi.commands.BitzCommand;
import com.rayzr522.bitzapi.commands.CommandInfo;
import com.rayzr522.bitzapi.utils.commands.CommandUtils;
import com.rayzr522.bitzapi.utils.data.BitzData;
import com.rayzr522.bitzapi.world.PartialRegion;

@CommandInfo(name = "region arena", usage = "/{command} setup", desc = "Set the arena region for the selected arena", pattern = "region a(rena)?", perm = "{base}.setup.region.arena")
public class MGPCSetup implements BitzCommand {

	public boolean execute(CommandSender sender, String[] args, BitzPlugin plugin) {

		if (!CommandUtils.isPlayer(sender)) { return true; }

		if (!MinigameUtils.isMGPlugin(plugin)) {
			plugin.messenger.playerMessage(sender, MGMessages.MGPLUGIN_ONLY_COMMAND.msg);
			return true;
		};

		Player player = (Player) sender;

		MinigamePlugin pl = (MinigamePlugin) plugin;

		MinigameData data = pl.getMinigame().getData();

		Arena arena = data.getSelectedArena(player);

		if (arena == null) {
			plugin.messenger.playerMessage(sender, MGMessages.MUST_SELECT_ARENA.msg);
			return true;
		}

		PartialRegion partial = BitzData.getRegionSelection(player);
		if (!partial.isComplete()) {
			plugin.messenger.playerMessage(sender, BitzMessages.NO_REGION_SEL.msg);
			return true;
		}

		arena.setArenaRegion(partial.toRegion());
		plugin.messenger.playerMessage(sender, MGMessages.ARENA_REGION_SET.msg, arena.getName());

		return true;
	}

}
