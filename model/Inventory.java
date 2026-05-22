package com.rpg.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    private int gold;
    private double maxWeight;
    private double currentWeight;

    public Inventory(double maxWeight) {
        this.items = new ArrayList<>();
        this.gold = 0;
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public int getGold() { return gold; }
    public void setGold(int gold) { this.gold = gold; }

    public double getMaxWeight() { return maxWeight; }
    public void setMaxWeight(double maxWeight) { this.maxWeight = maxWeight; }

    public double getCurrentWeight() { return currentWeight; }

    public boolean addItem(Item item) {
        if (currentWeight + item.getWeight() <= maxWeight) {
            items.add(item);
            currentWeight += item.getWeight();
            return true;
        }
        return false;
    }

    public boolean removeItem(Item item) {
        if (items.remove(item)) {
            currentWeight -= item.getWeight();
            return true;
        }
        return false;
    }

    public void addGold(int amount) { this.gold += amount; }
    public boolean spendGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            return true;
        }
        return false;
    }

    public int getItemCount() { return items.size(); }

    public boolean isFull() { return currentWeight >= maxWeight; }
}
