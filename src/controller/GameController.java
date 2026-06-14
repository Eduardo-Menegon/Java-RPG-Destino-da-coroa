package controller;

import model.*;
import view.*;
import util.Log;

import java.util.List;

public class GameController {

    private GameModel model;
    private GameView gameView;
    private PlayerController playerController;
    private PlayerView playerView;
    private Inventario inventario;
    private InventarioView inventarioView;
    private boolean rodando;
    private boolean pocaoGratisUsada;
    private InventarioController inventarioController;
    private LogController logController = new LogController();

    public GameController() {
        this.model = new GameModel();
        this.gameView = new GameView();
        this.rodando = true;
    }

    public void iniciar() {
        while (rodando) {
            switch (model.getState()) {

                case "MENU":
                    executarMenu();
                    break;

                case "CRIAR":
                    executarCriacao();
                    break;

                case "HISTORIA":
                    executarHistoria();
                    break;

                case "PREPARACAO":
                    executarPreparacao();
                    break;

                case "COMBATE":
                    executarCombate();
                    break;

                case "GAME_OVER":
                    executarGameOver();
                    break;

                case "VITORIA":
                    executarVitoria();
                    break;
            }
        }
    }

    // ==================== INVENTARIO ====================

   /* private void executarInventario() {
        List<Item> itens = inventario.listarTodos();

        if (itens.isEmpty()) {
            gameView.mostrarMensagem("Seu inventario esta vazio.");
            gameView.aguardar();
            return;
        }

        while (true) {
            gameView.mostrarMensagem("\n=== INVENTARIO ===");
            inventarioView.exibirLista(itens);
            gameView.mostrarMensagem("\n1 - Usar item");
            gameView.mostrarMensagem("0 - Voltar");

            int opcao = gameView.escolherOpcao();

            if (opcao == 0) break;

            if (opcao == 1) {
                usarPocaoForaCombate();
                itens = inventario.listarTodos(); // atualiza lista após usar
            } else {
                gameView.mostrarMensagem("Opcao invalida.");
            }

            gameView.aguardar();
        }
    }*/

    // ==================== MENU ====================

    private void executarMenu() {
        gameView.mostrarMenu();
        int opcao = gameView.escolherOpcaoMenu();

        if (opcao == 1) {
            model.setState("CRIAR");
        } else {
            rodando = false;
        }
    }

    // ==================== CRIAR PERSONAGEM ====================

    private void executarCriacao() {
        playerView = new PlayerView();
        playerController = new PlayerController(playerView);
        playerController.criarPersonagem();

        Player player = playerController.getPlayer();
        model.setPlayer(player);

        inventario = new Inventario(player.getNome(), 50.0);
        inventarioView = new InventarioView();

        inventario = new Inventario(player.getNome(), 50.0);
        inventarioView = new InventarioView();
        inventarioController = new InventarioController(inventario);

        Log.registar("Personagem criado: " + player.getNome() + " | Classe: " + player.getClass());

        String classe = player.getClass().getSimpleName();
        int hpBase = player.getHp();
        int atkBase = player.getAtaque();
        String poder = obterNomePoder(classe);

        gameView.mostrarClasseInfo(classe, hpBase, atkBase, poder);
        gameView.aguardar();

        model.setState("HISTORIA");
    }

    // ==================== HISTORIA ====================

    private void executarHistoria() {
        pocaoGratisUsada = false;
        int fase = model.getFase();

        if (fase == 1) {
            gameView.mostrarHistoria();
        } else {
            gameView.mostrarFaseInfo(fase);
        }

        gameView.aguardar();
        model.setState("PREPARACAO");
    }

    // ==================== PREPARACAO ====================

