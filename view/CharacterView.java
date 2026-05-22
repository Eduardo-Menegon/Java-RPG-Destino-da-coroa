package com.rpg.view;

import com.rpg.model.Player;

public class CharacterView {

    public void displayCharacterSheet(Player player) {
        System.out.println("\n--- Character Sheet ---");
        System.out.println("Name: " + player.getName());
        System.out.println("Level: " + player.getLevel());
        System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Defense: " + player.getDefense());
        System.out.println("Experience: " + player.getExperience() + "/" + (player.getLevel() * 50));
        System.out.println("Gold: " + player.getGold());
        System.out.println("------------------------");
    }

    public void displayLevelUp(Player player) {
        System.out.println("\n*** LEVEL UP! ***");
        System.out.println("You are now level " + player.getLevel() + "!");
        System.out.println("HP increased to " + player.getMaxHp());
        System.out.println("Attack increased to " + player.getAttack());
        System.out.println("Defense increased to " + player.getDefense());
    }

    public void displayHpChange(String name, int damage, boolean isHeal) {
        if (isHeal) {
            System.out.println(name + " healed for " + damage + " HP.");
        } else {
            System.out.println(name + " took " + damage + " damage.");
        }
    }

    public void displayDeath(String name) {
        System.out.println(name + " has been defeated!");
    }
}
