package model;

import interfaces.Atacante;

public abstract class  Character implements Atacante{
    protected String nome;
    protected int hp;
    protected int ataque;

    public Character(String nome, int hp, int ataque) {
        setNome(nome);
        setHp(hp);
        setAtaque(ataque);
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setNome(String nome) {
        if (!nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public void setHp(int hp) {
        if (hp > 0) {
            this.hp = hp;
        }
    }

    public void setAtaque(int ataque) {
        if (ataque > 0) {
            this.ataque = ataque;
        }
    }

    public void receberDano(int dano){
        if (dano > 0){
            this.hp -= dano;

            if (this.hp < 0){
                this.hp = 0;
            }
        }
    }

    public boolean vivo(){
        return hp > 0;
    }

    @Override
    public void ataque(Character alvo) {
        alvo.receberDano(this.ataque);
    }
}