    private void executarPreparacao() {
        Player player = model.getPlayer();

        if (!player.vivo()) {
            model.setState("GAME_OVER");
            return;
        }

        gameView.mostrarHUD(player, model.getGold());

        int fase = model.getFase();
        int inimigosFase = model.getInimigosNestaFase();
        int necessarios = (fase <= 3) ? 2 : 1;

        gameView.mostrarMensagem("Fase " + fase + " - Combate " + (inimigosFase + 1) + " de " + necessarios);

        gameView.mostrarPreparacao();
        int opcao = gameView.escolherOpcao();

        switch (opcao) {
            case 1:
                model.setState("COMBATE");
                break;

            case 2:
                executarExploracao();
                break;

            case 3:
                int cura = Math.min(30, model.getMaxHp() - player.getHp());
                if (cura > 0) {
                    player.setHp(player.getHp() + cura);
                    gameView.mostrarMensagem("Voce descansou e recuperou " + cura + " HP.");
                } else {
                    gameView.mostrarMensagem("Seu HP ja esta no maximo.");
                }
                gameView.aguardar();
                break;

            case 4:
                executarLoja();
                break;

            case 5:
                inventarioController.iniciarNoJogo();
                break;

            case 6:
                logController.iniciar();
                break;

            case 7:
                gameView.mostrarMensagem("Saindo do jogo...");
                rodando = false;
                break;

            default:
                gameView.mostrarMensagem("Opcao invalida.");
                gameView.aguardar();
                break;
        }
    }

    // ==================== EXPLORACAO ====================

    private void executarExploracao() {
        int fase = model.getFase();

        while (true) {
            gameView.mostrarExploracao(fase, model.getGold());
            int escolha = gameView.escolherOpcao();

            if (escolha == 0) return;

            if (escolha < 1 || escolha > fase || (fase >= 4 && escolha > 3)) {
                gameView.mostrarMensagem("Area invalida ou nao desbloqueada.");
                gameView.aguardar();
                continue;
            }

            int areaIdx = escolha - 1;
            Enemy inimigo = gerarInimigoExploracao(areaIdx);
            executarCombateExploracao(inimigo);
            return;
        }
    }

    private Enemy gerarInimigoExploracao(int areaIdx) {
        switch (areaIdx) {
            case 0: return new Goblin();
            case 1: return Math.random() < 0.5 ? new Goblin() : new saqueador();
            case 2: return Math.random() < 0.5 ? new saqueador() : new BruxaVerde();
            default: return new Goblin();
        }
    }

    private void executarCombateExploracao(Enemy inimigo) {
        Player player = model.getPlayer();
        int maxHpInimigo = obterMaxHpInimigo(inimigo);
        int maxHpPlayer = model.getMaxHp();
        int nivelAntes = player.getLevel();
        String classe = player.getClass().getSimpleName();

        gameView.mostrarAberturaCombate(inimigo.getNome());
        gameView.mostrarInimigoInfo(inimigo.getNome(), inimigo.getHp(), inimigo.getAtaque());
        gameView.aguardar();

        int turno = 0;
        boolean fugiu = false;

        while (player.vivo() && inimigo.vivo() && !fugiu) {
            turno++;

            gameView.mostrarStatusCombate(
                player.getNome(), player.getHp(), maxHpPlayer,
                inimigo.getNome(), inimigo.getHp(), maxHpInimigo, turno
            );

            gameView.mostrarMenuCombate(classe);
            int acao = gameView.escolherOpcao();

            switch (acao) {
                case 1:
                    boolean critico = Math.random() < 0.15;
                    int dano = critico ? (int)(player.getAtaque() * 1.5) : player.getAtaque();
                    inimigo.receberDano(dano);
                    if (critico) gameView.mostrarAtaqueCritico(player.getNome(), dano);
                    else gameView.mostrarAtaque(player.getNome(), dano);
                    break;
                case 2:
                    usarPoderEspecial(player, inimigo);
                    break;
                case 3:
                    usarPocaoEmCombate(player);
                    break;
                case 4:
                    if (Math.random() < 0.5) {
                        gameView.mostrouFugiu();
                        fugiu = true;
                    } else {
                        gameView.mostrarNaoFugiu();
                    }
                    break;
                default:
                    gameView.mostrarMensagem("Opcao invalida! Perdeu a vez.");
                    break;
            }

            if (inimigo.vivo() && !fugiu) {
                int danoE = inimigo.getAtaque();
                player.receberDano(danoE);
                gameView.mostrarAtaque(inimigo.getNome(), danoE);
            }
        }

        if (fugiu) {
            gameView.mostrarMensagem("\nVoce escapou.");
            gameView.aguardar();
            return;
        }

        if (!player.vivo()) {
            gameView.mostrarMensagem("\nVoce foi derrotado...");
            gameView.aguardar();
            model.setState("GAME_OVER");
            return;
        }

        gameView.mostrarMensagem(inimigo.getNome() + " foi derrotado!");

        int xpFinal = (int)(inimigo.getXpReward() * 1.5);
        int goldFinal = (int)(inimigo.getGoldReward() * 1.5);

        player.addXp(xpFinal);
        model.addGold(goldFinal);

        gameView.mostrarMensagem("Ganhou " + xpFinal + " de XP e " + goldFinal + " de ouro.");

        if (player.getLevel() > nivelAntes) {
            model.setMaxHp(model.getMaxHp() + 20);
            gameView.mostrarLevelUp(player.getNome(), player.getLevel(), player.getHp(), player.getAtaque());
        }

        if (Math.random() < 0.25) {
            inventario.adicionarItem("Pocao de Vida", "Recupera 40 de HP", 1, 0.5);
            gameView.mostrarItemObtido("Pocao de Vida");
        }

        gameView.aguardar();
    }

