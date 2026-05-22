public class EnemyView {

    public void displayEnemyInfo(Enemy enemy) {
        System.out.println("\n--- Enemy Info ---");
        System.out.println("Name: " + enemy.getName());
        System.out.println("Level: " + enemy.getLevel());
        System.out.println("HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
        System.out.println("Attack: " + enemy.getAttack());
        System.out.println("Defense: " + enemy.getDefense());
        System.out.println("EXP Reward: " + enemy.getExpReward());
        System.out.println("Gold Reward: " + enemy.getGoldReward());
        System.out.println("-------------------");
    }

    public void displayBossInfo(Boss boss) {
        System.out.println("\n!!! BOSS ENEMY !!!");
        displayEnemyInfo(boss);
        System.out.println("Phase: " + boss.getPhase());
        System.out.print("Abilities: ");
        for (String ability : boss.getSpecialAbilities()) {
            System.out.print("[" + ability + "] ");
        }
        System.out.println();
    }

    public void displayDrop(Item item) {
        System.out.println("Drop: " + item.getName() + " - " + item.getDescription());
    }

    public void displayEnemyEncounter(Enemy enemy) {
        System.out.println("\n>> You encountered: " + enemy.getName() + " (Lv." + enemy.getLevel() + ")");
    }
}
