package controller;

import model.Enemy;
import model.MapModel;
import model.MapModel.Area;
import view.MapView;

import java.util.List;

public class MapController {

    private MapModel model;
    private MapView view;
    private int areaAtual;

    public MapController(MapModel model, MapView view) {
        this.model = model;
        this.view = view;
        this.areaAtual = -1;
    }

    public void mostrarMapa() {
        List<Area> areas = model.getAreas();
        view.mostrarAreas(areas);
    }

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

    public Enemy gerarEncontro() {
        Area area = model.getArea(areaAtual);

        if (area == null) {
            return null;
        }

        Enemy inimigo = area.gerarInimigoAleatorio();
        view.mostrarEncontro(inimigo.getNome());
        return inimigo;
    }

    public Area getAreaAtual() {
        return model.getArea(areaAtual);
    }

    public int getIndiceAreaAtual() {
        return areaAtual;
    }

    public int getQuantidadeAreas() {
        return model.getQuantidadeAreas();
    }
}
