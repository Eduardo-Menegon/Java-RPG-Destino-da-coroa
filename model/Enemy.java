package com.rpg.model;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Character {
    private List<Item> drops;
    private int expReward;
    private int goldReward;

    public Enemy(String name, int hp, int attack, int defense, int expReward, int goldReward) {
        super(name, hp, attack, defense);
        this.expReward = expReward;
        this.goldReward = goldReward;
        this.drops = new ArrayList<>();
    }

    public List<Item> getDrops() { return drops; }
    public void setDrops(List<Item> drops) { this.drops = drops; }

    public int getExpReward() { return expReward; }
    public void setExpReward(int expReward) { this.expReward = expReward; }

    public int getGoldReward() { return goldReward; }
    public void setGoldReward(int goldReward) { this.goldReward = goldReward; }

    public void addDrop(Item item) { drops.add(item); }

    @Override
    public void levelUp() {
        level++;
        maxHp += 10;
        hp = maxHp;
        attack += 2;
        defense += 1;
    }
}
