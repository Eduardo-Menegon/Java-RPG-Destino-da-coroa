import java.util.List;

public class MapView {
    private static final String[] MAP_ART = {
        "      [T]     [Forest]",
        "       |         |    ",
        "   [Village]---[Plains]---[Cave]",
        "       |         |         |    ",
        "      [S]      [Lake]    [Dungeon]",
        "                         |       ",
        "                       [Boss]    "
    };

    private String playerLocation;

    public MapView() {
        this.playerLocation = "Village";
    }

    public void displayMap() {
        System.out.println("\n========== MAP ==========");
        for (String line : MAP_ART) {
            System.out.println("  " + line);
        }
        System.out.println("\nCurrent Location: [" + playerLocation + "]");
        System.out.println("=========================");
    }

    public void displayLocationOptions(List<String> locations) {
        System.out.println("\nAvailable destinations:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i));
        }
        System.out.println("0. Back");
        System.out.print("Choose destination: ");
    }

    public void displayTravel(String location) {
        System.out.println("\nTraveling to " + location + "...");
        this.playerLocation = location;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("You arrived at " + location + ".");
    }

    public void displayCurrentLocation() {
        System.out.println("You are at: [" + playerLocation + "]");
    }

    public void setPlayerLocation(String location) { this.playerLocation = location; }
    public String getPlayerLocation() { return playerLocation; }
}
