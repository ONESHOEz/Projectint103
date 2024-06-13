package com.example.presentation.views.game;

import com.example.core.domain.Character;
import com.example.core.domain.abstracts.Monster;
import com.example.presentation.views.View;

public class BattleView implements View {
    private Character character;
    private Monster monster;

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void winMessage(String s) {
        System.out.println(s + " won the battle!");
    }

    public void eventMessage(String turn,String message) {
        System.out.println(turn + ": " + message);
    }
    @Override
    public void display() {
        System.out.println("----------------------------------");
        System.out.println("Monster: " + monster.getName());
        System.out.println("Monster Health: " + monster.getHealth());
        System.out.println("----------------------------------");
        System.out.println("You: " + character.getName());
        System.out.println("You Health: " + character.getHealth());
        System.out.println("----------------------------------");
        System.out.println("(1) Attack");
        System.out.println("(2) Exit");
        System.out.print("Please enter your number: ");
    }
}
