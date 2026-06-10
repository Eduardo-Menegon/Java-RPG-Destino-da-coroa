package view;

import java.util.Scanner;

/**
 * Classe responsável pela exibição do inventário do jogador no console.
 * Segue o padrão MVC como as demais views do projeto (CharacterView, CombatView, etc.).
 * Fornece métodos para mostrar itens, ouro, mensagens e capturar entrada do usuário.
 */
public class InventoryView {

    /**
     * Scanner para capturar entrada do usuário via teclado.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Exibe o cabeçalho do inventário com formatação visual.
     */
    public void mostrarCabecalho() {
        System.out.println("\n========== INVENTÁRIO ==========");
    }

    /**
     * Exibe o rodapé do inventário com formatação visual.
     */
    public void mostrarRodape() {
        System.out.println("=================================\n");
    }

    /**
     * Exibe um item formatado com seu número, nome e descrição.
     * O índice é incrementado em 1 para exibição mais amigável ao usuário.
     *
     * @param index     Índice do item na lista (0-based).
     * @param nome      Nome do item a ser exibido.
     * @param descricao Descrição do item a ser exibida.
     */
    public void mostrarItem(int index, String nome, String descricao) {
        System.out.println((index + 1) + ". " + nome + " — " + descricao);
    }

    /**
     * Exibe a quantidade atual de ouro do jogador.
     *
     * @param ouro Quantidade de ouro a ser exibida.
     */
    public void mostrarOuro(int ouro) {
        System.out.println("Ouro: " + ouro);
    }

    /**
     * Exibe uma mensagem personalizada no console.
     * Útil para feedback de ações como adicionar/remover itens.
     *
     * @param mensagem Mensagem a ser exibida.
     */
    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    /**
     * Exibe uma mensagem indicando que o inventário está vazio.
     */
    public void mostrarVazio() {
        System.out.println("Nenhum item no inventário.");
    }

    /**
     * Solicita ao usuário que digite um número correspondente a uma opção.
     * Utiliza nextLine() para consumir corretamente a quebra de linha
     * e evitar problemas com entradas anteriores do scanner.
     *
     * @return O número inteiro digitado pelo usuário.
     */
    public int pedirOpcao() {
        System.out.print("Digite o número do item para usar (ou 0 para sair): ");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
