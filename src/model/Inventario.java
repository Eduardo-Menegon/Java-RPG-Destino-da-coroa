package model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Inventario implements Serializable {
    private static final String ARQUIVO_SALVO = "inventario.dat";
    private List<Item> itens;
    private int proximoId;
    private String nomePersonagem;
    private double capacidadeMaxima;
    public Inventario(String nomePersonagem, double capacidadeMaxima) {
        this.itens = new ArrayList<>();
        this.proximoId = 1;
        this.nomePersonagem = nomePersonagem;
        this.capacidadeMaxima = capacidadeMaxima;
    }
    public boolean adicionarItem(String nome, String descricao, int quantidade, double peso)
    {
        double pesoNovo = calcularPesoTotal() + (peso * quantidade);
        if (pesoNovo > capacidadeMaxima)
            return false;
        itens.add(new Item(proximoId++, nome, descricao, quantidade, peso));
        return true;
    }
    public boolean removerItem(int id) {
        return itens.removeIf(i -> i.getId() == id);
    }
    public boolean editarItem(int id, String nome, String descricao, int quantidade, double peso ) {
        Item item = buscarPorId(id);
        if (item == null) return false;
        double pesoSemEsteItem = calcularPesoTotal() - (item.getPeso() * item.getQuantidade());
        double pesoComNovo = pesoSemEsteItem + (peso * quantidade);
        if (pesoComNovo > capacidadeMaxima) return false;
        item.setNome(nome);
        item.setDescricao(descricao);
        item.setQuantidade(quantidade);
        item.setPeso(peso);
        return true;
    }
    public Item buscarPorId(int id) {
        return itens.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }
    public List<Item> buscarPorNome(String termo) {
        String t = termo.toLowerCase();
        return itens.stream()
                .filter(i -> i.getNome().toLowerCase().contains(t))
                .collect(Collectors.toList());
    }
    public List<Item> listarTodos() {
        return Collections.unmodifiableList(itens);
    }
    public List<Item> listarOrdenadoPorNome() {
        return itens.stream()
                .sorted(Comparator.comparing(Item::getNome))
                .collect(Collectors.toList());
    }
    public double calcularPesoTotal() {
        return itens.stream().mapToDouble(i -> i.getPeso() * i.getQuantidade()).sum();
    }
    public int getTotalItens() { return itens.size(); }
    public String getNomePersonagem() { return nomePersonagem; }
    public double getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setNomePersonagem(String nome) { this.nomePersonagem = nome; }
    public void setCapacidadeMaxima(double cap) { this.capacidadeMaxima = cap; }
    public void salvar() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SALVO))) {
            oos.writeObject(this);
        }
    }
    public static Inventario carregar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_SALVO))) {
            return (Inventario) ois.readObject();
        }
    }
    public static boolean arquivoExiste() {
        return new File(ARQUIVO_SALVO).exists();
    }
}
