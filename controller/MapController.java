import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MapController {
    private MapView mapView;
    private ResultView resultView;
    private PlayerController playerController;
    private Player player;
    private Scanner scanner;

    public MapController(Player player, PlayerController playerController) {
        this.mapView = new MapView();
        this.resultView = new ResultView();
        this.playerController = playerController;
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    public void explore() {
        boolean exploring = true;
        while (exploring && playerController.isAlive()) {
            mapView.displayMap();
            List<String> locations = getAvailableLocations();
            mapView.displayLocationOptions(locations);

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                exploring = false;
                continue;
            }

            if (choice > 0 && choice <= locations.size()) {
                String destination = locations.get(choice - 1);
                mapView.displayTravel(destination);
                handleLocation(destination);
            } else {
                resultView.displayError("Invalid choice.");
            }
        }
    }

    private List<String> getAvailableLocations() {
        String current = mapView.getPlayerLocation();
        switch (current) {
            case "Village":
                return Arrays.asList("Forest", "Plains", "Lake");
            case "Forest":
                return Arrays.asList("Village");
            case "Plains":
                return Arrays.asList("Village", "Cave", "Lake");
            case "Cave":
                return Arrays.asList("Plains", "Dungeon");
            case "Dungeon":
                return Arrays.asList("Cave", "Boss Room");
            case "Lake":
                return Arrays.asList("Village", "Plains");
            case "Boss Room":
                return Arrays.asList("Dungeon");
            default:
                return Arrays.asList("Village");
        }
    }

    private void handleLocation(String location) {
        switch (location) {
            case "Forest":
                encounterEnemy("Goblin", 30, 8, 4, 20, 10);
                break;
            case "Plains":
                encounterEnemy("Wolf", 40, 10, 5, 30, 15);
                break;
            case "Cave":
                encounterEnemy("Troll", 60, 12, 8, 50, 25);
                break;
            case "Lake":
                encounterEnemy("Water Spirit", 50, 11, 6, 40, 20);
                break;
            case "Dungeon":
                encounterEnemy("Dark Knight", 80, 15, 10, 70, 35);
                break;
            case "Boss Room":
                encounterBoss();
                break;
        }
    }

    private void encounterEnemy(String name, int hp, int atk, int def, int exp, int gold) {
        Enemy enemy = new Enemy(name, hp, atk, def, exp, gold);
        enemy.scaleToPlayer(player.getLevel());

        Weapon drop = new Weapon("Iron Sword", "A basic iron sword", 3.0, 30, 8, 1, "Sword");
        enemy.addDrop(drop);

        EnemyController enemyController = new EnemyController(enemy);
        enemyController.showEncounter();

        BattleController battleController = new BattleController(playerController, enemyController);
        boolean won = battleController.startBattle();

        if (!won) {
            resultView.displayGameOver("Defeated by " + name);
        }
    }

    private void encounterBoss() {
        Boss boss = new Boss("Dragon King", 200, 20, 15, 500, 200);
        boss.addAbility("Fire Breath");
        boss.addAbility("Tail Swipe");
        boss.addAbility("Dark Aura");
        boss.scaleToPlayer(player.getLevel());

        EnemyController enemyController = new EnemyController(boss);
        enemyController.showEncounter();

        BattleController battleController = new BattleController(playerController, enemyController);
        boolean won = battleController.startBattle();

        if (won) {
            resultView.displayGameComplete(player);
        } else {
            resultView.displayGameOver("Defeated by the Dragon King");
        }
    }
}
