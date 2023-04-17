package org.example;

import java.util.Arrays;

public class Frigate extends Ship {

        private boolean[] isolatedContainers;

        public Frigate(Point startPoint, Point endPoint){
            super(3, startPoint, endPoint);
            isolatedContainers = new boolean[3];
            Arrays.fill(isolatedContainers, false);
        }
        public Frigate(Point startPoint, Point endPoint, String name){
            super(3, startPoint, endPoint, name);
            isolatedContainers = new boolean[3];
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
}
