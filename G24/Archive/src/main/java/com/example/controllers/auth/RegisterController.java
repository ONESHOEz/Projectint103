package com.example.controllers.auth;

import com.example.controllers.interfaces.Controller;
import com.example.core.services.UserService;
import com.example.presentation.views.auth.RegisterView;

import java.io.Console;

public class RegisterController implements Controller {
    private final Console console;
    private final RegisterView view;
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.console = System.console();
        this.view = new RegisterView();
        this.userService = userService;
    }

    @Override
    public Controller execute() {
        view.display();
        String username = console.readLine("Username (Empty to back the main menu): ");
        if (username.isEmpty()) {
            return null;
        }
        char[] password = console.readPassword("Password: ");
        char[] repeatPassword = console.readPassword("Repeat Password: ");

        String passwordString = new String(password);
        String repeatPasswordString = new String(repeatPassword);

        if (!passwordString.equals(repeatPasswordString)) {
            return this;
        }

        userService.register(username, passwordString);
        return null;
    }
}
