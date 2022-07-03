package com.codingame.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.codingame.gameengine.core.AbstractMultiplayerPlayer;
import com.codingame.gameengine.core.AbstractPlayer.TimeoutException;
import com.codingame.gameengine.core.AbstractReferee;
import com.codingame.gameengine.core.GameManager;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.google.inject.Inject;

import static com.codingame.game.Constants.CANNONBALL_RANGE_TURN;
import static java.lang.Math.floor;

public class Referee extends AbstractReferee {
    @Inject private MultiplayerGameManager<AbstractMultiplayerPlayer> gameManager;
    @Inject private GraphicEntityModule graphicEntityModule;
    private Random random;

    Integer windAngle = 0, windForce = 0;

    ArrayList<CannonBall> cannonBalls = new ArrayList<>();

    @Override
    public void init() {
//        System.setProperty("league.level", "2"); // todo: remove

        random = new Random(gameManager.getSeed());
        gameManager.setFrameDuration(500);

        if (gameManager.getLeagueLevel() == 1) {
            gameManager.setMaxTurns(9);
        } else {
            windAngle = random.nextInt(360);
            windForce = 2 + random.nextInt(5);
            gameManager.setMaxTurns(9);
        }

        initWindow();

        initPlayers(gameManager.getLeagueLevel());
    }

    private void initWindow() {
        graphicEntityModule.createSprite().setImage(Constants.BACKGROUND_SPRITE);
        graphicEntityModule.createSprite().setImage(Constants.WINDSOCK_SPRITE)
                .setZIndex(100)
                .setScale(.25)
                .setAnchor(.5)
                .setRotation(windAngle)
                .setX(100)
                .setY(100);
        graphicEntityModule.createText(String.format("%d km/h", windForce*5))
                .setZIndex(101)
                .setScale(2)
                .setX(100)
                .setY(10);
    }

    private void initPlayers(Integer leagueLevel) {
        for (int i = 0; i < gameManager.getPlayerCount(); ++i) {
            Player player = (Player) gameManager.getPlayer(i);
            player.ship = new Ship(i);

            int x = 200 + 500 * (i % 4);
            int y = 200 + (int)floor((i)/4.)*500;

            player.ship.setPosition(new Vector(x, y));
            player.ship.setRotation(i*10);

            cannonBalls.add(new CannonBall(player.ship, Side.LEFT, graphicEntityModule));

            player.shipEntity = new ShipEntity(player.ship, graphicEntityModule);
        }
        sendGlobalInfo();
    }

    private void sendGlobalInfo() {
        for (int i = 0; i < gameManager.getPlayerCount(); ++i) {
            Player receiver = (Player) gameManager.getPlayer(i);
            sendInfoToPlayer(receiver);
        }
    }

    private void sendInfoToPlayer(Player receiver) {
        receiver.sendInputLine(String.valueOf(gameManager.getPlayerCount()));
        receiver.sendInputLine(String.valueOf(receiver.getIndex()));
        for (int i = 0; i < gameManager.getPlayerCount(); ++i) {
            Player player = (Player) gameManager.getPlayer(i);
            String[] playerInfo = playerInfo(player);
            for (String s : playerInfo) {
                receiver.sendInputLine(s);
            }
        }
    }

    private String[] playerInfo(Player player) {
        String[] info = new String[2];
        // <playerId>
        info[0] = String.valueOf(player.getIndex());
        // <ship.X> <ship.Y> <ship.rotation> <ship.sailRotation>
        info[1] = String.valueOf(player.ship.Info());
        return info;
    }

    @Override
    public void gameTurn(int turn) {
//        resetGameTurnData();

        for (int i = 0; i < gameManager.getPlayerCount(); ++i) {
            Player player = (Player) gameManager.getPlayer(i);
            player.ship.angleSail(10);
            sendInfoToPlayer(player);
            player.execute();
        }
        // Get output from players
        handlePlayerCommands();

        moveObjects();
        updateView();

        if (gameManager.getActivePlayers().size() < 2) {
            abort();
        }
    }

    private void handlePlayerCommands() {
        for (int i = 0; i < gameManager.getPlayerCount(); ++i) {
            Player player = (Player) gameManager.getPlayer(i);
            try {
                System.out.println(player.getOutputs());
//                handleCommands(player, player.getOutputs());
            } catch (TimeoutException e) {
                player.deactivate("Timeout!");
                gameManager.addToGameSummary(player.getNicknameToken() + " has not provided " + player.getExpectedOutputLines() + " lines in time");
            }
        }

    }

    private void moveObjects() {
        cannonBalls.removeIf(CannonBall::shouldBeRemoved);
        for (CannonBall cannonBall : cannonBalls) {
            cannonBall.move();
        }
    }

    private void updateView() {
        for (AtomicInteger i = new AtomicInteger(); i.get() < gameManager.getPlayerCount(); i.incrementAndGet()) {
            Player player = (Player) gameManager.getPlayer(i.get());
            player.shipEntity.update();
        }
        for (CannonBall cannonBall : cannonBalls) {
            cannonBall.update();
        }
    }

    private void abort() {
        gameManager.endGame();
    }
    private void endGame() {
        gameManager.endGame();

        Player p0 = (Player) gameManager.getPlayers().get(0);
        Player p1 = (Player) gameManager.getPlayers().get(1);
//        if (p0.getScore() > p1.getScore()) {
//            p1.hud.setAlpha(0.3);
//        }
//        if (p0.getScore() < p1.getScore()) {
//            p0.hud.setAlpha(0.3);
//        }
    }

    private void setWinner(Player player) {
        gameManager.addToGameSummary(GameManager.formatSuccessMessage(player.getNicknameToken() + " won!"));
        player.setScore(10);
        endGame();
    }
}
