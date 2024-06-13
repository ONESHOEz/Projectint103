package com.example.core.domain;

import com.example.core.domain.abstracts.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
    private int id;
    private String name;
    private int health;
    private int level;
    private int baseDamage;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Character() {}

    public Character(String name) {
        this.name = name;
        this.level = 1;
        this.health = 100;
        this.baseDamage = 10;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public int getBaseDamage() {
        return baseDamage;
    }

}