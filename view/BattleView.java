package com.rpg.view;

import com.rpg.model.Character;
import com.rpg.model.Enemy;

public class BattleView {

    public void displayBattleStart(String enemyName) {
        System.out.println("\n=== BATTLE START ===");
        System.out.println("A wild " + enemyName + " appears!");
    }

    public void displayStatus(Character player, Enemy enemy) {
        System.out.println("\n--- Status ---");
        System.out.println(player.getName() + " - HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println(enemy.getName() + " - HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
    }

    public void displayActionMenu() {
        System.out.println("\nChoose action:");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");
        System.out.println("4. Run");
        System.out.print("Your choice: ");
    }

    public void displayAttack(String attacker, String target, int damage) {
        System.out.println(attacker + " attacks " + target + " for " + damage + " damage!");
    }

    public void displayDefend(String name) {
        System.out.println(name + " takes a defensive stance.");
    }

    public void displayRunAttempt(boolean success) {
        if (success) {
            System.out.println("You escaped successfully!");
        } else {
            System.out.println("Failed to escape!");
        }
    }

    public void displayVictory(String enemyName, int exp, int gold) {
        System.out.println("\n=== VICTORY ===");
        System.out.println(enemyName + " defeated!");
        System.out.println("Gained " + exp + " experience and " + gold + " gold!");
    }

    public void displayDefeat() {
        System.out.println("\n=== YOU DIED ===");
        System.out.println("Game Over...");
    }

    public void displayItemDrop(String itemName) {
        System.out.println("The enemy dropped: " + itemName);
    }
}
