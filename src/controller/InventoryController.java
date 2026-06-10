package controller;

import model.Player;
import view.InventoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador responsável pela lógica do inventário do jogador.
 * Segue o padrão MVC utilizado no projeto (assim como PlayerController e CombatController).
 * Gerencia uma lista de itens e coordena as interações entre o modelo Player e a InventoryView.
 */
public class InventoryController {

    /**
     * Classe interna que representa um item dentro do inventário.
     * Cada item possui um nome e uma descrição que o identificam.
     * Utilizada exclusivamente pelo InventoryController para encapsular os dados do item.
     */
    public static class Item {
        private String nome;
        private String descricao;

        /**
         * Construtor do item.
         *
         * @param nome      Nome do item (ex: "Poção de Vida").
         * @param descricao Descrição do item (ex: "Recupera 30 de HP").
         */
        public Item(String nome, String descricao) {
            this.nome = nome;
            this.descricao = descricao;
        }

        /**
         * Retorna o nome do item.
         *
         * @return Nome do item.
         */
        public String getNome() {
            return nome;
        }

        /**
         * Retorna a descrição do item.
         *
         * @return Descrição do item.
         */
        public String getDescricao() {
            return descricao;
        }
    }

    private Player player;
    private InventoryView view;
    private List<Item> itens;

    /**
     * Construtor do controlador de inventário.
     * Inicializa as referências do jogador e da view, e cria a lista de itens vazia.
     *
     * @param player Jogador ao qual este inventário pertence.
     * @param view   Visão responsável por exibir o inventário no console.
     */
    public InventoryController(Player player, InventoryView view) {
        this.player = player;
        this.view = view;
        this.itens = new ArrayList<>();
    }

    /**
     * Adiciona um novo item ao inventário com nome e descrição.
     * Exibe uma mensagem de confirmação através da view.
     *
     * @param nome      Nome do item a ser adicionado.
     * @param descricao Descrição do item a ser adicionado.
     */
    public void adicionarItem(String nome, String descricao) {
        itens.add(new Item(nome, descricao));
        view.mostrarMensagem("Item adicionado ao inventário: " + nome);
    }

    /**
     * Remove um item do inventário pelo seu índice (0-based).
     * Verifica se o índice é válido antes de remover.
     * Exibe mensagens de sucesso ou erro através da view.
     *
     * @param index Índice do item a ser removido.
     */
    public void removerItem(int index) {
        if (index >= 0 && index < itens.size()) {
            Item removido = itens.remove(index);
            view.mostrarMensagem("Item removido do inventário: " + removido.getNome());
        } else {
            view.mostrarMensagem("Erro: índice de item inválido.");
        }
    }

    /**
     * Exibe todo o inventário no console, incluindo o ouro do jogador
     * e a lista de itens (ou mensagem de vazio se não houver itens).
     * Segue o padrão de mostrarStatus() do CombatController.
     */
    public void mostrarInventario() {
        view.mostrarCabecalho();
        view.mostrarOuro(player.getOuro());

        if (itens.isEmpty()) {
            view.mostrarVazio();
        } else {
            for (int i = 0; i < itens.size(); i++) {
                Item item = itens.get(i);
                view.mostrarItem(i, item.getNome(), item.getDescricao());
            }
        }

        view.mostrarRodape();
    }

    /**
     * Retorna a lista completa de itens do inventário.
     *
     * @return Lista de itens.
     */
    public List<Item> getItens() {
        return itens;
    }

    /**
     * Retorna a quantidade atual de itens no inventário.
     *
     * @return Número de itens no inventário.
     */
    public int getQuantidadeItens() {
        return itens.size();
    }

    /**
     * Retorna o item no índice especificado (0-based).
     * Retorna null se o índice estiver fora dos limites da lista.
     *
     * @param index Índice do item desejado.
     * @return O item no índice informado, ou null se inválido.
     */
    public Item getItem(int index) {
        if (index >= 0 && index < itens.size()) {
            return itens.get(index);
        }
        return null;
    }

    /**
     * Retorna o jogador associado a este inventário.
     *
     * @return O jogador dono do inventário.
     */
    public Player getPlayer() {
        return player;
    }
}