    // ==================== LOJA ====================

    private void executarLoja() {
        while (true) {
            gameView.mostrarLoja(model.getGold());
            int opcao = gameView.escolherOpcao();

            if (opcao == 1) {
                if (!pocaoGratisUsada) {
                    inventario.adicionarItem("Pocao de Vida", "Recupera 40 de HP", 1, 0.5);
                    gameView.mostrarMensagem("O ferreiro lhe entrega uma Pocao de Vida gratuita!");
                    pocaoGratisUsada = true;
                } else {
                    gameView.mostrarMensagem("O ferreiro diz: \"Ja te dei uma pocao hoje. Volte na proxima fase.\"");
                }
                gameView.aguardar();
            } else if (opcao == 2) {
                if (model.gastarGold(15)) {
                    inventario.adicionarItem("Pocao de Vida", "Recupera 40 de HP", 1, 0.5);
                    gameView.mostrarMensagem("Voce comprou uma Pocao de Vida por 15 de ouro!");
                    Log.registar("Item comprado: Pocao de Vida | Gold restante: " + model.getGold());
                } else {
                    gameView.mostrarMensagem("Ouro insuficiente! Uma pocao extra custa 15 de ouro.");
                }
                gameView.aguardar();
            } else {
                break;
            }
        }
    }

    // ==================== USAR POCAO FORA DE COMBATE ====================

    /*private void usarPocaoForaCombate() {
        List<Item> itens = inventario.listarTodos();

        if (itens.isEmpty()) {
            gameView.mostrarMensagem("Nenhum item no inventario.");
            return;
        }

        inventarioView.exibirLista(itens);
        gameView.mostrarMensagem("Digite o numero do item para usar (ou 0 para cancelar):");
        int escolha = gameView.escolherOpcao();

        if (escolha <= 0 || escolha > itens.size()) {
            gameView.mostrarMensagem("Cancelado.");
            return;
        }

        int index = escolha - 1;
        Item item = itens.get(index);

        if (item == null) {
            gameView.mostrarMensagem("Item invalido.");
            return;
        }

        if (item.getNome().equals("Pocao de Vida")) {
            Player player = model.getPlayer();
            int cura = Math.min(40, model.getMaxHp() - player.getHp());

            if (cura > 0) {
                player.setHp(player.getHp() + cura);
                inventario.removerItem(item.getId());
                gameView.mostrarMensagem("Voce usou uma Pocao de Vida e recuperou " + cura + " HP!");
            } else {
                gameView.mostrarMensagem("Seu HP ja esta no maximo.");
            }
        } else {
            gameView.mostrarMensagem("Este item nao pode ser usado agora.");
        }
    }*/

    // ==================== COMBATE ====================

