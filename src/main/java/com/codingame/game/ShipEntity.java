package com.codingame.game;

import com.codingame.gameengine.module.entities.Curve;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Group;
import com.codingame.gameengine.module.entities.Sprite;

import static com.codingame.game.Constants.SAILS;
import static com.codingame.game.Constants.SCALE;

public class ShipEntity {
    private final Ship ship;
    private final Sprite hull;
    private final Sprite sail;
    private final Group group;

    public ShipEntity(Ship ship, GraphicEntityModule gem)
    {
        this.ship = ship;
        double rotation = Helpers.toRadian(ship.getRotation());

        hull = gem.createSprite().setImage(String.format(Constants.SHIP_HULL_SPRITE, ship.getDamageLevel()))
                .setZIndex(2)
                .setAnchor(.5);

        Sprite cannonLeft = gem.createSprite().setImage(Constants.SHIP_CANNON_SPRITE)
                .setZIndex(3);

        Sprite cannonRight = gem.createSprite().setImage(Constants.SHIP_CANNON_SPRITE)
                .setZIndex(3)
                .setAnchorX(0)
                .setAnchorY(1)
                .setRotation(Helpers.toRadian(180));

        sail = gem.createSprite().setImage(String.format(Constants.SHIP_SAIL_SPRITE, SAILS[ship.getPlayerId()]))
                .setZIndex(4)
                .setAnchorX(.5)
                .setAnchorY(-0.5)
                .setScaleX(1)
                .setScaleY(.4)
                .setRotation(Helpers.toRadian(ship.getSailAngle()))
                .setY(-27);

        Sprite nest = gem.createSprite().setImage(Constants.SHIP_NEST_SPRITE)
                .setZIndex(5)
                .setY(-35)
                .setX(-10);

        group = gem.createGroup(hull, sail, cannonLeft, cannonRight, nest)
                .setX(ship.getX())
                .setY(ship.getY())
                .setRotation(rotation)
                .setScale(SCALE);
    }

    public ShipEntity update()
    {
        double rotation = Helpers.toRadian(ship.getRotation());
        double sailAngle = Helpers.toRadian(ship.getSailAngle());
        group
                .setX(ship.getX(), Curve.LINEAR)
                .setY(ship.getY(), Curve.LINEAR)
                .setRotation(rotation, Curve.LINEAR);
        hull.setImage(String.format(Constants.SHIP_HULL_SPRITE, ship.getDamageLevel()));
        sail.setRotation(sailAngle, Curve.LINEAR);
        return this;
    }
}
