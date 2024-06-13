package com.example.presentation.views.main;

import com.example.presentation.views.View;

import java.awt.*;
import java.util.ArrayList;

public class MainMenuView implements View {
    public MainMenuView() {}

    @Override
    public void display() {
        System.out.println("Welcome to the Primordial Fantasy!");
        System.out.println("----------------------");
        System.out.println("(1) Login");
        System.out.println("(2) Register");
        System.out.println("(3) Continue as Guest");
        System.out.println("(4) Exit");
        System.out.println("----------------------");
        System.out.print("Please enter your number: ");
    }


}
