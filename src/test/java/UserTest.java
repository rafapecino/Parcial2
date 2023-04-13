import org.example.CardinalPoints;
import org.example.Ship;
import org.example.User;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;

public class UserTest {
    @Test
    public void test() {

        // Carer barcos para el usuario
        ArrayList<Ship> ships = new ArrayList<Ship>();


                // Crear barcos para el usuario
                ArrayList<Ship> ships = new ArrayList<Ship>();
                Ship ship1 = new Ship(ShipType.FRIGATE, new Point(2, 3), CardinalPoints.EAST);
                Ship ship2 = new Ship(ShipType.SUBMARINE, new Point(6, 7), CardinalPoints.SOUTH);
                ships.add(ship1);
                ships.add(ship2);

                // Crear usuario y asignarle los barcos
                try {
                    User user = new User("John", ships);
                    System.out.println("Usuario creado correctamente");

                    // Comprobar getters
                    System.out.println("Nombre del usuario: " + user.getName());
                    System.out.println("Puntuación del usuario: " + user.getScore());
                    System.out.println("¿Está vivo el usuario?: " + user.isAlive());
                    System.out.println("Barcos del usuario: " + user.getShips());
                    System.out.println("Disparos restantes del usuario: " + user.getShotsLeft());
                    System.out.println("Número de impactos del usuario: " + user.getHits());
                    System.out.println("Número de fallos del usuario: " + user.getMisses());
                    System.out.println("Número de barcos hundidos del usuario: " + user.getSunkShips());

                    // Comprobar ataque a otro usuario
                    User otherUser = new User("Jane", new ArrayList<Ship>());
                    Point shotPoint = new Point(2, 3);
                    boolean hit = user.attack();
                    if (hit) {
                        System.out.println("¡Impacto en un barco enemigo!");
                    } else {
                        System.out.println("Disparo fallido");
                    }

                    // Comprobar recepción de disparo
                    shotPoint = new Point(5, 7);
                    boolean hitByEnemy = user.getShot(shotPoint);
                    if (hitByEnemy) {
                        System.out.println("¡Impacto recibido en uno de tus barcos!");
                    } else {
                        System.out.println("El enemigo falló su disparo");
                    }

                    // Comprobar si el usuario sigue vivo
                    if (user.isAlive()) {
                        System.out.println("El usuario sigue vivo");
                    } else {
                        System.out.println("El usuario ha muerto");
                    }

                    // Hundir barcos del usuario
                    user.getShot(new Point(2, 3));
                    user.getShot(new Point(3, 3));
                    if (user.isAlive()) {
                        System.out.println("El usuario sigue vivo");
                    } else {
                        System.out.println("El usuario ha muerto");
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
