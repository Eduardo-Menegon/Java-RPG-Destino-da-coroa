package model;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    public enum Tipo {
        ARMA, ARMADURA, POCAO, ACESSORIO
    }
    private int id;
    private String nome;
    private String descricao;
    private Tipo tipo;
    private int quantidade;
    private double peso;
    public Item(int id, String nome, String descricao, int quantidade, double peso) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.peso = peso;
    }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
    public double getPeso() { return peso; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setPeso(double peso) { this.peso = peso; }
    @Override
    public String toString() {
        return String.format("ID:[#%03d] Nome:%-20s  Qtd: %-3d  Peso: %5.1f kg",
                id, nome, quantidade, peso);
    }
}
