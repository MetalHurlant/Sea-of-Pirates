package com.codingame.game;

import com.codingame.gameengine.core.AbstractMultiplayerPlayer;

public class Player extends AbstractMultiplayerPlayer {

    Ship ship;
    ShipEntity shipEntity;

    @Override
    public int getExpectedOutputLines() {
        return 1;
    }
    public static void main(String[] args) { }
}