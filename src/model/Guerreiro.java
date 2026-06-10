package model;

public class Guerreiro extends Player{
    public Guerreiro(String nome) {
        super(nome);
        setHp(150);
        setAtaque(20);
        setClasse("Guerreiro");
    }
}