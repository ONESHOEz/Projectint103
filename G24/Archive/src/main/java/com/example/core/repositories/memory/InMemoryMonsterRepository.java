package com.example.core.repositories.memory;

import com.example.core.domain.Dragon;
import com.example.core.domain.Slime;
import com.example.core.domain.abstracts.Monster;
import com.example.core.repositories.MonsterRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryMonsterRepository implements MonsterRepository {
    private final ArrayList<Monster> monsters;

    public InMemoryMonsterRepository() {
        // monsters
        Dragon redDragon = new Dragon(5000,  "Red Dragon", 100, 1000, 10000);
        Slime blueSlime = new Slime(30, "Blue Slime", 1, 8, 100);

        monsters = new ArrayList<Monster>(Arrays.asList(blueSlime, redDragon));

    }
    @Override
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
