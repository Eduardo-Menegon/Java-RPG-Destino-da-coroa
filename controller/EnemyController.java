package com.rpg.controller;

import com.rpg.model.Enemy;
import com.rpg.model.Boss;
import com.rpg.model.Item;
import com.rpg.view.EnemyView;

public class EnemyController {
    private Enemy enemy;
    private EnemyView enemyView;

    public EnemyController(Enemy enemy) {
        this.enemy = enemy;
        this.enemyView = new EnemyView();
    }

    public Enemy getEnemy() { return enemy; }

    public boolean isAlive() {
        return enemy.isAlive();
    }

    public void showInfo() {
        if (enemy instanceof Boss) {
            enemyView.displayBossInfo((Boss) enemy);
        } else {
            enemyView.displayEnemyInfo(enemy);
        }
    }

    public void showEncounter() {
        enemyView.displayEnemyEncounter(enemy);
    }

    public void addDrop(Item item) {
        enemy.addDrop(item);
    }

    public void scaleToPlayer(int playerLevel) {
        int diff = playerLevel - enemy.getLevel();
        for (int i = 0; i < diff; i++) {
            enemy.levelUp();
        }
    }

    public void nextBossPhase() {
        if (enemy instanceof Boss) {
            ((Boss) enemy).nextPhase();
            enemyView.displayEnemyInfo(enemy);
        }
    }

    public void resetHp() {
        enemy.setHp(enemy.getMaxHp());
    }
}
