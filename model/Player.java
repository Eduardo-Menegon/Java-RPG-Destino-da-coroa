public class Player extends Character {
    private int experience;
    private int gold;
    private Inventory inventory;

    public Player(String name) {
        super(name, 100, 15, 10);
        this.experience = 0;
        this.gold = 0;
        this.inventory = new Inventory(50);
    }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public int getGold() { return gold; }
    public void setGold(int gold) { this.gold = gold; }

    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }

    public void addGold(int amount) { this.gold += amount; }
    public boolean spendGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            return true;
        }
        return false;
    }

    public void addExperience(int exp) {
        this.experience += exp;
        while (this.experience >= level * 50) {
            this.experience -= level * 50;
            levelUp();
        }
    }

    @Override
    public void levelUp() {
        level++;
        maxHp += 20;
        hp = maxHp;
        attack += 3;
        defense += 2;
    }
}
