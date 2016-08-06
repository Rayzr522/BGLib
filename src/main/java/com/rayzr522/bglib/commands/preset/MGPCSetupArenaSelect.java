
package com.rayzr522.bglib.commands.preset;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rayzr522.bglib.arena.Arena;
import com.rayzr522.bglib.messaging.MGMessages;
import com.rayzr522.bglib.plugin.MinigamePlugin;
import com.rayzr522.bglib.utils.MinigameUtils;
import com.rayzr522.bitzapi.BitzPlugin;
import com.rayzr522.bitzapi.commands.BitzCommand;
import com.rayzr522.bitzapi.commands.CommandInfo;
import com.rayzr522.bitzapi.message.BitzMessages;
import com.rayzr522.bitzapi.utils.CommandUtils;

@CommandInfo(name = "arena select", usage = "/{command} setup arena select <name>", desc = "Select an arena", pattern = "ar(ena)? sel(ect)?", perm = "{base}.setup.arena.select")
public class MGPCSetupArenaSelect implements BitzCommand {

	public boolean execute(CommandSender sender, String[] args, BitzPlugin plugin) {

		if (!CommandUtils.isPlayer(sender)) { return true; }

		if (!MinigameUtils.isMGPlugin(plugin)) {
			plugin.messenger.playerMessage(sender, MGMessages.MGPLUGIN_ONLY_COMMAND.msg);
			return true;
		};

		if (args.length < 1) {
			plugin.messenger.playerMessage(sender, BitzMessages.NO_ARG.msg, "name");
			return false;
		}

		String name = args[0];

		Player player = (Player) sender;

		MinigamePlugin pl = (MinigamePlugin) plugin;

		Arena arena = pl.getMinigame().createArena(name);
		pl.getMinigame().getData().setSelectedArena(player, arena);
		plugin.messenger.playerMessage(sender, MGMessages.ARENA_CREATED.msg, arena.getName());

		return true;
	}

}
