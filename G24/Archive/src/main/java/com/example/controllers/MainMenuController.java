package com.example.controllers;

import com.example.controllers.interfaces.Controller;
import com.example.controllers.storage.StorageTypeMenuController;
import com.example.core.domain.exceptions.InvalidChoiceException;
import com.example.presentation.views.main.MainMenuView;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenuController implements Controller {
    private final Scanner scanner;
    private final MainMenuView view;
    private final ArrayList<Controller> controllers;
    private final StorageTypeMenuController storageTypeMenuController;

    public MainMenuController(ArrayList<Controller> controllers, StorageTypeMenuController storageTypeMenuController) {
        this.view = new MainMenuView();
        this.scanner = new Scanner(System.in);
        this.storageTypeMenuController = storageTypeMenuController;
        this.controllers = controllers;
    }

    @Override
    public Controller execute() {
        view.display();
        int choice = scanner.nextInt();
        int index = choice - 1;
        return switch (choice) {
            case 1, 2 -> {
                storageTypeMenuController.withCallback(controllers.get(index));
                yield storageTypeMenuController;
            }
            case 3 -> controllers.get(index);
            case 4 -> null;
            default -> throw new InvalidChoiceException("Invalid choice");
        };
    }
}
