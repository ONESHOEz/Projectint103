package com.example.presentation.views.storage;

import com.example.presentation.views.View;

public class StorageTypeView implements View {
    public StorageTypeView() {}
    @Override
    public void display() {
        System.out.println("Select Storage Type");
        System.out.println("----------------------");
        System.out.println("(1) File");
        System.out.println("(2) Jdbc");
        System.out.println("----------------------");
        System.out.print("Please enter your number: ");
    }
}
