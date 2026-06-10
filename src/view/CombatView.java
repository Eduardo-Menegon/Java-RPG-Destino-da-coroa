package view;


public class CombatView {

    public void mostrarMsg(String msg){
        System.out.println(msg);
    }

    public void mostrarStatus(String nomePlayer, int hpPLayer, String nomeEnemy, int hpEnemy){
        System.out.println("= = = COMBATE = = =");
        System.out.println("Player: " + nomePlayer);
        System.out.println("HP: " + hpPLayer);
        System.out.println("Enemy: " + nomeEnemy);
        System.out.println("HP: " + hpEnemy);
        System.out.println("===================");

    }


}