    private void executarCombate() {
        Player player = model.getPlayer();
        int fase = model.getFase();

        Enemy inimigo = gerarInimigo(fase, model.getInimigosNestaFase());
        int maxHpInimigo = obterMaxHpInimigo(inimigo);
        int maxHpPlayer = model.getMaxHp();
        int nivelAntes = player.getLevel();
        String classe = player.getClass().getSimpleName();
        boolean ehBoss = inimigo instanceof DragaoBoss;

        gameView.mostrarAberturaCombate(inimigo.getNome());
        gameView.mostrarInimigoInfo(inimigo.getNome(), inimigo.getHp(), inimigo.getAtaque());

        if (ehBoss) {
            gameView.mostrarMensagem("*** DRAGAO CHEFE - A BATALHA FINAL! ***");
        }

        gameView.aguardar();

        // Loop de combate interativo
        int turno = 0;
        boolean fugiu = false;

        while (player.vivo() && inimigo.vivo() && !fugiu) {
            turno++;

            gameView.mostrarStatusCombate(
                player.getNome(), player.getHp(), maxHpPlayer,
                inimigo.getNome(), inimigo.getHp(), maxHpInimigo, turno
            );

            gameView.mostrarMenuCombate(classe);
            int acao = gameView.escolherOpcao();

            switch (acao) {
                case 1: // ATACAR
                    boolean critico = Math.random() < 0.15;
                    int dano = critico ? (int)(player.getAtaque() * 1.5) : player.getAtaque();
                    inimigo.receberDano(dano);

                    if (critico) {
                        gameView.mostrarAtaqueCritico(player.getNome(), dano);
                    } else {
                        gameView.mostrarAtaque(player.getNome(), dano);
                    }
                    break;

                case 2: // PODER ESPECIAL
                    usarPoderEspecial(player, inimigo);
                    break;

                case 3: // USAR POCAO
                    usarPocaoEmCombate(player);
                    break;

                case 4: // FUGIR
                    if (Math.random() < 0.5) {
                        gameView.mostrouFugiu();
                        fugiu = true;
                    } else {
                        gameView.mostrarNaoFugiu();
                    }
                    break;

                default:
                    gameView.mostrarMensagem("Opcao invalida! Perdeu a vez.");
                    break;
            }

            // Vez do inimigo (se ainda estiver vivo e nao fugiu)
            if (inimigo.vivo() && !fugiu) {
                int danoE = inimigo.getAtaque();
                player.receberDano(danoE);
                gameView.mostrarAtaque(inimigo.getNome(), danoE);
            }
        }

        // ===== RESULTADO DO COMBATE =====

        if (fugiu) {
            gameView.mostrarMensagem("\nVoce escapou. Preparacao novamente...");
            Log.registar("Fugiu de: " + inimigo.getNome() + " | Fase: " + fase);
            gameView.aguardar();
            model.setState("PREPARACAO");
            return;
        }

        if (!player.vivo()) {
            gameView.mostrarMensagem("\nVoce foi derrotado...");
            Log.registar("Derrota para: " + inimigo.getNome() + " | Fase: " + fase);
            gameView.aguardar();
            model.setState("GAME_OVER");
            return;
        }

        // VITORIA no combate
        gameView.mostrarMensagem(inimigo.getNome() + " foi derrotado!");
        Log.registar("Vitoria contra: " + inimigo.getNome() + " | XP: " + inimigo.getXpReward() + " | Gold: " + inimigo.getGoldReward());

        int xpFinal = inimigo.getXpReward() * 3;
        int goldFinal = inimigo.getGoldReward();

        player.addXp(xpFinal);
        model.addGold(goldFinal);

        gameView.mostrarMensagem("Ganhou " + xpFinal + " de XP e " + goldFinal + " de ouro.");

        if (player.getLevel() > nivelAntes) {
            model.setMaxHp(model.getMaxHp() + 20);
            gameView.mostrarLevelUp(player.getNome(), player.getLevel(), player.getHp(), player.getAtaque());
        }

        if (Math.random() < 0.3) {
            inventario.adicionarItem("Pocao de Vida", "Recupera 40 de HP", 1, 0.5);
            gameView.mostrarItemObtido("Pocao de Vida");
        }

        model.incrementarInimigos();

        gerenciarProgressao(inimigo, ehBoss);
    }

    // ==================== PROGRESSAO ====================

