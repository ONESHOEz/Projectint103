package com.example.core.services;

import com.example.core.domain.Character;
import com.example.core.domain.Weapon;
import com.example.core.domain.abstracts.Monster;

import java.util.Random;

public class BattleService {
    private Character player;
    private Monster monster;

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public Monster getMonster() {
        return monster;
    }
    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void playerAttack() {
       int totalDamage = player.getBaseDamage();
       monster.setHealth(monster.getHealth() - totalDamage);
    }

    public int checkWin() {
        if (monster.getHealth() <= 0) {
            return 0;
        } else if (player.getHealth() <= 0) {
            return 1;
        }
        return -1;
    }

    public void monsterAttack() {
        int totalDamage = 0;

        Random random = new Random();
        int skillChance = 10; // 1 in 10 chance
        int ultimateChance = 50; // 1 in 50 chance

        int skillRoll = random.nextInt(skillChance);
        int ultimateRoll = random.nextInt(ultimateChance);

        if (ultimateRoll == 0) {
            totalDamage += monster.skill();
        }
        else if (skillRoll == 0) {
            totalDamage += monster.ultimate();
        }
        else {
            totalDamage += monster.getDamage();
        }
        player.setHealth(player.getHealth() - totalDamage);
    }

}
