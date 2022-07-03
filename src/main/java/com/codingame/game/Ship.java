package com.codingame.game;


import static com.codingame.game.Constants.MAX_SAIL_ANGLE;

public class Ship extends MovableObject
{
    private final int playerId;
    private Integer life = 100;
    private Integer sailAngle = 0;
    private Integer wheelDirection = 0;

    public Ship(Integer playerId){ super(); this.playerId = playerId; }

    public void angleSail(int angle)
    {
        sailAngle += angle;
        if (sailAngle > MAX_SAIL_ANGLE) {
            sailAngle = MAX_SAIL_ANGLE;
        } else if (sailAngle < -MAX_SAIL_ANGLE) {
            sailAngle = -MAX_SAIL_ANGLE;
        }
    }

    public int getX() { return (int)position.x; }
    public int getY() { return (int)position.y; }
    public Integer getRotation() { return rotation; }

    public Integer getLife() { return life; }

    public Integer getSailAngle() { return sailAngle; }

    public Integer getDamageLevel() {
        if (life > 80) { return 1; }
        if (life > 50) { return 2; }
        if (life > 0) { return 3; }
        return 4;
    }

    public void damage(Integer damage) { life -= damage; }

    public String Info() {
        return String.format("%d %d %d %d %d", getX(), getY(), getRotation(), getSailAngle(), getLife());
    }

    public int getPlayerId() {
        return playerId;
    }
}
