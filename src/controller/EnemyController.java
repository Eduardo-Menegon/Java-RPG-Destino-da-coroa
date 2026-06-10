package controller;
import model.*;
import view.EnemyView;

import view.EnemyView;

public class EnemyController {
    private EnemyView view;

    public EnemyController(){
        this.view = new EnemyView();
    }

    public void mostrarEnemy(Enemy enemy){
        if(enemy != null){
            view.mostrarEnemy(enemy);
        } else {
            view.mostrarMsg("Inimigo inválido.");
        }

    }



}
