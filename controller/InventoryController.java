package com.rpg.controller;

import com.rpg.model.Inventory;
import com.rpg.model.Item;
import com.rpg.model.Weapon;
import com.rpg.model.Armor;
import com.rpg.view.InventoryView;
import com.rpg.view.ItemView;
import com.rpg.view.ResultView;

public class InventoryController {
    private Inventory inventory;
    private InventoryView inventoryView;
    private ItemView itemView;
    private ResultView resultView;

    public InventoryController(Inventory inventory) {
        this.inventory = inventory;
        this.inventoryView = new InventoryView();
        this.itemView = new ItemView();
        this.resultView = new ResultView();
    }

    public Inventory getInventory() { return inventory; }

    public void showInventory() {
        inventoryView.displayInventory(inventory);
    }

    public boolean addItem(Item item) {
        if (inventory.addItem(item)) {
            inventoryView.displayItemAdded(item);
            return true;
        }
        inventoryView.displayInventoryFull();
        return false;
    }

    public boolean removeItem(Item item) {
        if (inventory.removeItem(item)) {
            inventoryView.displayItemRemoved(item);
            return true;
        }
        resultView.displayError("Item not found in inventory.");
        return false;
    }

    public void inspectItem(int index) {
        if (index >= 0 && index < inventory.getItems().size()) {
            Item item = inventory.getItems().get(index);
            itemView.displayItem(item);
        } else {
            resultView.displayError("Invalid item index.");
        }
    }

    public boolean addGold(int amount) {
        inventory.addGold(amount);
        resultView.displaySuccess("Added " + amount + " gold to inventory.");
        return true;
    }

    public boolean spendGold(int amount) {
        if (inventory.spendGold(amount)) {
            resultView.displayInfo("Spent " + amount + " gold.");
            return true;
        }
        resultView.displayError("Not enough gold!");
        return false;
    }

    public int getItemCount() {
        return inventory.getItemCount();
    }

    public boolean isFull() {
        return inventory.isFull();
    }
}
