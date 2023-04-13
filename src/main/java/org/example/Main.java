package org.example;
import java.util.Scanner;
import java.util.Random;

import static org.example.Ship.placeShips;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("¡Bienvenido al juego hundir la flota!");

        // Configuración del tablero y los barcos de los jugadores
        Board board1 = new Board();
        Board board2 = new Board();
        System.out.println("Jugador 1, coloque sus barcos:");
        placeShips(scanner, board1);
        System.out.println("Jugador 2, coloque sus barcos:");
        placeShips(scanner, board2);

        // Mostrar el tablero de los jugadores
        System.out.println("Tablero del jugador 1:");
        board1.printBoard();
        System.out.println("Tablero del jugador 2:");
        board2.printBoard();

        // Comienzo de la partida
        boolean gameOver = false;
        int currentPlayer = 1;
        while (!gameOver) {
            // Turno del jugador actual
            Board currentBoard = (currentPlayer == 1) ? board2 : board1;
            Board opponentBoard = (currentPlayer == 1) ? board1 : board2;
            System.out.println("Jugador " + currentPlayer + ", es su turno.");
            //pide por teclado las coordenadas de ataque
            System.out.print("Ingrese las coordenadas de ataque (fila,columna): ");
            Point shotPoint = new Point(scanner.nextInt() , scanner.nextInt() );
            int x = shotPoint.getX();
            int y = shotPoint.getY();
            System.out.println("Atacando en la posición " + x + "," + y + "...");
            boolean hit = opponentBoard.attack(shotPoint);
            if (hit) {
                System.out.println("¡Ha acertado en un barco enemigo!");
                Ship hitShip = opponentBoard.getShipAt(shotPoint);
                if (hitShip != null && hitShip.isSunk()) {
                    System.out.println("¡Ha hundido un barco enemigo!");
                }
                if (opponentBoard.allShipsSunk()) {
                    System.out.println("¡Jugador " + currentPlayer + " ha ganado!");
                    gameOver = true;
                }
            } else {
                System.out.println("¡Ha fallado!");
            }
            // Mostrar el tablero de los jugadores
            System.out.println("Tablero del jugador 1:");
            board1.printBoard();
            System.out.println("Tablero del jugador 2:");
            board2.printBoard();
            // Cambiar de jugador
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
    }
}

            /*System.out.print("Coordenada de fin (fila,columna): ");
            int endX = scanner.nextInt();
            int endY = scanner.nextInt();
            boolean success = board.addShip(3, startX, startY, endX, endY);
            if (success) {
                numShips++;
            } else {
                System.out.println("¡Error al colocar el barco! Inténtelo de nuevo.");
            }
        }*/



