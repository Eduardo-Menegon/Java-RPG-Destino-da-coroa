public class Weapon extends Item {
    private int damage;
    private int range;
    private String weaponType;

    public Weapon(String name, String description, double weight, int value, int damage, int range, String weaponType) {
        super(name, description, weight, value);
        this.damage = damage;
        this.range = range;
        this.weaponType = weaponType;
    }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }

    public int getRange() { return range; }
    public void setRange(int range) { this.range = range; }

    public String getWeaponType() { return weaponType; }
    public void setWeaponType(String weaponType) { this.weaponType = weaponType; }

    @Override
    public String getType() { return "Weapon"; }
}
