package com.rpg.view;

import com.rpg.model.Item;
import com.rpg.model.Weapon;
import com.rpg.model.Armor;

public class ItemView {

    public void displayItem(Item item) {
        System.out.println("\n--- " + item.getName() + " ---");
        System.out.println("Type: " + item.getType());
        System.out.println("Description: " + item.getDescription());
        System.out.println("Weight: " + String.format("%.1f", item.getWeight()));
        System.out.println("Value: " + item.getValue() + " gold");

        if (item instanceof Weapon) {
            displayWeaponStats((Weapon) item);
        } else if (item instanceof Armor) {
            displayArmorStats((Armor) item);
        }
    }

    private void displayWeaponStats(Weapon weapon) {
        System.out.println("Damage: " + weapon.getDamage());
        System.out.println("Range: " + weapon.getRange());
        System.out.println("Weapon Type: " + weapon.getWeaponType());
    }

    private void displayArmorStats(Armor armor) {
        System.out.println("Defense Bonus: " + armor.getDefenseBonus());
        System.out.println("Slot: " + armor.getSlot());
        System.out.println("Durability: " + armor.getDurability());
    }

    public void displayItemUse(Item item) {
        System.out.println("You use " + item.getName() + "...");
    }

    public void displayItemEquip(Item item) {
        System.out.println(item.getName() + " has been equipped.");
    }

    public void displayItemDrop(String itemName) {
        System.out.println("You dropped " + itemName + ".");
    }
}
