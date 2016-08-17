
package com.rayzr522.bglib.handling;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.rayzr522.bglib.building.BEvent;
import com.rayzr522.bglib.minigame.Minigame;
import com.rayzr522.bglib.plugin.MinigamePlugin;
import com.rayzr522.bitzapi.utils.data.ListUtils;

public class MinigameHandler implements Listener {

	private Minigame						minigame;
	private MinigameExecutor				executor;
	private MinigamePlugin					plugin;
	private List<Class<? extends Event>>	registeredEvents	= ListUtils.<Class<? extends Event>> empty();

	public MinigameHandler(Minigame minigame, MinigamePlugin plugin) {

		this.plugin = plugin;

		this.minigame = minigame;
		this.executor = new MinigameExecutor(this);

	}

	public void event(Event event) {

		if (registeredEvents.contains(event.getClass())) {

			for (BEvent eventBlock : minigame.getEventBlocks(event.getClass())) {

				if (!eventBlock.isEventClass(event)) {
					continue;
				}

				eventBlock.call(event);

			}

		}

	}

	public List<Class<? extends Event>> getRegisteredEvents() {
		return registeredEvents;
	}

	public void registerEvents(List<Class<? extends Event>> eventClasses) {

		eventClasses.stream().filter(clazz -> !registeredEvents.contains(clazz)).forEach((clazz) -> {

			registeredEvents.add(clazz);
			Bukkit.getServer().getPluginManager().registerEvent(clazz, executor, EventPriority.HIGH, executor, plugin);

		});

	}

}
