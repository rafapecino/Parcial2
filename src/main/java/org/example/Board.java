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
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 30; j++) {
                System.out.print(board[i][j] + " ");
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
                if (board[i][j] >= 'A' && board[i][j] < 'A' + numShips) {
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
        if (newShip.getDirection() == CardinalPoints.NORTH) {
            for (int i = newShip.getStart().getY(); i >= newShip.getEnd().getY(); i--) {
                if (board[newShip.getStart().getX()][i] != '-') {
                    return false;
                }
            }
            for (int i = newShip.getStart().getY(); i >= newShip.getEnd().getY(); i--) {
                board[newShip.getStart().getX()][i] = (char) ('A' + numShips);
            }
        } else if (newShip.getDirection() == CardinalPoints.SOUTH) {
            for (int i = newShip.getStart().getY(); i <= newShip.getEnd().getY(); i++) {
                if (board[newShip.getStart().getX()][i] != '-') {
                    return false;
                }
            }
            for (int i = newShip.getStart().getY(); i <= newShip.getEnd().getY(); i++) {
                board[newShip.getStart().getX()][i] = (char) ('A' + numShips);
            }
        } else if (newShip.getDirection() == CardinalPoints.WEST) {
            for (int i = newShip.getStart().getX(); i >= newShip.getEnd().getX(); i--) {
                if (board[i][newShip.getStart().getY()] != '-') {
                    return false;
                }
            }
            for (int i = newShip.getStart().getX(); i >= newShip.getEnd().getX(); i--) {
                board[i][newShip.getStart().getY()] = (char) ('A' + numShips);
            }
        } else if (newShip.getDirection() == CardinalPoints.EAST) {
            for (int i = newShip.getStart().getX(); i <= newShip.getEnd().getX(); i++) {
                if (board[i][newShip.getStart().getY()] != '-') {
                    return false;
                }
            }
            for (int i = newShip.getStart().getX(); i <= newShip.getEnd().getX(); i++) {
                board[i][newShip.getStart().getY()] = (char) ('A' + numShips);
            }
        }
        numShips++;
        return true;
    }

}

