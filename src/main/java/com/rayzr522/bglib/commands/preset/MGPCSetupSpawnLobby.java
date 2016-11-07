
package com.rayzr522.bglib.commands.preset;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rayzr522.bglib.arena.Arena;
import com.rayzr522.bglib.data.MinigameData;
import com.rayzr522.bglib.messaging.MGMessages;
import com.rayzr522.bglib.plugin.MinigamePlugin;
import com.rayzr522.bglib.utils.MinigameUtils;
import com.rayzr522.bitzapi.BitzPlugin;
import com.rayzr522.bitzapi.commands.BitzCommand;
import com.rayzr522.bitzapi.commands.CommandInfo;
import com.rayzr522.bitzapi.message.BitzMessages;
import com.rayzr522.bitzapi.utils.CommandUtils;
import com.rayzr522.bitzapi.utils.data.BitzData;

@CommandInfo(name = "spawn lobby", usage = "/{command} setup spawn lobby", desc = "Set the lobby spawn for the selected arena", pattern = "sp(awn)? l(obby)?", perm = "{base}.setup.spawn.lobby")
public class MGPCSetupSpawnLobby implements BitzCommand {

    public boolean execute(CommandSender sender, String[] args, BitzPlugin plugin) {

        if (!CommandUtils.isPlayer(sender)) {
            return true;
        }

        if (!MinigameUtils.isMGPlugin(plugin)) {
            plugin.messenger.playerMessage(sender, MGMessages.MGPLUGIN_ONLY_COMMAND.msg);
            return true;
        }
        ;

        Player player = (Player) sender;

        MinigamePlugin pl = (MinigamePlugin) plugin;

        MinigameData data = pl.getMinigame().getData();

        Arena arena = data.getSelectedArena(player);

        if (arena == null) {
            plugin.messenger.playerMessage(sender, MGMessages.MUST_SELECT_ARENA.msg);
            return true;
        }

        Location spawn = BitzData.getLocationSelection(player);
        if (spawn == null) {
            plugin.messenger.playerMessage(sender, BitzMessages.NO_LOC_SEL.msg);
            return true;
        }

        arena.setLobbySpawn(spawn);
        plugin.messenger.playerMessage(sender, MGMessages.LOBBY_SPAWN_SET.msg, arena.getName());

        return true;
    }

}
