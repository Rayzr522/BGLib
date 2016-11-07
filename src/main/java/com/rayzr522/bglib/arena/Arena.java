
package com.rayzr522.bglib.arena;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.rayzr522.bglib.arena.player.APlayer;
import com.rayzr522.bglib.events.MinigameJoinEvent;
import com.rayzr522.bglib.minigame.Minigame;
import com.rayzr522.bitzapi.config.DEPRECATED_Serializable;
import com.rayzr522.bitzapi.utils.data.ListUtils;
import com.rayzr522.bitzapi.world.Region;

/**
 * 
 * Abstract arena class for minigames with base functionally already written
 * 
 * @author Rayzr522
 *
 */
public abstract class Arena implements DEPRECATED_Serializable<Arena> {

    protected Minigame       minigame;

    protected List<Round>    rounds;
    protected int            currentRound = 0;

    protected List<APlayer>  players;
    protected UUID           id;

    protected Region         arenaRegion;
    protected Region         lobbyRegion;

    protected Location       lobbySpawn;
    protected List<Location> playerSpawns;

    protected boolean        setup        = false;
    protected String         name;

    /**
     * @param id
     *            arena UUID
     * @param name
     *            arena name
     * @param minigame
     *            parent minigame
     */
    public Arena(UUID id, String name, Minigame minigame) {

        this.id = id;
        this.name = name;
        this.minigame = minigame;

        this.rounds = ListUtils.<Round> empty();
        this.players = ListUtils.<APlayer> empty();

    }

    /**
     * @param name
     *            arena name
     * @param minigame
     *            parent minigame
     */
    public Arena(String name, Minigame minigame) {

        this(UUID.randomUUID(), name, minigame);

    }

    /**
     * @param player
     *            the player to join the arena
     * @return true if the player successfully joined, false if it didn't
     */
    public boolean join(Player player) {

        if (player == null) {
            return false;
        }

        APlayer aplayer = new APlayer(player);

        if (players.contains(aplayer)) {
            return false;
        }

        players.add(aplayer);
        Bukkit.getPluginManager().callEvent(new MinigameJoinEvent(this, aplayer));

        if (minigame.getConfig().SAVE_INVENTORY_ON_JOIN) {

            aplayer.storeData();

            if (minigame.getConfig().CLEAR_INVENTORY_ON_JOIN) {

                aplayer.clearPlayer();

            }

        }

        onPlayerJoin(aplayer);

        return true;

    }

    /**
     * @param used
     *            for child classes to do stuff when a player joins
     */
    public abstract void onPlayerJoin(APlayer aplayer);

    /**
     * Used for child classes to do stuff when a player joins an arena.
     * 
     * @param aplayer
     */
    public boolean leave(Player player) {

        if (player == null) {
            return false;
        }

        APlayer aplayer = getAPlayer(player);

        if (aplayer == null) {
            return false;
        }

        if (minigame.getConfig().RESTORE_INVENTORY_ON_JOIN) {

            aplayer.restoreData();

        }

        players.remove(aplayer);

        onPlayerLeave(aplayer);

        return true;

    }

    /**
     * Used for child classes to do stuff when a player leaves an arena.
     * 
     * @param aplayer
     */
    public abstract void onPlayerLeave(APlayer aplayer);

    /**
     * @param player
     * @return The APlayer associated with {@code player} or {@code null}
     */
    public APlayer getAPlayer(Player player) {

        for (APlayer aplayer : players) {

            if (aplayer.getPlayer().getUniqueId().equals(player.getUniqueId())) {

                return aplayer;

            }

        }

        return null;

    }

    /**
     * @param player
     * @return whether there is an {@code APlayer} instance in {@code players}
     *         that has a player with a matching UUID
     */
    public boolean isPlayerInArena(Player player) {

        return getAPlayer(player) == null;

    }

    /**
     * Rewards the player. NOTE: This will not automatically run. You have to
     * manually run this in {@code onPlayerLeave}. Also, don't put this in
     * {@code onPlayerKick} because that's usually only called in situations
     * where the server is shutting down, so we don't want to be managing
     * currency or items at that time
     * 
     * @param aplayer
     */
    public abstract void rewardPlayer(APlayer aplayer);

    public void kick() {

        for (APlayer aplayer : players) {

            onPlayerKick(aplayer);

        }

    }

    /**
     * Used for child classes to do stuff when a player is kicked from an arena.
     * 
     * @param aplayer
     */
    public abstract void onPlayerKick(APlayer aplayer);

    public void forceKick() {

    }

    public void kickAll() {

    }

    public void forceKickAll() {

    }

    private void checkSetup() {

        if (arenaRegion == null || lobbyRegion == null || lobbySpawn == null || playerSpawns == null) {
            setup = false;
            return;
        }
        setup = true;

    }

    public boolean isSetup() {

        checkSetup();
        return setup;

    }

    public List<APlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<APlayer> players) {
        this.players = players;
    }

    public UUID getId() {
        return id;
    }

    public Region getArenaRegion() {
        return arenaRegion;
    }

    public void setArenaRegion(Region aregion) {
        this.arenaRegion = aregion;
    }

    public Region getLobbyRegion() {
        return lobbyRegion;
    }

    public void setLobbyRegion(Region lregion) {
        this.lobbyRegion = lregion;
    }

    public Location getLobbySpawn() {
        return lobbySpawn;
    }

    public void setLobbySpawn(Location lobbySpawn) {
        this.lobbySpawn = lobbySpawn;
    }

    public List<Location> getPlayerSpawns() {
        return playerSpawns;
    }

    public void setPlayerSpawns(List<Location> playerSpawns) {
        this.playerSpawns = playerSpawns;
    }

    public boolean insideArena(Location loc) {
        return (arenaRegion == null ? false : arenaRegion.inside(loc) || lobbyRegion == null ? false : lobbyRegion.inside(loc));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Minigame getMinigame() {
        return minigame;
    }

    public void setMinigame(Minigame minigame) {
        this.minigame = minigame;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Map<String, Object> serialize() {
        return null;
    }

    public Arena deserialize(Map<String, Object> serialized) {
        return null;
    }

    public void timerTick() {

        if (currentRound < 0 || currentRound > rounds.size() - 1) {
            forceKickAll();

        }

    }

}
