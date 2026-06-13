package controller;

import model.Enemy;
import model.MapModel;
import model.MapModel.Area;
import view.MapView;

import java.util.List;

/**
 * Controlador responsável pela lógica de navegação no mapa e geração
 * de encontros com inimigos. Segue o padrão MVC utilizado no projeto,
 * coordenando MapModel e MapView.
 */
public class MapController {

    private MapModel model;
    private MapView view;
    private int areaAtual;

    /**
     * Construtor do controlador do mapa.
     *
     * @param model Modelo do mapa com as áreas e configurações.
     * @param view  Visão responsável pela exibição do mapa.
     */
    public MapController(MapModel model, MapView view) {
        this.model = model;
        this.view = view;
        this.areaAtual = -1;
    }

    /**
     * Exibe todas as áreas disponíveis no mapa através da view.
     */
    public void mostrarMapa() {
        List<Area> areas = model.getAreas();
        view.mostrarAreas(areas);
    }

    /**
     * Permite ao jogador escolher uma área pelo número.
     * Retorna true se uma área válida foi escolhida,
     * false se o jogador escolheu sair (0).
     *
     * @return true se uma área foi selecionada, false para sair.
     */
    public boolean escolherArea() {
        int escolha = view.escolherArea();

        if (escolha == 0) {
            return false;
        }

        int indice = escolha - 1;

        if (indice >= 0 && indice < model.getQuantidadeAreas()) {
            areaAtual = indice;
            Area area = model.getArea(indice);
            view.mostrarAreaAtual(area);
            return true;
        } else {
            view.mostrarMensagem("Opção inválida. Tente novamente.");
            return escolherArea();
        }
    }

    /**
     * Gera um inimigo aleatório com base na área atualmente selecionada.
     * Exibe uma mensagem informando qual inimigo apareceu.
     *
     * @return Uma nova instância de Enemy da área atual,
     *         ou null se nenhuma área foi selecionada.
     */
    public Enemy gerarEncontro() {
        Area area = model.getArea(areaAtual);

        if (area == null) {
            return null;
        }

        Enemy inimigo = area.gerarInimigoAleatorio();
        view.mostrarEncontro(inimigo.getNome());
        return inimigo;
    }

    /**
     * Retorna a área atualmente selecionada pelo jogador.
     *
     * @return A área atual, ou null se nenhuma foi escolhida.
     */
    public Area getAreaAtual() {
        return model.getArea(areaAtual);
    }

    /**
     * Retorna o índice da área atualmente selecionada.
     *
     * @return Índice da área atual (-1 se nenhuma foi escolhida).
     */
    public int getIndiceAreaAtual() {
        return areaAtual;
    }

    /**
     * Retorna a quantidade total de áreas disponíveis no mapa.
     *
     * @return Número de áreas.
     */
    public int getQuantidadeAreas() {
        return model.getQuantidadeAreas();
    }
}
