
package com.rayzr522.bglib.commands.preset;

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

@CommandInfo(name = "arena remove", usage = "/{command} setup arena remove [name]", desc = "Remove the selected or specified arena", pattern = "ar(ena)? remove", perm = "{base}.setup.arena.remove")
public class MGPCSetupArenaRemove implements BitzCommand {

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

        Arena removedArena;
        Arena selectedArena = data.getSelectedArena(player);
        if (selectedArena == null) {

            if (args.length < 1) {

                plugin.messenger.playerMessage(sender, BitzMessages.NO_ARG.msg, "name");
                return false;

            }

            selectedArena = pl.getMinigame().getArena(args[0]);

            if (selectedArena == null) {

                plugin.messenger.playerMessage(sender, MGMessages.NO_ARENA.msg, args[0]);
                return true;

            }

        }

        removedArena = pl.getMinigame().removeArena(selectedArena);
        if (removedArena == null) {
            plugin.messenger.playerMessage(sender, MGMessages.NO_ARENA.msg, selectedArena.getName());
            return false;
        }

        plugin.messenger.playerMessage(sender, MGMessages.ARENA_REMOVED.msg, removedArena.getName());
        pl.getMinigame().getData().setSelectedArena(player, null);

        return true;
    }

}
