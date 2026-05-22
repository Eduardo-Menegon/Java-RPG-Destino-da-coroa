public class GameController {
    private MainMenuView mainMenuView;
    private ResultView resultView;
    private PlayerController playerController;
    private MapController mapController;
    private boolean running;

    public GameController() {
        this.mainMenuView = new MainMenuView();
        this.resultView = new ResultView();
        this.running = true;
    }

    public void start() {
        while (running) {
            mainMenuView.displayTitle();
            mainMenuView.displayMenu();
            int option = mainMenuView.getOption();

            switch (option) {
                case 1:
                    newGame();
                    break;
                case 2:
                    resultView.displayInfo("Load Game - Not implemented yet.");
                    break;
                case 3:
                    resultView.displayInfo("Options - Not implemented yet.");
                    break;
                case 4:
                    exit();
                    break;
                default:
                    mainMenuView.displayInvalidOption();
            }
        }
    }

    private void newGame() {
        String name = mainMenuView.getPlayerName();
        Player player = new Player(name);
        playerController = new PlayerController(player);
        mapController = new MapController(player, playerController);
        resultView.displayInfo("Welcome, " + name + "! Your journey begins...");
        mapController.explore();
    }

    private void exit() {
        mainMenuView.displayExit();
        running = false;
    }
}
