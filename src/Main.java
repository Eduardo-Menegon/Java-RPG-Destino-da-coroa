import controller.CombatController;
import controller.PlayerController;
import model.Combat;
import model.Enemy;
import model.Goblin;
import model.Player;
import view.CombatView;
import view.PlayerView;

public class Main {

    public static void main(String[] args) {

        // ===== CRIAÇÃO DO PERSONAGEM =====

        PlayerView playerView = new PlayerView();
        PlayerController playerController = new PlayerController(playerView);

        playerController.criarPersonagem();

        Player player = playerController.getPlayer();

        playerView.mostrarMensagem("\n===== PERSONAGEM CRIADO =====");
        playerController.mostrarPlayer();

        // ===== CRIAÇÃO DO INIMIGO =====

        Enemy enemy = new Goblin();

        CombatView combatView = new CombatView();
        Combat combat = new Combat(player, enemy);

        CombatController combatController =
                new CombatController(combat, combatView);

        combatView.mostrarMsg("\n===== COMBATE INICIADO =====");

        // ===== LOOP DE COMBATE =====

        while (!combatController.combatAcabou()) {

            combatController.mostrarStatus();

            combatController.ataquePlayer();

            if (combatController.combatAcabou()) {
                break;
            }

            combatController.ataqueEnemy();
        }

        // ===== RESULTADO =====

        combatView.mostrarMsg("\n===== COMBATE ENCERRADO =====");

        combatController.mostrarStatus();

        playerView.mostrarMensagem("\n===== STATUS FINAL =====");

        playerController.mostrarPlayer();

        if (player.vivo()) {
            playerView.mostrarMensagem("Vitória!");
        } else {
            playerView.mostrarMensagem("Derrota!");
        }
    }
}