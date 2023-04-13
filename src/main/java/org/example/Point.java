package org.example;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point other) {
        int dx = x - other.getX();
        int dy = y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean isAdjacent(Point endPoint) {
        return distance(endPoint) == 1;
    }

    public boolean isBetween(Point endPoint, Point shotPoint) {
        return distance(shotPoint) + shotPoint.distance(endPoint) == distance(endPoint);
    }
}

