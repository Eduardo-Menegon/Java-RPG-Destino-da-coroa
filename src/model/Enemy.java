package model;
import interfaces.Atacante;

public abstract class Enemy extends Character {
    private int xpReward;
    private int goldReward;

    public Enemy(String nome, int hp, int ataque, int xpReward, int goldReward) {
        super(nome, hp, ataque);
        setXpReward(xpReward);
        setGoldReward(goldReward);
    }


    public int getXpReward() {

        return xpReward;
    }

    public void setXpReward(int xpReward) {
        if (xpReward >= 0) {
            this.xpReward = xpReward;
        }
    }

    public int getGoldReward() {

        return goldReward;
    }

    public void setGoldReward(int goldReward) {
        if (goldReward >= 0) {
            this.goldReward = goldReward;
        }
    }

}


