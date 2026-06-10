package model;

public class Mago extends Player{

    public Mago(String nome) {
        super(nome);
        setHp(80);
        setAtaque(30);
        setClasse("Mago");
    }
}