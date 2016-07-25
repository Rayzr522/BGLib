
package com.rayzr522.bglib;

import java.util.List;

import com.rayzr522.bglib.plugin.MinigamePlugin;
import com.rayzr522.bitzapi.utils.data.ListUtils;

public class GlobalTimer implements Runnable {

	private static List<MinigamePlugin> registeredPlugins = ListUtils.<MinigamePlugin> empty();

	public void run() {

		tick();

	}

	private void tick() {

		for (MinigamePlugin pl : registeredPlugins) {

			pl.getMinigame().timerTick();

		}

	}

	public void registerPlugin(MinigamePlugin plugin) {
		if (plugin != null) {
			registeredPlugins.add(plugin);
		}
	}

}
