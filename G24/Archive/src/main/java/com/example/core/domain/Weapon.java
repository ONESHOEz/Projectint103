package com.example.core.domain;

import java.io.Serializable;

public class Weapon implements Serializable {
    private String name;
    private int damage;

    public Weapon() {}

    public int getDamage() {
        return damage;
    }
}