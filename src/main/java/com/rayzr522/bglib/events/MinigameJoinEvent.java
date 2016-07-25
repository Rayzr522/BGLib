
package com.rayzr522.bglib.events;

import com.rayzr522.bglib.arena.Arena;
import com.rayzr522.bglib.arena.player.APlayer;
import com.rayzr522.bitzapi.events.BitzEvent;

public class MinigameJoinEvent extends BitzEvent {

	private Arena	arena;
	private APlayer	player;

	public MinigameJoinEvent(Arena arena, APlayer player) {

		this.arena = arena;
		this.player = player;

	}

	public Arena getArena() {
		return arena;
	}

	public APlayer getPlayer() {
		return player;
	}

}
