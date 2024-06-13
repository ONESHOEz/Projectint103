package com.example.controllers.character;

import com.example.controllers.interfaces.Controller;
import com.example.core.domain.Character;
import com.example.core.services.UserService;
import com.example.presentation.views.character.EditCharacterView;

import java.util.Scanner;

public class NewCharacterController implements Controller {
    private final UserService userService;
    private final Scanner scanner;
    private final EditCharacterView view;
    private Controller callBackController;
    private Character character;
    public NewCharacterController(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
        this.view = new EditCharacterView();
    }

    public void withCallback(Controller controller) {
        this.callBackController = controller;
    }

    public void setCharacter(Character character) {
        if (this.character == null) {
            throw new IllegalArgumentException("Character cannot be null");
        }
        this.character =  character;

    }
    @Override
    public Controller execute() {
        view.display();
        String chracterName = scanner.nextLine();

        if (chracterName == null || chracterName.isEmpty()) {
            throw new IllegalArgumentException("Character name cannot be empty");
        }

        Character newChracter = new Character(chracterName);

        boolean success = userService.newCharacter(newChracter);

        if (!success) {
            throw new IllegalArgumentException("Character could not be created");
        }

        return callBackController;
    }
}
