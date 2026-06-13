package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Modelo que representa o mapa do jogo, contendo as áreas disponíveis
 * para o jogador explorar. Cada área possui geradores de inimigos
 * que permitem encontros aleatórios durante a exploração.
 */
public class MapModel {

    /**
     * Classe interna que representa uma área específica do mapa.
     * Cada área possui nome, descrição e uma lista de geradores de inimigos
     * que podem ser encontrados nela.
     */
    public static class Area {
        private String nome;
        private String descricao;
        private List<Supplier<Enemy>> geradoresInimigos;

        /**
         * Construtor da área.
         *
         * @param nome              Nome da área (ex: "Floresta Sombria").
         * @param descricao         Descrição temática da área.
         * @param geradoresInimigos Lista de Suppliers que criam inimigos
         *                         possíveis para esta área.
         */
        public Area(String nome, String descricao, List<Supplier<Enemy>> geradoresInimigos) {
            this.nome = nome;
            this.descricao = descricao;
            this.geradoresInimigos = geradoresInimigos;
        }

        /**
         * Retorna o nome da área.
         *
         * @return Nome da área.
         */
        public String getNome() {
            return nome;
        }

        /**
         * Retorna a descrição da área.
         *
         * @return Descrição da área.
         */
        public String getDescricao() {
            return descricao;
        }

        /**
         * Gera um inimigo aleatório dos possíveis para esta área.
         * Utiliza ThreadLocalRandom para selecionar um dos geradores disponíveis.
         *
         * @return Uma nova instância de Enemy de um tipo aleatório da área.
         */
        public Enemy gerarInimigoAleatorio() {
            int indice = ThreadLocalRandom.current().nextInt(geradoresInimigos.size());
            return geradoresInimigos.get(indice).get();
        }
    }

    private List<Area> areas;

    /**
     * Construtor do mapa. Inicializa as áreas disponíveis com suas
     * respectivas descrições e inimigos possíveis.
     */
    public MapModel() {
        this.areas = new ArrayList<>();
        inicializarAreas();
    }

    /**
     * Inicializa as áreas do jogo com seus nomes, descrições e inimigos.
     * Cada área possui inimigos específicos que podem ser encontrados.
     */
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

    /**
     * Retorna a lista completa de áreas do mapa.
     *
     * @return Lista de áreas disponíveis.
     */
    public List<Area> getAreas() {
        return areas;
    }

    /**
     * Retorna uma área específica pelo seu índice.
     *
     * @param index Índice da área na lista (0-based).
     * @return A área no índice informado, ou null se inválido.
     */
    public Area getArea(int index) {
        if (index >= 0 && index < areas.size()) {
            return areas.get(index);
        }
        return null;
    }

    /**
     * Retorna a quantidade total de áreas no mapa.
     *
     * @return Número de áreas disponíveis.
     */
    public int getQuantidadeAreas() {
        return areas.size();
    }
}
