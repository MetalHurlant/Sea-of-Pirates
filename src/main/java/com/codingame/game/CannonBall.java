package com.codingame.game;

import com.codingame.gameengine.module.entities.Curve;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Sprite;

import static com.codingame.game.Constants.*;
import static java.lang.Math.*;

public class CannonBall extends MovableObject
{
    Sprite sprite;
    int x, y;
    int turn = 0;
    Vector direction;

    public CannonBall(Ship ship, Side side, GraphicEntityModule gem)
    {
        double rotation = Helpers.toRadian(ship.getRotation() + 180);
        double rotationAdjusted = Helpers.toRadian(ship.getRotation() - 15 + 180);
        if (side == Side.LEFT) {
            rotation = Helpers.toRadian(ship.getRotation());
            rotationAdjusted = Helpers.toRadian(ship.getRotation() + 15);
        }

        x = ship.getX() + (int)(SCALE * 32 * cos(rotationAdjusted));
        y = ship.getY() + (int)(SCALE * 32 * sin(rotationAdjusted));

        direction = new Vector(cos(rotation), sin(rotation));

        sprite = gem.createSprite().setImage(Constants.CONNONBALL_SPRITE)
                .setZIndex(200)
                .setX(x)
                .setY(y)
                .setAnchor(.5)
                .setScale(SCALE * getScale());
    }

    public void move()
    {
        x += (int)(SCALE * CANNONBALL_SPEED * direction.x);
        y += (int)(SCALE * CANNONBALL_SPEED * direction.y);
        turn++;
    }

    public void update()
    {
        sprite.setX(x, Curve.LINEAR);
        sprite.setY(y, Curve.LINEAR);
        double scale = getScale();
        sprite.setScale(SCALE * scale, Curve.EASE_IN_AND_OUT);
    }

    public boolean shouldBeRemoved()
    {
        if (turn >= CANNONBALL_RANGE_TURN) {
            sprite.setAlpha(0, Curve.IMMEDIATE);
            return true;
        }
        return false;
    }

    private double getScale()
    {
        return turn < CANNONBALL_RANGE_TURN ? 1 : .5;
    }
}
