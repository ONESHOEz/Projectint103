package com.example.core.domain.abstracts;

public abstract class Monster {
    private int health;
    private final String name;
    private final int recommendLevel;
    private final int damage;
    private final int experience;

    public Monster(int health, String name, int recommendLevel, int damage, int experience) {
        this.health = health;
        this.name = name;
        this.recommendLevel = recommendLevel;
        this.damage = damage;
        this.experience = experience;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getExperience() {
        return experience;
    }

    public int getRecommendLevel() {
        return recommendLevel;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract int skill();
    public abstract int ultimate();
}
