package com.example.controllers.storage;

import com.example.controllers.interfaces.CallbackableController;
import com.example.controllers.interfaces.Controller;
import com.example.core.repositories.file.FileUserRepository;
import com.example.core.repositories.jdbc.JdbcUserRepository;
import com.example.core.services.UserService;
import com.example.presentation.views.storage.StorageTypeView;

import java.util.Scanner;

public class StorageTypeMenuController extends CallbackableController {
    private final StorageTypeView view;
    private final Scanner scanner;
    private final UserService userService;

    public StorageTypeMenuController( UserService userService) {
        this.scanner = new Scanner(System.in);
        this.view = new StorageTypeView();
        this.userService = userService;
    }

    @Override
    public Controller execute() {
        view.display();
        int choice = scanner.nextInt();

        // set storage type 1 = File, 2 = Jdbc
        return switch (choice) {
            case 1 -> {
                userService.setUserRepository(new FileUserRepository());
                yield callbackController;
            }
            case 2 -> {
                userService.setUserRepository(new JdbcUserRepository());
                yield callbackController;
            }
            default -> {
                System.out.println("Invalid choice. Please try again.");
                yield this;
            }
        };
    }
}
