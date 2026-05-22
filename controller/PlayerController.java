public class PlayerController {
    private Player player;
    private CharacterView characterView;
    private InventoryView inventoryView;
    private ResultView resultView;

    public PlayerController(Player player) {
        this.player = player;
        this.characterView = new CharacterView();
        this.inventoryView = new InventoryView();
        this.resultView = new ResultView();
    }

    public Player getPlayer() { return player; }

    public void showStatus() {
        characterView.displayCharacterSheet(player);
    }

    public void showInventory() {
        inventoryView.displayInventory(player.getInventory());
    }

    public void addGold(int amount) {
        player.addGold(amount);
        resultView.displaySuccess("Gained " + amount + " gold!");
    }

    public boolean spendGold(int amount) {
        if (player.spendGold(amount)) {
            resultView.displayInfo("Spent " + amount + " gold.");
            return true;
        }
        resultView.displayError("Not enough gold!");
        return false;
    }

    public void addExperience(int exp) {
        int currentLevel = player.getLevel();
        player.addExperience(exp);
        if (player.getLevel() > currentLevel) {
            characterView.displayLevelUp(player);
        }
    }

    public void healPlayer(int amount) {
        player.heal(amount);
        characterView.displayHpChange(player.getName(), amount, true);
    }

    public boolean isAlive() {
        return player.isAlive();
    }
}
