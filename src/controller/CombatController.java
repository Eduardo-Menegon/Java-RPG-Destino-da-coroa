package controller;
import model.Combat;
import model.*;
import view.CombatView;

public class CombatController {
    private Combat combat;
    private CombatView view;

    public CombatController(Combat combat, CombatView view) {
        this.combat = combat;
        this.view = view;
    }

    public void ataquePlayer(){
        Player player = combat.getPlayer();
        Enemy enemy = combat.getEnemy();

        enemy.receberDano(player.getAtaque());

        view.mostrarMsg(player.getNome() + " atacou " + enemy.getNome());

        if(!enemy.vivo()){
            player.addOuro(enemy.getGoldReward());
            player.addXp(enemy.getXpReward());

            view.mostrarMsg(enemy.getNome() + " foi derrotado! ");
            view.mostrarMsg("Você recebeu: " + enemy.getXpReward() + " de XP e " + enemy.getGoldReward() + " de ouro.");
        }

    }

    public void ataqueEnemy(){
        Enemy enemy = combat.getEnemy();
        Player player = combat.getPlayer();

        player.receberDano(enemy.getAtaque());

        view.mostrarMsg(enemy.getNome()+ " atacou ");
    }

    public void mostrarStatus(){
        Player player = combat.getPlayer();
        Enemy enemy = combat.getEnemy();

        view.mostrarStatus(
                player.getNome(),
                player.getHp(),
                enemy.getNome(),
                enemy.getHp()
        );
    }

    public boolean combatAcabou(){
        return combat.acabou();
    }

    public Player getPlayer(){
        return combat.getPlayer();
    }

    public Enemy getEnemy(){
        return combat.getEnemy();
    }
}
