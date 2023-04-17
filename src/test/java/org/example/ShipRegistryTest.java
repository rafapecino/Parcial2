package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShipRegistryTest {

    @Test
    void addShip() {
    }

    @Test
    void getShipByType() {
    }

    @Test
    void getShipByNum() {
    }

    @Test
    void getShipByName() {
        // Crear algunos barcos para la prueba
        Ship titanic = new Canoe(new Point(0, 0), new Point(1, 0), "Titanic");
        Ship queenMary = new Frigate(new Point(0, 0), new Point(3, 0), "Queen Mary");
        Ship bismarck = new Battleship(new Point(0, 0), new Point(5, 0), "Bismarck");

        // Agregar los barcos a la tabla de nombres
        Map<String, Map<String, Ship>> nameTable = new HashMap<>();
        Map<String, Ship> tNames = new HashMap<>();
        tNames.put("Titanic", titanic);
        nameTable.put("T", tNames);
        Map<String, Ship> qNames = new HashMap<>();
        qNames.put("Queen Mary", queenMary);
        nameTable.put("Q", qNames);
        Map<String, Ship> bNames = new HashMap<>();
        bNames.put("Bismarck", bismarck);
        nameTable.put("B", bNames);

        // Prueba 1: obtener el barco por su nombre
        assertEquals(titanic, getShipByName("Titanic"));
        assertEquals(queenMary, getShipByName("Queen Mary"));
        assertEquals(bismarck, getShipByName("Bismarck"));

        // Prueba 2: obtener un barco que no existe
        assertNull(getShipByName("Not a Ship"));

        // Prueba 3: verificar que la función sea sensible a las mayúsculas y minúsculas
        assertNull(getShipByName("titanic"));
        assertNull(getShipByName("QUEEN MARY"));
        assertNull(getShipByName("bISMARCK"));

        // Prueba 4: nombres de barcos con espacios
        assertEquals(queenMary, getShipByName("Queen Mary"));

        // Prueba 5: nombres de barcos que comienzan con diferentes letras
        assertEquals(titanic, getShipByName("Titanic"));
        assertEquals(queenMary, getShipByName("Queen Mary"));
        assertEquals(bismarck, getShipByName("Bismarck"));
    }

    private Ship getShipByName(String name) {
        return null;
    }


}