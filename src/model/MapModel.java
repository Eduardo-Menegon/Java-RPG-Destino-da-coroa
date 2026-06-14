package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;


public class MapModel {


    public static class Area {
        private String nome;
        private String descricao;
        private List<Supplier<Enemy>> geradoresInimigos;

        public Area(String nome, String descricao, List<Supplier<Enemy>> geradoresInimigos) {
            this.nome = nome;
            this.descricao = descricao;
            this.geradoresInimigos = geradoresInimigos;
        }


        public String getNome() {
            return nome;
        }



        public String getDescricao() {
            return descricao;
        }


        public Enemy gerarInimigoAleatorio() {
            int indice = ThreadLocalRandom.current().nextInt(geradoresInimigos.size());
            return geradoresInimigos.get(indice).get();
        }
    }

    private List<Area> areas;


    public MapModel() {
        this.areas = new ArrayList<>();
        inicializarAreas();
    }


    private void inicializarAreas() {
        List<Supplier<Enemy>> inimigosFloresta = new ArrayList<>();
        inimigosFloresta.add(Goblin::new);
        inimigosFloresta.add(Goblin::new);
        inimigosFloresta.add(BruxaVerde::new);

        List<Supplier<Enemy>> inimigosCaverna = new ArrayList<>();
        inimigosCaverna.add(saqueador::new);
        inimigosCaverna.add(saqueador::new);
        inimigosCaverna.add(Goblin::new);

        List<Supplier<Enemy>> inimigosMontanha = new ArrayList<>();
        inimigosMontanha.add(DragaoBoss::new);

        areas.add(new Area(
                "Floresta Sombria",
                "Uma floresta densa e misteriosa, habitada por goblins e bruxas.",
                inimigosFloresta
        ));

        areas.add(new Area(
                "Caverna dos Orcs",
                "Uma caverna escura onde orcs saqueadores espreitam nas sombras.",
                inimigosCaverna
        ));

        areas.add(new Area(
                "Montanha do Dragão",
                "O pico mais alto da região, guardado pelo temido Dragão Chefe.",
                inimigosMontanha
        ));
    }


    public List<Area> getAreas() {
        return areas;
    }


    public Area getArea(int index) {
        if (index >= 0 && index < areas.size()) {
            return areas.get(index);
        }
        return null;
    }


    public int getQuantidadeAreas() {
        return areas.size();
    }
}
