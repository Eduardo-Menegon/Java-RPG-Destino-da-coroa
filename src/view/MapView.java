package view;

import model.MapModel.Area;

import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela exibição do mapa e das áreas do jogo no console.
 * Segue o padrão MVC do projeto, fornecendo métodos para mostrar áreas,
 * detalhes e capturar escolhas do jogador.
 */
public class MapView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Exibe a lista de áreas disponíveis para exploração.
     * Mostra cada área numerada com nome e descrição.
     *
     * @param areas Lista de áreas a serem exibidas.
     */
    public void mostrarAreas(List<Area> areas) {
        System.out.println("\n========== MAPA — ESCOLHA UMA ÁREA ==========");

        for (int i = 0; i < areas.size(); i++) {
            Area area = areas.get(i);
            System.out.println((i + 1) + ". " + area.getNome());
            System.out.println("   " + area.getDescricao());
        }

        System.out.println("0. Sair do jogo");
        System.out.println("==============================================");
    }

    /**
     * Exibe o nome da área atual onde o jogador se encontra.
     *
     * @param area Área atual a ser mostrada.
     */
    public void mostrarAreaAtual(Area area) {
        System.out.println("\n--- Local atual: " + area.getNome() + " ---");
    }

    /**
     * Solicita ao jogador que escolha uma área pelo número.
     * Utiliza nextLine() para evitar problemas com quebra de linha do Scanner.
     *
     * @return O número da área escolhida (0 para sair).
     */
    public int escolherArea() {
        System.out.print("Escolha uma área (ou 0 para sair): ");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Exibe uma mensagem personalizada no console.
     *
     * @param mensagem Mensagem a ser exibida.
     */
    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    /**
     * Exibe uma mensagem informando que um inimigo apareceu na área atual.
     *
     * @param nomeInimigo Nome do inimigo encontrado.
     */
    public void mostrarEncontro(String nomeInimigo) {
        System.out.println("\n!!! Um " + nomeInimigo + " selvagem apareceu !!!");
    }
}
