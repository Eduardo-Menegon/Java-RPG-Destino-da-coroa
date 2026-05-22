import java.util.List;

public class ResultView {

    public void displayResult(String title, List<String> messages) {
        System.out.println("\n=== " + title + " ===");
        for (String msg : messages) {
            System.out.println("  " + msg);
        }
        System.out.println("===================");
    }

    public void displayStats(Player player) {
        System.out.println("\n=== Final Stats ===");
        System.out.println("Name: " + player.getName());
        System.out.println("Level: " + player.getLevel());
        System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("Gold: " + player.getGold());
        System.out.println("Items Collected: " + player.getInventory().getItemCount());
        System.out.println("===================");
    }

    public void displaySuccess(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    public void displayError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void displayInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    public void displayWarning(String message) {
        System.out.println("[WARNING] " + message);
    }

    public void displayGameOver(String reason) {
        System.out.println("\n=== GAME OVER ===");
        System.out.println("Cause: " + reason);
        System.out.println("Better luck next time!");
        System.out.println("==================");
    }

    public void displayGameComplete(Player player) {
        System.out.println("\n*** CONGRATULATIONS! ***");
        System.out.println("You have completed Destino da Coroa!");
        displayStats(player);
    }
}
