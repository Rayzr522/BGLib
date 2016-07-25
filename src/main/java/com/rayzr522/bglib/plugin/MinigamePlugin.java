
package com.rayzr522.bglib.plugin;

import com.rayzr522.bglib.minigame.Minigame;
import com.rayzr522.bitzapi.BitzPlugin;
import com.rayzr522.bitzapi.utils.Unicode;

public class MinigamePlugin extends BitzPlugin {

	/**
	 * This NEEDS to be set up
	 */
	protected Minigame minigame;

	@Override
	public void onEnable() {
		super.onEnable();

		messenger.setPrefix("&5" + Unicode.TRIANGLE_BLACK_LEFT + Unicode.BLOCK_THIN + " &d*name* &5" + Unicode.BLOCK_THIN + Unicode.TRIANGLE_BLACK_RIGHT);

	}

	@Override
	public void onDisable() {
		super.onDisable();

	}

	public Minigame getMinigame() {
		return minigame;
	}

}
