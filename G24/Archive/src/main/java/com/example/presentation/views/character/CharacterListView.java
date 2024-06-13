package com.example.presentation.views.character;

import com.example.core.domain.Character;
import com.example.presentation.views.View;

import java.util.ArrayList;

public class CharacterListView implements View {
    private ArrayList<Character> characters;

    public CharacterListView() {
        characters = new ArrayList<>();
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    @Override
    public void display() {
        System.out.println("characters list");
        System.out.println("Enter '0' for new character");
        String leftAlignFormat = "| %-5d | %-15s | %-7d | %-5d |%n";

        System.out.format("+-------+-----------------+---------+-------+%n");
        System.out.format("| Index | Name            | Health  | Level |%n");
        System.out.format("+-------+-----------------+---------+-------+%n");

        for (int i = 0; i < characters.size(); i++) {
            System.out.format(leftAlignFormat, (i+1), characters.get(i).getName(), characters.get(i).getHealth(), characters.get(i).getLevel());
        }
        System.out.format("+-------+-----------------+---------+-------+%n");
        System.out.print("Please enter your number: ");
    }
}
