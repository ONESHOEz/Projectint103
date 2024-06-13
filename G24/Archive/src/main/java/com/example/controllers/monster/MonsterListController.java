package com.example.controllers.monster;

import com.example.controllers.character.NewCharacterController;
import com.example.controllers.game.BattleController;
import com.example.controllers.interfaces.Controller;
import com.example.core.domain.Character;
import com.example.core.domain.abstracts.Monster;
import com.example.core.services.BattleService;
import com.example.core.services.MonsterService;
import com.example.presentation.views.monster.MonsterListView;

import java.util.ArrayList;
import java.util.Scanner;

public class MonsterListController implements Controller {
    private final Scanner scanner;
    private final MonsterListView view;
    private final MonsterService monsterService;
    private final BattleService battleService;
    private final BattleController battleController;

    public MonsterListController(BattleController battleController, MonsterService monsterService, BattleService battleService) {
        this.battleService = battleService;
        this.scanner = new Scanner(System.in);
        this.view = new MonsterListView();
        this.monsterService = monsterService;
        this.battleController = battleController;
    }

    @Override
    public Controller execute() {
        ArrayList<Monster> monsters = monsterService.getAllMonsters();
        view.setMonsters(monsters);
        view.display();
        int choice = scanner.nextInt();

        Monster monster = monsters.get(choice - 1);

        battleService.setMonster(monster);

        return battleController;
    }
}
