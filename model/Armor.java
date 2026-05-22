package com.rpg.model;

public class Armor extends Item {
    private int defenseBonus;
    private String slot;
    private int durability;

    public Armor(String name, String description, double weight, int value, int defenseBonus, String slot, int durability) {
        super(name, description, weight, value);
        this.defenseBonus = defenseBonus;
        this.slot = slot;
        this.durability = durability;
    }

    public int getDefenseBonus() { return defenseBonus; }
    public void setDefenseBonus(int defenseBonus) { this.defenseBonus = defenseBonus; }

    public String getSlot() { return slot; }
    public void setSlot(String slot) { this.slot = slot; }

    public int getDurability() { return durability; }
    public void setDurability(int durability) { this.durability = Math.max(0, durability); }

    @Override
    public String getType() { return "Armor"; }
}
