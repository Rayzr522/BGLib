
package com.rayzr522.bglib.minigame;

import com.rayzr522.bglib.BGLib;
import com.rayzr522.bglib.building.BEvent;
import com.rayzr522.bglib.building.BGameplay;

public class MinigameBuilder {

	private Minigame		minigame;
	@SuppressWarnings("unused")
	private MinigameConfig	config;

	public MinigameBuilder(Minigame minigame, MinigameConfig config) {

		this.minigame = minigame;
		this.config = config;

		if (config.XP_BAR_TIMER) {

			BGLib.registerToTimer(minigame.getPlugin());

		}

	}

	public MinigameBuilder add(BEvent bEvent) {

		minigame.addBEvent(bEvent);

		return this;

	}

	public MinigameBuilder add(BGameplay bGameplay) {

		minigame.addBGameplay(bGameplay);

		return this;

	}

	public Minigame build() {

		return minigame;

	}

}
