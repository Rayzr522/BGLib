
package com.rayzr522.bglib.arena;

import java.util.UUID;

public class Round {

    private boolean joinable;
    private int     currentTime;
    private int     roundLength;

    private UUID    id;

    /**
     * @param id
     *            the round id
     * @see Arena
     */
    public Round(UUID id) {

        this.id = id;

    }

    /**
     * @see Arena
     */
    public Round() {

        this(UUID.randomUUID());

    }

    /**
     * @return is the round joinable by a player
     */
    public boolean isJoinable() {
        return joinable;
    }

    /**
     * @param joinable
     *            set whether the round is joinable by a player
     */
    public void setJoinable(boolean joinable) {
        this.joinable = joinable;
    }

    /**
     * @return the currentTime
     */
    public int getCurrentTime() {
        return currentTime;
    }

    /**
     * Be careful with this since it actually changes the time elapsed in the
     * round. One use of this is to force-start a game by setting the time in
     * the lobby round e.g. <code>0s, 5s</code>
     * 
     * @param currentTime
     *            the currentTime to set
     */
    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;

    }

    /**
     * @return the roundLength
     */
    public int getRoundLength() {
        return roundLength;
    }

    /**
     * @param roundLength
     *            the roundLength to set
     */
    public void setRoundLength(int roundLength) {
        this.roundLength = roundLength;
    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

}
