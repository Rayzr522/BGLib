
package com.rayzr522.bglib.building;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import com.rayzr522.bglib.handling.MinigameHandler;
import com.rayzr522.bitzapi.utils.data.ListUtils;
import com.rayzr522.bitzapi.utils.data.MapUtils;

/**
 * A minigame building block the is triggered by an event
 * 
 * @author Rayzr522
 */
public abstract class BEvent {

	/**
	 * Warning: if you set {@code eventClass} to things that are called often
	 * (e.g. {@link PlayerMoveEvent}{@code .class}) then this can quickly cause
	 * lag.
	 * 
	 */
	protected List<Class<? extends Event>>	eventClasses;
	protected HashMap<UUID, Boolean>		enabledPlayers;

	public BEvent() {

		this.eventClasses = ListUtils.empty();
		this.enabledPlayers = MapUtils.empty();

	}

	public abstract void onCreate();

	public void addEvent(Class<? extends Event> clazz) {
		eventClasses.add(clazz);
	}

	public void removeEvent(Class<? extends Event> clazz) {
		eventClasses.remove(clazz);
	}

	/**
	 * Called in {@link MinigameHandler} when an event of the type specified is
	 * called
	 * 
	 * @param event
	 *            the event itself
	 */
	public abstract void call(Event event);

	public List<Class<? extends Event>> getEventClasses() {
		return eventClasses;
	}

	public boolean isEventClass(Event e) {

		return eventClasses.contains(e.getClass());

	}

}