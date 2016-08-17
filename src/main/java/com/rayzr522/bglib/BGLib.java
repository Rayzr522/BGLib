
package com.rayzr522.bglib;

import org.bukkit.Bukkit;

import com.rayzr522.bglib.commands.bglib.BGCVersion;
import com.rayzr522.bglib.plugin.MinigamePlugin;
import com.rayzr522.bitzapi.BitzPlugin;
import com.rayzr522.bitzapi.utils.Unicode;

public class BGLib extends BitzPlugin {

	public static BGLib			instance;
	private static GlobalTimer	timer;

	@Override
	public void onPluginLoad() {

		instance = this;

		messenger.setPrefix("&5" + Unicode.TRIANGLE_BLACK_LEFT + Unicode.BLOCK_THIN + " &dBGLib &5" + Unicode.BLOCK_THIN + Unicode.TRIANGLE_BLACK_RIGHT);

		timer = new GlobalTimer();
		Bukkit.getScheduler().runTaskTimer(this, timer, 0L, 20L);

	}

	@Override
	public void onPluginUnload() {

	}

	public void registerCommands() {

		commandHandler.registerCommand(BGCVersion.class);

	}

	public static void registerToTimer(MinigamePlugin plugin) {

		timer.registerPlugin(plugin);

	}

}
