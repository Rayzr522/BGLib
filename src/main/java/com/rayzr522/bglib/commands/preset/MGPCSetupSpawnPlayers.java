
package com.rayzr522.bglib.commands.preset;

import java.util.List;

import org.bukkit.Location;
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

@CommandInfo(name = "spawn players", usage = "/{command} setup spawn players", desc = "Set the player spawnpoints for the selected arena", pattern = "sp(awn)? p(l(ayers)?)?", perm = "{base}.setup.spawn.players")
public class MGPCSetupSpawnPlayers implements BitzCommand {

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

		List<Location> spawns = BitzData.getLocationListSelection(player);
		if (spawns.size() < 1) {
			plugin.messenger.playerMessage(sender, BitzMessages.NO_LOCS_SEL.msg);
			return true;
		}

		arena.setPlayerSpawns((spawns));
		plugin.messenger.playerMessage(sender, MGMessages.PLAYER_SPAWNS_SET.msg, arena.getName());

		return true;
	}

}
