
package com.rayzr522.bglib.minigame;

import java.util.UUID;

import com.rayzr522.bglib.arena.Arena;
import com.rayzr522.bglib.arena.player.APlayer;

public class MinigameArena extends Arena {

    public MinigameArena(String name, Minigame minigame) {
        super(name, minigame);
    }

    public MinigameArena(UUID id, String name, Minigame minigame) {
        super(id, name, minigame);
    }

    @Override
    public void rewardPlayer(APlayer aplayer) {

    }

    @Override
    public void onPlayerJoin(APlayer aplayer) {

        aplayer.getPlayer().teleport(lobbySpawn);

    }

    @Override
    public void onPlayerLeave(APlayer aplayer) {

        aplayer.getPlayer().teleport(lobbySpawn.getWorld().getSpawnLocation());
        rewardPlayer(aplayer);

    }

    @Override
    public void onPlayerKick(APlayer aplayer) {

    }

}
