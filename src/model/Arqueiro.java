package model;

public class Arqueiro extends Player{
    public Arqueiro(String nome) {
        super(nome);
        setHp(100);
        setAtaque(25);
        setClasse("Arqueiro");

    }
}