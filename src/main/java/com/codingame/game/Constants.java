package com.codingame.game;

import com.codingame.gameengine.module.entities.World;

public class Constants {
    public static final int WIDTH = World.DEFAULT_WIDTH;
    public static final int HEIGHT = World.DEFAULT_HEIGHT;
    public static final int SCALE = 3;
    public static final String SHIP_HULL_SPRITE = "pirates/Sprites/Ship parts/hullLarge (%s).png";
    public static final String SHIP_CANNON_SPRITE = "pirates/Sprites/Ship parts/cannon.png";
    public static final String SHIP_SAIL_SPRITE = "pirates/Sprites/Ship parts/sailLarge (%s).png";
    public static final String SHIP_NEST_SPRITE = "pirates/Sprites/Ship parts/nest.png";
    public static final String CONNONBALL_SPRITE = "pirates/Sprites/Ship parts/cannonBall.png";
    public static final String BACKGROUND_SPRITE = "background.png";
    public static final String WINDSOCK_SPRITE = "windsock.png";
    public static final int MAX_WHEEL_ANGLE = 15;
    public static final int MAX_SAIL_ANGLE = 75;
    public static final Integer[] SAILS = {10, 12, 11, 13, 10, 12, 11, 13,};


    public static int CANNONBALL_SPEED = 50;
    public static int CANNONBALL_RANGE_TURN = 5;
}

enum Side {
    LEFT,
    RIGHT
}
