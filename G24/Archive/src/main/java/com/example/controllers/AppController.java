package com.example.controllers;


import com.example.controllers.auth.LoginController;
import com.example.controllers.auth.RegisterController;
import com.example.controllers.character.CharacterListController;
import com.example.controllers.character.NewCharacterController;
import com.example.controllers.game.BattleController;
import com.example.controllers.game.GameMenuController;
import com.example.controllers.interfaces.Controller;
import com.example.controllers.monster.MonsterListController;
import com.example.controllers.storage.StorageTypeMenuController;
import com.example.core.domain.Dragon;
import com.example.core.domain.Slime;
import com.example.core.domain.abstracts.Monster;
import com.example.core.domain.exceptions.InvalidChoiceException;
import com.example.core.domain.exceptions.InvalidPasswordFormatException;
import com.example.core.repositories.memory.InMemoryMonsterRepository;
import com.example.core.repositories.memory.InMemoryUserRepository;
import com.example.core.services.BattleService;
import com.example.core.services.MonsterService;
import com.example.core.services.UserService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppController {
    private Controller currentController;
    private final UserService userService;
    private final BattleService battleService;
    private final MonsterService monsterService;
    private final MainMenuController mainMenuController;


    public AppController() {
        // services
        userService = new UserService(new InMemoryUserRepository());
        monsterService = new MonsterService(new InMemoryMonsterRepository());
        battleService = new BattleService();

        // controllers
        BattleController battleController = new BattleController(battleService);
        MonsterListController monsterListController = new MonsterListController(battleController, monsterService, battleService);
        StorageTypeMenuController storageTypeMenuController = new StorageTypeMenuController(userService);
        RegisterController registerController = new RegisterController(userService);
        NewCharacterController newCharacterController = new NewCharacterController(userService);
        CharacterListController characterListController = new CharacterListController(newCharacterController,monsterListController, userService, battleService);


        GameMenuController gameMenuController = new GameMenuController(characterListController, userService);
        LoginController loginController = new LoginController(gameMenuController, userService);

        // main menu
        ArrayList<Controller> mainMenus = new ArrayList<Controller>(
                Arrays.asList(loginController, registerController, gameMenuController)
        );

        // menu controller
        this.mainMenuController = new MainMenuController(mainMenus, storageTypeMenuController);

        this.currentController = mainMenuController;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
        try {
            Controller nextController = currentController.execute();
            if (nextController == null && currentController == mainMenuController) {
                exit = true;
            } else if (nextController == null) {
                currentController = mainMenuController;
            } else {
                currentController = nextController;
            }
        } catch (InvalidPasswordFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Password does not match must be At least one digit, lowercase, uppercase and more than 4 long;");
            exit = true;
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid option");
            exit = true;
        } catch (InvalidChoiceException e) {
            System.out.println(e.getMessage() + ": Please choose a valid option");
            exit = true;
        }
        }
        System.out.println("Have a great day. :)");
    }

}
