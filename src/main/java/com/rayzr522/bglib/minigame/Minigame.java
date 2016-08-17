
package com.rayzr522.bglib.minigame;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.rayzr522.bglib.arena.Arena;
import com.rayzr522.bglib.building.BEvent;
import com.rayzr522.bglib.building.BGameplay;
import com.rayzr522.bglib.data.MinigameData;
import com.rayzr522.bglib.handling.MinigameHandler;
import com.rayzr522.bglib.plugin.MinigamePlugin;
import com.rayzr522.bitzapi.utils.TextUtils;
import com.rayzr522.bitzapi.utils.data.ListUtils;
import com.rayzr522.bitzapi.utils.data.MapUtils;

/**
 * 
 * @author Rayzr522
 * @see MinigamePlugin
 *
 */
public class Minigame {

	private List<BEvent>									eventBlocks		= ListUtils.empty();
	private HashMap<Class<? extends Event>, List<BEvent>>	mappedEvents	= MapUtils.empty();

	private List<BGameplay>									gameplayBlocks	= ListUtils.<BGameplay> empty();

	private List<Arena>										arenas			= ListUtils.<Arena> empty();

	private MinigamePlugin									plugin;
	private MinigameHandler									handler;
	private MinigameData									data;
	private MinigameConfig									minigameConfig;

	public Minigame(MinigamePlugin plugin, MinigameConfig config) {

		this.plugin = plugin;
		this.handler = new MinigameHandler(this, plugin);
		this.data = new MinigameData(plugin);
		this.minigameConfig = config;

	}

	public static MinigameBuilder builder(MinigamePlugin plugin, MinigameConfig config) {

		return new MinigameBuilder(new Minigame(plugin, config), config);

	}

	/**
	 * @param bEvent
	 *            the {@link BEvent} to add
	 */
	public void addBEvent(BEvent bEvent) {

		eventBlocks.add(bEvent);
		handler.registerEvents(bEvent.getEventClasses());

		for (Class<? extends Event> clazz : bEvent.getEventClasses()) {

			List<BEvent> events = mappedEvents.get(clazz);
			if (events == null) events = ListUtils.empty();

			events.add(bEvent);
			mappedEvents.put(clazz, events);

		}

	}

	public void addBGameplay(BGameplay bGameplay) {

		gameplayBlocks.add(bGameplay);

	}

	public Arena createArena(String name) {

		Arena arena = getArena(name);

		if (arena != null) { return arena; }

		arena = new MinigameArena(name, this);

		arenas.add(arena);

		return arena;

	}

	public Arena removeArena(String name) {

		Arena arena = getArena(name);
		arenas.remove(arena);
		return arena;

	}

	public Arena removeArena(UUID id) {

		Arena arena = getArena(id);
		arenas.remove(arena);
		return arena;

	}

	public Arena removeArena(Arena arena) {

		return arenas.remove(arena) ? arena : null;

	}

	public Arena getArena(Location loc) {

		for (Arena arena : arenas) {

			if (!arena.isSetup()) {
				continue;
			}

			if (arena.insideArena(loc)) { return arena; }

		}

		return null;

	}

	public Arena getArena(String name) {

		for (Arena arena : arenas) {

			if (TextUtils.equalsLowerCase(arena.getName(), name)) {

			return arena;

			}

		}

		return null;

	}

	public Arena getArena(UUID id) {

		for (Arena arena : arenas) {

			if (arena.getId() == id) {

			return arena;

			}

		}

		return null;

	}

	public Arena getArena(Player player) {

		for (Arena arena : arenas) {

			if (arena.isPlayerInArena(player)) {

			return arena;

			}

		}

		return null;

	}

	public List<BEvent> getEventBlocks(Class<? extends Event> eventClass) {

		return mappedEvents.get(eventClass);

	}

	public MinigameHandler getHandler() {
		return handler;
	}

	public HashMap<Class<? extends Event>, List<BEvent>> getMappedEvents() {
		return mappedEvents;
	}

	public void setMappedEvents(HashMap<Class<? extends Event>, List<BEvent>> mappedEvents) {
		this.mappedEvents = mappedEvents;
	}

	public List<Arena> getArenas() {
		return arenas;
	}

	public MinigamePlugin getPlugin() {
		return plugin;
	}

	public MinigameData getData() {
		return data;
	}

	public MinigameConfig getConfig() {
		return minigameConfig;
	}

	public void timerTick() {

		for (Arena arena : arenas) {

			arena.timerTick();

		}

	}

}
