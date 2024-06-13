package com.example.controllers.game;

import com.example.controllers.interfaces.Controller;
import com.example.core.domain.exceptions.InvalidChoiceException;
import com.example.core.services.BattleService;
import com.example.presentation.views.game.BattleView;
import com.example.presentation.views.game.GameMenuView;

import java.util.Scanner;

public class BattleController implements Controller {
    Scanner scanner = new Scanner(System.in);
    private final BattleView view;
    private final BattleService battleService;
    private String turn;
    public BattleController(BattleService battleService) {
        scanner = new Scanner(System.in);
        view = new BattleView();
        this.battleService = battleService;
        this.turn = "you";
    }
    @Override
    public Controller execute() {
        int isWin = battleService.checkWin();
        switch (isWin) {
            case 0:
                view.winMessage(battleService.getPlayer().getName());
                scanner.nextLine();
                scanner.nextLine();
                return null;
            case 1:
                view.winMessage(battleService.getMonster().getName());
                scanner.nextLine();
                scanner.nextLine();
                return null;
        }
        view.setMonster(battleService.getMonster());
        view.setCharacter(battleService.getPlayer());

        if (!turn.equals("you")) {
            battleService.monsterAttack();
            view.eventMessage(turn, battleService.getMonster().getName() + " Attack");
            swapTurn();
            return this;
        }

        view.display();
        int choice = scanner.nextInt();
        swapTurn();
        return switch (choice) {
            case 1 -> {
                battleService.playerAttack();
                this.view.eventMessage(turn, "Normal Attack");
                yield this;
            }
            case 2 -> null;
            default -> throw new InvalidChoiceException("Invalid choice");
        };
    }

    private void swapTurn() {
        turn = turn.equals("you") ? "monster" : "you";
    }
}
