package com.example.presentation.views.game;

import com.example.presentation.views.View;

public class GameMenuView implements View {
    @Override
    public void display() {
        System.out.println("Game Menu");
        System.out.println("----------------------");
        System.out.println("(1) Start");
        System.out.println("(2) Sign Out");
        System.out.println("----------------------");
        System.out.print("Please enter your number: ");
    }
}
