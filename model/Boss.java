import java.util.ArrayList;
import java.util.List;

public class Boss extends Enemy {
    private List<String> specialAbilities;
    private int phase;

    public Boss(String name, int hp, int attack, int defense, int expReward, int goldReward) {
        super(name, hp, attack, defense, expReward, goldReward);
        this.phase = 1;
        this.specialAbilities = new ArrayList<>();
    }

    public List<String> getSpecialAbilities() { return specialAbilities; }
    public void setSpecialAbilities(List<String> specialAbilities) { this.specialAbilities = specialAbilities; }

    public int getPhase() { return phase; }
    public void setPhase(int phase) { this.phase = phase; }

    public void addAbility(String ability) { specialAbilities.add(ability); }

    public void nextPhase() {
        phase++;
        maxHp += 50;
        hp = maxHp;
        attack += 5;
        defense += 3;
    }

    @Override
    public void levelUp() {
        level++;
        maxHp += 30;
        hp = maxHp;
        attack += 5;
        defense += 3;
    }
}
