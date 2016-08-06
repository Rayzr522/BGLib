
package com.rayzr522.bglib.messaging;

import org.bukkit.ChatColor;

import com.rayzr522.bitzapi.message.Message;
import com.rayzr522.bitzapi.message.Messenger;

public enum MGMessages {

	MUST_SELECT_ARENA(new Message("You have to select an arena first", Messenger.ERROR, true)),
	MGPLUGIN_ONLY_COMMAND(new Message("This plugin is using a minigame command, but the plugin is not a minigame plugin! Please conact an administrator", Messenger.ERROR, true)),

	ARENA_REGION_SET(new Message("The arena region for '%' has been set", Messenger.INFO, true)),
	LOBBY_REGION_SET(new Message("The lobby region for '%' has been set", Messenger.INFO, true)),
	PLAYER_SPAWNS_SET(new Message("The player spawnpoints for '%' have been set", Messenger.INFO, true)),
	LOBBY_SPAWN_SET(new Message("The lobby spawnpoint for '%' has been set", Messenger.INFO, true)),
	ARENA_CREATED(new Message("The arena '%' has been created and selected", Messenger.INFO, true)),
	NO_ARENA(new Message("The arena '%' does not exist!", Messenger.WARNING, false)),
	ARENA_REMOVED(new Message("The arena '%' has been removed", Messenger.INFO, true)),
	ARENA_RENAMED(new Message("Name set to '%'", Messenger.INFO, true)),
	ARENA_JOINED(new Message("You joined '%'", ChatColor.LIGHT_PURPLE, true)),
	ARENA_LEFT(new Message("You left '%'", ChatColor.LIGHT_PURPLE, true)),
	NOT_SETUP(new Message("The arena '%' is not setup yet", Messenger.WARNING, true)),
	NOT_IN_ARENA(new Message("You're not in an arena!", ChatColor.LIGHT_PURPLE, true));

	public final Message msg;

	MGMessages(Message msg) {
		this.msg = msg;
	}

}
