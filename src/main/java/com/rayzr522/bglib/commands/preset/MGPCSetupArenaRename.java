
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

@CommandInfo(name = "arena rename", usage = "/{command} setup arena rename <name> [arena]", desc = "Rename the selected or specified arena", pattern = "ar(ena)? rename", perm = "{base}.setup.arena.rename")
public class MGPCSetupArenaRename implements BitzCommand {

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

        Arena selectedArena = data.getSelectedArena(player);

        if (selectedArena == null) {

            if (args.length < 2) {

                plugin.messenger.playerMessage(sender, BitzMessages.NO_ARG.msg, "arena");
                return false;

            }

            selectedArena = pl.getMinigame().getArena(args[1]);

            if (selectedArena == null) {

                plugin.messenger.playerMessage(sender, MGMessages.NO_ARENA.msg, args[1]);
                return true;

            }

        }

        selectedArena.setName(args[0]);
        plugin.messenger.playerMessage(sender, MGMessages.ARENA_RENAMED.msg, selectedArena.getName());

        return true;
    }

}
