package org.example;

import java.util.Arrays;

public class Canoe extends Ship{
    private boolean[] isolatedContainers;
    private int size;
    private int hits;
    private boolean sunk;
    private Point start;
    private Point end;
    private CardinalPoints direction;

    public Canoe(Point point) throws IllegalArgumentException {
        super(1, point, point);
        isolatedContainers = new boolean[1];
        Arrays.fill(isolatedContainers, false);
    }

    public boolean getShot(Point shotPoint) {
        boolean hit = super.getShot(shotPoint);
        if (hit) {
            int index = getPointIndex(shotPoint);
            isolatedContainers[index] = true;
        }
        return hit;
    }

    public boolean isSunk() {
        for (boolean container : isolatedContainers) {
            if (!container) {
                return false;
            }
        }
        return true;
    }

    private int getPointIndex(Point point) {
        int index = 0;
        if (getDirection() == CardinalPoints.NORTH) {
            index = getStart().getY() - point.getY();
        } else if (getDirection() == CardinalPoints.SOUTH) {
            index = point.getY() - getStart().getY();
        } else if (getDirection() == CardinalPoints.WEST) {
            index = getStart().getX() - point.getX();
        } else if (getDirection() == CardinalPoints.EAST) {
            index = point.getX() - getStart().getX();
        }
        return index;
    }

    public boolean[] getIsolatedContainers() {
        return isolatedContainers;
    }

    public void setIsolatedContainers(boolean[] isolatedContainers) {
        this.isolatedContainers = isolatedContainers;
    }

}
