package view;

import model.Player;

import java.util.Scanner;

public class GameView {

    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("    DESTINO DA COROA");
        System.out.println("========================================");
        System.out.println("1. Novo Jogo");
        System.out.println("2. Sair");
        System.out.println("========================================");
    }

    public int escolherOpcaoMenu() {
        System.out.print("Escolha uma opcao: ");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int escolherOpcao() {
        System.out.print("Escolha uma opcao: ");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarHistoria() {
        System.out.println("\n========== DESTINO DA COROA ==========");
        System.out.println("A Coroa do Reino foi roubada pelo temivel Dragao Chefe!");
        System.out.println("Sem ela, o reino esta em caos.");
        System.out.println("Voce, um jovem aventureiro, foi convocado pelo rei");
        System.out.println("para recuperar a Coroa do Destino.");
        System.out.println();
        System.out.println("Sua jornada comeca agora...");
        System.out.println("========================================");
    }

    public void mostrarFaseInfo(int fase) {
        if (fase == 1) {
            System.out.println("\n=== FASE 1: FLORESTA SOMBRIA ===");
            System.out.println("A floresta a leste da vila esta infestada de criaturas.");
            System.out.println("Prove seu valor e abra caminho ate o norte!");
        } else if (fase == 2) {
            System.out.println("\n=== FASE 2: CAVERNA DOS ORCS ===");
            System.out.println("Alem da floresta, uma caverna escura abriga orcs saqueadores.");
            System.out.println("Eles guardam o caminho para a montanha!");
        } else if (fase == 3) {
            System.out.println("\n=== FASE 3: MONTANHA GELADA ===");
            System.out.println("O frio e cortante. Criaturas perigosas vagam pela neve.");
            System.out.println("O Dragao esta cada vez mais perto!");
        } else if (fase == 4) {
            System.out.println("\n=== FASE 4: PICO DO DRAGAO ===");
            System.out.println("O topo da montanha. O Dragao Chefe espera com a Coroa!");
            System.out.println("PREPARE-SE PARA A BATALHA FINAL!");
        }
    }

    public void mostrarProgressoHistoria(int inimigosDerrotados) {
        if (inimigosDerrotados == 1) {
            System.out.println("\n--- Um velho sabio diz: \"O Dragao guarda a coroa");
            System.out.println("    no pico mais alto da Montanha Negra...\" ---");
        } else if (inimigosDerrotados == 3) {
            System.out.println("\n--- Os aldeoes falam de uma caverna de orcs");
            System.out.println("    no caminho para a montanha. Cuidado! ---");
        } else if (inimigosDerrotados == 5) {
            System.out.println("\n--- \"O Dragao Chefe eh implacavel. Prepare-se");
            System.out.println("    para o confronto final!\" ---");
        }
    }

    public void mostrarVitoria() {
        System.out.println("\n========================================");
        System.out.println("           VITORIA!");
        System.out.println("========================================");
        System.out.println("Voce derrotou o Dragao Chefe e");
        System.out.println("recuperou a Coroa do Destino!");
        System.out.println();
        System.out.println("O reino esta salvo gracas a voce!");
        System.out.println("========================================");
        System.out.println("       DESTINO DA COROA - FIM");
        System.out.println("========================================");
    }

    public void mostrarGameOver() {
        System.out.println("\n========================================");
        System.out.println("           GAME OVER");
        System.out.println("========================================");
        System.out.println("Voce caiu em batalha...");
        System.out.println("O reino ainda precisa de um heroi.");
        System.out.println("========================================");
    }

    public void mostrarHUD(Player player, int gold) {
        System.out.println("\n----- STATUS -----");
        System.out.println(player.getNome() + " | " + player.getClass().getSimpleName());
        System.out.println("HP: " + player.getHp() + " | Level: " + player.getLevel());
        System.out.println("XP: " + player.getXp() + " | Ouro: " + gold);
        System.out.println("------------------");
    }

    public void mostrarPreparacao() {
        System.out.println("\n=== O QUE DESEJA FAZER? ===");
        System.out.println("1. Avancar (combate da fase)");
        System.out.println("2. Explorar (farmar XP)");
        System.out.println("3. Descansar (cura 30 HP)");
        System.out.println("4. Loja");
        System.out.println("5. Usar Inventario");
        System.out.println("6. Ver Historico de Logs");
        System.out.println("7. Sair do jogo");
        System.out.println("===========================");
    }

    public void mostrarExploracao(int fase, int gold) {
        System.out.println("\n=== ESCOLHA UMA AREA ===");
        System.out.println("1. Floresta Sombria (Goblins)");
        if (fase >= 2) System.out.println("2. Caverna dos Orcs (Orcs)");
        if (fase >= 3) System.out.println("3. Montanha Gelada (Bruxas)");
        System.out.println("0. Voltar");
        System.out.println("Ouro: " + gold);
        System.out.println("========================");
    }

    public void mostrarLoja(int gold) {
        System.out.println("\n========== LOJA ==========");
        System.out.println("1. Pocao de Vida (cura 40 HP) — 1 gratuita por fase");
        System.out.println("2. Comprar pocao extra — 15 de ouro");
        System.out.println("3. Sair");
        System.out.println("Ouro: " + gold);
        System.out.println("===========================");
    }

    public void mostrarItemObtido(String nome) {
        System.out.println("Voce encontrou: " + nome + "!");
    }

    public void mostrarLevelUp(String nome, int level, int hp, int ataque) {
        System.out.println("\n*** " + nome + " subiu para o nivel " + level + "! ***");
        System.out.println("HP aumentou para " + hp);
        System.out.println("Ataque aumentou para " + ataque);
    }

    public void mostrarInimigoInfo(String nome, int hp, int ataque) {
        System.out.println("\n--- Um " + nome + " selvagem aparece! ---");
        System.out.println("HP: " + hp + " | Ataque: " + ataque);
    }

    public void mostrarClasseInfo(String classe, int hp, int ataque, String poder) {
        System.out.println("\n=== CLASSE: " + classe + " ===");
        System.out.println("HP base: " + hp + " | Ataque base: " + ataque);
        System.out.println("Poder especial: " + poder);
        System.out.println("=================================");
    }

    // ========== COMBATE ==========

    public void mostrarAberturaCombate(String nomeInimigo) {
        System.out.println("\n>>>>>>> COMBATE CONTRA " + nomeInimigo.toUpperCase() + " <<<<<<<");
    }

    public void mostrarStatusCombate(String nomeJ, int hpJ, int maxJ, String nomeE, int hpE, int maxE, int turno) {
        System.out.println("\n--- Turno " + turno + " ------------------------");
        System.out.println(nomeJ + "  HP: " + hpJ + "/" + maxJ + "  " + barra(hpJ, maxJ, 15));
        System.out.println(nomeE + "  HP: " + hpE + "/" + maxE + "  " + barra(hpE, maxE, 15));
        System.out.println("---------------------------------");
    }

    private String barra(int hp, int max, int tam) {
        int cheio = (int) Math.round((double) hp / max * tam);
        if (cheio < 0) cheio = 0;
        if (cheio > tam) cheio = tam;
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tam; i++) {
            sb.append(i < cheio ? '#' : '.');
        }
        sb.append("]");
        return sb.toString();
    }

    public void mostrarMenuCombate(String classe) {
        String poder = "Poder Especial";
        if (classe.equals("Guerreiro")) poder = "Golpe Pesado (2x dano, 65%)";
        else if (classe.equals("Mago")) poder = "Bola de Fogo (1.8x dano)";
        else if (classe.equals("Arqueiro")) poder = "Tiro Certeiro (1.5x dano)";

        System.out.println("1. Atacar");
        System.out.println("2. " + poder);
        System.out.println("3. Usar Pocao");
        System.out.println("4. Fugir (50%)");
    }

    public void mostrarAtaque(String nome, int dano) {
        System.out.println(nome + " atacou causando " + dano + " de dano!");
    }

    public void mostrarAtaqueCritico(String nome, int dano) {
        System.out.println("!!! " + nome + " acertou um GOLPE CRITICO causando " + dano + " de dano !!!");
    }

    public void mostrarPoderUsado(String nomeClasse, String nomePoder, int dano) {
        System.out.println(nomeClasse + " usou " + nomePoder + " causando " + dano + " de dano!");
    }

    public void mostrarPoderFalhou() {
        System.out.println("O poder especial errou o alvo!");
    }

    public void mostrouFugiu() {
        System.out.println("Voce conseguiu fugir do combate!");
    }

    public void mostrarNaoFugiu() {
        System.out.println("Voce nao conseguiu fugir!");
    }

    public void mostrarUsouPocao(int cura) {
        System.out.println("Voce usou uma Pocao de Vida e recuperou " + cura + " HP!");
    }

    public void mostrarSemPocoes() {
        System.out.println("Voce nao tem pocoes no inventario.");
    }

    public void mostrarNadaParaUsar() {
        System.out.println("Nao ha nada para usar em combate agora.");
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void aguardar() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}
