package org.example;

import java.util.Scanner;

public class Board {
    private char[][] board;
    private int numShips;

    public Board() {
        board = new char[30][30];
        numShips = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < 30; i++) {
            System.out.print((char) (i + 65) + " ");
        }
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print((i) + " ");
            for (int j = 0; j < 30; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printAtBoard() {
        System.out.print("  ");
        for (int i = 0; i < 30; i++) {
            System.out.print((char) (i + 65) + " ");
        }
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print((i) + " ");
            for (int j = 0; j < 30; j++) {
                if (board[i][j] == 'X' || board[i][j] == 'O') {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public boolean attack(Point attackPoint) {
        if (board[attackPoint.getX()][attackPoint.getY()] >= 'A' && board[attackPoint.getX()][attackPoint.getY()] < 'A' + numShips) {
            board[attackPoint.getX()][attackPoint.getY()] = 'X';
            return true;
        } else if (board[attackPoint.getX()][attackPoint.getY()] == '-') {
            board[attackPoint.getX()][attackPoint.getY()] = 'O';
            return false;
        } else {
            return false;
        }

    }

    public boolean allShipsSunk() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (board[i][j] == 'C' || board[i][j] == 'B' || board[i][j] == 'F') {
                    return false;
                }
            }
        }
        return true;
    }



    public boolean addShip(Ship newShip) {
        if (numShips >= 3) {
            return false;
        }
        char shipSymbol;
        if (newShip instanceof Canoe) {
            shipSymbol = 'C';
        } else if (newShip instanceof Frigate) {
            shipSymbol = 'F';
        } else if (newShip instanceof Battleship) {
            shipSymbol = 'B';
        } else {
            shipSymbol = '-';
        }
        if (newShip.getDirection() == CardinalPoints.NORTH) {
            for (int i = newShip.getStart().getY(); i >= newShip.getEnd().getY(); i--) {
                if (board[newShip.getStart().getX()][i] != '-') {
                    return false;
                }
            }
            for (int i = newShip.getStart().getY(); i >= newShip.getEnd().getY(); i--) {
                board[newShip.getStart().getX()][i] = shipSymbol;
            }
        } else if (newShip.getDirection() == CardinalPoints.SOUTH) {
            for (int i = newShip.getStart().getY(); i <= newShip.getEnd().getY(); i++) {
                if (board[newShip.getStart().getX()][i] != '-') {
                    return false;
                }
            }
            for (int i = newShip.getStart().getY(); i <= newShip.getEnd().getY(); i++) {
                board[newShip.getStart().getX()][i] = shipSymbol;
            }
        } else if (newShip.getDirection() == CardinalPoints.WEST) {
            for (int i = newShip.getStart().getX(); i >= newShip.getEnd().getX(); i--) {
                if (board[i][newShip.getStart().getY()] != '-') {
                    return false;
                }
            }
            for (int i = newShip.getStart().getX(); i >= newShip.getEnd().getX(); i--) {
                board[i][newShip.getStart().getY()] = shipSymbol;
            }
        } else if (newShip.getDirection() == CardinalPoints.EAST) {
            for (int i = newShip.getStart().getX(); i <= newShip.getEnd().getX(); i++) {
                if (board[i][newShip.getStart().getY()] != '-') {
                    return false;
                }
            }
            for (int i = newShip.getStart().getX(); i <= newShip.getEnd().getX(); i++) {
                board[i][newShip.getStart().getY()] = shipSymbol;
            }
        }
        numShips++;
        return true;
    }


    public Ship getShipAt(Point shotPoint) {
        if (board[shotPoint.getX()][shotPoint.getY()] >= 'A' && board[shotPoint.getX()][shotPoint.getY()] < 'A' + numShips) {
            char shipChar = board[shotPoint.getX()][shotPoint.getY()];
            Point startPoint = null;
            Point endPoint = null;
            CardinalPoints direction = null;
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if (board[i][j] == shipChar) {
                        if (startPoint == null) {
                            startPoint = new Point(i, j);
                        } else {
                            endPoint = new Point(i, j);
                        }
                    }
                }
            }
            if (startPoint != null && endPoint != null) { // Comprobación de nulidad
                if (startPoint.getX() == endPoint.getX()) {
                    if (startPoint.getY() > endPoint.getY()) {
                        direction = CardinalPoints.NORTH;
                    } else {
                        direction = CardinalPoints.SOUTH;
                    }
                } else {
                    if (startPoint.getX() > endPoint.getX()) {
                        direction = CardinalPoints.WEST;
                    } else {
                        direction = CardinalPoints.EAST;
                    }
                }
                if (shipChar == 'F') {
                    return new Frigate(startPoint, endPoint);
                } else if (shipChar == 'B') {
                    return new Battleship(startPoint, endPoint);
                } else if (shipChar == 'C') {
                    return new Canoe(startPoint, endPoint);
                }
            }
        }
        return null;
    }

}

