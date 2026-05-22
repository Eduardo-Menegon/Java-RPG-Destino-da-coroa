package com.rpg.model;

public class Item {
    protected String name;
    protected String description;
    protected double weight;
    protected int value;

    public Item(String name, String description, double weight, int value) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public String getType() { return "Item"; }
}
