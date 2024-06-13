package com.example.controllers.character;

import com.example.controllers.interfaces.CallbackableController;
import com.example.controllers.interfaces.Controller;
import com.example.controllers.monster.MonsterListController;
import com.example.core.domain.Character;
import com.example.core.services.BattleService;
import com.example.core.services.UserService;
import com.example.presentation.views.character.CharacterListView;

import java.util.ArrayList;
import java.util.Scanner;

public class CharacterListController implements Controller {
    private final Scanner scanner;
    private final UserService userService;
    private final BattleService battleService;
    private final CharacterListView view;
    private final NewCharacterController newCharacterController;
    private final MonsterListController monsterListController;

    public CharacterListController(NewCharacterController newCharacterController, MonsterListController monsterListController, UserService userService, BattleService battleService) {
        this.scanner = new Scanner(System.in);
        this.view = new CharacterListView();
        this.userService = userService;
        this.battleService = battleService;
        this.newCharacterController = newCharacterController;
        this.monsterListController = monsterListController;
    }

    @Override
    public Controller execute() {
        ArrayList<Character> characters = userService.getCharacters();
        view.setCharacters(characters);
        view.display();
        int choice = scanner.nextInt();

        if (choice == 0) {
            newCharacterController.withCallback(this);
            return newCharacterController;
        }

        Character character = characters.get(choice - 1);

        battleService.setPlayer(character);

        return monsterListController;
    }
}