    private void gerenciarProgressao(Enemy inimigo, boolean ehBoss) {
        int fase = model.getFase();

        if (ehBoss) {
            model.setState("VITORIA");
            return;
        }

        model.incrementarInimigosNaFase();
        int necessarios = (fase <= 3) ? 2 : 1;

        if (model.getInimigosNestaFase() >= necessarios) {
            gameView.mostrarMensagem("\n=== FASE " + fase + " COMPLETA! ===");
            Log.registar("Fase " + fase + " completa! | Inimigos derrotados: " + model.getInimigosDerrotados());
            gameView.aguardar();

            if (fase >= 4) {
                model.setState("VITORIA");
            } else {
                model.setFase(fase + 1);
                model.setState("HISTORIA");
            }
        } else {
            gameView.aguardar();
            model.setState("PREPARACAO");
        }
    }

    // ==================== PODER ESPECIAL ====================

    private String obterNomePoder(String classe) {
        if (classe.equals("Guerreiro")) return "Golpe Pesado";
        if (classe.equals("Mago")) return "Bola de Fogo";
        if (classe.equals("Arqueiro")) return "Tiro Certeiro";
        return "Poder";
    }

    private void usarPoderEspecial(Player player, Enemy inimigo) {
        String classe = player.getClass().getSimpleName();

        if (classe.equals("Guerreiro")) {
            if (Math.random() < 0.65) {
                int dano = player.getAtaque() * 2;
                inimigo.receberDano(dano);
                gameView.mostrarPoderUsado("Guerreiro", "Golpe Pesado", dano);
            } else {
                gameView.mostrarPoderFalhou();
            }
        } else if (classe.equals("Mago")) {
            int dano = (int) (player.getAtaque() * 1.8);
            inimigo.receberDano(dano);
            gameView.mostrarPoderUsado("Mago", "Bola de Fogo", dano);
        } else if (classe.equals("Arqueiro")) {
            int dano = (int) (player.getAtaque() * 1.5);
            inimigo.receberDano(dano);
            gameView.mostrarPoderUsado("Arqueiro", "Tiro Certeiro", dano);
        }
    }

    // ==================== POCAO EM COMBATE ====================

    private boolean usarPocaoEmCombate(Player player) {
        List<Item> itens = inventario.listarTodos();
        for (Item item : itens) {
            if (item.getNome().equals("Pocao de Vida")) {
                int cura = Math.min(40, model.getMaxHp() - player.getHp());
                if (cura > 0) {
                    player.setHp(player.getHp() + cura);
                    inventario.removerItem(item.getId());
                    gameView.mostrarUsouPocao(cura);
                    return true;
                } else {
                    gameView.mostrarMensagem("Seu HP ja esta no maximo.");
                    return false;
                }
            }
        }
        gameView.mostrarSemPocoes();
        return false;
    }

    // ==================== GERAR INIMIGOS ====================

    private Enemy gerarInimigo(int fase, int combateAtual) {
        switch (fase) {
            case 1:
                return new Goblin();
            case 2:
                return Math.random() < 0.5 ? new Goblin() : new saqueador();
            case 3:
                return Math.random() < 0.5 ? new saqueador() : new BruxaVerde();
            case 4:
                return new DragaoBoss();
            default:
                return new Goblin();
        }
    }

    private int obterMaxHpInimigo(Enemy e) {
        if (e instanceof Goblin) return 40;
        if (e instanceof saqueador) return 90;
        if (e instanceof BruxaVerde) return 70;
        if (e instanceof DragaoBoss) return 250;
        return 100;
    }

    // ==================== GAME OVER / VITORIA ====================

    private void executarGameOver() {
        gameView.mostrarGameOver();
        gameView.aguardar();
        rodando = false;
    }

    private void executarVitoria() {
        gameView.mostrarVitoria();

        Player player = model.getPlayer();
        gameView.mostrarHUD(player, model.getGold());
        gameView.mostrarMensagem("Inimigos derrotados: " + model.getInimigosDerrotados());
        Log.registar("VITORIA FINAL! Personagem: " + player.getNome() + " | Inimigos derrotados: " + model.getInimigosDerrotados());

        gameView.aguardar();
        rodando = false;
    }
}
