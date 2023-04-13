package org.example;
import java.util.HashMap;
import java.util.Map;

public class ShipRegistry {

    private Map<String, Map<Integer, Ship>> typeTable;
    private Map<Integer, Map<String, Ship>> numTable;
    private Map<String, Map<String, Ship>> nameTable;

    public ShipRegistry() {
        typeTable = new HashMap<>();
        numTable = new HashMap<>();
        nameTable = new HashMap<>();
    }

    public void addShip(Ship ship) {
        String type = ship.getClass().getSimpleName();
        int num = ship.hashCode();
        String name = ship.getName();

        // Add ship to type table
        if (!typeTable.containsKey(type)) {
            typeTable.put(type, new HashMap<>());
        }
        typeTable.get(type).put(num, ship);

        // Add ship to number table
        int numIndex = num % 15;
        if (!numTable.containsKey(numIndex)) {
            numTable.put(numIndex, new HashMap<>());
        }
        numTable.get(numIndex).put(name, ship);

        // Add ship to name table
        String nameIndex = name.substring(0, 1);
        if (!nameTable.containsKey(nameIndex)) {
            nameTable.put(nameIndex, new HashMap<>());
        }
        nameTable.get(nameIndex).put(name, ship);
    }

    public Ship getShipByType(String type, int num) {
        if (typeTable.containsKey(type)) {
            Map<Integer, Ship> numMap = typeTable.get(type);
            if (numMap.containsKey(num)) {
                return numMap.get(num);
            }
        }
        return null;
    }

    public Ship getShipByNum(int num, String name) {
        int numIndex = num % 15;
        if (numTable.containsKey(numIndex)) {
            Map<String, Ship> nameMap = numTable.get(numIndex);
            if (nameMap.containsKey(name)) {
                return nameMap.get(name);
            }
        }
        return null;
    }

    public Ship getShipByName(String name) {
        String nameIndex = name.substring(0, 1);
        if (nameTable.containsKey(nameIndex)) {
            Map<String, Ship> nameMap = nameTable.get(nameIndex);
            if (nameMap.containsKey(name)) {
                return nameMap.get(name);
            }
        }
        return null;
    }
}