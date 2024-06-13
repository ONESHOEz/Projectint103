package com.example.presentation.views.monster;

import com.example.core.domain.abstracts.Monster;
import com.example.presentation.views.View;

import java.util.ArrayList;

public class MonsterListView implements View {
    private ArrayList<Monster> monsters;

    public MonsterListView() {
        monsters = new ArrayList<>();
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    @Override
    public void display() {
        System.out.println("monsters list");
        String leftAlignFormat = "| %-5d | %-15s | %-7d | %-15d |%n";

        System.out.format("+-------+-----------------+---------+-----------------+%n");
        System.out.format("| Index | Name            | Health  | Recommend Level |%n");
        System.out.format("+-------+-----------------+---------+-----------------+%n");

        for (int i = 0; i < monsters.size(); i++) {
            System.out.format(leftAlignFormat, (i+1), monsters.get(i).getName(), monsters.get(i).getHealth(), monsters.get(i).getRecommendLevel());
        }
        System.out.format("+-------+-----------------+---------+-----------------+%n");
        System.out.print("Please enter your number: ");
    }
}
