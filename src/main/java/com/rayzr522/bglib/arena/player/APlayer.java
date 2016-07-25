
package com.rayzr522.bglib.arena.player;

import org.bukkit.entity.Player;

import com.rayzr522.bitzapi.utils.data.PlayerData;

public class APlayer {

	private Player		player;
	private PlayerData	data;

	public APlayer(Player player) {

		this.player = player;
		this.data = new PlayerData(player);

	}

	public PlayerData storeData() {

		data.restore();

		return data;

	}

	public void clearPlayer() {
		data.clearPlayer();
	}

	public void restoreData() {

		if (!data.isStored()) {

		return;

		}

		data.restore();

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayerData getData() {
		return data;
	}

	public void setData(PlayerData data) {
		this.data = data;
	}

}
