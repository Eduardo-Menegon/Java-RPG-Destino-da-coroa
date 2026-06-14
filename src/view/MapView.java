package view;

import model.MapModel.Area;

import java.util.List;
import java.util.Scanner;


public class MapView {

    private Scanner scanner = new Scanner(System.in);


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


    public void mostrarAreaAtual(Area area) {
        System.out.println("\n--- Local atual: " + area.getNome() + " ---");
    }


    public int escolherArea() {
        System.out.print("Escolha uma área (ou 0 para sair): ");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }


    public void mostrarEncontro(String nomeInimigo) {
        System.out.println("\n!!! Um " + nomeInimigo + " selvagem apareceu !!!");
    }
}
