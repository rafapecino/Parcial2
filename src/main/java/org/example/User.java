package org.example;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String name;
    private int score;
    private boolean is_alive;
    private ArrayList<Ship> ships;
    private int shotsLeft;
    private int hits;
    private int misses;
    private int sunkShips;
    private ArrayList<Point> used_points;
    private Scanner sc;

    public User(String name, ArrayList<Ship> ships) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name for user.");
        }
        if (ships == null || ships.isEmpty()) {
            throw new IllegalArgumentException("User must have at least one ship.");
        }
        for (Ship ship : ships) {
            if (ship == null) {
                throw new IllegalArgumentException("Invalid ship object in ships list.");
            }
        }
        this.name = name;
        this.score = 0;
        this.is_alive = true;
        this.ships = ships;
        this.shotsLeft = 0;
        this.hits = 0;
        this.misses = 0;
        this.sunkShips = 0;
        this.used_points = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean isAlive() {
        return is_alive;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public int getShotsLeft() {
        return shotsLeft;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getSunkShips() {
        return sunkShips;
    }

    public boolean attack(User user, Point attack_point) throws Exception {
        if (!this.is_alive) {
            throw new Exception("El usuario ya está muerto");
        }
        if (!user.is_alive) {
            throw new Exception("El usuario objetivo ya está muerto");
        }

        // Solicitamos las coordenadas de ataque al usuario y las almacenamos en una variable Point
        System.out.print("Ingrese las coordenadas de ataque (fila,columna): ");

        // Verificamos si el punto de ataque ya fue utilizado anteriormente
        if (this.used_points.contains(attack_point)) {
            System.out.println("Ya se ha atacado ese punto anteriormente. Intente nuevamente.");
            return false;
        } else {
            this.used_points.add(attack_point);
        }

        // Realizamos el ataque
        if (user.getShot(attack_point)) {
            this.score += 10;
            this.hits++;
            if (user.getSunkShips() == 0) {
                user.die();
                this.score += 100;
            }
            return true;
        } else {
            this.misses++;
            this.shotsLeft--;
            return false;
        }
    }


    public boolean getShot(Point shot_point) {
        for (Ship ship : ships) {
            if (ship.getShot(shot_point)) {
                return true;
            }
        }
        return false;
    }

    public void die() {
        this.is_alive = false;
    }


}