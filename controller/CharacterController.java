public class CharacterController {
    private Character character;
    private CharacterView characterView;
    private ResultView resultView;

    public CharacterController(Character character) {
        this.character = character;
        this.characterView = new CharacterView();
        this.resultView = new ResultView();
    }

    public Character getCharacter() { return character; }

    public void showStatus() {
        if (character instanceof Player) {
            characterView.displayCharacterSheet((Player) character);
        } else {
            resultView.displayInfo("Name: " + character.getName());
            resultView.displayInfo("HP: " + character.getHp() + "/" + character.getMaxHp());
            resultView.displayInfo("Attack: " + character.getAttack());
            resultView.displayInfo("Defense: " + character.getDefense());
            resultView.displayInfo("Level: " + character.getLevel());
        }
    }

    public void takeDamage(int damage) {
        int oldHp = character.getHp();
        character.takeDamage(damage);
        int actualDamage = oldHp - character.getHp();
        characterView.displayHpChange(character.getName(), actualDamage, false);
    }

    public void heal(int amount) {
        int oldHp = character.getHp();
        character.heal(amount);
        int actualHeal = character.getHp() - oldHp;
        characterView.displayHpChange(character.getName(), actualHeal, true);
    }

    public boolean isAlive() {
        return character.isAlive();
    }

    public void levelUp() {
        character.levelUp();
        if (character instanceof Player) {
            characterView.displayLevelUp((Player) character);
        }
    }

    public String getName() {
        return character.getName();
    }
}
