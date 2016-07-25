
package com.rayzr522.bglib.commands.bglib;

import org.bukkit.command.CommandSender;

import com.rayzr522.bitzapi.BitzPlugin;
import com.rayzr522.bitzapi.commands.BitzCommand;
import com.rayzr522.bitzapi.commands.CommandInfo;

@CommandInfo(name = "version", usage = "/bglib version", desc = "Plugin information", pattern = "ver(sion)?|info", perm = "bglib.info")
public class BGCVersion implements BitzCommand {

	public boolean execute(CommandSender sender, String[] args, BitzPlugin plugin) {

		plugin.messenger.playerInfo(sender, "You are running BGLib v" + plugin.getVersion());

		return true;
	}

}
