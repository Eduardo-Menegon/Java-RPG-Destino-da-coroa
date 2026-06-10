package model;

import model.Character;
import interfaces.Atacante;


public class Player extends Character {
    private int xp;
    private int level;
    private int ouro;
    private String classe;



    public Player(String nome) {
        super(nome, 100, 10);
        this.xp = 0;
        this.level = 1;
        this.ouro = 0;
        setClasse(classe);
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void levelUp(){
        level++;
        ataque += 5;
        hp += 20;

        //Log.registar(nome + "subiu para o nivel " + level);
    }

    public void addXp(int quantidade){
        if (quantidade > 0 ){
            xp += quantidade;
        }
        if (xp >= 100){
            xp -= 100;
            levelUp();
        }
    }

    public void addOuro(int quantidade){
        if (quantidade > 0){
            ouro += quantidade;
        }
    }


    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public int getOuro() {
        return ouro;
    }
}