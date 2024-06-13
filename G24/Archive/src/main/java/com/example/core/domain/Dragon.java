package com.example.core.domain;

import com.example.core.domain.abstracts.Monster;

public class Dragon extends Monster {

    public Dragon(int health, String name, int recommendLevel, int damage, int experience) {
        super(health, name, recommendLevel, damage, experience);
    }

    @Override
    public int skill() {
        return getDamage() * 2;
    }

    @Override
    public int ultimate() {
        return getDamage() * 10;
    }
}