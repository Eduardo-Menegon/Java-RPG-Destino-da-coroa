package com.rpg.controller;

import com.rpg.model.Enemy;
import com.rpg.model.Item;
import com.rpg.view.BattleView;
import com.rpg.view.CharacterView;

public class BattleController {
    private BattleView battleView;
    private PlayerController playerController;
    private EnemyController enemyController;
    private boolean playerDefending;

    public BattleController(PlayerController playerController, EnemyController enemyController) {
        this.battleView = new BattleView();
        this.playerController = playerController;
        this.enemyController = enemyController;
        this.playerDefending = false;
    }

    public boolean startBattle() {
        Enemy enemy = enemyController.getEnemy();
        battleView.displayBattleStart(enemy.getName());

        while (playerController.isAlive() && enemyController.isAlive()) {
            battleView.displayStatus(playerController.getPlayer(), enemy);
            battleView.displayActionMenu();

            int choice = new java.util.Scanner(System.in).nextInt();
            playerDefending = false;

            switch (choice) {
                case 1: playerAttack(); break;
                case 2: playerDefend(); break;
                case 3: useItem(); break;
                case 4:
                    if (tryRun()) return true;
                    break;
                default:
                    battleView.displayAttack(playerController.getPlayer().getName(), enemy.getName(), 0);
                    break;
            }

            if (enemyController.isAlive()) {
                enemyTurn();
            }
        }

        if (playerController.isAlive()) {
            battleView.displayVictory(enemy.getName(), enemy.getExpReward(), enemy.getGoldReward());
            playerController.addExperience(enemy.getExpReward());
            playerController.addGold(enemy.getGoldReward());

            for (Item drop : enemy.getDrops()) {
                if (playerController.getPlayer().getInventory().addItem(drop)) {
                    battleView.displayItemDrop(drop.getName());
                }
            }
            return true;
        } else {
            battleView.displayDefeat();
            return false;
        }
    }

    private void playerAttack() {
        Enemy enemy = enemyController.getEnemy();
        int damage = playerController.getPlayer().getAttack();
        enemy.takeDamage(damage);
        battleView.displayAttack(playerController.getPlayer().getName(), enemy.getName(), Math.max(1, damage - enemy.getDefense()));
    }

    private void playerDefend() {
        playerDefending = true;
        battleView.displayDefend(playerController.getPlayer().getName());
    }

    private void useItem() {
        playerController.showInventory();
        battleView.displayInfo("Select item to use - feature not fully implemented.");
    }

    private boolean tryRun() {
        boolean success = Math.random() > 0.4;
        battleView.displayRunAttempt(success);
        return success;
    }

    private void enemyTurn() {
        Enemy enemy = enemyController.getEnemy();
        int damage = enemy.getAttack();
        if (playerDefending) {
            damage /= 2;
        }
        playerController.getPlayer().takeDamage(damage);
        battleView.displayAttack(enemy.getName(), playerController.getPlayer().getName(), Math.max(1, damage - playerController.getPlayer().getDefense()));
    }
}
