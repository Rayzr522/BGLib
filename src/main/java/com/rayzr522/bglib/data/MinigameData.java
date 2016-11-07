
package com.rayzr522.bglib.data;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.rayzr522.bglib.arena.Arena;
import com.rayzr522.bglib.plugin.MinigamePlugin;
import com.rayzr522.bitzapi.utils.data.MapUtils;

public class MinigameData {

    private HashMap<UUID, Arena> selectedArenas = MapUtils.<UUID, Arena> empty();
    private MinigamePlugin       plugin;

    public MinigameData(MinigamePlugin plugin) {

        this.plugin = plugin;

    }

    public Arena getSelectedArena(Player player) {

        return getSelectedArena(player.getUniqueId());

    }

    public Arena getSelectedArena(UUID uniqueId) {

        return selectedArenas.get(uniqueId);

    }

    public void setSelectedArena(Player player, Arena arena) {

        setSelectedArena(player.getUniqueId(), arena);

    }

    public void setSelectedArena(UUID uniqueId, Arena arena) {

        selectedArenas.put(uniqueId, arena);

    }

    public MinigamePlugin getPlugin() {
        return plugin;
    }

}
