package org.example;

import java.util.List;
import java.util.Scanner;

public abstract class Ship extends Point{
    private String name;
    private int size;
    private int hits;
    private boolean sunk;
    private Point start;
    private Point end;
    private CardinalPoints direction;
    private List<Point> positions;
    private List<Point> occupiedPositions;

    public Ship(int size, Point start, Point end) {
        super(start.getX(), start.getY());
        this.size = size;
        this.start = start;
        this.end = end;
        this.hits = 0;
        this.sunk = false;
        if (start.getX() == end.getX()) {
            if (start.getY() > end.getY()) {
                this.direction = CardinalPoints.NORTH;
            } else {
                this.direction = CardinalPoints.SOUTH;
            }
        } else if (start.getY() == end.getY()) {
            if (start.getX() > end.getX()) {
                this.direction = CardinalPoints.WEST;
            } else {
                this.direction = CardinalPoints.EAST;
            }
        } else {
            throw new IllegalArgumentException("Ship must be horizontal or vertical");
        }
    }

    public boolean isSunk() {
        if (hits >= size && !sunk) {
            sunk = true;
            System.out.println(name + " has been sunk!");
        }
        return sunk;
    }
    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }



    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getHits() {
        return hits;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public CardinalPoints getDirection() {
        return direction;
    }

    public void setDirection(CardinalPoints direction) {
        this.direction = direction;
    }

    public void hit() {
        hits++;
        if (hits == size) {
            sunk = true;
        }
    }

    public boolean getShot(Point shotPoint) {
        if (shotPoint.equals(start) || shotPoint.equals(end)) {
            hit();
            return true;
        } else if (direction == CardinalPoints.NORTH || direction == CardinalPoints.SOUTH) {
            if (shotPoint.getX() == start.getX() && shotPoint.getY() >= start.getY() && shotPoint.getY() <= end.getY()) {
                hit();
                return true;
            }
        } else if (direction == CardinalPoints.EAST || direction == CardinalPoints.WEST) {
            if (shotPoint.getY() == start.getY() && shotPoint.getX() >= start.getX() && shotPoint.getX() <= end.getX()) {
                hit();
                return true;
            }
        }
        return false;
    }
    public static void placeShips(Scanner scanner, Board board) {
        int numShips = 0;
        while (numShips < 3) {
            System.out.println("Ingrese las coordenadas de inicio del barco #" + (numShips+1) + ":");
            System.out.print("Coordenada de inicio (fila,columna): ");
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();

            System.out.print("Tamaño del barco (1, 3 o 5): ");
            String size = scanner.next();
            if (!size.equals("1") && !size.equals("3") && !size.equals("5")) {
                throw new IllegalArgumentException("Tamaño de barco inválido, debe ser 1, 3 o 5.");
            }

            System.out.print("Orientación (n/s/e/o): ");
            String orientation = scanner.next();

            int endX = startX;
            int endY = startY;
            switch (orientation) {
                case "n":
                    endX -= Integer.parseInt(size) - 1;
                    break;
                case "s":
                    endX += Integer.parseInt(size) - 1;
                    break;
                case "e":
                    endY += Integer.parseInt(size) - 1;
                    break;
                case "o":
                    endY -= Integer.parseInt(size) - 1;
                    break;
                default:
                    throw new IllegalArgumentException("Orientación inválida, debe ser n, s, e o o.");
            }

            Point startPoint = new Point(startX, startY);
            Point endPoint = new Point(endX, endY);

            Ship newShip;
            String shipType;
            switch (size) {
                case "1":
                    newShip = new Canoe(startPoint);
                    shipType = "C";
                    break;
                case "3":
                    newShip = new Frigate(startPoint, endPoint);
                    shipType = "F";
                    break;
                case "5":
                    newShip = new Battleship(startPoint, endPoint);
                    shipType = "B";
                    break;
                default:
                    throw new IllegalArgumentException("Tamaño de barco inválido, debe ser 1, 3 o 5.");
            }

            boolean success = board.addShip(newShip);
            if (success) {
                numShips++;
                System.out.println("Barco #" + numShips + " colocado exitosamente: " + shipType);
            } else{
                System.out.println("¡Error al colocar el barco! Inténtelo de nuevo.");
            }
        }
    }
}
