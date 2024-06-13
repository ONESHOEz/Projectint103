package com.example.controllers.game;

import com.example.controllers.interfaces.Controller;
import com.example.core.domain.Character;
import com.example.core.domain.exceptions.InvalidChoiceException;
import com.example.core.services.UserService;
import com.example.presentation.views.game.GameMenuView;

import java.util.ArrayList;
import java.util.Scanner;

public class GameMenuController implements Controller {
    private final Scanner scanner;
    private final GameMenuView view;
    private final UserService userService;
    private final Controller characterListController;

    public GameMenuController(Controller characterListController, UserService userService) {
        this.view = new GameMenuView();
        this.userService = userService;
        this.scanner = new Scanner(System.in);
        this.characterListController = characterListController;
    }

    @Override
    public Controller execute() {
        if (userService.getUsername() == null) {
            boolean success = userService.guestLogin();

            if (!success) {
                throw new IllegalArgumentException("Create not continue with guest");
            }
        }

        ArrayList<Character> characters = userService.getCharacters();

        view.display();
        int choice = scanner.nextInt();
        return switch (choice) {
            case 1 -> characterListController;
            case 2 -> null;
            default -> throw new InvalidChoiceException("Invalid choice");
        };

    }

}
