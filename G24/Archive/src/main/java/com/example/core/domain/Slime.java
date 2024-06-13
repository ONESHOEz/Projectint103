package com.example.core.domain;

import com.example.core.domain.abstracts.Monster;

public class Slime extends Monster {

    public Slime(int health, String name, int recommendLevel, int damage, int experience) {
        super(health, name, recommendLevel, damage, experience);
    }

    @Override
    public int skill() {
        setHealth(getHealth() + 10);
        return 0;
    }

    @Override
    public int ultimate() {
        return getDamage() * 2;
    }
}
