
package com.rayzr522.bglib.handling;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

public class MinigameExecutor implements Listener, EventExecutor {

    private MinigameHandler handler;

    public MinigameExecutor(MinigameHandler handler) {

        this.handler = handler;

    }

    public void execute(Listener listener, Event event) throws EventException {

        if (listener != handler) {
            return;
        }

        handler.event(event);

    }

}
