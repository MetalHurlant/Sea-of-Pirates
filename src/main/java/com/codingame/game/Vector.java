package com.codingame.game;

public class Vector {
    public double x, y;
    public Vector() { this(0, 0); }
    public Vector(Vector c) { this(c.x, c.y); }

    public Vector(double x, double y) { this.x = x; this.y = y; }

    public Vector add(double x, double y) {
        this.x = this.x + x;
        this.y = this.y + y;

        return this;
    }
    
    public Vector add(Vector c) {
        return add(c.x, c.y);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector other = (Vector) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
