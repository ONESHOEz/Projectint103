package com.example.core.services;

import com.example.core.domain.abstracts.Monster;
import com.example.core.repositories.MonsterRepository;

import java.util.ArrayList;
import java.util.List;

public class MonsterService {
    private final MonsterRepository monsterRepository;

    public MonsterService(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    public ArrayList<Monster> getAllMonsters() {
        return monsterRepository.getMonsters();
    }

}
