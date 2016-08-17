
package com.rayzr522.bglib.plugin;

import com.rayzr522.bglib.minigame.Minigame;
import com.rayzr522.bitzapi.BitzPlugin;

public abstract class MinigamePlugin extends BitzPlugin {

	/**
	 * This NEEDS to be set up
	 */
	protected Minigame minigame;

	public Minigame getMinigame() {
		return minigame;
	}

}
