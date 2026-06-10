package view;
import model.Enemy;

public class EnemyView {

    public void mostrarEnemy (Enemy enemy){
        System.out.println("= = = = INIMIGO = = = =");
        System.out.println("Nome: " +enemy.getNome());
        System.out.println("Vida: " +enemy.getHp());
        System.out.println("Ataque: " + enemy.getAtaque());
        System.out.println("XP ao derrotar: " + enemy.getXpReward());
        System.out.println("Ouro ao derrotar: " + enemy.getGoldReward());
    }

    public void mostrarMsg(String msg){
        System.out.println(msg);
    }
}
