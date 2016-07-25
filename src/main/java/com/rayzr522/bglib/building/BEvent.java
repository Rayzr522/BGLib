
package com.rayzr522.bglib.building;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import com.rayzr522.bglib.handling.MinigameHandler;
import com.rayzr522.bitzapi.utils.MapUtils;

/**
 * A minigame building block the is triggered by an event
 * 
 * @author PeterBlood
 */
public abstract class BEvent {

	/**
	 * Warning: if you set {@code eventClass} to things that are called often (e.g. 
	 * {@link PlayerMoveEvent}{@code .class}) then this can quickly cause lag.
	 * 
	 */
	protected Class<? extends Event>	eventClass;
	protected HashMap<UUID, Boolean>	enabledPlayers;

	public BEvent(Class<? extends Event> eventClass) {

		this.eventClass = eventClass;
		this.enabledPlayers = MapUtils.<UUID, Boolean> empty();

	}

	/**
	 * Called in {@link MinigameHandler} when an event of the type specified is
	 * called
	 * 
	 * @param event
	 *            the event itself
	 */
	public abstract void call(Event event);

	public Class<? extends Event> getEventClass() {
		return eventClass;
	}

	public boolean isEventClass(Event e) {

		return e.getClass() == eventClass;

	}

}