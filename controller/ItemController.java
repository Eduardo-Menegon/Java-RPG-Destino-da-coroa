public class ItemController {
    private Item item;
    private ItemView itemView;
    private ResultView resultView;

    public ItemController(Item item) {
        this.item = item;
        this.itemView = new ItemView();
        this.resultView = new ResultView();
    }

    public Item getItem() { return item; }

    public void showItem() {
        itemView.displayItem(item);
    }

    public void useItem() {
        itemView.displayItemUse(item);
    }

    public void equipItem() {
        if (item instanceof Weapon || item instanceof Armor) {
            itemView.displayItemEquip(item);
        } else {
            resultView.displayWarning("This item cannot be equipped.");
        }
    }

    public void dropItem() {
        itemView.displayItemDrop(item.getName());
    }

    public boolean isWeapon() {
        return item instanceof Weapon;
    }

    public boolean isArmor() {
        return item instanceof Armor;
    }

    public int getValue() {
        return item.getValue();
    }

    public String getName() {
        return item.getName();
    }
}
