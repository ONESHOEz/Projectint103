package com.example.controllers.auth;

import com.example.controllers.interfaces.Controller;
import com.example.core.domain.exceptions.UserNotFoundException;
import com.example.core.services.UserService;
import com.example.presentation.views.auth.LoginView;

import java.io.Console;

public class LoginController implements Controller {
    private final Console console;
    private final LoginView view;
    private final UserService userService;
    private final Controller nextController;

    public LoginController(Controller nextController, UserService userService) {
        this.console = System.console();
        this.view = new LoginView();
        this.nextController = nextController;
        this.userService = userService;
    }

    @Override
    public Controller execute() {
        try {
            view.display();
            String username = console.readLine("Username (Empty to back the main menu): ");
            if (username.isEmpty()) {
                return null;
            }
            char[] password = console.readPassword("Password: ");

            String strPassword = new String(password);

            boolean success = userService.login(username, strPassword);
            if (!success) {
                System.out.println("Username or password is incorrect");
                console.readLine();
                return this;
            }
            return nextController;
        } catch (UserNotFoundException e) {
            System.out.println("User not found Please try again.");
            return this;
        }
    }
}
