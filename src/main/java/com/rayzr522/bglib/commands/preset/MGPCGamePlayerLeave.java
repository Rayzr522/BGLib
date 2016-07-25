
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
import com.rayzr522.bitzapi.utils.commands.CommandUtils;

@CommandInfo(name = "leave", usage = "/{command} leave", desc = "Leave an arena", pattern = "l(eave)?", perm = "{base}.game.leave")
public class MGPCGamePlayerLeave implements BitzCommand {

	public boolean execute(CommandSender sender, String[] args, BitzPlugin plugin) {

		if (!CommandUtils.isPlayer(sender)) { return true; }

		if (!MinigameUtils.isMGPlugin(plugin)) {
			plugin.messenger.playerMessage(sender, MGMessages.MGPLUGIN_ONLY_COMMAND.msg);
			return true;
		};

		Player player = (Player) sender;

		MinigamePlugin pl = (MinigamePlugin) plugin;

		Arena arena = pl.getMinigame().getArena(player);

		if (arena == null) {
			plugin.messenger.playerMessage(sender, MGMessages.NOT_IN_ARENA.msg);
			return true;
		}

		arena.leave(player);
		plugin.messenger.playerMessage(sender, MGMessages.ARENA_LEFT.msg, arena.getName());

		return true;

	}

}
