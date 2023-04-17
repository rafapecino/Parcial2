package org.example;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @org.junit.jupiter.api.Test
    void attack() throws Exception {
        Point punto = new Point(0, 0);
        ArrayList<Ship> ships = new ArrayList<>();
        ships.add(new Canoe(new Point(3,3)));
        User user1 = new User("user1", ships);
        User user2 = new User("user2", ships);
        user1.attack(user2, punto);
    }

}