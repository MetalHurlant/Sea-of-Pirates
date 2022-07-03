package com.codingame.game;

public abstract class MovableObject {
    protected Vector position = new Vector();
    protected Vector speed = new Vector();
    protected Vector acceleration = new Vector();
    protected Integer rotation = 0;
    protected Integer rotationSpeed = 0;
    protected Integer rotationAcceleration = 0;

    protected MovableObject() {}
    
    protected MovableObject setX(int x) { this.position.x = x; return this;}
    protected MovableObject setY(int y) { this.position.y = y; return this;}
    protected MovableObject setVX(int vX) { this.speed.x = vX; return this;}
    protected MovableObject setVY(int vY) { this.speed.y = vY; return this;}
    protected MovableObject setAX(int aX) { this.acceleration.x = aX; return this;}
    protected MovableObject setAY(int aY) { this.acceleration.y = aY; return this;}
    protected MovableObject setPosition(Vector position) { this.position = position; return this;}
    protected MovableObject setSpeed(Vector speed) { this.speed = speed; return this;}
    protected MovableObject setAcceleration(Vector acceleration) { this.acceleration = acceleration; return this;}
    protected MovableObject setRotation(Integer rotation) { this.rotation = rotation; return this;}
    protected MovableObject setRotationSpeed(Integer rotationSpeed) { this.rotationSpeed = rotationSpeed; return this;}
    protected MovableObject setRotationAcceleration(Integer rotationAcceleration) { this.rotationAcceleration = rotationAcceleration; return this;}

    protected void nextTurn() {
        position.add(speed);
        speed.add(acceleration);
        rotation += rotationSpeed;
        rotationSpeed += rotationAcceleration;
    }
}
